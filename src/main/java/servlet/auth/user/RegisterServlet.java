package servlet.auth.user;

import com.mysql.cj.util.StringUtils;
import dao.UserModel;
import utils.JsonUtil;
import utils.SHA256Util;

import com.alibaba.fastjson.JSONObject;
import utils.SendMessageUtil;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api/register")
public class RegisterServlet extends HttpServlet {
    static String checkCode;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject reqJson = JsonUtil.getJson(req);
        String account = reqJson.getString("account");
        String pass = reqJson.getString("password");
        String code = reqJson.getString("code");
        String name = reqJson.getString("userName");
        String avatar = reqJson.getString("avatar");
        PrintWriter pw = resp.getWriter();
        JSONObject ret = new JSONObject();
        if (StringUtils.isNullOrEmpty(account) || StringUtils.isNullOrEmpty(pass)) {
            ret.put("code", 1);
            ret.put("message", "dataInvalid");
        } else {
            if (code.equals(checkCode) || code.equals("1234")) {//后删
                account = SHA256Util.getSHA256(account);
                pass = SHA256Util.getSHA256(pass);
                System.out.println(account);
                System.out.println(name);
                System.out.println(avatar);
                try {
                    UserModel.addUser(account, pass, name, avatar);
                    ret.put("code", 0);
                    ret.put("message", "success");
                } catch (Exception e) {
                    ret.put("code", 10);
                    ret.put("message", "registerError");
                }
            } else {
                ret.put("code", 12);
                ret.put("message", "CheckCodeError");
            }
        }
        pw.write(ret.toJSONString());
        pw.flush();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String phone = request.getParameter("phone");
        JSONObject ret = new JSONObject();
        try {
            checkCode = SendMessageUtil.sendMess(phone);
            ret.put("code", 0);
            ret.put("message", "success");
        } catch (Exception e) {
            ret.put("code", 11);
            ret.put("message", "sendMessageError");
        }
        response.getWriter().write(ret.toJSONString());
    }
}
