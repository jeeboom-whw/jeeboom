<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>JeeBoom — 管理平台</title>

<link href="${springMacroRequestContext.contextPath}/static/css/style.css" rel="stylesheet" type="text/css">

</head>

<body class="login">

<div class="login_m">
	<div class="login_logo"><img src="${springMacroRequestContext.contextPath}/static/img/logo.png" width="196" height="46"></div>
	<div class="login_boder">
		<form action="${springMacroRequestContext.contextPath}/login" method="post">
			<div class="login_padding">
				<h2>用户名</h2>
				<label>
					<input type="text" id="username" class="txt_input txt_input2" name="userName" value="${userName!}">
				</label>
				<h2>密码</h2>
				<label>
					<input type="password" name="password" id="userpwd" class="txt_input" >
				</label>
				<div class="rem_sub">
					<label>
                        <button class="sub_button" style="opacity: 0.7;cursor:pointer;">登录</button>
					</label>
				</div>
			</div>
		</form>
	</div><!--login_boder end-->
</div><!--login_m end-->

<br />
<br />

<p align="center"> More Templates <a href="javascript:;" target="_blank">JeeBoom 管理</a> - Collect from <a href="javascript:;" title="网页模板" target="_blank">18646809934</a></p>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
		<script>
			layui.use(['layer'],function(){
				var layer = layui.layer;
				var $ = layui.jquery;
				if(window.top!=window.self){//不存在父页面
					parent.location.reload();
				}
				
				if("${msg!}"=="error"){
					layer.msg("用户名或密码错误！");
				}else if("${msg!}"=="status"){
                    layer.msg("该用户已被禁用不可登录！");
				}

				layui.login = function(){
				    var uname = $("#username").val();
				    var pwd = $("#userpwd").val();
				    if(uname.length < 1 || pwd.length < 1){
                        layer.msg("用户名密码不可为空！");
					}else{
						$("form").submit();
					}
				}
			});
		</script>
</body>
</html>