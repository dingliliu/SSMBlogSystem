<script type="text/javascript" src="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript">
    SyntaxHighlighter.all();
</script>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="UTF-8" isELIgnored="false" isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
    .round_icon{
        width: 45px;
        height: 45px;
        display: inline;
        border-radius: 50%;
        align-items: center;
        justify-content: center;
        overflow: hidden;
    }
    .img{
        width: 18px;
        height: 18px;
        display: inline;
        border-radius: 50%;
        align-items: center;
        justify-content: center;
        overflow: hidden;
    }
</style>
<script type="text/javascript">
    function loadimage(){
        document.getElementById("randImage").src="${pageContext.request.contextPath}/image.jsp?"+Math.random();
    }

    function submitData(){
        var content=$("#content").val();
        var imageCode=$("#imageCode").val();
        var rid=sessionStorage.getItem('rid');
        var reviewName=sessionStorage.getItem('reviewName');
        if(content==null || content==''){
            alert("请输入留言内容！");
        }else if(imageCode==null || imageCode==''){
            alert("请填写验证码！");
        }else{
            $.post("${pageContext.request.contextPath}/message/save.do",{'content':content,'imageCode':imageCode,'rid':rid,'reviewName':reviewName},function(result){
                if(result.success){
                    window.location.reload();
                    alert("留言已提交成功，审核通过后显示！");
                    sessionStorage.setItem('rid',0);
                    sessionStorage.setItem('reviewName',0);
                }else{
                    alert(result.errorInfo);
                    sessionStorage.setItem('rid',0);
                    sessionStorage.setItem('reviewName',0);
                }
            },"json");
        }
    }

    function showOtherMessage(){
        $('.otherMessage').show();
    }

    function test(id,bloggerName){
        sessionStorage.setItem('rid', id);
        sessionStorage.setItem('reviewName', bloggerName);
    }

    function praise(objectId){
        $.post("${pageContext.request.contextPath}/praise/save.do",{'objectId':objectId,'objectType':3},function(result){
            if(result.success){
                    $('.h')[objectId-1].innerHTML++;
                    $('.img')[objectId-1].src = "../static/images/praise.jpg";
                alert("点赞成功！");
            }else{
                $('.h')[objectId-1].innerHTML--;
                $('.img')[objectId-1].src = "../static/images/nopraise.jpg";
                alert("取消点赞！");
            }
        },"json");
    }
</script>

<div class="data_list">
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/static/images/list_icon.png"/>
        留言信息
        <c:if test="${messageList.size()>10}">
            <a href="javascript:showOtherMessage()" style="float: right;padding-right: 40px;">显示所有留言</a>
        </c:if>
    </div>
    <div class="commentDatas">
        <c:choose>
            <c:when test="${messageList.size()==0}">
                暂无留言
            </c:when>
            <c:otherwise>
                <c:forEach var="message" items="${messageList }" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index<10 }">
                            <c:if test="${message.rid==0}">
                            <div class="comment">
                                <p><font>${status.index+1 }楼&nbsp;&nbsp  ${message.bloggerName } &nbsp</font>[&nbsp;<fmt:formatDate value="${message.messageDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</p>
                                <table style="border-collapse:separate; border-spacing:0px 0px;">
                                    <tr>
                                        <td>
                                            <img src="../static/userImages/${message.imageName}" float="left" class="round_icon" border=0 />
                                        </td>
                                        <td>
                                            &nbsp;&nbsp;&nbsp;&nbsp; ${message.content } &nbsp; &nbsp; <a href="#review" onclick=test('${message.id}','${message.bloggerName}')><u>回复</u></a>
                                        </td>
                                        <td>
                                            &nbsp;&nbsp; <img class="img" src="../static/images/nopraise.jpg" border=0 onclick="praise('${message.id}')"/> <h class="h">${message.praise}</h>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            </c:if>
                            <c:if test="${message.rid!=0}">
                                <div class="comment">
                                    <p><font>${status.index+1 }楼&nbsp;&nbsp  ${message.bloggerName } &nbsp</font>[&nbsp;<fmt:formatDate value="${message.messageDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</p>
                                    <table style="border-collapse:separate; border-spacing:0px 0px;">
                                        <tr>
                                            <td>
                                                <img src="../static/userImages/${message.imageName}" float="left" class="round_icon" border=0 />
                                            </td>
                                            <td>
                                                &nbsp;&nbsp;&nbsp;&nbsp;回复&nbsp; ${message.reviewName}&nbsp;: ${message.content } &nbsp; &nbsp; <a href="#review" onclick=test('${message.id}','${message.bloggerName}')><u>回复</u></a>
                                            </td>
                                            <td>
                                                &nbsp;&nbsp; <img class="img" src="../static/images/nopraise.jpg" border=0 onclick="praise('${message.id}')"/> <h class="h">${message.praise}</h>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <div class="otherComment">
                                <div class="comment">
                                    <p><font>${status.index+1 }楼&nbsp;&nbsp  ${message.bloggerName } &nbsp</font>[&nbsp;<fmt:formatDate value="${message.messageDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]</p>
                                    <table style="border-collapse:separate; border-spacing:0px 0px;">
                                        <tr>
                                            <td>
                                                <img src="../static/userImages/${sessionScope.loginBlogger.imageName}" float="left" class="round_icon" border=0 />
                                            </td>
                                            <td>
                                                &nbsp;&nbsp;&nbsp;&nbsp; ${message.content }
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<a name="review"></a>
<div class="data_list" >
    <div class="data_list_title">
        <img src="${pageContext.request.contextPath}/static/images/publish_comment_icon.png"/>
        发表留言
    </div>
    <div class="publish_comment">
        <div>
            <textarea style="width: 100%" rows="3" id="content" name="content" placeholder="来说两句吧..."></textarea>
        </div>
        <div class="verCode">
            验证码：<input type="text" value="${imageCode }" name="imageCode"  id="imageCode" size="10" onkeydown= "if(event.keyCode==13)form1.submit()"/>&nbsp;<img onclick="javascript:loadimage();"  title="换一张试试" name="randImage" id="randImage" src="${pageContext.request.contextPath}/image.jsp" width="60" height="20" border="1" align="absmiddle">
        </div>
        <div class="publishButton">
            <button class="btn btn-primary" type="button" onclick="submitData()">发表留言</button>
        </div>
        </form>
    </div>
</div>