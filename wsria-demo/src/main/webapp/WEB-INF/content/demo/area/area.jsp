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
	<script src="${ctx }/js/common/jquery.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/plugin/select/jquery.area2select.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			$('#areaDiv1').area();
			$('#getAreaId1').click(function(){
				var sv = $('#areaDiv1').getAreaId($('#vt1').val());
				$('<div>文字=' + sv.text + "，value=" + sv.value + "</div>").appendTo('#results1');
			});
			$('#clearResult1').click(function(){
				$('#results1').html('');
			});
		});
	</script>
</head>
<body>
	<fieldset>
		<legend>使用插件默认值</legend>
		<div id="areaDiv1">地区信息：</div>
		<hr/>
		<select id="vt1" multiple="multiple" size="3">
			<option value="1">第一级</option>
			<option value="2">第二级</option>
			<option value="3">第三级</option>
		</select>
		<button id="getAreaId1" type="button">获取选中的ID</button>
		<button id="clearResult1" type="button">清空结果</button>
		<div id="results1"></div>
		<div class="caption">
			<ol>
				<li>本例全部使用插件默认的参数读取城市列表</li>
				<li></li>
			</ol>
		</div>
	</fieldset>
</body>
</html>