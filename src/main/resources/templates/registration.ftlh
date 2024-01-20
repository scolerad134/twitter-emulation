<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    Add new user
    <#if errorMessage??>
        <h2 style="color: red">${errorMessage}</h2>
    </#if>
    <@l.login "/registration"/>
</@c.page>