package dao;

import bean.Resource;
import utils.MyBatisUtil;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ResourceModel {
    static SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

    public static void addAuditResource(String name, String address, String uploadUserName, int uploadUserId,
                                        int ownerUserId, int ownerCatalogId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);
            Resource resource = new Resource(name, address, uploadUserName, uploadUserId, ownerUserId, ownerCatalogId);
            resourceMapper.insertAuditResource(resource);
            sqlSession.commit();
        }
    }

    public static void passResource(Resource resource) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);
            resourceMapper.insertResource(resource);
            sqlSession.commit();
        }
    }

    public static ArrayList<JSONObject> getResource(int ownerUserId, int ownerCatalogId, int page) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);
            ArrayList<Resource> resources = resourceMapper.getResource(ownerUserId, ownerCatalogId, 8 * (page - 1));
            return convertToJson(resources);
        }
    }

    public static ArrayList<JSONObject> getAuditResource(int page) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);
            ArrayList<Resource> resources = resourceMapper.getAuditResource(8 * (page - 1));
            return convertToJson(resources);
        }
    }

    public static Resource getResourceById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);
            return resourceMapper.getResourceById(id);
        }
    }

    public static Resource getAudioResourceById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);
            return resourceMapper.getAuditResourceById(id);
        }
    }

    public static void delResourceById(int id, int ownerUserId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);
            resourceMapper.delResourceById(id, ownerUserId);
            sqlSession.commit();
        }
    }

    public static void delAuditResourceById(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);
            resourceMapper.delAuditResourceById(id);
            sqlSession.commit();
        }
    }

    public static int getCount(int id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);
            return resourceMapper.count(id);
        }
    }

    public static int getAdminCount() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ResourceMapper resourceMapper = sqlSession.getMapper(ResourceMapper.class);
            return resourceMapper.adminCount();
        }
    }

    private static ArrayList<JSONObject> convertToJson(ArrayList<Resource> resources) {
        ArrayList<JSONObject> ret = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Resource r : resources) {
            JSONObject tmp = new JSONObject();
            tmp.put("id", r.getId());
            tmp.put("name", r.getName());
            tmp.put("uploadUserName", r.getUploadUserName());
            tmp.put("uploadTime", sdf.format(r.getUploadTime()));
            tmp.put("uploadUserId", r.getUploadUserId());
            ret.add(tmp);
        }
        return ret;
    }
}
