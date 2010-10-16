/**
 * 
 * @author HenryYan
 */
(function($) {
	
	//-- 初始化方法 --//
	_initFunction();
	
	// -- 公共方法 --//
	// 调用方式：$.common.plugin.jqGrid
	$.common = {
		// 插件默认值
		plugin : {
			// jqGrid默认参数
			jqGrid : {
				prmNames : {
					page : 'page.pageNo',
					rows : 'page.pageSize',
					sort : 'page.orderBy',
					order : 'page.order',
					search : 'page.search',
					id : 'jqid'
				},
				jsonReader : {
					root : 'page.result',
					page : 'page.pageNo',
					total : 'page.totalPages',
					records : 'page.totalCount',
					repeatitems : false
				},
				navGrid : {
					showAllField : function(formid) {
						$('tr[id^=tr].FormData', formid).show();
					}
				},
				//-- jqGrid工具栏按钮 --//
				navButtonAdd : {
					//-- 显示/隐藏字段 --//
					setColumns : {
						caption : "字段",
						title : "设置列表显示的字段",
						buttonicon : "ui-icon-wrench",
						onClickButton : function() {
							$(this).jqGrid('columnChooser');
						}
					}
				},
				//-- 搜索比较符号 --/
				search: {
					text : ['eq', 'ne', 'cn'],
					select : ['eq', 'ne'],
					integer : ['eq', 'ne', 'lt', 'le', 'gt', 'ge'],
					float : ['eq', 'ne', 'lt', 'le', 'gt', 'ge'],
					date : ['eq', 'ne', 'lt', 'le', 'gt', 'ge']
				},
				//-- 格式化 值--//
				fomatter:{
					// 日期类型，例如：2010-08-19
					date: function(cellvalue, options, cellobject){
						if( cellvalue == null || cellvalue == 'null' ) return "";
						else{
							if(cellvalue.length >= 10){
								return cellvalue.substring(0,10);
							} else { 
								return cellvalue;
							}
						}
					},
					// 日期和时间类型，例如：2010-08-19 12:12:13
					datetime: function(cellvalue, options, cellobject){
						if (cellvalue == null || cellvalue == 'null'){ 
							return "";
						} else {
							var preCellValue = cellvalue.substring(0,10);
							var postCellvalue = cellvalue.substring(11,cellvalue.length);
							return preCellValue + " " + postCellvalue;
						}
					},
					
					trueOrfalse:function(cellvalue, options, cellobject){
						if(cellvalue == null
								|| cellvalue == 'null'
								|| cellvalue == 0) return "否";
						return "是";
					},
					float2precent:function(cellvalue, options, cellobject){
						if(cellvalue == null || cellvalue == 'null') return "";
						return cellvalue * 100 + '%';
					}
				},
				form:{
					// 表单必填标志
					must: function(){
						return "<span class='must'>*</span>";
					}
				},
				/**
				 * 改变窗口大小的时候自动根据iframe大小设置jqGrid列表宽度和高度
				 * 参数说明：{
				 * 		dataGrid : jqGrid数据列表的ID
				 * 		callback : 计算完dataGrid需要的高度和宽度后的回调函数
				 * 		width : 默认为iframe的宽度，如果指定则设置为指定的宽度
				 * 		height : 默认为iframe的高度，如果指定则设置为指定的高度
				 * }
				 */
				autoResize: function(options) {
					// 第一次调用
					var size = getWidthAndHeigh();
					if ($.isFunction(options.callback)) {
						options.callback(size);
					}
					
					// 窗口大小改变的时候
					window.onresize = function() {
						var size = getWidthAndHeigh(true);
						$(options.dataGrid).jqGrid('setGridHeight', size.height).jqGrid('setGridWidth', size.width);
					};
					
					// 获取iframe大小
					function getWidthAndHeigh(resize) {
						
						var hasToolbar = !options.toolbar ? false : options.toolbar[0];
						if (hasToolbar) {
							var toolbarType = options.toolbar[1];
							if (!toolbarType) {
								alert('请设置工具栏的属性，toolbar ： [true, [top, both]]');
							}
						}
						
						//alert(resize);
						var iframeHeight = !options.height ? document.body.clientHeight : options.height;
						var iframeWidth = !options.width ? document.body.clientWidth : options.width;
						// chrome
						if ($.common.browser.isChrome()) {
							if (hasToolbar) {
								if (toolbarType == 'top') {
									iframeWidth -= 13;
									iframeHeight -= 108;
								} else if (toolbarType == 'both') {
									iframeWidth -= 14;
									iframeHeight -= 140;
								}
							} else {
								iframeWidth -= 16;
								iframeHeight -= 85;
							}
						}
						// firefox
						else if ($.common.browser.isMozila()) {
							if (hasToolbar) {
								if (toolbarType == 'top') {
									iframeWidth -= 13;
									iframeHeight -= 117;
								} else if (toolbarType == 'both') {
									iframeWidth -= 14;
									iframeHeight -= 140;
								}
							} else {
								iframeWidth -= 15;
								iframeHeight -= 85;
							}
						} 
						// IE
						else {
							if (hasToolbar) {
								if (toolbarType == 'top') {
									iframeWidth -= 13;
									iframeHeight -= 108;
								} else if (toolbarType == 'both') {
									iframeWidth -= 14;
									iframeHeight -= 138;
								}
							} else {
								iframeWidth -= 12;
								iframeHeight -= 83;
							}
						}
						return {width: iframeWidth, height: iframeHeight};
					}
					
				},
				// jqGrid快捷键支持
				keys: {
					savekey: [true, 13],
					navkeys: [true, 38,40]
				}
			},
			validator : {
				// 错误信息显示位置
				error : function(error, element) {
		            if (element.is(":radio")) {
		                error.appendTo(element.parent());
		            } else if (element.is(":checkbox")) {
		                error.appendTo(element.parent());
		            } else {
		                error.appendTo(element.parent());
		            }
		        },
		        success : function(label) {
		            label.html("&nbsp;").addClass("checked");
					var forEle = label.attr('for');
					if (forEle == 'phone') {
						if ($.isFunction(callback)) {
							callback();
						}
					}
		        }
			},
			// jQuery UI
			jqui : {
				button: {
					onOff : function(options) {
						var defaults = {
							btnText : false // text
						};
						options = $.extend({}, defaults, options);
						var dlgButton = $('.ui-dialog-buttonpane button');
						if (options.btnText) {
							// TODO 查询优化，兼容有相同文字的情况
							dlgButton = $('.ui-button-text:contains(' + options.btnText + ')').parent();
						}
					    if (options.enable) {
					        dlgButton.attr('disabled', '');
					        dlgButton.removeClass('ui-state-disabled');
					    } else {
					        dlgButton.attr('disabled', 'disabled');
					        dlgButton.addClass('ui-state-disabled');
					    }
					}
				}
			}
			
			// 插件功能结束
		},
		
		//-- 窗口工具 --//
		window: {
			//-- 获得最上层的window对象 --//
			getTopWin: function() {
				if(parent) {
					var tempParent = parent;
					while(true) {
						if(tempParent.parent) {
							if(tempParent.parent == tempParent) {
								break;
							}
							tempParent = tempParent.parent;
						} else {
							break;
						}
					}
					return tempParent;
				} else {
					return window;
				}
			}
		},
		
		//-- 和系统有关的函数 --//
		system : {
			
		},
		
		//-- 浏览器工具 --//
		browser : {
			// 检测是否是IE浏览器
			isIE : function() {
				var _uaMatch = $.uaMatch(navigator.userAgent);
				var _browser = _uaMatch.browser;
				if (_browser == 'msie') {
					return true;
				} else {
					return false;
				}
			},
			// 检测是否是chrome浏览器
			isChrome : function() {
				var _uaMatch = $.uaMatch(navigator.userAgent);
				var _browser = _uaMatch.browser;
				if (_browser == 'webkit') {
					return true;
				} else {
					return false;
				}
			},
			// 检测是否是Firefox浏览器
			isMozila : function() {
				var _uaMatch = $.uaMatch(navigator.userAgent);
				var _browser = _uaMatch.browser;
				if (_browser == 'mozilla') {
					return true;
				} else {
					return false;
				}
			}
		},
		
		//-- 文件相关 --//
		file : {
			/**
			 * 下载文件 
			 * @fileName	相对于Web根路径
			 */
			download : function(fileName){
				var downUrl = $.common.custom.getCtx() + '/file/download.action?fileName=' + fileName;
	    		open(encodeURI(encodeURI(downUrl)));
			}
		},
		
		//-- 数学工具 --//
		math : {
			/*
			 * 四舍五入
			 */
			round : function(dight, how) {
				return dight.toFixed(how);
			}
		},
		
		//-- 未分类 --//
		custom: {
			// 得到应用名
			getCtx : function() {
				var url = location.pathname;
				var contextPath = url.substr(0, url.indexOf('/', 1));
				return contextPath;
			},
			getLoadingImg : function() {
				return '<img src="' + $.common.custom.getCtx() + '/images/ajax/loading.gif" align="absmiddle"/>&nbsp;';
			},
			/**
			 * 创建小时下拉框
			 */
			createHourSelect : function(selectId, defaultValue) {
				var hours = new StringBuffer();
				var tempValue = "";
				for(var i = 0; i < 24; i++) {
					if(i < 10) {
						tempValue = "0" + i;
					} else {
						tempValue = i;
					}
					hours.append("<option value='" + tempValue + "'" + (defaultValue == tempValue ? " selected" : "") + ">" + tempValue + "</option>");
				}
				$(selectId).append(hours.toString());
			},
			/**
			 * 创建分钟下拉框
			 */
			createMinuteSelect : function(selectId, defaultValue) {
				var hours = new StringBuffer();
				var tempValue = "";
				for(var i = 0; i < 60; i++) {
					if(i < 10) {
						tempValue = "0" + i;
					} else {
						tempValue = i;
					}
					hours.append("<option value='" + tempValue + "'" + (defaultValue == tempValue ? " selected" : "") + ">" + tempValue + "</option>");
				}
				$(selectId).append(hours.toString());
			},
			
			/**
			 * 日期增加年数或月数或天数
			 * @param {String} BaseDate	要增加的日期
			 * @param {Object} interval	增加数量
			 * @param {Object} DatePart	增加哪一部分
			 * @param {String} returnType 返回类型strunt|date
			 */
			dateAdd : function(BaseDate, interval, DatePart, returnType) {
			    var dateObj;
				if(typeof BaseDate == 'object') {
					dateObj = BaseDate;
				} else {
					var strDs = BaseDate.split('-');
					var year = parseInt(strDs[0]);
					var month = parseInt(strDs[1]);
					var date = parseInt(strDs[2]);
					dateObj = new Date(year, month, date);
				}
				returnType = returnType || 'string';
			    var millisecond = 1;
			    var second = millisecond * 1000;
			    var minute = second * 60;
			    var hour = minute * 60;
			    var day = hour * 24;
			    var year = day * 365;

			    var newDate;
			    var dVal = new Date(dateObj);
			    var dVal = dVal.valueOf();
			    switch (DatePart) {
			        case "ms":
			            newDate = new Date(dVal + millisecond * interval);
			            break;
			        case "s":
			            newDate = new Date(dVal + second * interval);
			            break;
			        case "mi":
			            newDate = new Date(dVal + minute * interval);
			            break;
			        case "h":
			            newDate = new Date(dVal + hour * interval);
			            break;
			        case "d":
			            newDate = new Date(dVal + day * interval);
			            break;
			        case "y":
			            newDate = new Date(dVal + year * interval);
			            break;
			        default:
			            return escape("日期格式不对");
			    }
			    newDate = new Date(newDate);
				if (returnType == 'string') {
				    return newDate.getFullYear() + "-" + (newDate.getMonth() + 1) + "-" + newDate.getDate();
				} else if (returnType == 'date') {
					return newDate;
				}
			}
		}
		
	};
	
	//-- 表单自定义功能 --//
	$.form = {
			
		// 绑定form的ajax提交功能
		bindAjaxSubmit : function(settings) {
		
			var defaults = {
				formId : '',
		        beforeSubmit : showRequest,
		        success : showResponse,
		        clearForm : true
		    };
			
			settings = $.extend({}, defaults, settings);
			
			$('#' + settings.formId).submit(function() {
		        $(this).ajaxSubmit(settings);
		        return false;
		    });
			
		},
		
		// 表示层设置
		ui : {
			// 红色星号
			required : function() {
				return $.common.plugin.jqGrid.form.must();
			}
		}
	};
	
	//-- 列表自定义功能 --//
	$.list = {
		// 全选功能
		selectAll : function() {
			$('#selAll').data('chkState', true);
			$('#selAll').bind("click", function() {
				if ($(this).data('chkState')) {
					$('#row :checkbox:not(:eq(0))').attr('checked', true).parent().parent().addClass('row_active');
					$(this).data('chkState', false);
					$(this).attr("title", "撤销全选记录");
				} else {
					$('#row :checkbox:not(:eq(0))').attr('checked', false).parent().parent().removeClass('row_active');
					$(this).data('chkState', true);
					$(this).attr("title", "全选记录");
				}
			});
		},
		// 选择一行
		selectRow : function() {
			$('#row tr td').each(function() {
				if ($(this).attr('class') != 'noclick')
					$(this).unbind("click").bind('click', 
						function() {
							$.list.selectColumn(this);
						}
					);
			});
		},
		selectColumn : function(obj) {
			var tdObj = $(obj).parent().children().get(0);
			var chkObj = $(tdObj).children().get(0);
			if ($(obj).parent().hasClass('row_active')) {
				$(chkObj).attr("checked", false);
				$(obj).parent().removeClass('row_active');
			} else {
				if ($(chkObj).attr("type") == 'radio')
					$('.row_active').removeClass('row_active');

				$(chkObj).attr("checked", true);
				$(obj).parent().addClass('row_active');
			}
		}
	};

	// -- 自定义插件 --//

	/**
	 * 插件名称：cursorInsert（光标处插入） 功能：可以在文本框的
	 */
	$.fn.cursorInsert = function(options) {

		// default settings
		var settings = {
			content : ''
		};

		if (options) {
			$.extend(settings, options);
		}

		return this.each(function() {
			var obj = $(this).get(0);
			if (document.selection) {
				obj.focus();
				var sel = document.selection.createRange();
				document.selection.empty();
				sel.text = options.content;
			} else {
				var prefix, main, suffix;
				prefix = obj.value.substring(0, obj.selectionStart);
				main = obj.value
						.substring(obj.selectionStart, obj.selectionEnd);
				suffix = obj.value.substring(obj.selectionEnd);
				obj.value = prefix + options.content + suffix;
			}
			obj.focus();
		});
	};
	
	/**
	 * 动态加载JavaScript
	 */
	$.loadScript = function(options) {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = options.src;
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	};

})(jQuery);

//-- 自定义函数 --//
function _initFunction() {
	
	// 全局ajax设置
	$.ajaxSetup({
		cache : false,
		global : true,
		complete: function(req, status) {
			
		},
		error: function(req, status) {
			var reqText = req.responseText;
			if(reqText == 'login') {
				return;
			}
			if(reqText == 'error') {
				alert('操作失败！');
			} else if (reqText != ''){
				alert(reqText);
			}
		}
	});
	
}

//-- Javascript对象扩展 -//
/**
 * 去掉开头、结尾的空格
 *
 * @return {}
 */
String.prototype.trim = function() {
	return this.replace(/(^\s+)|\s+$/g, "");
};

String.prototype.toJson = function() {
	return eval('(' + this + ')');
};

/**
 * 输出2010-02-05格式的日期字符串
 *
 * @return {}
 */
Date.prototype.toDateStr = function() {
	return ($.common.browser.isMozila() || $.common.browser.isChrome() ? (this.getYear() + 1900) : this.getYear()) + "-"
			+ (this.getMonth() < 10 ? "0" + this.getMonth() : this.getMonth())
			+ "-" + (this.getDate() < 10 ? "0" + this.getDate() : this.getDate());
};

//+---------------------------------------------------
//| 日期计算
//+---------------------------------------------------
Date.prototype.DateAdd = function(strInterval, Number) {
    var dtTmp = this;
    switch (strInterval) {
        case 's' :return new Date(Date.parse(dtTmp) + (1000 * Number));
        case 'n' :return new Date(Date.parse(dtTmp) + (60000 * Number));
        case 'h' :return new Date(Date.parse(dtTmp) + (3600000 * Number));
        case 'd' :return new Date(Date.parse(dtTmp) + (86400000 * Number));
        case 'w' :return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
        case 'q' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number*3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'm' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
        case 'y' :return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
    }
};

//-- 自定义类-开始 --/
function StringBuffer() {
	this._strings_ = new Array();
}

StringBuffer.prototype.append = function(str) {
	this._strings_.push(str);
	return this;
};

StringBuffer.prototype.toString = function() {
	return this._strings_.join("").trim();
};

function Map() {
    var struct = function(key, value) {
        this.key = key;
        this.value = value;
    };

    var put = function(key, value) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                this.arr[i].value = value;
                return;
            }
        }
        this.arr[this.arr.length] = new struct(key, value);
		this._keys[this._keys.length] = key;
    };

    var get = function(key) {
        for (var i = 0; i < this.arr.length; i++) {
            if (this.arr[i].key === key) {
                return this.arr[i].value;
            }
        }
        return null;
    };

    var remove = function(key) {
        var v;
        for (var i = 0; i < this.arr.length; i++) {
            v = this.arr.pop();
            if (v.key === key) {
                continue;
            }
            this.arr.unshift(v);
			this._keys.unshift(v);
        }
    };

    var size = function() {
        return this.arr.length;
    };

	var keys = function() {
        return this._keys;
    };

    var isEmpty = function() {
        return this.arr.length <= 0;
    };

    this.arr = new Array();
	this._keys = new Array();
	this.keys = keys;
    this.get = get;
    this.put = put;
    this.remove = remove;
    this.size = size;
    this.isEmpty = isEmpty;
}

//-- 自定义类-结束 --/

//-- Javascript工具 --//
