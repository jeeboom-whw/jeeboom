<#-- 权限宏 -->
<#macro permission per>
    <@role permission=per;result><#if result>
        <#nested />
    </#if></@role>
</#macro>

<#-- 根据title与value获取字典对象info值 -->
<#macro mdictInfo title value>
    <@mdict t=title v=value>
        ${fre_info}
    </@mdict>
</#macro>

<#-- 根据title获取字典列表拼装option的dom -->
<#macro mdictOptions title value>
    <@mdict t=title>
        <#list fre_mdicts as mdict>
            <option value = "${mdict.value}" ${(mdict.value == value)?string("selected","")}>${(mdict.info)!}</option>
        </#list>
    </@mdict>
</#macro>