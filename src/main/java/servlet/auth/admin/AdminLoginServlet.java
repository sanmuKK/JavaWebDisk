package servlet.auth.admin;

import bean.Admin;
import dao.AdminModel;
import utils.JsonUtil;
import utils.TokenUtil;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api/adminLogin")
public class AdminLoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject reqJson = JsonUtil.getJson(req);
        String account = reqJson.getString("account");
        String pass = reqJson.getString("password");
        JSONObject ret = new JSONObject();
        try {
            Admin admin = AdminModel.getAdmin(account, pass);
            ret.put("code", 0);
            ret.put("message", "success");
            ret.put("token", TokenUtil.token(admin.getId(), admin.getName(), admin.getAvatar(), 2));
        } catch (Exception e) {
            ret.put("message", "adminLoginError");
            ret.put("code", 6);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(ret.toJSONString());
        pw.flush();
    }
}
