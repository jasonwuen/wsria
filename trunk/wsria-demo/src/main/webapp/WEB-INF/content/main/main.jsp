<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="${ctx }/images/favicon.ico" type="image/x-icon" />
    <title>What's the RIA? ->基于Java和jQuery应用演示 www.wsria.cn</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/css/xdefault.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/js/plugin/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${ctx }/js/plugin/easyui/js/themes/icon.css" />
    <style type="text/css">
    .title {
    	overflow: hidden; height: 30px;
        background: url(${ctx }/images/xeasyui/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;
        color: #fff; 
        font-family: Verdana, 微软雅黑,黑体;
    }
    .noscript {
    	position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;
    }
    </style>
    
    <script type="text/javascript" src="${ctx }/js/common/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/plugin/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src='${ctx }/js/module/main/outlook.js'> </script>
    <script type="text/javascript" src='${ctx }/js/module/main/main-frame.js'></script>

</head>
<body class="easyui-layout" style="overflow-y: hidden">
	<noscript>
		<div class="noscript">
		    <img src="${ctx }/images/xeasyui/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
    <div region="north" split="true" border="false" class="title">
        <span style="padding-left:10px; font-size: 16px; ">
        	<img src="${ctx }/images/ria.jpg" width="30" height="30" align="absmiddle" />
        	What's the RIA? ->基于Java、Springside和jQuery应用演示；构建工具：Maven；当前版本：1.0.0-SNAPSHOT
        </span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">By <a href="http://www.wsria.com" target="_blank" title="访问作者博客">咖啡兔 www.wsria.com</a></div>
    </div>
    <div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
		<div class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
		</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs" fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">
				<p>演示各种基于Java和jQuery开发的企业应用</p>
				<div>为了让大家更好的使用和学习基于Java和jQuery的应用构建，特地建立了一个演示系统wsria-demo，项目基本情况如下：</div>
				<div>Java架构：<a href="http://www.springside.org.cn" target="_blank" linkindex="14">springside</a></div>
				<div>项目构建工具：Maven</div><div><br></div><div>本次发布的版本为初稿，功能如下：</div>
				<div>1、使用easy-ui搭建的系统页面框架</div>
				<div>2、实现了动态加载jstree的功能</div>
				<div><br/></div>
				<div>提示：因为系统使用maven构建，开发环境也依赖maven，所以先让会maven的童鞋<b>尝鲜</b>，不会maven或者想使用maven的童鞋也可以参考本例子构建企业应用，关于maven的介绍请自己google不再本文范围</div>
				<div>关于后续版本再我<a href="http://www.wsria.com" target="_blank" linkindex="15">博客</a>的右侧有计划列表，敬请期待！</div>
				<div><br/></div>
				<div><b><font color="#339966" class="Apple-style-span">SVN地址</font></b>：
					<span style="font-family: monospace; line-height: normal; font-size: 13px;" class="Apple-style-span"><strong><em>http</em></strong>://wsria.googlecode.com/svn/trunk/</span></div>
				<div><span style="font-family: monospace; line-height: normal; font-size: 13px;" class="Apple-style-span">不定时在线<b>演示地址</b>：</span><a href="http://wsria.gnway.net:10000/wsria-demo/main/main.action" target="_blank">http://wsria.gnway.net:10000/wsria-demo</a><a href="http://kafeitu.gicp.net:10000/wsria-demo" target="_blank"></a></div>
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