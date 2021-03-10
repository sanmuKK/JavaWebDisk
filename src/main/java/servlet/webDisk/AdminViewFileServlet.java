package servlet.webDisk;

import dao.ResourceModel;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/api/adminViewFile")
public class AdminViewFileServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int page = Integer.parseInt(req.getParameter("page"));
        ArrayList<JSONObject> resourcesList = ResourceModel.getAuditResource(page);
        int count = ResourceModel.getAdminCount();
        JSONObject ret = new JSONObject();
        ret.put("message", "success");
        ret.put("code", 0);
        ret.put("resourcesList", resourcesList);
        ret.put("total", count);
        PrintWriter pw = resp.getWriter();
        pw.write(ret.toJSONString());
        pw.flush();
    }
}


