<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom — 属性列表列表</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 15px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">属性列表列表</li>
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
                        <input  type = "text" name="name" value="${(autoTableColumn.name)!}" placeholder="名称" class="layui-input" />
                    </div>
                    <div class="layui-inline">
                        <button onclick="layui.submitForm()" class="layui-btn">搜索&nbsp;<i class="layui-icon" >&#xe615;</i></button>
                    </div>
                    <@common.permission per='auto:autoTableColumn:edit'>
                        <div class="layui-inline">
                            <a href="javascript:layui.add();" class="layui-btn">添加属性列表&nbsp;<i class="layui-icon" >&#xe612;</i></a>
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
                            <th>表id</th>
                            <th>数据库属性名称</th>
                            <th>属性别名</th>
                            <th>数据库类型</th>
                            <th>JAVA类型</th>
                            <th>长度</th>
                            <th>注释</th>
                            <th>是否为非空</th>
                            <th>是否展示在列表</th>
                            <th>是否为查询条件</th>
                            <th>查询种类 1 相等 2 like</th>
                            <th>排序</th>
                            <th>数据字典title</th>
                            <@common.permission per='auto:autoTableColumn:edit'>
                            <th>操作</th>
                            </@common.permission>
                        </tr>
                    </thead>
                    <tbody>
                        <#list pager.list as entity>
                        <tr>
                            <td><a href="javascript:layui.showInfo('${(entity.id)!}')">${(entity.tableId)!}</a></td>
                            <td>${(entity.columnName)!}</a></td>
                            <td>${(entity.name)!}</a></td>
                            <td>${(entity.columnType)!}</a></td>
                            <td>${(entity.type)!}</a></td>
                            <td>${(entity.length)!}</a></td>
                            <td>${(entity.label)!}</a></td>
                            <td>${(entity.nullable)!}</a></td>
                            <td>${(entity.isList)!}</a></td>
                            <td>${(entity.isSelect)!}</a></td>
                            <td>${(entity.isSelectType)!}</a></td>
                            <td>${(entity.orderNo)!}</a></td>
                            <td>${(entity.mdictTitle)!}</a></td>
                            <@common.permission per='auto:autoTableColumn:edit'>
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

                    //搜索按钮点击事件
                    layui.submitForm = function(){
                        $("#searchForm").submit();
                    }

                    //查看菜单信息
                    layui.showInfo = function(id){
                        layui.save("查看属性列表信息",id);
                    }

                    //编辑
                    layui.edit = function(id){
                        layui.save("修改属性列表信息",id)
                    }

                    //添加
                    layui.add = function(){
                        layui.save("添加属性列表信息");
                    }

                    //添加or编辑弹窗
                    layui.save = function(title,id){
                        var url ='${springMacroRequestContext.contextPath}/autoTableColumn/saveFrom';
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
                            title = "您是否确定要开启该属性列表！";
                            b = ['开启', '取消'];
                        }else{
                            title = "您是否确定要禁用该属性列表！";
                            b = ['禁用', '取消'];
                        }
                        layer.msg(title, {
                            btn: b,
                            yes:function(){
                                window.location.href="${springMacroRequestContext.contextPath}/autoTableColumn/accredit?id="+id+"&status="+status;
                            }
                        });
                    }

                    //删除
                    layui.del = function(id){
                        layer.msg("您是否确定要删除该属性列表！", {
                            btn: ['删除', '不删除'],
                            yes:function(){
                                window.location.href="${springMacroRequestContext.contextPath}/autoTableColumn/delById?id="+id;
                            }
                        });
                    }
                });
        </script>
    </body>
</html>