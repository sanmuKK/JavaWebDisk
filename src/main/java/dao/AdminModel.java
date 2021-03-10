package dao;

import bean.Admin;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import utils.MyBatisUtil;

public class AdminModel {
    static SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

//    public static void addAdmin(String account, String password, String name, String avatar) {
//        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
//            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
//            Admin admin = new Admin(account, password, name, avatar);
//            adminMapper.insertAdmin(admin);
//            sqlSession.commit();
//        }
//    }

    public static Admin getAdmin(String account, String password) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            AdminMapper adminMapper = sqlSession.getMapper(AdminMapper.class);
            return adminMapper.getAdmin(account, password);
        }
    }
}
