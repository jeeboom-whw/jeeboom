<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom — 数据字典列表</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 15px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">数据字典列表</li>
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
                        <input  type = "text" name="title" value="${(sysMdict.title)!}" placeholder="标题" class="layui-input" />
                    </div>
                    <div class="layui-inline">
                        <input  type = "text" name="name" value="${(sysMdict.name)!}" placeholder="标题名称" class="layui-input" />
                    </div>
                    <div class="layui-inline">
                        <button onclick="layui.submitForm()" class="layui-btn">搜索&nbsp;<i class="layui-icon" >&#xe615;</i></button>
                    </div>
                    <@common.permission per='sys:sysMdict:edit'>
                        <div class="layui-inline">
                            <a href="javascript:layui.add();" class="layui-btn">添加数据字典&nbsp;<i class="layui-icon" >&#xe61f;</i></a>
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
                            <th>标题</th>
                            <th>标题名称</th>
                            <th>值</th>
                            <th>值描述</th>
                            <th>排序</th>
                            <@common.permission per='sys:sysMdict:edit'>
                            <th>操作</th>
                            </@common.permission>
                        </tr>
                    </thead>
                    <tbody>
                        <#list pager.list as entity>
                        <tr>
                            <td><a href="javascript:layui.showInfo('${(entity.id)!}')">${(entity.title)!}</a></td>
                            <td>${(entity.name)!}</a></td>
                            <td>${(entity.value)!}</a></td>
                            <td>${(entity.info)!}</a></td>
                            <td>${(entity.orderNo)!}</a></td>
                            <@common.permission per='sys:sysMdict:edit'>
                            <td>
                                <a href="javascript:layui.addChild('${(entity.id)!}')" class="layui-btn layui-btn-warm layui-btn-mini">新增字典</a>
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

                    //搜索按钮点击事件
                    layui.submitForm = function(){
                        $("#searchForm").submit();
                    }

                    //查看菜单信息
                    layui.showInfo = function(id){
                        layui.save("查看数据字典信息",id);
                    }

                    //编辑
                    layui.edit = function(id){
                        layui.save("修改数据字典信息",id)
                    }

                    //添加
                    layui.add = function(){
                        layui.save("添加数据字典信息");
                    }

                    //添加字典
                    layui.addChild = function(id){
                        layui.save("添加数据字典信息",id,true);
                    }

                    //添加or编辑弹窗
                    layui.save = function(title,id,t){
                        var url ='${springMacroRequestContext.contextPath}/sysMdict/saveFrom';
                        if(t){
                            url ='${springMacroRequestContext.contextPath}/sysMdict/saveFromTwo';
                        }
                        if(id!=null&&id!='undfined'){
                            url += "?id="+id
                        }
                        layer.open({
                            type: 2,
                            title:title,
                            area: ['800px', '500px'],
                            fixed: false, //不固定
                            maxmin: true,
                            content: url
                        });
                    }

                    //上下架
                    layui.accredit = function(id,status){
                        var title,b;
                        if(status == 1){
                            title = "您是否确定要开启该数据字典！";
                            b = ['开启', '取消'];
                        }else{
                            title = "您是否确定要禁用该数据字典！";
                            b = ['禁用', '取消'];
                        }
                        layer.msg(title, {
                            btn: b,
                            yes:function(){
                                window.location.href="${springMacroRequestContext.contextPath}/sysMdict/accredit?id="+id+"&status="+status;
                            }
                        });
                    }

                    //删除
                    layui.del = function(id){
                        layer.msg("您是否确定要删除该数据字典！", {
                            btn: ['删除', '不删除'],
                            yes:function(){
                                window.location.href="${springMacroRequestContext.contextPath}/sysMdict/delById?id="+id;
                            }
                        });
                    }
                });
        </script>
    </body>
</html>