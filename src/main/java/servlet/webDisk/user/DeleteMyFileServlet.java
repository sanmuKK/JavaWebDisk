package servlet.webDisk.user;

import dao.ResourceModel;
import utils.TokenUtil;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/api/delFile")
public class DeleteMyFileServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int resourceId = Integer.parseInt(req.getParameter("id"));
        String token = req.getHeader("token");
        JSONObject info = TokenUtil.getTokenContext(token, 1);
        int ownerUserId = info.getIntValue("id");
        JSONObject ret = new JSONObject();
        try {
            String address = ResourceModel.getResourceAddress(resourceId, ownerUserId);
            String savePath = req.getServletContext().getRealPath("/WEB-INF/uploadFile") + File.separator + address;
            File file = new File(savePath);
            if (file.exists()) {
                boolean isDelete = file.delete();
                if (isDelete) {
                    ResourceModel.delResourceById(resourceId, ownerUserId);
                    ret.put("message", "success");
                    ret.put("code", 0);
                } else {
                    ret.put("message", "FileDeleteError");
                    ret.put("code", 4);
                }
            }
        } catch (Exception e) {
            ret.put("message", "delError");
            ret.put("code", 7);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(ret.toJSONString());
        pw.flush();
    }
}


