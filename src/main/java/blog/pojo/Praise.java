package blog.pojo;

import java.util.Date;

public class Praise {
    private Integer id; // 编号
    private Integer bloggerId; // 点赞人ID
    private Integer objectId; // 点赞对象ID
    private Integer objectType; // 点赞对象类型  1 博客 2 评论 3 留言
    private Date praiseDate; // 点赞日期

    public Praise() {
    }

    public Praise(Integer id, Integer bloggerId, Integer objectId, Integer objectType, Date praiseDate) {
        this.id = id;
        this.bloggerId = bloggerId;
        this.objectId = objectId;
        this.objectType = objectType;
        this.praiseDate = praiseDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBloggerId() {
        return bloggerId;
    }

    public void setBloggerId(Integer bloggerId) {
        this.bloggerId = bloggerId;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Date getPraiseDate() {
        return praiseDate;
    }

    public void setPraiseDate(Date praiseDate) {
        this.praiseDate = praiseDate;
    }

    @Override
    public String toString() {
        return "PraiseMapper{" +
                "id=" + id +
                ", bloggerId=" + bloggerId +
                ", objectId=" + objectId +
                ", objectType=" + objectType +
                ", praiseDate=" + praiseDate +
                '}';
    }
}
