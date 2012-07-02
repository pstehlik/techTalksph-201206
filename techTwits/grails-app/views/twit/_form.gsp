<%@ page import="techtwits.Twit" %>



<div class="fieldcontain ${hasErrors(bean: twitInstance, field: 'text', 'error')} required">
	<label for="text">
		<g:message code="twit.text.label" default="Text" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="text" maxlength="140" required="" value="${twitInstance?.text}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: twitInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="twit.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${techtwits.User.list()}" optionKey="id" required="" value="${twitInstance?.user?.id}" class="many-to-one"/>
</div>

