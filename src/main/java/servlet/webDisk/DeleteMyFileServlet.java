package servlet.webDisk;

import dao.ResourceModel;
import utils.TokenUtil;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            ResourceModel.delResourceById(resourceId, ownerUserId);
            ret.put("message", "success");
            ret.put("code", 0);
        } catch (Exception e) {
            ret.put("message", "delError");
            ret.put("code", 7);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(ret.toJSONString());
        pw.flush();
    }
}


