<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>What's the RIA? ->基于Java和jQuery应用演示</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/css/xdefault.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/js/plugin/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${ctx }/js/plugin/easyui/js/themes/icon.css" />
    <style type="text/css">
    .title {
    	overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;
        color: #fff; 
        font-family: Verdana, 微软雅黑,黑体;
    }
    .noscript {
    	position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;
    }
    </style>
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${ctx }/js/plugin/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src='js/outlook.js'> </script>
    <script type="text/javascript" src='js/main-frame.js'></script>

</head>
<body class="easyui-layout" style="overflow-y: hidden">
	<noscript>
		<div class="noscript">
		    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
    <div region="north" split="true" border="false" class="title">
        <span style="padding-left:10px; font-size: 16px; "><img src="images/blocks.gif" width="20" height="20" align="absmiddle" /> What's the RIA? ->基于Java和jQuery应用演示</span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">By <a href="http://www.wsria.cn" target="_blank" title="访问作者博客">咖啡兔 www.wsria.cn</a></div>
    </div>
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
		<div class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
		</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs" fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">
				演示各种基于Java和jQuery开发的企业应用
			</div>
		</div>
    </div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
	
</body>
</html>