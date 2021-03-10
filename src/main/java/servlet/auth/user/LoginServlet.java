package servlet.auth.user;

import bean.User;
import dao.UserModel;
import utils.JsonUtil;
import utils.SHA256Util;
import utils.TokenUtil;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject reqJson = JsonUtil.getJson(req);
        String account = SHA256Util.getSHA256(reqJson.getString("account"));
        String pass = SHA256Util.getSHA256(reqJson.getString("password"));
        JSONObject ret = new JSONObject();
        try {
            User user = UserModel.getUser(account, pass);
            ret.put("code", 0);
            ret.put("message", "success");
            ret.put("token", TokenUtil.token(user.getId(), user.getName(), user.getAvatar(), 1));
        } catch (Exception e) {
            ret.put("message", "loginError");
            ret.put("code", 2);
        }
        PrintWriter pw = resp.getWriter();
        pw.write(ret.toJSONString());
        pw.flush();
    }
}
