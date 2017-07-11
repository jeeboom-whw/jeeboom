<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — 修改密码</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 10px;">
			<div class="layui-tab-brief" >
				<ul class="layui-tab-title">
					<li class="layui-this">修改密码</li>
				</ul>
			</div>
			<form id="saveForm" class="layui-form layui-form-pane mt10" >
				<div class="layui-form-item">
					<label class="layui-form-label">原密码</label>
					<div class="layui-input-block">
                        <input type="password" lay-verify="required|oldPassword" value="" placeholder="请输入原登录密码" class="layui-input">
					</div>
				</div>
                <div class="layui-form-item">
                    <label class="layui-form-label">新密码</label>
                    <div class="layui-input-block">
                        <input type="password" name="newPassword" lay-verify="required|newPassword" value="" placeholder="请输入新的登录密码" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">确认密码</label>
                    <div class="layui-input-block">
                        <input type="password" id="cpwd" lay-verify="required|cpwd" value="" placeholder="请重新输入新的登录密码" class="layui-input">
                    </div>
                </div>
				<div class="layui-form-item">
					<button class="layui-btn fr" lay-submit="" lay-filter="demo2">确认修改</button>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
		<script>
			layui.use(['form','laypage','layer','laydate'],function(){
				var form = layui.form();
				var layer = layui.layer;
				var $ = layui.jquery;
                var laydate = layui.laydate;

                //表单验证
                form.verify({
                    oldPassword: function(value){
                        $.ajaxSettings.async = false;
                        var data = $.getJSON("${springMacroRequestContext.contextPath}/sysUser/validationPwd?pwd=" + value +"&_=" + new Date(),function(d){
							return d;
						});
                        if(data.responseJSON.code != 200){
							return data.message;
						}else if(data.responseJSON.data == false){
							return "与原密码不匹配！";
						}
                    },
                    newPassword: function(value){
						if(value.length < 6){
						    return "密码必须6位长度";
						}
                    },
                    cpwd:function(value){
						var newPwd = $("input[name='newPassword']").val();
						if(value != newPwd){
						    return "确认密码与新密码不匹配！";
						}
					}
                });


                form.on('submit(demo2)',function(data){
                    layer.load();
                    $.ajax({
                        url:"${springMacroRequestContext.contextPath}/sysUser/updatePwd",
                        type:"post",
                        data:$("#saveForm").serialize(),
                        dataType:"json",
                        success:function(d){
                            layer.closeAll('loading');
                            if(d.code == 200){
                                parent.layer.msg("密码修改成功！");
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭 layer.msg("保存成功");
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
