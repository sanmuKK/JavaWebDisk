package servlet.webDisk.user;

import dao.CatalogModel;
import utils.JsonUtil;
import utils.TokenUtil;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/api/createCatalog")
public class CreateCatalogServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject reqJson = JsonUtil.getJson(req);
        String catalogName = reqJson.getString("catalogName");
        String token = req.getHeader("token");
        JSONObject info = TokenUtil.getTokenContext(token, 1);
        int ownerUserId = info.getIntValue("id");
        String ownerUserName = info.getString("name");
        CatalogModel.addCatalog(catalogName, ownerUserId, ownerUserName);
        JSONObject ret = new JSONObject();
        ret.put("code", 0);
        ret.put("message", "success");
        PrintWriter pw = resp.getWriter();
        pw.write(ret.toJSONString());
        pw.flush();
    }
}

