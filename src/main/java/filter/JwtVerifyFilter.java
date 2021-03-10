package filter;

import utils.TokenUtil;

import com.mysql.cj.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = {"/api/upload", "/api/viewFile", "/api/download", "/api/createCatalog",
        "/api/getMyCatalog", "/api/delFile"})
public class JwtVerifyFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String token = req.getHeader("token");
        if (StringUtils.isNullOrEmpty(token) || !TokenUtil.verify(token, 1)) {
            JSONObject ret = new JSONObject();
            ret.put("code", -1);
            ret.put("message", "noPermission");
            PrintWriter pw = resp.getWriter();
            pw.write(ret.toJSONString());
        } else {
            chain.doFilter(request, response);
        }
    }
}
