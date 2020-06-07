package blog.pojo;

import java.util.Date;

public class Comment {

    private Integer id; // 编号
    private String userIp; // 用户IP
    private String content; // 评论内容
    private Blog blog; // 被评论的博客
    private Date commentDate; // 评论日期
    private Integer state; // 审核状态  0 待审核 1 审核通过 2 审核未通过

    private String blogger; //评论人

    private String imageName;

    private Integer rid;
    private String reviewName;

    private Integer praise;

    public Comment() {
    }

    public Comment(Integer id, String userIp, String content, Blog blog, Date commentDate, Integer state, String blogger,String imageName,Integer rid,String reviewName,Integer praise) {
        this.id = id;
        this.userIp = userIp;
        this.content = content;
        this.blog = blog;
        this.commentDate = commentDate;
        this.state = state;
        this.blogger = blogger;
        this.imageName = imageName;
        this.rid = rid;
        this.reviewName = reviewName;
        this.praise = praise;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getBlogger() {
        return blogger;
    }

    public void setBlogger(String blogger) {
        this.blogger = blogger;
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
    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userIp='" + userIp + '\'' +
                ", content='" + content + '\'' +
                ", blog=" + blog +
                ", commentDate=" + commentDate +
                ", state=" + state +
                ", blogger=" + blogger +
                '}';
    }
}
