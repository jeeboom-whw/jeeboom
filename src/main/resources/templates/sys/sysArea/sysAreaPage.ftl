<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom  地区列表</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 15px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">地区列表</li>
                </ul>
            </div>
            <form id="searchForm" class="layui-form" method="post" style="margin-top: 20px;">
                <div class="layui-form-item">
                    <input type ="hidden" name="pageNo" value="${(pager.pageNo)!}">
                    <input type ="hidden" name="pageSize" value="15">
                    <div class="layui-inline">
                        <select lay-filter="province">
                            <option value="">全部</option>
                            <#list province as area>
                                <option value="${area.id}">${area.name}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="layui-inline">
                        <select lay-filter="city" id="city">
                            <option value="">全部</option>
                        </select>
                    </div>
                    <div class="layui-inline">
                        <select id="area">
                            <option value="">全部</option>
                        </select>
                    </div>
                    <div class="layui-inline">
                        <button onclick="layui.submitForm()" class="layui-btn">搜索&nbsp;<i class="layui-icon" >&#xe615;</i></button>
                    </div>
                    <@common.permission per='sys:sysArea:edit'>
                    <div class="layui-inline">
                        <a href="javascript:layui.add();" class="layui-btn">添加地区&nbsp;<i class="layui-icon" >&#xe612;</i></a>
                    </div>
                    </@common.permission>
                </div>
            </form>

            <div style="margin-top: 20px;">
                <table class="layui-table">
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>父级id</th>
                        <th>级别</th>
                        <@common.permission per='sys:sysArea:edit'>
                        <th>操作</th>
                        </@common.permission>
                    </tr>
                    </thead>
                    <tbody>
                    <#list pager.list as entity>
                    <tr>
                        <td><a href="javascript:layui.showInfo('${(entity.id)!}')">${(entity.name)!}</a></td>
                        <td>${(entity.pid)!}</td>
                        <td>${(entity.level)!}</td>
                        <@common.permission per='sys:sysArea:edit'>
                        <td>
                            <a href="javascript:layui.edit('${(entity.id)!}')" class="layui-btn layui-btn-mini">编辑</a>
                            <a href="javascript:layui.del('${(entity.id)!}')" class="layui-btn layui-btn-danger layui-btn-mini">删除</a>
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

                //=============================== demo  str ===============================

                //监听省份select事件
                form.on("select(province)",function(data){
                    //获取城市列表
                    layui.getArea(data.value, "city");
                    //初始化区域select
                    $("#area").html("<option value=''>全部</option>");
                    form.render('select')
                });

                //监听城市select事件
                form.on("select(city)",function(data){
                    //获取区域列表
                    layui.getArea(data.value, "area");
                    form.render('select')
                });

                //通过上级id获取下级信息
                layui.getArea = function(pid,idEle){
                    var selectHtml = "<option value=''>全部</option>";
                    //获取子区域集合
                    $.ajax({
                        url:"${springMacroRequestContext.contextPath}/sysArea/getAreaByPid?pid=" + pid,
                        type:"get",
                        async: false,
                        dataType:"json",
                        success:function(d){
                            if(d.code == 200){
                                //拼接dom元素
                                $.each(d.data,function(index,ele){
                                    selectHtml += "<option value='" + ele.id + "'>" + ele.name + "</option>";
                                });
                            }else{
                                layer.msg("对不起，访问不成功！错误编码：" + d.code);
                            }
                        }
                    });
                    $("#"+idEle).html(selectHtml);
                }


                //=============================== demo  end ===============================

                //搜索
                layui.submitForm = function(){
                    $("#searchForm").submit();
                }

                //查看
                layui.showInfo = function(id){
                    layui.save("查看地区",id);
                }

                //编辑
                layui.edit = function(id){
                    layui.save("编辑地区",id)
                }

                //添加
                layui.add = function(){
                    layui.save("添加地区");
                }

                //添加or编辑ifream
                layui.save = function(title,id){
                    var url ='${springMacroRequestContext.contextPath}/sysArea/saveFrom';
                    if(id!=null&&id!='undfined'){
                        url += "?id="+id
                    }
                    layer.open({
                        type: 2,
                        title:title,
                        area: ['800px', '500px'],
                        fixed: false,
                        maxmin: true,
                        content: url
                    });
                }

                //删除
                layui.del = function(id){
                    layer.msg("您是否确定要删除该地区！", {
                        btn: ['删除', '不删除'],
                        yes:function(){
                            window.location.href="${springMacroRequestContext.contextPath}/sysArea/delById?id="+id;
                        }
                    });
                }
            });
        </script>
    </body>
</html>