package bean;

import java.util.Date;

public class Resource {
    private int id;
    private String name;
    private String address;
    private Date uploadTime;
    private String uploadUserName;
    private int uploadUserId;
    private int ownerUserId;
    private int ownerCatalogId;

    public Resource() {
    }

    public Resource(String name, String address, String uploadUserName, int uploadUserId, int ownerUserId, int ownerCatalogId) {
        this.name = name;
        this.address = address;
        this.uploadUserName = uploadUserName;
        this.uploadUserId = uploadUserId;
        this.ownerUserId = ownerUserId;
        this.ownerCatalogId = ownerCatalogId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadUserName() {
        return uploadUserName;
    }

    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
    }

    public int getUploadUserId() {
        return uploadUserId;
    }

    public void setUploadUserId(int uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public int getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(int ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public int getOwnerCatalogId() {
        return ownerCatalogId;
    }

    public void setOwnerCatalogId(int ownerCatalogId) {
        this.ownerCatalogId = ownerCatalogId;
    }
}
