<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <title>地区信息插件演示</title>
    <style type="text/css">
    legend {font-weight: bold;padding: 5px;}
    .caption {border: 1px dotted green; padding: 5px; font-size: 12px;}
    </style>
</head>
<body>
	<fieldset>
		<legend>插件目的</legend>
		<div class="caption">
			为了从数据库读取城市列表专门扩展的jQuery插件--<b>jquery.area2select</b>
		</div>
	</fieldset>
	<fieldset>
		<legend>插件结构</legend>
		<div class="caption">
			数据库结构：<img src="${ctx }/images/module/area/area-pmd.png" alt="地区信息PMD设计" align="middle" />
		</div>
	</fieldset>
</body>
</html>