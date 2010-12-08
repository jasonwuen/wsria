/**
 * 数据字典JavaScript 功能：数据字典的列表以及CRUD操作
 * 
 * @author HenryYan
 */
$(function() {
	// 自动根据窗口大小改变数据列表大小
	$.common.plugin.jqGrid.autoResize({
		dataGrid: '#list',
		callback: listDatas
	});
});

var validator;
var moduleAction  = "data-library";

/**
 * 加载列表
 * 
 * @return
 */
function listDatas(size) {
    $("#list").jqGrid(
	$.extend($.common.plugin.jqGrid.settings({size: size}), {
		url: moduleAction + '!list.action',
		colNames: ['主键ID', '数据字典类型', '数据字典名称', '数据字典代码', '数据字典值','有效性', '排序号'],
        colModel: [{
            name: 'id',
            hidden: true,
			editable: true,
			editoptions:{
    			hidden: true
    		}
        }, {
            name: 'libraryType',
            align: 'center',
			editable: true,
            edittype: 'text',
            editoptions: {
	        	size :20,
	            maxlength: 200
            },
            searchoptions : {
    			sopt : $.common.plugin.jqGrid.search.text
    		},
			formoptions: {
            	elmsuffix: $.common.plugin.jqGrid.form.must
            }
        }, {
            name: 'libraryName',
            align: 'center',
			editable: true,
			edittype: 'text',
			formoptions: {
            	elmsuffix: $.common.plugin.jqGrid.form.must
            }
        }, {
            name: 'libraryCode',
            align: 'center',
			editable: true,
            edittype: 'text',
            editoptions: {
                size: 20,
                maxlength: 50
            },
            formoptions: {
            	elmsuffix: $.common.plugin.jqGrid.form.must
            },
            searchoptions : {
    			sopt : $.common.plugin.jqGrid.search.text
    		}
        }, {
            name: 'libraryValue',
            align: 'center',
			editable: true,
			edittype: 'text',
			editoptions: {
	            size: 20,
	            maxlength: 50
	        },
			formoptions: {
            	elmsuffix: $.common.plugin.jqGrid.form.must
            }
        }, {
            name: 'enabled',
            align: 'center',
			editable: true,
			edittype: "select",
			editoptions:{
        		value: {'true' : "是", 'false' : "否"}
        	},
        	stype : 'select',
        	searchoptions : {
        		value : {1 : '是', 0 : '否'}
        	},
			formatter : $.common.plugin.jqGrid.formatter.trueOrfalse
        }, {
            name: 'sort',
            align: 'center',
			editable: true,
			edittype: "text"
        }],
		caption: "数据字典管理",
		editurl: moduleAction + '!save.action',
		grouping: true,
       	groupingView : {
       		groupField : ['libraryName'],
       		groupText : ['<b>{0} - {1} 项</b>']
       	}
	})
	).jqGrid('navGrid', '#pager', $.extend($.common.plugin.jqGrid.pager, {
		
	}), 
	// edit options
    $.extend($.common.plugin.jqGrid.form.edit, {
		width : 450,
		editCaption: '编辑数据字典',
		beforeShowForm: commonBeforeShowForm,
    	beforeSubmit: beforeSubmit
	}),
	
	// add options
    $.extend($.common.plugin.jqGrid.form.add, {
		width : 450,
		addCaption: '添加数据字典',
		beforeShowForm: commonBeforeShowForm,
    	beforeSubmit: beforeSubmit
	}), 
	
    // delete options
    $.extend($.common.plugin.jqGrid.form.remove, {
		url: moduleAction + '!delete.action'
	}),
	
	// search optios
	$.extend($.common.plugin.jqGrid.form.search, {}), 
	
	// view options
	$.extend($.common.plugin.jqGrid.form.view, {
		beforeShowForm: function(formid) {
    		$.common.plugin.jqGrid.navGrid.showAllField(formid);
	    }
	})).jqGrid('navButtonAdd', '#pager', {
		caption: "重载字典",
		title: "重新设置内存数据字典",
	   	buttonicon: "ui-icon-arrowrefresh-1-s",
	   	onClickButton: function(){
			reloadDatalibrary();
	   	}
	});
       
}

/**
 * 表单验证
 * 
 * @return
 */
function validatorForm() {
	validator = $("#FrmGrid_list").validate({
        rules: {
			libraryType: {
				required: true
			},
			libraryName: {
				required: true
			},
			libraryCode: {
				required: true,
				maxlength: 32,
				remote : {
					url: moduleAction + "!validateLibraryCode.action",
					type: "post",
					dataType:"json",
					data: {
						newId : function()	{
							return $("#id").val();
						},
						librarycode: function(){
							return $("#libraryCode").val();
						}
					}
				}
			},
			libraryValue: {
				required: true
			},
			sort: {
				required: true,
				number: true,
				digits: true
			}
		},
		messages:{
			libraryCode : {
				remote:"数据字典代码已存在，请重新输入！"
			}
		},
        errorPlacement: $.common.plugin.validator.error,
        success: $.common.plugin.validator.success
    });
}

function reloadDatalibrary() {
	var reloadDialog = $('<div title="重载数据字典">正在重载数据字典，请稍等……</div>').dialog({
		height: 100,
		modal: true
	});
	$.get('data-library!reload.action', function(resp){
		reloadDialog.dialog('close');
		if (resp == 'success') {
			alert('重载数据字典完毕！');
		} else {
			alert(resp);
		}
	});
}

function commonBeforeShowForm() {
	// 注册表单验证事件
    validatorForm();
	$('.CaptionTD').width(70);
}

function beforeSubmit() {
	var valid = $("#FrmGrid_list").valid();
	return [valid, '表单有 ' + validator.numberOfInvalids() + ' 项错误，请检查！'];
}
