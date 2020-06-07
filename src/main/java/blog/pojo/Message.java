package blog.pojo;

import java.util.Date;

public class Message {

    private Integer id; // 编号
    private Integer bloggerId; // 用户IP
    private String bloggerName; // 留言内容
    private String content; // 留言内容
    private Date messageDate; // 留言日期
    private Integer state; // 审核状态  0 待审核 1 审核通过 2 审核未通过
    private String imageName;

    private Integer rid;
    private String reviewName;

    private Integer praise;

    public Message() {
    }

    public Message(Integer id, Integer bloggerId, String bloggerName,String content, Date messageDate, Integer state,String imageName,Integer rid,String reviewName,Integer praise) {
        this.id = id;
        this.bloggerId = bloggerId;
        this.bloggerName = bloggerName;
        this.content = content;
        this.messageDate = messageDate;
        this.state = state;
        this.imageName = imageName;
        this.rid = rid;
        this.reviewName = reviewName;
        this.praise = praise;
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

    public String getBloggerName() {
        return bloggerName;
    }

    public void setBloggerName(String bloggerName) {
        this.bloggerName = bloggerName;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }


    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }


    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    @Override
    public String toString() {
        return "message{" +
                "id=" + id +
                ", bloggerId='" + bloggerId + '\'' +
                ", bloggerName='" + bloggerName + '\'' +
                ", content='" + content + '\'' +
                ", messageDate=" + messageDate +
                ", state=" + state +
                ", imageName=" + imageName +
                '}';
    }
}
