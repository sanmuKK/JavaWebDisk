package dao;

import bean.User;

public interface UserMapper {
    void insertUser(User user);

    User getUser(String account, String password);
}