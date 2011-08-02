<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/global.jsp" %>
	<%@ include file="/common/meta.jsp" %>
	<title>数据字典</title>
	<link href="${ctx}/css/blueprint/screen.css" type="text/css" rel="stylesheet" media="screen, projection" />
	<link href="${ctx}/css/blueprint/print.css" type="text/css"	rel="stylesheet" media="print" />
	<!--[if lt IE 8]>
		<link href="${ctx}/css/blueprint/blueprint/ie.css" type="text/css" rel="stylesheet" media="screen, projection">
	<![endif]-->
	<link href="${ctx }/js/plugin/jui/themes/${themeName }/jquery-ui.css" type="text/css" rel="stylesheet" />
	<link href="${ctx}/js/plugin/jqGrid/css/ui.jqgrid.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/js/plugin/validate/jquery.validate.css" type="text/css" rel="stylesheet"/>
	<link href="${ctx}/css/style.css" type="text/css" rel="stylesheet"/>
	
	<script src="${ctx}/js/common/jquery.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/plugin/jui/jquery-ui.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/plugin/jqGrid/i18n/grid.locale-cn.js" type="text/javascript"></script>
	<script type="text/javascript">
	    $.jgrid.no_legacy_api = true;
	    $.jgrid.useJSON = true;
    </script>
	<script src="${ctx}/js/plugin/jqGrid/jquery.jqGrid.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/plugin/validate/jquery.validate.pack.js" type="text/javascript"></script>
	<script src="${ctx}/js/plugin/validate/messages_cn.js" type="text/javascript"></script>
	<script src="${ctx}/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/module/demo/jqgrid/datalibrary-list.js" type="text/javascript"></script>
</head>

<body>
	<table id="list"></table>
	<div id="pager"></div>
</body>
</html>