<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — 管理平台</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body>
		<div class="layui-layout layui-layout-admin">
			<!--
            	作者：196410791@qq.com
            	时间：2017-03-14
            	描述：header
            -->
			<div class="layui-header header">
				<div class="layui-main">
					<div class="admin-login-box">
						<a class="logo" href="#">
							<span>· JeeBoom ·</span>
						</a>
					</div>
					<ul class="layui-nav" lay-filter="top" style="left: 260px;">
						<#if menus?? >
							<#list menus as menu>
								<li class="layui-nav-item ${(menu.id == leftTree.id)?string('layui-this','')}">
									<a href="javascript:;" data-url="${springMacroRequestContext.contextPath}/common/index?m=${(menu.id)!}">
										<#if menu.img?? && menu.img != "">
											<i class="layui-icon">${menu.img}</i>&nbsp;
										</#if>
										${(menu.name)!}
									</a>
								</li>
							</#list>
						</#if>
					</ul>

					
					<ul class="layui-nav" lay-filter="main">
						<li class="layui-nav-item">
							<a href="javascript:;" class="admin-header-user">
								<img src="${springMacroRequestContext.contextPath}/static/img/0.jpg" />
								<cite class="fs16">${loginUser.name}</cite>
							</a>
							<dl class="layui-nav-child">
								<dd>
									<a href="javascript:" dataUrl="${springMacroRequestContext.contextPath}/common/mySelfFrom"><i class="fa fa-user" aria-hidden="true"></i> 个人信息</a>
								</dd>
								<dd>
									<a href="javascript:" dataUrl="${springMacroRequestContext.contextPath}/common/newPwdFrom"><i class="fa fa-gear" aria-hidden="true"></i> 修改密码</a>
								</dd>
								<dd>
									<a href="${springMacroRequestContext.contextPath}/logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
								</dd>
							</dl>
						</li>
					</ul>
				</div>
			</div>
			<!--
            	作者：196410791@qq.com
            	时间：2017-03-14
            	描述：nav
            -->
			<div class="layui-side layui-bg-black" id="admin-side">
				<div class="layui-side-scroll" >
					<ul class="layui-nav layui-nav-tree" lay-filter="demo">
						<#if leftTree?? && leftTree.sysMenuResps??>
							<#list leftTree.sysMenuResps as menu>
								<li class="layui-nav-item layui-nav-itemed">
									<a href="javascript:;">
										<#if menu.img?? && menu.img != "">
											<i class="layui-icon">${menu.img}</i>&nbsp;&nbsp;
										</#if>
										${(menu.name)!}
									</a>
									<dl class="layui-nav-child">
										<#if menu.sysMenuResps??>
											<#list menu.sysMenuResps as childMenu>
												<dd ${(childMenu_index == 0 && menu_index == 0)?string('class = \"layui-this\"','')}>
													<a href="javascript:;" data-url="${(childMenu.path)!}">
														<#if childMenu.img??>
															<i class="layui-icon">${childMenu.img}</i>
														</#if>
														<cite>${(childMenu.name)!}</cite>
													</a>
												</dd>
											</#list>
										</#if>
									</dl>
								</li>
							</#list>
						</#if>
					</ul>
				</div>
			</div>
			<!--
            	作者：196410791@qq.com
            	时间：2017-03-14
            	描述：main
            -->
			<div class="layui-body"  id="admin-body">
				<#if mainUrl?? && mainUrl != ''>
					<iframe src="${springMacroRequestContext.contextPath}${mainUrl}" ></iframe>
				</#if>
			</div>
		</div>
		
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
		<script>
			layui.use(['layer','form','element'],function(){
				var layer = layui.layer;
				var element = layui.element();
				$ = layui.jquery;

				//锁定页面iframe高度
				$(window).on('resize', function() {
					$('iframe').height($(this).height() - 65);
				}).resize();
				
				//监听导航点击
				element.on('nav(demo)', function(elem){
					var url = $(this).find('a').attr("data-url");
					if(url.indexOf("http://") > -1 || url.indexOf("https://") > -1){
						window.open(url);
					}else{
                        $('iframe').remove();
                        $("#admin-body").html('<iframe src="${springMacroRequestContext.contextPath}'+url+'" ></iframe>');
                        layer.msg('玩命加载中 。。。', {icon: 16,shade: 0.05,time: 500});
					}
				});
				
				//头部nav
				element.on('nav(top)', function(elem){
					var url = $(this).find('a').attr("data-url");
					window.location.href=url;
				});

				//个人信息nav
				element.on('nav(main)', function(elem){
				    var nav_main = $(this).find('a');
					var dataUrl = nav_main.attr("dataUrl");
					if(dataUrl){
                        layer.open({
                            type: 2,
                            title:nav_main.text(),
                            area: ['550px', '380px'],
                            fixed: false, //不固定
                            maxmin: true,
                            content: dataUrl
                        });
					}
					return false;
				});
			});
		</script>
	</body>
</html>