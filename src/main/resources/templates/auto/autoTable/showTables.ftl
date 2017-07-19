<!DOCTYPE html>
<#import "../../macro/common.ftl" as common>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>JeeBoom — 数据库表列表</title>
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/layui/css/layui.css" />
        <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/static/css/global.css" />
        <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    </head>
    <body class="anim-fadeInUp">
        <div style="margin: 15px;">
            <div class="layui-tab-brief" >
                <ul class="layui-tab-title">
                    <li class="layui-this">数据库表列表</li>
                </ul>
            </div>
            <!--
                作者：196410791@qq.com
                时间：2017-03-15
                描述：表单搜索
            -->
            <form id="searchForm" action="${springMacroRequestContext.contextPath}/autoTable/showColumns" class="layui-form" style="margin-top: 20px;">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <select name="tableName">
                            <option value="">请选择数据库表</option>
                            <#list tables as table>
                                <option value="${(table.dbName)!}">${(table.dbName)!} : ${(table.label)!}</option>
                            </#list>
                        </select>
                    </div>
                    <div class="layui-inline">
                        <button onclick="layui.nextForm()" class="layui-btn">下一步&nbsp;<i class="layui-icon" >&#xe602;</i></button>
                    </div>
                </div>
            </form>

            </div>
            <script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
            <script>
                layui.use(['form','laypage','layer'],function(){
                    var form = layui.form();
                    var laypage = layui.laypage;
                    var layer = layui.layer;
                    var $ = layui.jquery;


                    //搜索按钮点击事件
                    layui.nextForm = function(){
                        $("#searchForm").submit();
                    }
                });
        </script>
    </body>
</html>