<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<title>JeeBoom — 系统菜单添加</title>
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
		<link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
		<link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
	</head>
	<body class="anim-fadeInUp">
		<div style="margin: 15px;">
			<div class="layui-tab-brief" >
				<ul class="layui-tab-title">
					<li class="layui-this">${(sysMenu?? && sysMenu.id??)?string('编辑','添加')}菜单</li>
				</ul>
			</div>
			<form id="saveForm" class="layui-form layui-form-pane mt20" >
				<input type="hidden" value = "${(sysMenu.id)!}" name="id" />
				<input type="hidden" value = "${(sysMenu.pid)!}" name="pid" />
				<input type="hidden" value = "${(sysMenu.level)!}" name="level" />
				<input type="hidden" value = "${(sysMenu.img)!}" name="img" />
				<div class="layui-form-item">
					<label class="layui-form-label">上级菜单</label>
					<div class="layui-input-block">
						<input type="text" id="pname" value="${(pname)!}" lay-verify="pname" onclick="layui.showTree();" class="layui-input">
					</div>
				</div>

				<div class="layui-form-item">
					<label class="layui-form-label">菜单名称</label>
					<div class="layui-input-block">
						<input type="text" name="name" lay-verify="required" value="${(sysMenu.name)!}" placeholder="请输入菜单名称" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">访问地址</label>
					<div class="layui-input-block">
						<input type="text" name="path" value="${(sysMenu.path)!}" placeholder="请输入菜单访问地址" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">权限标识</label>
					<div class="layui-input-block">
						<input type="text" name="permision" value="${(sysMenu.permision)!}" placeholder="请输入权限标识" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">是否展示</label>
					<div class="layui-input-block">
						<input name="isShow" title="展示" type="radio" checked="" value="1" />
						<input name="isShow" title="隐藏" type="radio" ${(sysMenu.isShow?? && sysMenu.isShow ==2)?string('checked','')}  value="2" />
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">选择图标</label>
					<div class="layui-input-block">
						<a class="layui-btn" id="imgBtn" onclick="layui.iconFrom();" >
							<#if sysMenu.img??>
								<i class='layui-icon'>${(sysMenu.img)!}</i> ${(sysMenu.name)!}
							<#else>
								请选择
							</#if>
						</a>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">排序编号</label>
					<div class="layui-input-block">
						<input type="text" name="orderNo" lay-verify="number" value="${(sysMenu.orderNo)!}" placeholder="请输入排序编号" class="layui-input">
					</div>
				</div>
				<@common.permission per='sys:sysMenu:edit'>
					<div class="layui-form-item">
						<button class="layui-btn fr" lay-submit="" lay-filter="submitBtn">保存菜单信息</button>
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
                    pname: function(value){
                        var pid = $("input[name='pid']").val();
                        if(pid == ''){
                            return '上级菜单要选择呀！';
                        }
                    }
                });

				//展示上级菜单树弹窗
				layui.showTree = function(){
                    layer.open({
                        type: 2,
                        title:'请选择上级菜单',
                        area: ['400px', '500px'],
                        fixed: false, //不固定
                        maxmin: true,
                        content: '${springMacroRequestContext.contextPath}/sysMenu/getTreeFrom'
                    });
                }

                //展示图片弹窗
                layui.iconFrom = function(){
                    layer.open({
                        type: 2,
                        title:'请选择菜单图标',
                        area: ['800px', '500px'],
                        fixed: false, //不固定
                        maxmin: true,
                        content: '${springMacroRequestContext.contextPath}/common/iconFrom'
                    });
				}

                //上级菜单
                layui.addPValue = function(name,pid,level){
					$("#pname").val(name);
					$("input[name='pid']").val(pid);
					$("input[name='level']").val(level);
				}

				//获取图标
				layui.addIcon = function(icon){
					$("#imgBtn").html("<i class='layui-icon'>"+icon+"</i> "+ $("input[name='name']").val());
					$("input[name='img']").val(icon);
				}

				//表单提交
				form.on('submit(submitBtn)',function(data){
					layer.load();
					$.ajax({
						url:"${springMacroRequestContext.contextPath}/sysMenu/save",
						type:"POST",
						data:$("#saveForm").serialize(),
						dataType:"json",
						success:function(d){
                            layer.closeAll('loading');
						    if(d.code==200){
                                window.top.layer.msg('保存成功', {icon: 1});
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
