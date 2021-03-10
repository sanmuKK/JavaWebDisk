package bean;

import java.util.Date;

public class Catalog {
    private int id;
    private String name;
    private Date uploadTime;
    private int ownerUserId;
    private String ownerUserName;

    public Catalog() {
    }

    public Catalog(String name, int ownerUserId, String ownerUserName) {
        this.name = name;
        this.ownerUserId = ownerUserId;
        this.ownerUserName = ownerUserName;
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

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(int ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }
}
