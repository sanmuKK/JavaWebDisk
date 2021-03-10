package filter;

import com.mysql.cj.util.StringUtils;
import utils.TokenUtil;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/api/adminViewFile", "/api/adminPassFile", "/api/adminDownload"})
public class JwtVerifyAdminFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String token = request.getHeader("token");
        if (token == null || token.equals("") || !TokenUtil.verify(token,2)) {
            JSONObject ret = new JSONObject();
            ret.put("code", -1);
            ret.put("message", "noPermission");
            PrintWriter pw = response.getWriter();
            pw.write(ret.toJSONString());
        } else {
            chain.doFilter(req, resp);
        }
    }
}
