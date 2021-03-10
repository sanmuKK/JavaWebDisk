package dao;

import bean.Catalog;
import utils.MyBatisUtil;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CatalogModel {
    static SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

    public static void addCatalog(String name, int ownerUserId, String ownerUserName) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CatalogMapper CatalogMapper = sqlSession.getMapper(CatalogMapper.class);
            Catalog Catalog = new Catalog(name, ownerUserId, ownerUserName);
            CatalogMapper.insertCatalog(Catalog);
            sqlSession.commit();
        }
    }

    public static ArrayList<JSONObject> getCatalog(int ownerUserId, int page) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CatalogMapper CatalogMapper = sqlSession.getMapper(CatalogMapper.class);
            return convertToJson(CatalogMapper.getCatalog(ownerUserId, 8 * (page - 1)));
        }
    }

    public static void delCatalogById(int catalogId,int ownerUserID){
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CatalogMapper CatalogMapper = sqlSession.getMapper(CatalogMapper.class);
            CatalogMapper.delCatalog(catalogId,ownerUserID);
        }
    }

    public static int getTotal(int ownerUserId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            CatalogMapper CatalogMapper = sqlSession.getMapper(CatalogMapper.class);
            return CatalogMapper.count(ownerUserId);
        }
    }

    private static ArrayList<JSONObject> convertToJson(ArrayList<Catalog> catalogs) {
        ArrayList<JSONObject> ret = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for (Catalog r : catalogs) {
            JSONObject tmp = new JSONObject();
            tmp.put("id", r.getId());
            tmp.put("name", r.getName());
            tmp.put("uploadTime", sdf.format(r.getUploadTime()));
            tmp.put("ownerUserId", r.getOwnerUserId());
            tmp.put("ownerUserName", r.getOwnerUserName());
            ret.add(tmp);
        }
        return ret;
    }
}
