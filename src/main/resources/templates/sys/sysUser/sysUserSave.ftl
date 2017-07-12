<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — 用户信息添加</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 10px;">
			<div class="layui-tab-brief" >
				<ul class="layui-tab-title">
					<li class="layui-this">${(sysUser?? && sysUser.id??)?string('编辑','添加')}用户信息</li>
				</ul>
			</div>
			<form id="saveForm" class="layui-form layui-form-pane mt10" >
				<input type="hidden" value="${(sysUser.id)!}" name="id" />
				<div class="layui-form-item">
					<label class="layui-form-label">用户姓名</label>
					<div class="layui-input-block">
						<input type="text" name="name" lay-verify="required" value="${(sysUser.name)!}" placeholder="请输入用户姓名" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">手机号码</label>
					<div class="layui-input-block">
						<input type="text" name="phone" lay-verify="required" value="${(sysUser.phone)!}" placeholder="请输入手机号码" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">用 户 名</label>
					<div class="layui-input-block">
						<input type="text" name="username" lay-verify="username" value="${(sysUser.username)!}" placeholder="请输入用户名" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">登录密码</label>
					<div class="layui-input-block">
						<input type="password" name="password" lay-verify="password" value="" placeholder="请输入登录密码" class="layui-input">
					</div>
				</div>
                <div class="layui-form-item">
                    <label class="layui-form-label">用户角色</label>
                    <div class="layui-input-block">
					<#if roles??>
						<#list roles as role>
                            <input name="roleIds" title="${role.name}" lay-verify="roles" lay-skin="primary" value="${role.id}" type="checkbox">
						</#list>
					</#if>
                    </div>
                </div>
				<@common.permission per='sys:sysUser:edit'>
					<div class="layui-form-item">
						<button class="layui-btn fr" lay-submit="" lay-filter="demo2">保存用户信息</button>
					</div>
				</@common.permission>
			</form>
		</div>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
		<script>
			layui.use(['form','laypage','layer'],function(){
				var form = layui.form();
				var layer = layui.layer;
				var $ = layui.jquery;

                //表单验证
                form.verify({
                    username: function(value){
                        if(value.length < 6){
                            return "用户名最少要给6位长度呀！";
						}
                        var userId = $("input[name='id']").val();
                        var username = value;
                        var key = false;
                        $.ajax({
							url:"${springMacroRequestContext.contextPath}/sysUser/validationUname?username="+username+"&userId="+userId,
							type:"get",
                            async: false,
							dateType:"json",
							success:function(d){
								key = !d;
							}
						});
                        if(key){
                            return "这个已经有人用了，我们还是换一个吧！";
						}
                    },
                    password: function(value){
                        var userId = $("input[name='id']").val();
                        if(userId == '' && value.length < 6){
                            return '密码最少要给6位长度呀！';
                        }
                    },
                    roles: function(value){
						var i = $("input[name='roleIds']:checked").length;
						if(i<1){
							return "公怎么也要选一个角色呀！";
						}
					}
                });

				//初始化角色checkbox
				var id = $("input[name='id']").val();
				if(id != "" && id > 0){
                    $.ajax({
                        url:"${springMacroRequestContext.contextPath}/sysUser/getRids?userId="+id,
						type:"post",
                        dateType:"json",
						success:function(d){
							for(var i in d){
							    $("input[name='roleIds'][value='"+d[i]+"']").attr("checked",true);
							}
                            form.render('checkbox');
						}
                    });
				}

                //表单提交
				form.on('submit(demo2)',function(data){
					layer.load();
					$.ajax({
						url:"${springMacroRequestContext.contextPath}/sysUser/save",
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
					});
					return false;
				});
            });
		</script>
	</body>
</html>
