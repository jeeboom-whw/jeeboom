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
            <form class="layui-form" method="post" id="searchForm" action="${springMacroRequestContext.contextPath}/autoTable/createAutoFile">
            <div style="margin-top: 20px;">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        表名：
                    </div>
                    <div class="layui-inline">
                        <input  type = "text" value="${(tableName)!}" class="layui-input" disabled="disabled"/>
                        <input type = "hidden" value = "${(tableName)!}" name="autoTable.tableName" />
                    </div>
                    <div class="layui-inline fr">
                        <a href="javascript:layui.pathFrom()" class="layui-btn">下一步&nbsp;<i class="layui-icon" >&#xe602;</i></a>
                    </div>
                    <div class="layui-inline fr">
                        <input  type = "checkbox" value="1" name="autoTable.isAllStatus" class="layui-input" /> 批量上下架
                    </div>
                    <div class="layui-inline fr">
                        <input  type = "checkbox" value="1" name="autoTable.isStatus" class="layui-input" /> 上下架
                    </div>
                    <div class="layui-inline fr">
                        <input  type = "checkbox" value="1" name="autoTable.isAllDel" class="layui-input" /> 批量删除
                    </div>
                    <div class="layui-inline fr">
                        <input  type = "checkbox" value="1" name="autoTable.isDel" class="layui-input" /> 删除
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
                            <th>非空</th>
                            <th>展示</th>
                            <th>查询</th>
                            <th width="100px">查询类型</th>
                            <th width="100px">排序</th>
                            <th width="100px">数据字典</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list list as entity>
                        <input type="hidden" name="autoTableColumns[${entity_index}].label" value="${(entity.label)!}"/>
                        <tr>
                            <td>${(entity.columnName)!}<input type="hidden" name="autoTableColumns[${entity_index}].columnName" value="${(entity.columnName)!}"/> </td>
                            <td>${(entity.name)!}<input type="hidden" name="autoTableColumns[${entity_index}].name" value="${(entity.name)!}"/></td>
                            <td>${(entity.columnType)!}<input type="hidden" name="autoTableColumns[${entity_index}].columnType" value="${(entity.columnType)!}"/></td>
                            <td>${(entity.type)!}<input type="hidden" name="autoTableColumns[${entity_index}].type" value="${(entity.type)!}"/></td>
                            <td>${(entity.length)!}<input type="hidden" name="autoTableColumns[${entity_index}].length" value="${(entity.length)!}"/></td>
                            <td><input type="checkbox" value="1" name = "autoTableColumns[${entity_index}].nullable" lay-skin="primary" ${(entity.nullable?? && entity.nullable == 1)?string("checked","")} /></td>
                            <td><input type="checkbox" value="1" name = "autoTableColumns[${entity_index}].isList" lay-skin="primary" ${(entity.isList?? && entity.isList == 1)?string("checked","")} /></td>
                            <td><input type="checkbox" value="1" name = "autoTableColumns[${entity_index}].isSelect" lay-skin="primary" ${(entity.isSelect?? && entity.isSelect == 1)?string("checked","")} /></td>
                            <td>
                                <select name = "autoTableColumns[${entity_index}].isSelectType">
                                    <@common.mdictOptions title="auto_select_type" value="${(entity.isSelectType)!}" />
                                </select>
                            </td>
                            <td><input type = "text" name = "autoTableColumns[${entity_index}].orderNo" value = "${(entity.orderNo)!}" lay-verify="required|number" class="layui-input" maxlength="4"/></td>
                            <td><input type = "text" name = "autoTableColumns[${entity_index}].mdictTitle" value = "${(entity.mdictTitle)!}" lay-verify="required" class="layui-input" maxlength="50"/></td>
                        </tr>
                        </#list>
                    </tbody>
                </table>
                <div class="hide" id="chieldHtm">
                    <input type="hidden" name = "outRoot" />
                    <input type="hidden" name = "basePackage" />
                    <input type="hidden" name = "model" />
                    <input type="hidden" name = "info" />
                    <input type="hidden" name = "createType" />
                </div>
            </form>
            </div>
            </div>
            <script type="text/javascript" src="${springMacroRequestContext.contextPath}/static/layui/layui.js"></script>
            <script>
                layui.use(['form','laydate','laypage','layer'],function(){
                    var form = layui.form();
                    var layer = layui.layer;
                    var $ = layui.jquery;

                    //搜索按钮点击事件
                    layui.submitForm = function(outRoot,basePackage,model,info,createType){
                        $("input[name='outRoot']").val(outRoot);
                        $("input[name='basePackage']").val(basePackage);
                        $("input[name='model']").val(model);
                        $("input[name='info']").val(info);
                        $("input[name='createType']").val(createType);
                        $("#searchForm").submit();
                    }

                    //追加信息
                    layui.pathFrom = function(){
                        var url ='${springMacroRequestContext.contextPath}/autoTable/pathFrom';
                        layer.open({
                            type: 2,
                            title:"最终代码生成配置",
                            area: ['800px', '460px'],
                            fixed: false, //不固定
                            maxmin: true,
                            content: url
                        });
                    }
                });
        </script>
    </body>
</html>