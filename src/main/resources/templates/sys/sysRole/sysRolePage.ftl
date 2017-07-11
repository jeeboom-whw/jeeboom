<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — 系统角色列表</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 15px;">
			<div class="layui-tab-brief" >
				<ul class="layui-tab-title">
					<li class="layui-this">系统角色列表</li>
				</ul>
			</div>
	 		<!--
             	作者：196410791@qq.com
             	时间：2017-03-15
             	描述：表单搜索
             -->
			<form id="searchForm" class="layui-form" style="margin-top: 20px;">
				<div class="layui-form-item">
					<input type ="hidden" name="pageNo" value="${(pager.pageNo)!}">
					<input type ="hidden" name="pageSize" value="15">
                    <div class="layui-inline">
                        <input  type = "text" name="name" value="${(sysRole.name)!}" placeholder="角色名称" class="layui-input" />
                    </div>
                    <div class="layui-inline">
                        <select name="status">
							<option value="">全部</option>
							<@common.mdictOptions title="sys_status" value="${(sysRole.status)!}" />
						</select>
                    </div>
					<div class="layui-inline">
						<button onclick="layui.submitForm()" class="layui-btn">搜索&nbsp;<i class="layui-icon" >&#xe615;</i></button>
					</div>
					<@common.permission per='sys:sysRole:edit'>
						<div class="layui-inline">
							<a href="javascript:layui.add();" class="layui-btn">添加角色&nbsp;<i class="layui-icon" >&#xe61f;</i></a>
						</div>
					</@common.permission>
				</div>
			</form>
						
			<!--
            	作者：196410791@qq.com
            	时间：2017-03-15
            	描述：表格
            -->
            <div style="margin-top: 20px;">
				<table class="layui-table" >
				    <thead>
						<tr>
							<th>角色名称</th>
							<th>是否禁用</th>
							<th>创建时间</th>
							<@common.permission per='sys:sysRole:edit'>
								<th>操作</th>
							</@common.permission>
						</tr>
					</thead>
					<tbody>
						<#list pager.list as entity>
							<tr>
								<td><a href="javascript:layui.showInfo('${(entity.id)}')">${(entity.name)!}</a></td>
								<td><@common.mdictInfo title='sys_status' value='${entity.status}' /></td>
								<td>${entity.createTime?string("yyyy年MM月dd日 HH:mm:ss")}</td>
								<@common.permission per='sys:sysRole:edit'>
									<td>
										<a href="javascript:;" onclick="layui.edit('${(entity.id)!}')" class="layui-btn layui-btn-mini">编辑</a>
										<a href="javascript:;" onclick="layui.accredit('${(entity.id)!}','${(entity.status)!}')" class="layui-btn layui-btn-danger layui-btn-mini">
											${(entity.status?? && entity.status == 1)?string('开启','禁用')}
										</a>
									</td>
								</@common.permission>
							</tr>
						</#list>
					</tbody>
				</table>
				<div id="pager" class="fr"></div>
			</div>
		</div>
		<script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
		<script>
			layui.use(['form','laydate','laypage','layer','upload'],function(){
				var form = layui.form();
				var laypage = layui.laypage;
				var layer = layui.layer;
				var $ = layui.jquery;
				laypage({
					cont: 'pager',
					pages: '${(pager.pageNum)!1}',
					curr: '${(pager.pageNo)!1}',
					skip: true,
					jump:function(obj,first){
						if(!first){
							$("input[name=pageNo]").val(obj.curr);
							layui.submitForm();
						}
					}
				});

                //搜索按钮点击事件
                layui.submitForm = function(){
                    $("#searchForm").submit();
                }

                //查看
                layui.showInfo = function(id){
                    layui.save("查看角色信息",id)
                }

				//编辑
                layui.edit = function(id){
                    layui.save("修改角色信息",id)
                }
				
				//添加
                layui.add = function(){
                    layui.save("添加角色信息");
				}

				//添加or编辑弹窗
				layui.save = function(title,id){
					var url ='${springMacroRequestContext.contextPath}/sysRole/saveFrom';
					if(id!=null&&id!='undfined'){
						url += "?id="+id
					}
					layer.open({
						type: 2,
						title:title,
						area: ['600px', '550px'],
						fixed: false, //不固定
						maxmin: true,
						content: url
					});	
				}

                //上下架
                layui.accredit = function(id,status){
                    var title,b;
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
                            window.location.href="${springMacroRequestContext.contextPath}/sysRole/accredit?id="+id+"&status="+status;
                        }
                    });
                }
			});
		</script>
	</body>
</html>
