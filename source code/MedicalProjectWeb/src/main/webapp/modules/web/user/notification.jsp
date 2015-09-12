<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>我的消息</title>
		<%@include file="/modules/web/include/common-head.jsp" %>
	</head>
	<body>
<div class="wrapper">
    <div class="box">
        <div class="row row-offcanvas row-offcanvas-left">
 
            <%@include file="/modules/web/include/user-sidebar.jsp" %>
          
            <!-- main right col -->
            <div class="column col-sm-10 col-xs-11" id="main">
                
                <%@include file="/modules/web/include/user-top-nav.jsp" %>
              
              	<%-- 内容部分 --%>
                <div class="padding">
                	
                    <div class="full col-sm-12">
						<div class="page-header">
						  <h1>我的消息 <small></small></h1>
						</div>
                     	<div id="my-notification-wrap">
							
                     	</div>
                     
                     
                    </div><!-- /col-12 -->
                    
                    
                    <div class="col-sm-12">
						<nav>
						  <ul class="pager">
						    <li class="previous"><a href="#"><span aria-hidden="true">&larr;</span> 上一页</a></li>
						    <li class="next"><a href="#">下一页 <span aria-hidden="true">&rarr;</span></a></li>
						  </ul>
						</nav>
					</div>
                </div><!-- /padding -->
                
                <%-- END 内容部分 --%>
            </div>
            <!-- /main -->
          
        </div>
    </div>
</div>

	
	<script id="notificationTmpl" type="x-tmpl-mustache">
	<ul class="list-unstyled" id="notification-list-ul">
		{{#notifications}}
		<li data-notification-id="{{notificationId}}">
			<div>
				<h5>{{content}}</h5>
				<div>
					<span>{{sendTime}}</span> <span class="label label-success read-status">{{readStatus}}</span>
				</div>
			</div>

		</li>
		{{/notifications}}

	</ul>
	</script>
	<%@include file="/modules/web/include/common-footer.jsp" %>
	<script src='<c:url value="/modules/web/assets/js/notification.js"/>'></script>
	</body>
</html>