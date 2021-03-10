package dao;

import bean.Admin;

public interface AdminMapper {
    void insertAdmin(Admin admin);

    Admin getAdmin(String account, String password);
}