package dao;

import bean.Resource;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface ResourceMapper {
    void insertResource(Resource file);

    void insertAuditResource(Resource file);

    ArrayList<Resource> getResource(int ownerUserId, int ownerCatalogId, int page);

    ArrayList<Resource> getAuditResource(int page);

    Resource getResourceById(int id);

    Resource getAuditResourceById(int id);

    void delResourceById(int id, int ownerUserId);

    void delAuditResourceById(int id);

    String getResourceAddressById(int id, int ownerUserId);

    String getAuditResourceAddressById(int id);

    @Select("select count(*) from resource where ownerCatalogId = #{arg0}")
    int count(int id);

    @Select("select count(*) from auditresource")
    int adminCount();
}