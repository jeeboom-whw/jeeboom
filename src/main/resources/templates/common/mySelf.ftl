<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — 用户详情</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 10px;">
			<div class="layui-tab-brief" >
				<ul class="layui-tab-title">
					<li class="layui-this">${(sysMdict?? && sysMdict.id??)?string('编辑','添加')}数据字典</li>
				</ul>
			</div>
			<form id="saveForm" class="layui-form layui-form-pane mt10" >
				<div class="layui-form-item">
					<label class="layui-form-label">用户账号</label>
					<div class="layui-input-block">
						<input type="text" value="${(loginUser.username)!}" class="layui-input" disabled>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">用户姓名</label>
					<div class="layui-input-block">
						<input type="text" value="${(loginUser.name)!}" class="layui-input" disabled>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">手机号码</label>
					<div class="layui-input-block">
						<input type="text" value="${(loginUser.phone)!}" class="layui-input" disabled>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">登录时间</label>
					<div class="layui-input-block">
						<input type="text" value="${((loginUser.loginTime??)?string(loginUser.loginTime?string("yyyy年MM月dd日 HH:mm:ss"),""))}" class="layui-input" >
					</div>
				</div>
				<div class="layui-form-item">
					<button class="layui-btn fr" lay-submit="" lay-filter="demo2">关闭</button>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
		<script>
			layui.use(['form','laypage','layer','laydate'],function(){
				var form = layui.form();
                form.on('submit(demo2)',function(data){
                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                    parent.layer.close(index); //再执行关闭 layer.msg("保存成功");
                });
            });
		</script>
	</body>
</html>
