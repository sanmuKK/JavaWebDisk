package dao;

import bean.User;
import utils.MyBatisUtil;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserModel {
    static SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();

    public static void addUser(String account, String password, String name, String avatar) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User(account, password, name, avatar);
            userMapper.insertUser(user);
            sqlSession.commit();
        }
    }

    public static User getUser(String account, String password) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.getUser(account, password);
        }
    }
}
