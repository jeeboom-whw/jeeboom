<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — 系统菜单树</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 15px;">
			<div class="layui-form layui-form-pane mt20" >
				<ul id="menuTree"></ul>
			</div>
		</div>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
		<script>
			layui.use(['tree', 'layer'],function(){
				var layer = layui.layer;
				var $ = layui.jquery;

				//获取菜单列表
				$.ajax({
					url:"${springMacroRequestContext.contextPath}/sysMenu/getTree",
					type:"POST",
                    async: false,
					dataType:"json",
					success:function(d){
					    if(d.code == 200){
                            layui.tree({
                                elem:"#menuTree",
                                nodes:d.data,
                                click:function(item){
                                    parent.layui.addPValue(item.name,item.id,item.alias-0+1);
                                    var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                    parent.layer.close(index);
                                }
                            });
						}else{
                            layer.msg("对不起，访问不成功！错误编码：" + d.code);
						}
					}
				});

			});
		</script>
	</body>
</html>
