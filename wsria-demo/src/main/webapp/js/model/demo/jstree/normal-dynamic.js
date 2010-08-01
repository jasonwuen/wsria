$(function(){
	
	$("#subjectTree").jstree({
		"json_data" : {
			"ajax" : {
				"url" : "/wsria-demo/demo/jstree/city.action",
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
			if ($city.attr('leaf')) {
				$('#result').text($city.text());
			}
		}
	});
	
});