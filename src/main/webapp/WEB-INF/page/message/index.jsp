<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/include/tagtld.jspf"%>
<%@ include file="../jspf/import-css.jspf"%>
<%@ include file="../jspf/import-js.jspf"%>
<html lang="zh-CN">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>全文检索</title>
<style>
<!--
    a:HOVER {
		color:#ffa829;
		text-decoration:underline;
	}
	
	.success{
		background-color: #e1e1e8;
	}
	
-->
</style>
</head>
<body>
	<div class="container-fluid" style="margin-top: 10px;">
		<div class="row-fluid">
			<div class="span12">
				<c:if test="${not empty msg}">
					<div class="alert alert-success fade in">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<span class="icon-ok-sign icon-large"></span> ${msg}
					</div>
				</c:if>

				<div id="addScheduleJob" class="modal hide fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						<h3 id="myModalLabel">添加留言信息</h3>
					</div>
					<div class="modal-body" style="max-height: 435px;"
						id="addScheduleJob_modal_body">
						<!-- 加载内容 -->
					</div>
				</div>

				<div class="tabbable">
					<ul class="nav nav-tabs">
						<li <c:if test="${active eq 1 || empty active}">class="active"</c:if>><a href="#tab1" data-toggle="tab">留言列表</a></li>
					</ul>

					<div class="tab-content">
						<div class='tab-pane<c:if test="${active eq 1 || empty active}"> active</c:if>' id="tab1">
							<ul class="nav nav-pills">
								<li class='active'><a data-toggle="modal" data-target="#addScheduleJob" href="${ctx}/add">新增留言信息</a></li>
								<li>
									<div>
										<form style="margin:0px;" action="${ctx}/" method="post">
											<input value="${keyword}" name="keyword" required="required" style="margin-left:30px; margin-top: 4px; padding-left:7px; border-radius:8px 0 0 8px" type="text" class="search-query" placeholder="Search">
											<button style="height: 28px;margin-top: 4px;border-radius:0 8px 8px 0; margin-left: -3px;" type="submit" class="btn btn-info">搜索</button>
										</form>
									</div>
								</li>
								<a href="${ctx}" class="btn btn-link pull-right">刷新</a>
							</ul>
							<c:if test="${empty messages}">
								<div style="padding: 10px;">
									<label style="float: left; margin-right:20px; font-size: 15px;">抱歉，没有找到相关信息！</label>
								</div>
							</c:if>
							<c:forEach items="${messages}" var="messages">
								<div class="div_message" style="border-bottom:1px solid #ddd;padding: 10px;">
									<label style="float: left; margin-right:20px; font-size: 15px;">${messages.title}</label>
									<label style="color: #999;margin-top:1px;">${fn:substring(messages.addTime, 0, 19)}</label>
									<div style="color: #999;">${messages.content}</div>
								</div>
							</c:forEach>						
						</div>
					</div>
				</div>
				<script type="text/javascript">
					$('#addScheduleJob').on('hidden', function() {
						$(this).removeData("modal");
						$('#addScheduleJob_modal_body').html("");
					});
					
					
					$(function(){
						$(".div_message").mouseover(function() {
							$(this).removeClass("warning").addClass("success");
						}).mouseout(function() {
							$(this).removeClass("success");
						});
					})
				</script>
			</div>
		</div>
	</div>
</body>
</html>