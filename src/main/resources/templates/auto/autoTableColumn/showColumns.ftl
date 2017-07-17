<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom — 属性列表</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 15px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">属性列表</li>
                </ul>
            </div>
            <!--
                作者：196410791@qq.com
                时间：2017-03-15
                描述：表单搜索
            -->
            <form id="searchForm" class="layui-form" style="margin-top: 20px;">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <input  type = "text" name="name" value="${(autoTableColumn.name)!}" placeholder="名称" class="layui-input" />
                    </div>
                    <div class="layui-inline">
                        <button onclick="layui.submitForm()" class="layui-btn">搜索&nbsp;<i class="layui-icon" >&#xe615;</i></button>
                    </div>
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
                            <th>属性名称</th>
                            <th>映射名</th>
                            <th>数据库类型</th>
                            <th>JAVA类型</th>
                            <th>长度</th>
                            <th>是否空</th>
                            <th>是否展示</th>
                            <th>是否查询</th>
                            <th>查询类型</th>
                            <th>排序</th>
                            <th>数据字典</th>
                            <th>注释</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list list as entity>
                        <tr>
                            <td>${(entity.columnName)!}</td>
                            <td>${(entity.name)!}</td>
                            <td>${(entity.columnType)!}</td>
                            <td>${(entity.type)!}</td>
                            <td>${(entity.length)!}</td>
                            <td>${(entity.nullable)!}</td>
                            <td>${(entity.isList)!}</td>
                            <td>${(entity.isSelect)!}</td>
                            <td>${(entity.isSelectType)!}</td>
                            <td>${(entity.orderNo)!}</td>
                            <td>${(entity.mdictTitle)!}</td>
                            <td>${(entity.label)!}</td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
                <div id="pager" class="fr"></div>
            </div>
            </div>
            <script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
            <script>
                layui.use(['form','laydate','laypage','layer'],function(){
                    var form = layui.form();
                    var laypage = layui.laypage;
                    var layer = layui.layer;
                    var $ = layui.jquery;

                    //搜索按钮点击事件
                    layui.submitForm = function(){
                        $("#searchForm").submit();
                    }
                });
        </script>
    </body>
</html>