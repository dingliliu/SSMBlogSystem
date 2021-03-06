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
		width: 40px;
		height: 40px;
		display: inline;
		border-radius: 50%;
		align-items: center;
		justify-content: center;
		overflow: hidden;
	}
	.img{
		width: 15px;
		height: 15px;
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
			alert("请输入评论内容！");
		}else if(imageCode==null || imageCode==''){
			alert("请填写验证码！");
		}else{
			$.post("${pageContext.request.contextPath}/comment/save.do",{'content':content,'imageCode':imageCode,'blog.id':'${blog.id}','rid':rid,'reviewName':reviewName},function(result){
				if(result.success){
					window.location.reload();
					alert("评论已提交成功，审核通过后显示！");
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
	
	function showOtherComment(){
		$('.otherComment').show();
	}

	function test(id,blogger){
        sessionStorage.setItem('rid', id);
        sessionStorage.setItem('reviewName', blogger);
    }

	function praiseBlog(objectId){
		$.post("${pageContext.request.contextPath}/praise/save.do",{'objectId':objectId,'objectType':1},function(result){
			if(result.success){
				document.getElementById("blogPraise").innerHTML++;
				document.getElementById("blogImg").src = "../../static/images/praise.jpg";
				alert("点赞成功！");
			}else{
				document.getElementById("blogPraise").innerHTML--;
				document.getElementById("blogImg").src = "../../static/images/nopraise.jpg";
				alert("取消点赞！");
			}
		},"json");
	}

	function praiseView(objectId){
		$.post("${pageContext.request.contextPath}/praise/save.do",{'objectId':objectId,'objectType':2},function(result){
			if(result.success){
				$('.h')[objectId-1].innerHTML++;
				$('.img')[objectId-1].src = "../static/images/praise.jpg";
				alert("点赞成功！");
			}else{
				$('.h')[objectId-1].innerHTML--;
				$('.img')[objectId-1].src = "../static/images/nopraise.jpg";
				// window.location.reload();
				alert("取消点赞！");
			}
		},"json");
	}
</script>
<div class="data_list">
	<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/static/images/blog_show_icon.png"/>
		博客信息
	</div>
	<div>
	   <div class="blog_title"><h3><strong>${blog.title }</strong></h3></div>
	   <div style="padding-left: 330px;padding-bottom: 20px;padding-top: 10px">
		<div class="bshare-custom"><a title="分享到QQ空间" class="bshare-qzone"></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到人人网" class="bshare-renren"></a><a title="分享到腾讯微博" class="bshare-qqmb"></a><a title="分享到网易微博" class="bshare-neteasemb"></a><a title="更多平台" class="bshare-more bshare-more-icon more-style-addthis"></a><span class="BSHARE_COUNT bshare-share-count">0</span></div><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=&amp;pophcol=1&amp;lang=zh"></script><script type="text/javascript" charset="utf-8" src="http://static.bshare.cn/b/bshareC0.js"></script>
	   </div>
		<div class="blog_info">
			发布时间：『 <fmt:formatDate value="${blog.releaseDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>』&nbsp;&nbsp;博客类别：${blog.blogType.typeName}&nbsp;&nbsp;阅读(${blog.clickHit}) 评论(${blog.replyHit}) <img id="blogImg" class="img" src="../../static/images/nopraise.jpg" border=0 onclick="praiseBlog('${blog.id}')"/> (<h id="blogPraise">${blog.praise}</h>)
		</div>
		<div class="blog_content">
			${blog.content }
		</div>
		<div class="blog_keyWord">
			<font><strong>关键字：</strong></font>
			<c:choose>
				<c:when test="${keyWords==null}">
					&nbsp;&nbsp;无
				</c:when>
				<c:otherwise>
					<c:forEach var="keyWord" items="${keyWords }">
						&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/blog/q.html?q=${keyWord}" target="_blank">${keyWord }</a>&nbsp;&nbsp;
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="blog_lastAndNextPage">
			${pageCode }
		</div>
	</div>
</div>
<div class="data_list">
	<div class="data_list_title">
		<img src="${pageContext.request.contextPath}/static/images/comment_icon.png"/>
		评论信息    
		<c:if test="${commentList.size()>10}">
			<a href="javascript:showOtherComment()" style="float: right;padding-right: 40px;">显示所有评论</a>
		</c:if>
	</div>
	<div class="commentDatas">
		<c:choose>
			<c:when test="${commentList.size()==0}">
				暂无评论
			</c:when>
			<c:otherwise>
				<c:forEach var="comment" items="${commentList }" varStatus="status">
						<c:choose>
							<c:when test="${status.index<10}">
								<c:if test="${comment.rid==0}">
								<div class="comment">
									<table style="border-collapse:separate; border-spacing:0px 0px;">
										<tr>
											<td>
												<img src="../../static/userImages/${comment.imageName}" float="left" class="round_icon" border=0 />
											</td>
											<td>
												<font>&nbsp;&nbsp;&nbsp;&nbsp;${comment.blogger } : </font>${comment.content } [&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;] &nbsp; <a href="#review" onclick=test('${comment.id}','${comment.blogger}')><u>回复</u></a>
											</td>
											<td>
												&nbsp;&nbsp; <img class="img" src="../../static/images/nopraise.jpg" border=0 onclick="praiseView('${comment.id}')"/> <h class="h">${comment.praise}</h>
											</td>
										</tr>
									</table>
								</div>
								</c:if>
							<c:if test="${comment.rid!=0}">
								<div class="comment">
									<table style="border-collapse:separate; border-spacing:0px 0px;">
										<tr>
											<td>
												<img src="../../static/userImages/${comment.imageName}" float="left" class="round_icon" border=0 />
											</td>
												<td>
													<font>&nbsp;&nbsp;&nbsp;&nbsp;${comment.blogger }&nbsp;回复&nbsp; ${comment.reviewName}:</font>${comment.content } [&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;] &nbsp; <a href="#review" onclick=test('${comment.id}','${comment.blogger}')><u>回复</u></a>
												</td>
											<td>
												&nbsp;&nbsp; <img class="img" src="../../static/images/nopraise.jpg" border=0 onclick="praiseView('${comment.id}')"/> <h class="h">${comment.praise}</h>
											</td>
										</tr>
									</table>
								</div>
								</c:if>
							</c:when>
							<c:otherwise>
								<div class="otherComment">
									<div class="comment">
                                        <table style="border-collapse:separate; border-spacing:0px 0px;">
                                            <tr>
                                                <td>
                                                    <img src="../../static/userImages/${comment.imageName}" float="left" class="round_icon" border=0 />
                                                </td>
                                                <td>
                                                    <font>&nbsp;&nbsp;&nbsp;&nbsp;${comment.blogger }&nbsp;:&nbsp;</font>${comment.content } [&nbsp;<fmt:formatDate value="${comment.commentDate }" type="date" pattern="yyyy-MM-dd HH:mm"/>&nbsp;]
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
		发表评论
	</div>
	<div class="publish_comment" >
			<div>
				<textarea style="width: 100%" rows="3" id="content" name="content" placeholder="来说两句吧..."></textarea>
			</div>
			<div class="verCode">
				验证码：<input type="text" value="${imageCode }" name="imageCode"  id="imageCode" size="10" onkeydown= "if(event.keyCode==13)form1.submit()"/>&nbsp;<img onclick="javascript:loadimage();"  title="换一张试试" name="randImage" id="randImage" src="${pageContext.request.contextPath}/image.jsp" width="60" height="20" border="1" align="absmiddle">
			</div>
			<div class="publishButton">
				<button class="btn btn-primary" type="button" onclick="submitData()">发表评论</button>
			</div>
		</form>
	</div>
</div>
