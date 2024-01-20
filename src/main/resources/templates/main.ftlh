<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout />
        <#if user.isAdmin()>
            <a href="/user">Admin Panel</a>
        </#if>
    </div>
    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Enter your message" />
            <input type="text" name="tag" placeholder="Tag">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add</button>
        </form>
    </div>
    <div>List of messages</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter}">
        <button type="submit">Search</button>
    </form>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>#${message.tag}</i>
            <strong>${message.author.username}</strong>
        </div>
    <#else>
        No message
    </#list>
</@c.page>