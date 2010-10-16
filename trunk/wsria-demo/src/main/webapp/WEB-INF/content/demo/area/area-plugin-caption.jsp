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
    .caption {padding: 5px; font-size: 12px;}
    .caption ol li {padding-top: 5px;padding-bottom: 5px;}
    </style>
</head>
<c:set var="googlecode" value="http://code.google.com/p/wsria/source/browse/trunk/wsria-demo"></c:set>
<body>
	<fieldset>
		<legend>插件目的</legend>
		<div class="caption">
			为了从数据库读取城市列表专门扩展的jQuery插件--<b>jquery.area2select</b>
		</div>
	</fieldset>
	<fieldset>
		<legend>后台设计</legend>
		<div class="caption">
			<ol>
			<li>数据库结构：<a href="${ctx }/images/module/area/area-pmd.png">点击查看</a>，使用Mysql</li>
			<li>数据库PDM：<a href="${googlecode }/designer/wsria-demo">点击查看</a>，PowerDesigner 15.1版本</li>
			<li>实体类(JPA)：<a href="${googlecode }/src/main/java/cn/wsria/demo/entity/area/AreaInfo.java">点击查看</a>，使用Eclipse With JAVAEE 3.6自动生成</li>
			<li>地区信息DAO：<a href="${googlecode }/src/main/java/cn/wsria/demo/dao/area/AreaInfoDao.java">点击查看</a>，数据库查询</li>
			<li>地区信息Service：<a href="${googlecode }/src/main/java/cn/wsria/demo/service/area/AreaInfoService.java">点击查看</a>，各种查询接口和下拉框相关的查询接口</li>
			<li>地区信息Action：<a href="${googlecode }/src/main/java/cn/wsria/demo/web/area/AreaInfoAction.java">点击查看</a>，供<b>jquery.area2select</b>插件调用</li>
			<li>地区信息初始化Servlet：<a href="${googlecode }/src/main/java/cn/wsria/demo/servlet/AreaInfoServlet.java">点击查看</a>，Servlet，在启动应用的时候初始化地区信息存放到内存中，依赖<b>地区信息工具类</b></li>
			<li>地区信息工具类：<a href="${googlecode }/src/main/java/cn/wsria/demo/util/AreaInfoUtil.java">点击查看</a>，供其他模块直接调用，Servlet在初始化数据的时候报错在此类的静态属性中，
			其他模块再调用的时候直接通过工具类提供的方法查询结果</li>
			</ol>
		</div>
	</fieldset>
	<fieldset>
		<legend>插件设计</legend>
		<div class="caption">
		<ol>
			<li>插件原理：读取后台数据生成下拉框append到一个元素中(div、span、p等)</li>
			<li>插件源码：<a href="${googlecode }/src/main/webapp/js/plugin/select/jquery.area2select.js">点击查看</a>，标准的jQuery插件写法</li>
			<li>设计思路：
				<ol>
					<li><b>没有默认值情况</b>：假如地区信息有三级，如：上海-徐汇区-斜土路街道，插件的加载数据方式为依次根据级别读取数据然后
				返回到前台用插件生成一个下拉框，绑定刚刚生成的下拉框的change事件加载下一级数据；下拉框生成后自动触发change事件。</li>
					<li><b>设置了默认值(defaultValue : 地区编号)：</b>例如地区信息：(北京市-东城区-朝阳门街道)对应的编号为(5210-5211-5218)，
						如果要在加载的时候自动设置选中的值，这里设置$('#areaDiv').area({defaultValue : 5218});，则下拉框加载完成后会<b>自动选中</b>(北京市-东城区-朝阳门街道)<br/><br/>
						<b>有</b>默认值的情况和<b>没有</b>默认值的情况加载下拉框列表有些区别，没有默认值的加载方式是<b>逐级</b>发送Ajax请求生成下拉框<br/><br/>
						而有默认值的时候是<b>一次性加载</b>下拉框，用$.load(url)函数，原因是因为需要从最小级别的街道查询上一级的地区信息，如果在前段不容易实现，在后台则不然；
						所以由后台一次性生成3级下拉框并且都自动选中各级的地区信息；<br/>
						PS：其实也可以实现，但是没有则有效率来得高
					</li>
				</ol>
			</li>
		</ol>
		</div>
	</fieldset>
	<fieldset>
		<legend>插件API</legend>
		<ol>
			<li>默认参数：
			<xmp></xmp>
			</li>
		</ol>
	</fieldset>
</body>
</html>