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
            <div class="layui-form" style="margin-top: 20px;">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        表名：
                    </div>
                    <div class="layui-inline fr">
                        <button onclick="layui.nextForm()" class="layui-btn">下一步&nbsp;<i class="layui-icon" >&#xe602;</i></button>
                    </div>
                    <div class="layui-inline">
                        <input  type = "text" disabled value="${(tableName)!}" class="layui-input" />
                    </div>
                    <div class="layui-inline fr">
                        <input  type = "checkbox" value="1" name="is_del" class="layui-input" /> 批量上下架
                    </div>
                    <div class="layui-inline fr">
                        <input  type = "checkbox" value="1" name="is_del" class="layui-input" /> 上下架
                    </div>
                    <div class="layui-inline fr">
                        <input  type = "checkbox" value="1" name="is_del" class="layui-input" /> 批量展示隐藏
                    </div>
                    <div class="layui-inline fr">
                        <input  type = "checkbox" value="1" name="is_del" class="layui-input" /> 展示隐藏
                    </div>
                    <div class="layui-inline fr">
                        <input  type = "checkbox" value="1" name="is_del" class="layui-input" /> 批量删除
                    </div>
                    <div class="layui-inline fr">
                        <input  type = "checkbox" value="1" name="is_del" class="layui-input" /> 删除
                    </div>
                </div>
            </div>

            <!--
                作者：196410791@qq.com
                时间：2017-03-15
                描述：表格
            -->
            <div style="margin-top: 20px;">
                <table class="layui-table layui-form" >
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
                            <th width="100px">查询类型</th>
                            <th width="100px">排序</th>
                            <th width="100px">数据字典</th>
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
                            <td><input type="checkbox" name = "nullable[${entity_index}]" lay-skin="primary" ${(entity.nullable?? && entity.nullable == 1)?string("checked","")} /></td>
                            <td><input type="checkbox" name = "isList[${entity_index}]" lay-skin="primary" ${(entity.isList?? && entity.isList == 1)?string("checked","")} /></td>
                            <td><input type="checkbox" name = "isSelect[${entity_index}]" lay-skin="primary" ${(entity.isSelect?? && entity.isSelect == 1)?string("checked","")} /></td>
                            <td>
                                <select name = "isSelectType[${entity_index}]">
                                    <@common.mdictOptions title="auto_select_type" value="${(entity.isSelectType)!}" />
                                </select>
                            </td>
                            <td><input type = "text" name = "orderNo[${entity_index}]" value = "${(entity.orderNo)!}" lay-verify="required|number" class="layui-input" maxlength="4"/></td>
                            <td><input type = "text" name = "mdictTitle[${entity_index}]" value = "${(entity.mdictTitle)!}" lay-verify="required" class="layui-input" maxlength="50"/></td>
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