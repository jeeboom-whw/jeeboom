<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — 属性列表信息添加</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 10px;">
			<div class="layui-tab-brief" >
				<ul class="layui-tab-title">
					<li class="layui-this">${(autoTableColumn?? && autoTableColumn.id??)?string('编辑','添加')}属性列表</li>
				</ul>
			</div>
			<form id="saveForm" class="layui-form layui-form-pane mt10" >
				<input type="hidden" value="${(autoTableColumn.id)!}" name="id" />

				<div class="layui-form-item">
					<label class="layui-form-label">表id</label>
					<div class="layui-input-block">
						<input type="text" name="tableId" value="${(autoTableColumn.tableId)!}" placeholder="请输入表id" class="layui-input" maxlength="19">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">数据库属性名称</label>
					<div class="layui-input-block">
						<input type="text" name="columnName" value="${(autoTableColumn.columnName)!}" placeholder="请输入数据库属性名称" class="layui-input" maxlength="50">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">属性别名</label>
					<div class="layui-input-block">
						<input type="text" name="name" value="${(autoTableColumn.name)!}" placeholder="请输入属性别名" class="layui-input" maxlength="50">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">数据库类型</label>
					<div class="layui-input-block">
						<input type="text" name="columnType" lay-verify="required" value="${(autoTableColumn.columnType)!}" placeholder="请输入数据库类型" class="layui-input" maxlength="50">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">JAVA类型</label>
					<div class="layui-input-block">
						<input type="text" name="type" lay-verify="required" value="${(autoTableColumn.type)!}" placeholder="请输入JAVA类型" class="layui-input" maxlength="50">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">长度</label>
					<div class="layui-input-block">
						<input type="text" name="length" value="${(autoTableColumn.length)!}" placeholder="请输入长度" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">注释</label>
					<div class="layui-input-block">
						<input type="text" name="label" value="${(autoTableColumn.label)!}" placeholder="请输入注释" class="layui-input" maxlength="255">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">是否为非空</label>
					<div class="layui-input-block">
						<input type="text" name="nullable" value="${(autoTableColumn.nullable)!}" placeholder="请输入是否为非空" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">是否展示在列表</label>
					<div class="layui-input-block">
						<input type="text" name="isList" lay-verify="required|number" value="${(autoTableColumn.isList)!}" placeholder="请输入是否展示在列表" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">是否为查询条件</label>
					<div class="layui-input-block">
						<input type="text" name="isSelect" lay-verify="required|number" value="${(autoTableColumn.isSelect)!}" placeholder="请输入是否为查询条件" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">查询种类 1 相等 2 like</label>
					<div class="layui-input-block">
						<input type="text" name="isSelectType" value="${(autoTableColumn.isSelectType)!}" placeholder="请输入查询种类 1 相等 2 like" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序</label>
					<div class="layui-input-block">
						<input type="text" name="orderNo" lay-verify="required|number" value="${(autoTableColumn.orderNo)!}" placeholder="请输入排序" class="layui-input" maxlength="10">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">数据字典title</label>
					<div class="layui-input-block">
						<input type="text" name="mdictTitle" value="${(autoTableColumn.mdictTitle)!}" placeholder="请输入数据字典title" class="layui-input" maxlength="50">
					</div>
				</div>

				<@common.permission per='auto:autoTableColumn:edit'>
					<div class="layui-form-item">
						<button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存属性列表</button>
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
                        url:"${springMacroRequestContext.contextPath}/autoTableColumn/save",
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
