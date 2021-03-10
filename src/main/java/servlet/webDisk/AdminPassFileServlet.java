package servlet.webDisk;

import bean.Resource;
import dao.ResourceModel;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/api/adminPassFile")
public class AdminPassFileServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int resourceId = Integer.parseInt(req.getParameter("id"));
        Resource resource = ResourceModel.getAudioResourceById(resourceId);
        ResourceModel.passResource(resource);
        ResourceModel.delAuditResourceById(resourceId);
        JSONObject ret = new JSONObject();
        ret.put("message", "success");
        ret.put("code", 0);
        PrintWriter pw = resp.getWriter();
        pw.write(ret.toJSONString());
        pw.flush();
    }
}


