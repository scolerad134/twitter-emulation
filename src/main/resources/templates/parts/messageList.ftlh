<#include "security.ftlh">
<#import "pager.ftlh" as p>

<@p.pager url page />

<div class="card-columns">
    <#list page.content as message>
        <div class="card my-3">
            <#list message.imageList as img>
                <img src="/images/${img.id}" height="60px"/><br><br>
            </#list>
            <div class="m-2">
                <span>${message.text}</span><br/>
                <i>#${message.tag}</i>
            </div>
            <div class="card-footer text-muted container">
                <div class="row">
                    <a class="col align-self-center"
                       href="/user-messages/${message.author.id}">${message.author.username}</a>
                    <#if message.author.id == currentUserId>
                        <a class="col btn btn-primary mr-5" href="/user-messages/${message.author.id}?message=${message.id}">
                            Edit
                        </a>
                        <a class="col btn btn-primary" href="/user-messages/delete/${message.id}">
                            Delete
                        </a>
                    </#if>
                </div>
            </div>
        </div>
    <#else>
        No message
    </#list>
</div>

<@p.pager url page />