<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — 数据库表信息添加</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 10px;">
			<div class="layui-tab-brief" >
				<ul class="layui-tab-title">
					<li class="layui-this">最终配置</li>
				</ul>
			</div>
			<div id="saveForm" class="layui-form layui-form-pane mt10" >
				<div class="layui-form-item">
					<label class="layui-form-label">生成路径</label>
					<div class="layui-input-block">
						<input type="text" name="outRoot" lay-verify="required" value="${(outRoot)!}" class="layui-input" maxlength="200">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">包 路 径</label>
					<div class="layui-input-block">
						<input type="text" name="basePackage" lay-verify="required" value="${(package)!}" class="layui-input" maxlength="150">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">模块名称</label>
					<div class="layui-input-block">
						<input type="text" name="model" lay-verify="required" value="demo" placeholder="例如 test" class="layui-input" maxlength="200">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">代码说明</label>
					<div class="layui-input-block">
						<input type="text" name="info" lay-verify="required" value="测试" placeholder="例如 学生测试" class="layui-input" maxlength="50">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">选择类型</label>
					<div class="layui-input-block">
                        <@common.mdictRedio name="createType" title="auto_create_type" value = "1" />
					</div>
				</div>
				<@common.permission per='auto:autoTable:edit'>
					<div class="layui-form-item">
						<button class="layui-btn fr" lay-submit="" lay-filter="demo2">生成代码</button>
					</div>
				</@common.permission>
			</div>
		</div>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
		<script>
			layui.use(['form','laypage','layer','laydate'],function(){
				var form = layui.form();
				var layer = layui.layer;
				var $ = layui.jquery;
                form.on('submit(demo2)',function(data){
                    layer.load();
                    var outRoot = $("input[name='outRoot']").val();
                    var basePackage = $("input[name='basePackage']").val();
                    var model = $("input[name='model']").val();
                    var info = $("input[name='info']").val();
                    var createType = $("input[name='createType']:checked").val();
					parent.layui.submitForm(outRoot,basePackage,model,info,createType);
                    return false;
                });

            });
		</script>
	</body>
</html>
