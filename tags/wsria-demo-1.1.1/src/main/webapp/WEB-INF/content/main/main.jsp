<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/global.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="${ctx }/images/favicon.ico" type="image/x-icon" />
    <title>What's the RIA? ->基于Java和jQuery应用演示 www.wsria.com</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/css/xdefault.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/js/plugin/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${ctx }/js/plugin/easyui/js/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="${ctx }/js/plugin/tip/gritter/css/jquery.gritter.css" />
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
    
    <script type="text/javascript" src="${ctx }/js/common/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/plugin/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/plugin/tip/gritter/jquery.gritter.min.js"></script>
	<script type="text/javascript" src='${ctx }/js/module/main/outlook.js'> </script>
    <script type="text/javascript" src='${ctx }/js/module/main/menu.js'></script>
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
        	What's the RIA? ->基于Java、Springside和jQuery应用演示；构建工具：Maven3
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
			<div title="欢迎使用" style="padding:20px;" id="home">
				<p>演示各种基于Java和jQuery开发的企业应用</p>
				<div>为了让大家更好的使用和学习基于Java和jQuery的应用构建，特地建立了一个演示系统wsria-demo，项目基本情况如下：</div>
				<div>Java架构：<a href="http://www.springside.org.cn" target="_blank" linkindex="14">springside</a>
					(版本：<b>3.3.4.1</b>，由咖啡兔在3.3.4版本基础上修改添加了jqGrid支持)
					<p><b>SVN地址：</b>https://wsria.googlecode.com/svn/trunk/springside-3.3.4.x</p>
				</div>
				<h3>演示功能如下：</h3>
				<div>1、使用easy-ui搭建的系统页面框架</div>
				<div>2、实现了动态加载jstree的功能</div>
				<div>3、新增area2select插件</div>
				<div>2、添加了对jqGrid的前后台支持</div>
				<div><br/></div>
				<div>
					<b>开发环境</b>：
					<ol>
						<li>Eclipse3.6 with JAVAEE，插件：m2eclipse</li>
						<li>JDK：1.6</li>
						<li>Tomcat 6.0.29</li>
					</ol>
				</div>
				<div>关于后续版本再我<a href="http://www.wsria.com" target="_blank" linkindex="15">博客</a>的右侧有计划列表，敬请期待！</div>
				<h3>系统运行初始化工作：</h3>
				<ol>
					<li>运行src\main\resources\sql\mysql\wsriademo.sql文件创建所需数据库并初始化数据</li>
					<li>可以通过导入到eclipse(需要有m2eclipse插件)或者直接用<b>mvn jetty:run</b>运行</li>
					<li>数据库配置信息位于：src\main\resources\application.properties</li>
				</ol>
				<div>
					<b>项目信息：</b>
					<ul>
						<li><b>项目主页：</b><a href="http://code.google.com/p/wsria" target="_blank">http://code.google.com/p/wsria</a></li>
						<li><b>SVN地址：</b>http://wsria.googlecode.com/svn/trunk/</li>
						<li>在线<b>演示地址</b>：</span><a href="http://demo.wsria.com:10000/wsria-demo" target="_blank">http://demo.wsria.com:10000/wsria-demo</a></li>
					</ul>
				</div>
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