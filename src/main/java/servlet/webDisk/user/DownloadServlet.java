package servlet.webDisk.user;

import bean.Resource;
import dao.ResourceModel;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/api/download")
public class DownloadServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/octet-stream");
        int fileId = Integer.parseInt(req.getParameter("fileId"));
        Resource resource = ResourceModel.getResourceById(fileId);
        resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(resource.getName(),
                "utf-8"));
        String path = req.getServletContext().getRealPath("/WEB-INF/uploadFile");
        FileInputStream fileInputStream = new FileInputStream(path + File.separator + resource.getAddress());
        int len;
        byte[] bytes = new byte[1024];
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        while ((len = fileInputStream.read(bytes)) > 0) {
            servletOutputStream.write(bytes, 0, len);
        }
        servletOutputStream.close();
        fileInputStream.close();
    }
}

