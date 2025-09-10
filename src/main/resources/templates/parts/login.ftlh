<#import "/spring.ftl" as spring />
<#include "security.ftlh">

<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-6">
                <input type="text" name="username" class="form-control" placeholder="User name" />
                <div style="color: red"><#if validError??><@spring.bind "user.username" /><@spring.showErrors "<br />"/></#if></div>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-6">
                <input type="password" name="password" class="form-control" placeholder="Password" />
                <div style="color: red"><#if validError??><@spring.bind "user.password" /><@spring.showErrors "<br />"/></#if></div>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <#if !isRegisterForm><a href="/registration">Sign up</a></#if>
        <#if isRegisterForm>
            <div class="col-sm-6 form-group">
                <div class="g-recaptcha" data-sitekey="6LeuEWYpAAAAAOLCBeZ_DtN9ZyVNRDa02_ObnLcX"></div>
                <div style="color: red"><#if captchaError??>${captchaError}</#if></div>
            </div>
        </#if>
        <button class="btn btn-primary ml-3" type="submit"><#if isRegisterForm>Create<#else>Sign In</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit"><#if user??>Sign Out<#else>Log in</#if></button>
    </form>
</#macro>