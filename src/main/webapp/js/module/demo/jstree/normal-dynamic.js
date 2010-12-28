$(function(){
	
	$("#subjectTree").jstree({
		"json_data" : {
			"ajax" : {
				"url" : ctx + "/demo/jstree/city.action",
				"data" : function (n) {
					return { parentId : n.attr ? n.attr("id") : -1 }; 
				}
			}
		},
		"plugins" : [ "themes", "json_data", "ui", "crrm", "cookies", "hotkeys" ]
	}).bind('click.jstree', function(event){
		var eventNodeName = event.target.nodeName;
		if (eventNodeName == 'INS') {
			return;
		} else if (eventNodeName == 'A') {
			var $city = $(event.target);
			
			// 点击A展开子节点
			$("#subjectTree").jstree('toggle_node', $city.parent().find('ins').get(0));
			
			if ($city.attr('leaf')) {
				$('#result').text($city.text() + "，ID=" + $city.parent().attr('id'));
			}
		}
	});
	
});