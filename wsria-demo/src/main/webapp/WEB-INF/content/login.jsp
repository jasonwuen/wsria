<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<title>wsria demo login</title>
	<link href="${ctx}/css/blueprint/screen.css" type="text/css" rel="stylesheet" media="screen, projection" />
	<link href="${ctx}/css/blueprint/print.css" type="text/css"	rel="stylesheet" media="print" />
	<!--[if lt IE 8]>
		<link href="${ctx}/css/blueprint/blueprint/ie.css" type="text/css" rel="stylesheet" media="screen, projection">
	<![endif]-->
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/common/js/easyui/themes/default/easyui.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/common/js/easyui/themes/icon.css" type="text/css" rel="stylesheet"/>
	
	<script src="${ctx}/js/common/jquery.min.js" type="text/javascript"></script>
	<script src="${ctx}/common/js/easyui/jquery.easyui.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/jquery.validate.js" type="text/javascript"></script>
	<script src="${ctx}/js/validate/messages_cn.js" type="text/javascript"></script>
	<script>
		$(function(){
			$('#dd').dialog({
				buttons:[{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						alert('ok');
					}
				},{
					text:'Cancel',
					handler:function(){
						$('#dd').dialog('close');
					}
				}]
			});
		});
	</script>
</head>
<body>
	<div id="dd" style="padding:5px;width:400px;height:200px;">
	</div>
</body>
</html>

