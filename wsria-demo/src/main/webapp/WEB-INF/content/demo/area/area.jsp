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
    .code {border: 1px dotted blue;}
    .result {background-color: white; font-size: 13px; margin-top: 5px;}
    </style>
	<script src="${ctx }/js/common/jquery.min.js" type="text/javascript"></script>
	<script src="${ctx }/js/common/common.js" type="text/javascript"></script>
	<script src="${ctx }/js/plugin/select/jquery.area2select.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(function(){
			// demo1
			$('#areaDiv1').area();
			$('#getAreaId1').click(function(){
				var sv = $('#areaDiv1').getAreaId($('#vt1').val());
				$('<div>文字=' + sv.text + "，value=" + sv.value + "</div>").appendTo('#results1');
			});
			$('#clearResult1').click(function(){
				$('#results1').html('');
			});
			
			// demo2
			$('#areaDiv2').area({
				defaultValue : 5698
			});
			$('#getAreaId2').click(function(){
				var sv = $('#areaDiv2').getAreaId($('#vt2').val());
				$('<div>文字=' + sv.text + "，value=" + sv.value + "</div>").appendTo('#results2');
			});
			$('#clearResult2').click(function(){
				$('#results2').html('');
			});

			// demo3
			$('#areaDiv3').area({
				layer : 2,
				callback : function() {
					$('#results3').append('加载完了' + $('#areaDiv3 select:last option:selected').text() + "<br/>");
				}
			});
			$('#getAreaId3').click(function(){
				var sv = $('#areaDiv3').getAreaId($('#vt3').val());
				$('<div>文字=' + sv.text + "，value=" + sv.value + "</div>").appendTo('#results3');
			});
			$('#clearResult3').click(function(){
				$('#results3').html('');
			});

			// demo4
			$('#areaDiv31').area({
				layer : 2,
				defaultValue: 6579,
				attrs : {
					title : '通过插件设置的title'
				},
				callback : function() {
					$('#results31').append('加载完了');
				}
			});
			$('#getAreaId31').click(function(){
				var sv = $('#areaDiv31').getAreaId($('#vt31').val());
				$('<div>文字=' + sv.text + "，value=" + sv.value + "</div>").appendTo('#results31');
			});
			$('#clearResult31').click(function(){
				$('#results31').html('');
			});

			// demo5
			$('#areaDiv4').area({parentName : '上海市'});
			$('#getAreaId4').click(function(){
				var sv = $('#areaDiv4').getAreaId($('#vt4').val());
				$('<div>文字=' + sv.text + "，value=" + sv.value + "</div>").appendTo('#results4');
			});
			$('#clearResult4').click(function(){
				$('#results4').html('');
			});
			
		});
	</script>
</head>
<body>
	<fieldset style="background-color: #F0FFFF">
		<legend>不设置任何参数</legend>
		<div id="areaDiv1">地区信息：</div>
		<hr/>
		<select id="vt1" multiple="multiple" size="3">
			<option value="1">第一级</option>
			<option value="2">第二级</option>
			<option value="3">第三级</option>
		</select>
		<button id="getAreaId1" type="button">获取选中的ID</button>
		<button id="clearResult1" type="button">清空结果</button>
		<div id="results1" class="result"></div>
		<div class="caption">
			<ol>
				<li>本例全部使用插件默认的参数读取城市列表</li>
				<li><b>代码</b>：
				<div class="code">
				<xmp>HTML：<div id="areaDiv1">地区信息：</div></xmp>
				<b>Javascript</b>：$('#areaDiv1').area();
				</div>
				</li>
			</ol>
		</div>
	</fieldset>
	
	<fieldset style="background-color: #FFEBCD">
		<legend>根据默认值显示</legend>
		<div id="areaDiv2">地区信息：</div>
		<hr/>
		<select id="vt2" multiple="multiple" size="3">
			<option value="1">第一级</option>
			<option value="2">第二级</option>
			<option value="3">第三级</option>
		</select>
		<button id="getAreaId2" type="button">获取选中的ID</button>
		<button id="clearResult2" type="button">清空结果</button>
		<div id="results2" class="result"></div>
		<div class="caption">
			<ol>
				<li>本例根据制定的默认值读取列表</li>
				<li><b>代码</b>：
				<div class="code">
				<xmp>HTML：<div id="areaDiv2">地区信息：</div></xmp>
				<b>Javascript</b>：$('#areaDiv2').area({defaultValue : 5698});
				</div>
				</li>
			</ol>
		</div>
	</fieldset>
	
	<fieldset style="background-color: #FFFACD">
		<legend>只读取两级地区</legend>
		<div id="areaDiv3">地区信息：</div>
		<hr/>
		<select id="vt3" multiple="multiple" size="2">
			<option value="1">第一级</option>
			<option value="2">第二级</option>
		</select>
		<button id="getAreaId3" type="button">获取选中的ID</button>
		<button id="clearResult3" type="button">清空结果</button>
		<div id="results3" class="result"></div>
		<div class="caption">
			<ol>
				<li>本例根据制定的默认值读取列表</li>
				<li><b>代码</b>：
				<div class="code">
				<xmp>HTML：<div id="areaDiv3">地区信息：</div></xmp>
				<b>Javascript</b>：$('#areaDiv3').area({layer : 2});
				</div>
				</li>
			</ol>
		</div>
	</fieldset>
	
	<fieldset style="background-color: #F0E68C">
		<legend>只读取两级地区 With 默认值 And 属性集合</legend>
		<div id="areaDiv31">地区信息：</div>
		<hr/>
		<select id="vt31" multiple="multiple" size="2">
			<option value="1">第一级</option>
			<option value="2">第二级</option>
		</select>
		<button id="getAreaId31" type="button">获取选中的ID</button>
		<button id="clearResult31" type="button">清空结果</button>
		<div id="results31" class="result"></div>
		<div class="caption">
			<ol>
				<li>本例根据制定的默认值读取列表</li>
				<li><b>代码</b>：
				<div class="code">
				<xmp>HTML：<div id="areaDiv31">地区信息：</div></xmp>
				<b>Javascript</b>：$('#areaDiv31').area({layer : 2});
				</div>
				</li>
			</ol>
		</div>
	</fieldset>
	
	<fieldset style="background-color: #FFEBCD">
		<legend>显示上海市下面的地区</legend>
		<div id="areaDiv4">地区信息：</div>
		<hr/>
		<select id="vt4" multiple="multiple" size="2">
			<option value="1">第一级</option>
			<option value="2">第二级</option>
		</select>
		<button id="getAreaId4" type="button">获取选中的ID</button>
		<button id="clearResult4" type="button">清空结果</button>
		<div id="results4" class="result"></div>
		<div class="caption">
			<ol>
				<li>本例根据制定的默认值读取列表</li>
				<li><b>代码</b>：
				<div class="code">
				<xmp>HTML：<div id="areaDiv4">地区信息：</div></xmp>
				<b>Javascript</b>：$('#areaDiv4').area({parentName : '上海市'});
				</div>
				</li>
			</ol>
		</div>
	</fieldset>
</body>
</html>