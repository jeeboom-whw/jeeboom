<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — ${(sysRole?? && sysRole.id??)?string('编辑','添加')}角色信息</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/zTreeStyle.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 15px;">
			<form id="saveForm" class="layui-form layui-form-pane mt20" >
				<input type="hidden" value="${(sysRole.id)!}" name="id" />
				<input type="hidden" value="${(menuIds)!}" name="menuIds" />
				<div class="layui-form-item">
					<label class="layui-form-label">角色名称</label>
					<div class="layui-input-block">
						<input type="text" name="name" lay-verify="required" value="${(sysRole.name)!}" placeholder="请输入角色名称" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">角色权限</label>
					<div class="layui-input-block">
						<ul id="tree" class="ztree"></ul>
					</div>
				</div>
				<@role permission="sys:sysRole:edit";result><#if result>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" lay-submit="" lay-filter="demo2">保存角色信息</button>
						</div>
					</div>
				</#if></@role>
			</form>
		</div>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/js/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/js/jquery.ztree.all.min.js"></script>
		<script>
			layui.use(['form','laypage','layer'],function(){
				var form = layui.form();
				var layer = layui.layer;

				form.on('submit(demo2)',function(data){
					layer.load();
					//获取ztree所选中的值
                    var treeObj = $.fn.zTree.getZTreeObj("tree");
                    var nodes = treeObj.getCheckedNodes(true);
                    var mids = "";
                    for(var i in nodes){
						mids += nodes[i].id + ",";
					}
                    $("input[name='menuIds']").val(mids);
                    //表单提交
					$.ajax({
						url:"${springMacroRequestContext.contextPath}/sysRole/save",
						type:"POST",
						data:$("#saveForm").serialize(),
						dataType:"json",
						success:function(d){
							layer.closeAll('loading');
                            if(d.code == 200){
                                window.top.layer.msg('保存成功', {icon: 1});
                                parent.layui.submitForm();
                            }else{
                                layer.msg("对不起，访问不成功！错误编码：" + d.code);
                            }
						}
					})
					return false;
				});

				//ztree初始化
                $(function(){
                    var setting = {check: {enable: true},data: {simpleData: {enable: true}}};
                    $.ajax({
                        url:"${springMacroRequestContext.contextPath}/sysMenu/getZTree?roleId="+$("input[name='id']").val(),
						type:"POST",
                        async: false,
                        dataType:"json",
                        success:function(d){
                            if(d.code == 200){
                                $.fn.zTree.init($("#tree"), setting,d.data);
                            }else{
                                layer.msg("对不起，访问不成功！错误编码：" + d.code);
                            }
                        }
                    });
                })
            });
		</script>
	</body>
</html>
