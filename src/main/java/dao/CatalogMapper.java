package dao;

import bean.Catalog;

import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface CatalogMapper {
    void insertCatalog(Catalog catalog);

    ArrayList<Catalog> getCatalog(int ownerUserId, int page);

    void delCatalog(int catalogId, int ownerUserId);

    @Select("select count(*) from catalog where ownerUserId = #{arg0}")
    int count(int id);
}