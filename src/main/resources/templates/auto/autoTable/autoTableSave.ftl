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
					<li class="layui-this">${(autoTable?? && autoTable.id??)?string('编辑','添加')}数据库表</li>
				</ul>
			</div>
			<form id="saveForm" class="layui-form layui-form-pane mt10" >
				<input type="hidden" value="${(autoTable.id)!}" name="id" />

				<div class="layui-form-item">
					<label class="layui-form-label">数据库表名</label>
					<div class="layui-input-block">
						<input type="text" name="tableName" lay-verify="required" value="${(autoTable.tableName)!}" placeholder="请输入数据库表名" class="layui-input" maxlength="50">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">表别名</label>
					<div class="layui-input-block">
						<input type="text" name="name" lay-verify="required" value="${(autoTable.name)!}" placeholder="请输入表别名" class="layui-input" maxlength="50">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">表注释</label>
					<div class="layui-input-block">
						<input type="text" name="label" value="${(autoTable.label)!}" placeholder="请输入表注释" class="layui-input" maxlength="200">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">表生成类型1单表 2树行列表结构 3包含关联关系表(扩展以后用)</label>
					<div class="layui-input-block">
						<input type="text" name="type" lay-verify="required|number" value="${(autoTable.type)!}" placeholder="请输入表生成类型1单表 2树行列表结构 3包含关联关系表(扩展以后用)" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">是否有删除功能(真删除)</label>
					<div class="layui-input-block">
						<input type="text" name="isDel" value="${(autoTable.isDel)!}" placeholder="请输入是否有删除功能(真删除)" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">是否有展示隐藏功能(假删除)</label>
					<div class="layui-input-block">
						<input type="text" name="isShow" value="${(autoTable.isShow)!}" placeholder="请输入是否有展示隐藏功能(假删除)" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">是否有上下架功能</label>
					<div class="layui-input-block">
						<input type="text" name="isStatus" value="${(autoTable.isStatus)!}" placeholder="请输入是否有上下架功能" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">批量删除功能(真删除)</label>
					<div class="layui-input-block">
						<input type="text" name="isAllDel" value="${(autoTable.isAllDel)!}" placeholder="请输入批量删除功能(真删除)" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">批量展示隐藏功能(假删除)</label>
					<div class="layui-input-block">
						<input type="text" name="isAllShow" value="${(autoTable.isAllShow)!}" placeholder="请输入批量展示隐藏功能(假删除)" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">批量上下架功能</label>
					<div class="layui-input-block">
						<input type="text" name="isAllStatus" value="${(autoTable.isAllStatus)!}" placeholder="请输入批量上下架功能" class="layui-input" maxlength="10">
					</div>
				</div>

				<@common.permission per='auto:autoTable:edit'>
					<div class="layui-form-item">
						<button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存数据库表</button>
					</div>
				</@common.permission>
			</form>
		</div>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
		<script>
			layui.use(['form','laypage','layer','laydate'],function(){
				var form = layui.form();
				var layer = layui.layer;
				var $ = layui.jquery;
                var laydate = layui.laydate;
                form.on('submit(demo2)',function(data){
                    layer.load();
                    $.ajax({
                        url:"${springMacroRequestContext.contextPath}/autoTable/save",
                        type:"post",
                        data:$("#saveForm").serialize(),
                        dataType:"json",
                        success:function(d){
                            layer.closeAll('loading');
                            if(d.code == 200){
                                parent.layui.submitForm();
                            }else{
                                layer.msg("对不起，访问不成功！错误编码：" + d.code);
                            }
                        }
                    })
                    return false;
                });

            });
		</script>
	</body>
</html>
