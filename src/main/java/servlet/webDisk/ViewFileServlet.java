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
import java.util.ArrayList;

@WebServlet(urlPatterns = "/api/viewFile")
public class ViewFileServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        int catalogId = Integer.parseInt(req.getParameter("catalogId"));
        String token = req.getHeader("token");
        JSONObject info = TokenUtil.getTokenContext(token, 1);
        int ownerUserId = info.getIntValue("id");
        ArrayList<JSONObject> resourcesList = ResourceModel.getResource(ownerUserId, catalogId, page);
        JSONObject ret = new JSONObject();
        ret.put("code", 0);
        ret.put("message", "success");
        ret.put("resourcesList", resourcesList);
        PrintWriter pw = resp.getWriter();
        pw.write(ret.toJSONString());
        pw.flush();
    }
}


