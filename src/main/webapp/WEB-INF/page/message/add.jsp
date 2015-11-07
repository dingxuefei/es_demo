<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/include/tagtld.jspf"%>
<style>
<!--
    a:HOVER {
		color:#ffa829;
		text-decoration:underline;
	}
-->
</style>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<form action="${ctx}/add" method="post" class="form-horizontal" style="margin-top: 10px;">
				<div class="control-group">
					<label style="width: 120px;" for="title" class="control-label">标题：</label>
					<div class="controls" style="margin-left: 120px;">
						<input type="text" id="title" required="required" name="title" value="" />
				        <label style="display: inline;padding-left: 5px;color: red;">*</label>
					</div>
				</div>
				<div class="control-group">
					<label style="width: 120px;" for="content" class="control-label">内容：</label>
					<div class="controls" style="margin-left: 120px;">
						<textarea id="content" name="content" required="required" rows="3" style="width: 300px;font-size: 12px;"></textarea>
					</div>
				</div>
				<div class="control-group">
					<div class="controls" style="margin-left: 120px;">
			            <button type="submit" class="btn btn-info btn-small"><i class="icon-plus"></i>&nbsp;新增&nbsp;</button>
						<button class="btn btn-small" data-dismiss="modal" aria-hidden="true">
							<i class="icon-remove"></i>&nbsp;关闭
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>