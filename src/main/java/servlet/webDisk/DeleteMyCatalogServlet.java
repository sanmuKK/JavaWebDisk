package servlet.webDisk;

import com.alibaba.fastjson.JSONObject;
import dao.CatalogModel;
import utils.TokenUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/api/delCatalog")
public class DeleteMyCatalogServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int catalogId = Integer.parseInt(req.getParameter("id"));
        String token = req.getHeader("token");
        JSONObject info = TokenUtil.getTokenContext(token, 1);
        int ownerUserId = info.getIntValue("id");
        JSONObject ret = new JSONObject();
        try {
            CatalogModel.delCatalogById(catalogId, ownerUserId);
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


