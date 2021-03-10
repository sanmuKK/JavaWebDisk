package servlet.webDisk.user;

import dao.ResourceModel;
import utils.TokenUtil;

import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = "/api/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private final static ArrayList<String> fileTypes = new ArrayList<>(Arrays.asList(".jpg", ".png",
            ".jpeg", ".gif", ".bmp"));

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String savePath = req.getServletContext().getRealPath("/WEB-INF/uploadFile");
        Collection<Part> parts = req.getParts();
        int catalogId = Integer.parseInt(req.getParameter("catalogId"));
        String token = req.getHeader("token");
        JSONObject info = TokenUtil.getTokenContext(token, 1);
        int ownerUserId = info.getIntValue("id");
        String uploadUserName = info.getString("name");
        JSONObject ret = new JSONObject();
        ret.put("code", 0);
        ret.put("message", "success");
        for (Part part : parts) {
            String header = part.getHeader("content-disposition");
            String fileName = getFileName(header);
            if (fileName == null)
                continue;
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            if (!fileTypes.contains(fileType)) {
                ret.put("code", 5);
                ret.put("message", "fileTypeError");
                continue;
            }
            String uuid = UUID.randomUUID().toString();
            String saveLocation = uuid + fileType;
            ResourceModel.addAuditResource(fileName, saveLocation, uploadUserName, ownerUserId, ownerUserId, catalogId);
            part.write(savePath + File.separator + saveLocation);
        }
        PrintWriter pw = resp.getWriter();
        pw.println(ret.toJSONString());
        pw.flush();
    }

    public static String getFileName(String header) {
        String[] tempArr1 = header.split(";");
        if (tempArr1.length < 3)
            return null;
        String[] tempArr2 = tempArr1[2].split("=");
        return tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
    }
}

