<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — 系统菜单列表</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 15px;">
			<div class="layui-tab-brief" >
				<ul class="layui-tab-title">
					<li class="layui-this">菜单列表</li>
				</ul>
			</div>
			<@common.permission per='sys:sysMenu:edit'>
				<div class="layui-form-item mt20">
					<div class="layui-inline">
						<a class="layui-btn layui-btn-primary " onclick="layui.add();"><i class="layui-icon" style="font-size: 20px;color: #00CCEE;">&#xe61f;</i>&nbsp;添加菜单</a>
					</div>
				</div>
			</@common.permission>

			<!--
            	作者：196410791@qq.com
            	时间：2017-03-15
            	描述：表格
            -->
            <div style="margin-top: 20px;">
				<table class="layui-table" >
				    <thead>
						<tr>
							<th>菜单名称</th>
							<th>访问地址</th>
							<th>权限标识</th>
							<th>是否禁用</th>
							<th>是否展示</th>
							<@common.permission per='sys:sysMenu:edit'>
								<th>操作</th>
							</@common.permission>
						</tr>
					</thead>
					<tbody>
						<!-- 定义宏 -->
						<#macro treeArray entityArr>
							<#if entityArr??>
								<#list entityArr as ent>
									<tr data_pid="${(ent.pid)!}" ${(ent.level>2)?string('class="hide"','')}>
										<td>
											<#list 1..ent.level as t>&nbsp;&nbsp;&nbsp;&nbsp;</#list>
											<#if ent.sysMenuResps??>
												<#if ent.level lt 2>
													<a href="javascript:" title="关闭" class="tree" data_tree="${(ent.id)!}"><i class="layui-icon" style="color: #999;font-size: 14px;">&#xe625;</i></a>
												<#else>
													<a href="javascript:" class="tree" title="展开" data_tree="${(ent.id)!}"><i class="layui-icon" style="color: #999;font-size: 14px;">&#xe623;</i></a>
												</#if>
											</#if>
											<a href="javascript:layui.showInfo('${(ent.id)!}')">${(ent.name)!}</a>
										</td>
										<td>${(ent.path)!}</td>
										<td>${(ent.permision)!}</td>
										<td><@common.mdictInfo title='sys_status' value='${ent.status}' /></td>
										<td><@common.mdictInfo title='is_show' value='${ent.isShow}' /></td>
										<@common.permission per='sys:sysMenu:edit'>
										<td>
											<a href="javascript:;" onclick="layui.edit('${(ent.id)!}')" class="layui-btn layui-btn-mini">编辑</a>
											<a href="javascript:;" onclick="layui.accredit('${(ent.id)!}','${(ent.status)!}')" class="layui-btn layui-btn-danger layui-btn-mini">
												${(ent.status?? && ent.status == 1)?string('开启','禁用')}
											</a>
										</td>
										</@common.permission>
									</tr>
									<#if ent.sysMenuResps??> <@treeArray entityArr=ent.sysMenuResps /></#if>
								</#list>
							</#if>
						</#macro>
						<!-- 调用宏 -->
						<@treeArray entityArr=treeList />
					</tbody>
				</table>
				<div id="pager" class="fr"></div>
			</div>
		</div>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
		<script>
			layui.use(['form','laydate','laypage','layer','upload'],function(){
				var layer = layui.layer;
				var $ = layui.jquery;

				//搜索按钮点击事件
                layui.submitForm = function(){
                    window.location.reload();
                }

				//查看菜单信息
                layui.showInfo = function(id){
                    layui.saveMenu("查看菜单信息",id);
				}

				//添加菜单
                layui.add = function(){
                    layui.saveMenu("添加菜单信息");
				}

				//修改菜单
				layui.edit = function(id){
                    layui.saveMenu("修改菜单信息",id)
				}

				//打开保存页面弹窗
				layui.saveMenu = function(title,id){
					var url ='${springMacroRequestContext.contextPath}/sysMenu/saveForm';
					if(id!=null&&id!='undfined'){
						url += "?id="+id
					}
					layer.open({
						type: 2,
						title:title,
						area: ['1000px', '600px'],
						fixed: false, //不固定
						maxmin: true,
						content: url
					});	
				}

				//上下架
				layui.accredit = function(id,status){
				    var title;
				    var b;
				    if(status == 1){
						title = "您是否确定要开启该菜单！";
						b = ['开启', '取消'];
					}else{
                        title = "您是否确定要禁用该菜单！";
                        b = ['禁用', '取消'];
					}
                    layer.msg(title, {
                        btn: b,
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/sysMenu/accredit?id="+id+"&status="+status;
                        }
                    });
				}
				
				//菜单展开关闭图标被点击
				$(".tree").click(function(){
				    var a_tree = $(this);
				    var title = a_tree.attr("title");
					if(title == '展开'){
                        openTr(a_tree);
					}else{
					    closeTr(a_tree);
					}
				});

				//开启子菜单
                function openTr(a){
                    a.attr("title","关闭");
                    a.find("i").html("&#xe625;");
                    $("tr[data_pid='"+a.attr("data_tree")+"']").show();
                }

                //关闭子菜单
                function closeTr(a){
                    a.attr("title","展开");
                    a.find("i").html("&#xe623;");
                    var childs = $("tr[data_pid='"+a.attr("data_tree")+"']");
                    childs.hide();
                    $.each(childs,function(idx,ele){
                        var array = $(ele).find(".tree");
                        if(array.size() > 0){
                            $.each(array,function(i,e){
                                closeTr($(e));
                            });
						}
                    });
                }
			});
		</script>
	</body>
</html>
