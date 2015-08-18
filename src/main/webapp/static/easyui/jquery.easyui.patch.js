/**
 * The Patch for jQuery EasyUI 1.4.1
 */
(function($){
	function setMe(target){
		var opts = $(target).textbox('options');
		var tb = $(target).next();
		tb.removeClass('textbox-disabled textbox-readonly');
		if (opts.readonly){tb.addClass('textbox-readonly')}
		if (opts.disabled){tb.addClass('textbox-disabled')}
	}

	var plugin = $.fn.textbox;
	$.fn.textbox = function(options, param){
		if (typeof options != 'string'){
			return this.each(function(){
				plugin.call($(this), options, param);
				setMe(this);
			})
		} else {
			return plugin.call(this, options, param);
		}
	};
	$.fn.textbox.methods = plugin.methods;
	$.fn.textbox.defaults = plugin.defaults;
	$.fn.textbox.parseOptions = plugin.parseOptions;

	var enable = $.fn.textbox.methods.enable;
	$.fn.textbox.methods.enable = function(jq){
		return jq.each(function(){
			enable.call($.fn.textbox.methods, $(this));
			$(this).next().removeClass('textbox-disabled');
		});
		return enable.call($.fn.textbox.methods, jq);
	};
	var disable = $.fn.textbox.methods.disable;
	$.fn.textbox.methods.disable = function(jq){
		return jq.each(function(){
			disable.call($.fn.textbox.methods, $(this));
			$(this).next().addClass('textbox-disabled');
		});
	};
	var readonly = $.fn.textbox.methods.readonly;
	$.fn.textbox.methods.readonly = function(jq, mode){
		return jq.each(function(){
			readonly.call($.fn.textbox.methods, $(this), mode);
			var tb = $(this).next();
			tb.removeClass('textbox-readonly');
			if ($(this).textbox('options').readonly){
				tb.addClass('textbox-readonly');
			}
		});
	}
	$.extend($.fn.textbox.methods, {
		setValue: function(jq, value){
			return jq.each(function(){
				var opts = $.data(this, 'textbox').options;
				var oldValue = $(this).textbox('getValue');
				$(this).textbox('initValue', value);
				if (oldValue != value){
					opts.onChange.call(this, value, oldValue);
					$(this).closest('form').trigger('_change', [this]);
				}
			});
		}
	});
})(jQuery);

(function($){
	$(function(){
		$(document).unbind(".combo").bind("mousedown.combo mousewheel.combo", function(e) {
			var p = $(e.target).closest("span.combo,div.combo-p,div.menu");
			if (p.length) {
				p.find('.combo-f').each(function(){
					var p1 = $(this).combo('panel');
					if (p1.is(':visible')){
						p1.panel('close');
					}
				});
				return;
			}
			$("body>div.combo-p>div.combo-panel:visible").panel("close");
		});		
	});

	function setMe(target){
		var p = $(target).combo('panel');
		p.panel('options').onClose = function(){
			var target = $(this).panel('options').comboTarget;
			var state = $(target).data('combo');
			if (state){
				state.options.onHidePanel.call(target);
			}
		}
	}
	var plugin = $.fn.combo;
	$.fn.combo = function(options, param){
		if (typeof options != 'string'){
			return this.each(function(){
				plugin.call($(this), options, param);
				setMe(this);
			});
		} else {
			return plugin.call(this, options, param);
		}
	};
	$.fn.combo.methods = plugin.methods;
	$.fn.combo.defaults = plugin.defaults;
	$.fn.combo.parseOptions = plugin.parseOptions;

	$.extend($.fn.combo.methods, {
		setValue: function(jq, value){
			return jq.each(function(){
				$(this).combo('setValues', [value]);
			})
		},
		setValues: function(jq, values){
			return jq.each(function(){
				var target = this;
				var state = $.data(target, 'combo');
				var opts = state.options;
				var combo = state.combo;
				if (!$.isArray(values)){values = values.split(opts.separator)}
				
				var oldValues = $(target).combo('getValues');
				combo.find('.textbox-value').remove();
				var name = $(target).attr('textboxName') || '';
				for(var i=0; i<values.length; i++){
					var input = $('<input type="hidden" class="textbox-value">').appendTo(combo);
					input.attr('name', name);
					if (opts.disabled){
						input.attr('disabled', 'disabled');
					}
					input.val(values[i]);
				}
				
				var changed = (function(){
					if (oldValues.length != values.length){return true;}
					var a1 = $.extend(true, [], oldValues);
					var a2 = $.extend(true, [], values);
					a1.sort();
					a2.sort();
					for(var i=0; i<a1.length; i++){
						if (a1[i] != a2[i]){return true;}
					}
					return false;
				})();
				
				if (changed){
					if (opts.multiple){
						opts.onChange.call(target, values, oldValues);
					} else {
						opts.onChange.call(target, values[0], oldValues[0]);
					}
					$(this).closest('form').trigger('_change', [this]);
				}

			})
		}
	})

})(jQuery);

(function($){
	function setValues(target, values, remainText){
		var opts = $.data(target, 'combobox').options;
		var panel = $(target).combo('panel');
		
		if (!$.isArray(values)){values = values.split(opts.separator)}
		panel.find('div.combobox-item-selected').removeClass('combobox-item-selected');
		var vv = [], ss = [];
		for(var i=0; i<values.length; i++){
			var v = values[i];
			var s = v;
			opts.finder.getEl(target, v).addClass('combobox-item-selected');
			var row = opts.finder.getRow(target, v);
			if (row){
				s = row[opts.textField];
			}
			vv.push(v);
			ss.push(s);
		}
		
		$(target).combo('setValues', vv);
		if (!remainText){
			$(target).combo('setText', ss.join(opts.separator));
		}
	}

	var query = $.fn.combobox.defaults.keyHandler.query;
	$.fn.combobox.defaults.keyHandler.query = function(q){
		query.call(this, q);
		var opts = $(this).combobox('options');
		var vv = $(this).combobox('getValues');
		if (!vv.length){
			if (opts.multiple && !q){
				setValues(this, [], true);
			} else {
				setValues(this, [q], true);
			}
		}
	}
})(jQuery);

(function($){
	function setMe(target){
		var opts = $(target).combogrid('options');
		var onShowPanel = opts.onShowPanel;
		$(target).combogrid('options').onShowPanel = $(target).combo('options').onShowPanel = function(){
			var p = $(this).combogrid('panel');
			var distance = p.outerHeight() - p.height();
			var minHeight = p._size('minHeight');
			var maxHeight = p._size('maxHeight');
			var dg = $(this).combogrid('grid');
			dg.datagrid('resize', {
				width: '100%',
				height: (isNaN(parseInt(opts.panelHeight)) ? 'auto' : '100%'),
				minHeight: (minHeight ? minHeight-distance : ''),
				maxHeight: (maxHeight ? maxHeight-distance : '')
			});
			var row = dg.datagrid('getSelected');
			if (row){
				dg.datagrid('scrollTo', dg.datagrid('getRowIndex', row));
			}
			onShowPanel.call(this);
		}
	}

	var plugin = $.fn.combogrid;
	$.fn.combogrid = function(options, param){
		if (typeof options != 'string'){
			return this.each(function(){
				plugin.call($(this), options, param);
				setMe(this);
			});
		} else {
			return plugin.call(this, options, param);
		}
	};
	$.fn.combogrid.methods = plugin.methods;
	$.fn.combogrid.defaults = plugin.defaults;
	$.fn.combogrid.parseOptions = plugin.parseOptions;
})(jQuery);

(function($){
	var FILE_INDEX = 0;
	function buildFileBox(target){
		var state = $.data(target, 'filebox');
		var opts = state.options;
		var id = 'filebox_file_id_' + (++FILE_INDEX);
		$(target).addClass('filebox-f').textbox(opts);
		$(target).textbox('textbox').attr('readonly','readonly');
		state.filebox = $(target).next().addClass('filebox');
		state.filebox.find('.textbox-value').remove();
		opts.oldValue = "";
		var file = $('<input type="file" class="textbox-value">').appendTo(state.filebox);
		file.attr('id', id).attr('name', $(target).attr('textboxName')||'');
		file.change(function(){
			$(target).filebox('setText', this.value);
			opts.onChange.call(target, this.value, opts.oldValue);
			opts.oldValue = this.value;
		});
		var btn = $(target).filebox('button');
		if (btn.length){
			$('<label class="filebox-label" for="'+id+'"></label>').appendTo(btn);
			if (btn.linkbutton('options').disabled){
				file.attr('disabled', 'disabled');
			} else {
				file.removeAttr('disabled');
			}			
		}
	}
	function initCss(){
		if (!$('#easyui-filebox-style').length){
			$('head').append(
				'<style id="easyui-filebox-style">' +
				'.filebox-label{display:inline-block;position:absolute;cursor:pointer;left:0;top:0;width:100%;height:100%;z-index:10;}' +
				'.l-btn-disabled .filebox-label{cursor:default;}' +
				'</style>'
			);
		}
	}
	
	initCss();
	
	var plugin = $.fn.filebox;
	$.fn.filebox = function(options, param){
		if (typeof options != 'string'){
			return this.each(function(){
				plugin.call($(this), options, param);
				buildFileBox(this);
			})
		} else {
			return plugin.call(this, options, param);
		}
	};
	$.fn.filebox.methods = plugin.methods;
	$.fn.filebox.defaults = plugin.defaults;
	$.fn.filebox.parseOptions = plugin.parseOptions;
	
})(jQuery);

(function($){
	var plugin = $.fn.form;
	$.fn.form = function(options, param){
		if (typeof options == 'string'){
			return plugin.call(this, options, param);
		} else {
			return this.each(function(){
				plugin.call($(this), options, param);
				var opts = $(this).form('options');
				$(this).unbind('.form').bind('change.form', function(e){
					if (!$(e.target).hasClass('textbox-text')){
						if (opts.onChange){
							opts.onChange.call(this, e.target);
						}
					}
				}).bind('_change.form', function(e, target){
					if (opts.onChange){
						opts.onChange.call(this, target);
					}
				})
			});
		}
	};
	$.fn.form.methods = plugin.methods;
	$.fn.form.defaults = plugin.defaults;
	$.fn.form.parseOptions = plugin.parseOptions;
	
	function load(target, data){
		var opts = $.data(target, 'form').options;
		
		if (typeof data == 'string'){
			var param = {};
			if (opts.onBeforeLoad.call(target, param) == false) return;
			
			$.ajax({
				url: data,
				data: param,
				dataType: 'json',
				success: function(data){
					_load(data);
				},
				error: function(){
					opts.onLoadError.apply(target, arguments);
				}
			});
		} else {
			_load(data);
		}
		
		function _load(data){
			var form = $(target);
			for(var name in data){
				var val = data[name];
				if (!_checkField(name, val)){
					if (!_loadBox(name, val)){
						form.find('input[name="'+name+'"]').val(val);
						form.find('textarea[name="'+name+'"]').val(val);
						form.find('select[name="'+name+'"]').val(val);
					}
				}
			}
			opts.onLoadSuccess.call(target, data);
			form.form('validate');
		}
		
		/**
		 * check the checkbox and radio fields
		 */
		function _checkField(name, val){
			var cc = $(target).find('input[name="'+name+'"][type=radio], input[name="'+name+'"][type=checkbox]');
			if (cc.length){
				cc._propAttr('checked', false);
				cc.each(function(){
					var f = $(this);
					if (f.val() == String(val) || $.inArray(f.val(), $.isArray(val)?val:[val]) >= 0){
						f._propAttr('checked', true);
					}
				});
				return true;
			}
			return false;
		}
		
		function _loadBox(name, val){
			var field = $(target).find('[textboxName="'+name+'"],[sliderName="'+name+'"]');
			if (field.length){
				for(var i=0; i<opts.fieldTypes.length; i++){
					var type = opts.fieldTypes[i];
					var state = field.data(type);
					if (state){
						if (state.options.multiple || state.options.range){
							field[type]('setValues', val);
						} else {
							field[type]('setValue', val);
						}
						return true;
					}
				}
			}
			return false;
		}
	}
	$.extend($.fn.form.defaults, {
		fieldTypes: ['combobox','combotree','combogrid','datetimebox','datebox','combo',
		        'datetimespinner','timespinner','numberspinner','spinner',
		        'slider','searchbox','numberbox','textbox']
	});
	$.extend($.fn.form.methods, {
		load: function(jq, data){
			return jq.each(function(){
				load(this, data);
			});
		}
	});
})(jQuery);

(function($){
	function setBodySize(target){
		var state = $.data(target, 'datagrid');
		var opts = state.options;
		var dc = state.dc;
		var wrap = state.panel;
		var innerWidth = wrap.width();
		var innerHeight = wrap.height();
		
		var view = dc.view;
		var view1 = dc.view1;
		var view2 = dc.view2;
		var header1 = view1.children('div.datagrid-header');
		var header2 = view2.children('div.datagrid-header');
		var table1 = header1.find('table');
		var table2 = header2.find('table');
		
		// set view width
		view.width(innerWidth);
		var headerInner = header1.children('div.datagrid-header-inner').show();
		view1.width(headerInner.find('table').width());
		if (!opts.showHeader) headerInner.hide();
		view2.width(innerWidth - view1._outerWidth());
		view1.children('div.datagrid-header,div.datagrid-body,div.datagrid-footer').width(view1.width());
		view2.children('div.datagrid-header,div.datagrid-body,div.datagrid-footer').width(view2.width());
		
		// set header height
		var hh;
		header1.add(header2).css('height', '');
		table1.add(table2).css('height', '');
		hh = Math.max(table1.height(), table2.height());
		table1.add(table2).height(hh);
		header1.add(header2)._outerHeight(hh);
		
		// set body height
		dc.body1.add(dc.body2).children('table.datagrid-btable-frozen').css({
			position: 'absolute',
			top: dc.header2._outerHeight()
		});
		var frozenHeight = dc.body2.children('table.datagrid-btable-frozen')._outerHeight();
		var fixedHeight = frozenHeight
				+ view2.children('div.datagrid-header')._outerHeight()
				+ view2.children('div.datagrid-footer')._outerHeight()
				+ wrap.children('div.datagrid-toolbar')._outerHeight();
		wrap.children('div.datagrid-pager').each(function(){
			fixedHeight += $(this)._outerHeight();
		});
		var distance = wrap.outerHeight() - wrap.height();
		var minHeight = wrap._size('minHeight') || '';
		var maxHeight = wrap._size('maxHeight') || '';
		view1.add(view2).children('div.datagrid-body').css({
			marginTop: frozenHeight,
			height: (isNaN(parseInt(opts.height)) ? '' : (innerHeight-fixedHeight)),
			minHeight: (minHeight ? minHeight-distance-fixedHeight : ''),
			maxHeight: (maxHeight ? maxHeight-distance-fixedHeight : '')
		});
		
		view.height(view2.height());
	}
	function setMe(target){
		var state = $.data(target, 'datagrid');
		var opts = state.options;
		var panel = state.panel;
		panel.panel('options').onResize = function(width, height){
			setBodySize(target);
			$(target).datagrid('fitColumns');
			opts.onResize.call(panel, width, height);
		}
		
		var dc = state.dc;
		var header = dc.header1.add(dc.header2);
		header.find('div.datagrid-cell').each(function(){
			var ropts = $(this).resizable('options');
			var onStopResize = ropts.onStopResize;
			ropts.onStopResize = function(e){
				setGroupWidth();
				onStopResize.call(this, e);
				setGroupWidth(true);
			}
		});
		setGroupWidth();
		setTimeout(function(){
			setGroupWidth(true);
		}, 0);
		
		function setGroupWidth(fit){
			var groups = header.find('.datagrid-cell-group');
			if (groups.length){
				groups.each(function(){
					$(this)._outerWidth(fit ? $(this).parent().width() : 10);
				});
				if (fit){
					setBodySize(target);
				}
			}
		}
	}

	var plugin = $.fn.datagrid;
	$.fn.datagrid = function(options, param){
		if (typeof options != 'string'){
			return this.each(function(){
				plugin.call($(this), options, param);
				setMe(this);
			});
		} else {
			return plugin.call(this, options, param);
		}
	};
	$.fn.datagrid.methods = plugin.methods;
	$.fn.datagrid.defaults = plugin.defaults;
	$.fn.datagrid.parseOptions = plugin.parseOptions;
	$.fn.datagrid.parseData = plugin.parseData;

})(jQuery);

(function($){
	$(document).bind('mousedown.propertygrid', function(e){
		var p = $(e.target).closest('div.datagrid-view,div.combo-panel');
		if (p.length){return;}
		$('.propertygrid').find('.datagrid-f').each(function(){
			var opts = $(this).propertygrid('options');
			opts.editIndex = undefined;
		})
	});
})(jQuery);

(function($){
	function setMe(target){
		var opts = $.data(target, 'treegrid').options;
		var dgOpts = $.data(target, 'datagrid').options;
		opts.columns = dgOpts.columns;
		opts.frozenColumns = dgOpts.frozenColumns;
		dgOpts.onBeginEdit = function(index, row){
			opts.onBeginEdit.call(this, row);
		};
		dgOpts.onEndEdit = function(index, row){
			opts.onEndEdit.call(this, row);
		}
	}
	
	var plugin = $.fn.treegrid;
	$.fn.treegrid = function(options, param){
		if (typeof options != 'string'){
			return this.each(function(){
				plugin.call($(this), options, param);
				setMe(this);
			});
		} else {
			return plugin.call(this, options, param);
		}
	};
	$.fn.treegrid.methods = plugin.methods;
	$.fn.treegrid.defaults = plugin.defaults;
	$.fn.treegrid.parseOptions = plugin.parseOptions;
	
})(jQuery);

(function($){
	var resizing = false;
	function splitMe(container, region){
		var cc = $(container);
		var handles = '';
		var dir = region;
		if (dir == 'north') handles = 's';
		if (dir == 'south') handles = 'n';
		if (dir == 'east') handles = 'w';
		if (dir == 'west') handles = 'e';
		var pp = cc.layout('panel', region);
		pp.panel('options').split = true;
		var panel = pp.panel('panel');
		panel.addClass('layout-split-'+region);
		panel.resizable({
			disabled:false,
			handles:handles,
			onStartResize: function(e){
				resizing = true;
				
				if (dir == 'north' || dir == 'south'){
					var proxy = $('>div.layout-split-proxy-v', container);
				} else {
					var proxy = $('>div.layout-split-proxy-h', container);
				}
				var top=0,left=0,width=0,height=0;
				var pos = {display: 'block'};
				if (dir == 'north'){
					pos.top = parseInt(panel.css('top')) + panel.outerHeight() - proxy.height();
					pos.left = parseInt(panel.css('left'));
					pos.width = panel.outerWidth();
					pos.height = proxy.height();
				} else if (dir == 'south'){
					pos.top = parseInt(panel.css('top'));
					pos.left = parseInt(panel.css('left'));
					pos.width = panel.outerWidth();
					pos.height = proxy.height();
				} else if (dir == 'east'){
					pos.top = parseInt(panel.css('top')) || 0;
					pos.left = parseInt(panel.css('left')) || 0;
					pos.width = proxy.width();
					pos.height = panel.outerHeight();
				} else if (dir == 'west'){
					pos.top = parseInt(panel.css('top')) || 0;
					pos.left = panel.outerWidth() - proxy.width();
					pos.width = proxy.width();
					pos.height = panel.outerHeight();
				}
				proxy.css(pos);
				
				$('<div class="layout-mask"></div>').css({
					left:0,
					top:0,
					width:cc.width(),
					height:cc.height()
				}).appendTo(cc);
			},
			onResize: function(e){
				if (dir == 'north' || dir == 'south'){
					var proxy = $('>div.layout-split-proxy-v', container);
					proxy.css('top', e.pageY - $(container).offset().top - proxy.height()/2);
				} else {
					var proxy = $('>div.layout-split-proxy-h', container);
					proxy.css('left', e.pageX - $(container).offset().left - proxy.width()/2);
				}
				return false;
			},
			onStopResize: function(e){
				cc.children('div.layout-split-proxy-v,div.layout-split-proxy-h').hide();
				pp.panel('resize',e.data);
				
				cc.layout('resize');
				resizing = false;
				
				cc.find('>div.layout-mask').remove();
			}

		})
	}

	$.extend($.fn.layout.methods, {
		split: function(jq, region){
			return jq.each(function(){
				splitMe(this, region);
				$(this).layout('resize');
			});
		},
		unsplit: function(jq, region){
			return jq.each(function(){
				var p = $(this).layout('panel', region);
				p.panel('options').split = false;
				p.panel('panel').removeClass('layout-split-'+region).resizable({disabled:true});
				$(this).layout('resize');
			})
		}
	})
})(jQuery);

(function($){
	$(function(){
		$(document).unbind('.messager').bind('keydown.messager', function(e){
			if (e.keyCode == 27){	//ESC
				$('body').children('div.messager-window').children('div.messager-body').each(function(){
					$(this).window('close');
				});
			} else if (e.keyCode == 9){	//TAB
				var win = $('body').children('div.messager-window').children('div.messager-body');
				if (!win.length){return}
				var buttons = win.find('.messager-input,.messager-button .l-btn');
				for(var i=0; i<buttons.length; i++){
					if ($(buttons[i]).is(':focus')){
						$(buttons[i>=buttons.length-1?0:i+1]).focus();
						return false;
					}
				}
			}
		});
	});
	var prompt = $.messager.prompt;
	$.messager.prompt = function(title, msg, fn){
		var win = prompt(title, msg, fn);
		win.find('.messager-input').focus();
		return win;
	};
})(jQuery);

(function($){
	function bindEvents(container){
		var state = $.data(container, 'tabs')
		var opts = state.options;
		$(container).children('div.tabs-header').unbind().bind('click', function(e){
			if ($(e.target).hasClass('tabs-scroller-left')){
				$(container).tabs('scrollBy', -opts.scrollIncrement);
			} else if ($(e.target).hasClass('tabs-scroller-right')){
				$(container).tabs('scrollBy', opts.scrollIncrement);
			} else {
				var li = $(e.target).closest('li');
				if (li.hasClass('tabs-disabled')){return false;}
				var a = $(e.target).closest('a.tabs-close');
				if (a.length){
					$(container).tabs('close', getLiIndex(li));
				} else if (li.length){
					var index = getLiIndex(li);
					var popts = state.tabs[index].panel('options');
					if (popts.collapsible){
						$(container).tabs(popts.closed ? 'select' : 'unselect', index);
					} else {
						$(container).tabs('select', index);
					}
				}
				return false;
			}
		}).bind('contextmenu', function(e){
			var li = $(e.target).closest('li');
			if (li.hasClass('tabs-disabled')){return;}
			if (li.length){
				opts.onContextMenu.call(container, e, li.find('span.tabs-title').html(), getLiIndex(li));
			}
		});
		
		function getLiIndex(li){
			var index = 0;
			li.parent().children('li').each(function(i){
				if (li[0] == this){
					index = i;
					return false;
				}
			});
			return index;
		}
	}

	var plugin = $.fn.tabs;
	$.fn.tabs = function(options, param){
		if (typeof options != 'string'){
			return this.each(function(){
				plugin.call($(this), options, param);
				bindEvents(this);
			})
		} else {
			return plugin.call(this, options, param);
		}
	};
	$.fn.tabs.methods = plugin.methods;
	$.fn.tabs.defaults = plugin.defaults;
	$.fn.tabs.parseOptions = plugin.parseOptions;
	$.extend($.fn.tabs.defaults, {
		onBeforeSelect: function(title, index){},
		onBeforeUnselect: function(title, index){}
	});
	var selectMethod = $.fn.tabs.methods.select;
	$.fn.tabs.methods.select = function(jq, which){
		return jq.each(function(){
			var opts = $(this).tabs('options');
			var tab = $(this).tabs('getTab', which);
			var title = tab.panel('options').title;
			var index = $(this).tabs('getTabIndex', tab);
			if (opts.onBeforeSelect.call(this, title, index) == false){return}
			selectMethod.call($.fn.tabs.methods, $(this), which);
		});
	};
	var unselectMethod = $.fn.tabs.methods.unselect;
	$.fn.tabs.methods.unselect = function(jq, which){
		return jq.each(function(){
			var opts = $(this).tabs('options');
			var tab = $(this).tabs('getTab', which);
			var title = tab.panel('options').title;
			var index = $(this).tabs('getTabIndex', tab);
			if (opts.onBeforeUnselect.call(this, title, index) == false){return}
			unselectMethod.call($.fn.tabs.methods, $(this), which);
		});
	}
})(jQuery);

(function($){
	function forNodes(data, callback){
		var nodes = [];
		for(var i=0; i<data.length; i++){
			nodes.push(data[i]);
		}
		while(nodes.length){
			var node = nodes.shift();
			if (callback(node) == false){return;}
			if (node.children){
				for(var i=node.children.length-1; i>=0; i--){
					nodes.unshift(node.children[i]);
				}
			}
		}
	}
	function getCheckedNode(target, state){
		state = state || 'checked';
		if (!$.isArray(state)){state = [state]}
		
		var selectors = [];
		for(var i=0; i<state.length; i++){
			var s = state[i];
			if (s == 'checked'){
				selectors.push('span.tree-checkbox1');
			} else if (s == 'unchecked'){
				selectors.push('span.tree-checkbox0');
			} else if (s == 'indeterminate'){
				selectors.push('span.tree-checkbox2');
			}
		}
		
		var nodes = [];
		$(target).find(selectors.join(',')).each(function(){
			var node = $(this).parent();
			if (!node.hasClass('tree-node-hidden')){
				nodes.push($(target).tree('getNode', node[0]));
			}
		});
		return nodes;
	}
	function getSelectedNode(target){
		var node = $(target).find('div.tree-node-selected:not(.tree-node-hidden)');
		return node.length ? $(target).tree('getNode', node[0]) : null;
	}

	$.extend($.fn.tree.methods, {
		getChecked: function(jq, state){	// the state available values are: 'checked','unchecked','indeterminate', default is 'checked'.
			return getCheckedNode(jq[0], state);
		},
		getSelected: function(jq){
			return getSelectedNode(jq[0]);
		},
		doFilter: function(jq, text){
			return jq.each(function(){
				var target = this;
				var data = $.data(target, 'tree').data;
				var ids = {};
				forNodes(data, function(node){
					if (node.text.toLowerCase().indexOf(text.toLowerCase()) == -1){
						$('#'+node.domId).addClass('tree-node-hidden').hide();
					} else {
						$('#'+node.domId).removeClass('tree-node-hidden').show();
						ids[node.domId] = 1;
					}
				});
				for(var id in ids){
					showParents(id);
				}

				function showParents(domId){
					var p = $(target).tree('getParent', $('#'+domId)[0]);
					while(p){
						$(p.target).removeClass('tree-node-hidden').show();
						p = $(target).tree('getParent', p.target);
					}
				}
			});
		}
	});

})(jQuery);

(function($){
	function setValues(target, values){
		var state = $.data(target, 'combotree');
		var opts = state.options;
		var tree = state.tree;
		var topts = tree.tree('options');
		var onCheck = topts.onCheck;
		var onSelect = topts.onSelect;
		topts.onCheck = topts.onSelect = function(){};
		
		tree.find('span.tree-checkbox').addClass('tree-checkbox0').removeClass('tree-checkbox1 tree-checkbox2');
		if (!$.isArray(values)){values = values.split(opts.separator)}
		var vv = $.map(values, function(value){return String(value)});
		var ss = [];
		$.map(vv, function(v){
			var node = tree.tree('find', v);
			if (node){
				tree.tree('check', node.target).tree('select', node.target);
				ss.push(node.text);
			} else {
				ss.push(v);
			}
		});
		if (opts.multiple){
			var nodes = tree.tree('getChecked');
			$.map(nodes, function(node){
				var id = String(node.id);
				if ($.inArray(id, vv) == -1){
					vv.push(id);
					ss.push(node.text);
				}
			});
		}
		topts.onCheck = onCheck;
		topts.onSelect = onSelect;
//		retrieveValues(target);
		$(target).combo('setText', ss.join(opts.separator)).combo('setValues', opts.multiple?vv:(vv.length?vv:['']));
	}
	
	$.extend($.fn.combotree.methods, {
		setValues: function(jq, values){
			return jq.each(function(){
				setValues(this, values);
			});
		},
		setValue: function(jq, value){
			return jq.each(function(){
				setValues(this, [value]);
			});
		}
	});
})(jQuery);

(function($){
	$.extend($.fn.numberbox.methods, {
		setValue: function(jq, value){
			return jq.each(function(){
				var target = this;
				var state = $.data(target, 'numberbox');
				var opts = state.options;
				value = opts.parser.call(target, value);
				var text = opts.formatter.call(target, value);
				opts.value = value;
				$(target).textbox('setText', text).textbox('setValue', value);
				text = opts.formatter.call(target, $(target).textbox('getValue'));
				$(target).textbox('setText', text);
			})
		}
	});
})(jQuery);

(function($){
	$.fn.datebox.defaults.buttons[0].handler = function(target){
		var now = new Date();
		$(target).datebox('calendar').calendar({
			year:now.getFullYear(),
			month:now.getMonth()+1,
			current:new Date(now.getFullYear(), now.getMonth(), now.getDate())
		});
		var e = new $.Event('keydown');
		e.keyCode = 13;
		$(target).datebox('textbox').trigger(e);
	}
})(jQuery);

(function($){
    $.fn.datagrid.defaults.data = [];
    var onAfterRender = $.fn.datagrid.defaults.view.onAfterRender;
    $.extend($.fn.datagrid.defaults.view, {
        renderTable: function(target, index, rows, frozen){
            var state = $.data(target, 'datagrid');
            var opts = state.options;

            if (frozen){
                if (!(opts.rownumbers || (opts.frozenColumns && opts.frozenColumns.length))){
                    return '';
                }
            }
            
            var fields = $(target).datagrid('getColumnFields', frozen);
            var table = ['<table class="datagrid-btable" cellspacing="0" cellpadding="0" border="0"><tbody>'];
            for(var i=0; i<rows.length; i++){
                var row = rows[i];
                // get the class and style attributes for this row
                var css = opts.rowStyler ? opts.rowStyler.call(target, index, row) : '';
                var classValue = '';
                var styleValue = '';
                if (typeof css == 'string'){
                    styleValue = css;
                } else if (css){
                    classValue = css['class'] || '';
                    styleValue = css['style'] || '';
                }
                
                var cls = 'class="datagrid-row ' + (index % 2 && opts.striped ? 'datagrid-row-alt ' : ' ') + classValue + '"';
                var style = styleValue ? 'style="' + styleValue + '"' : '';
                var rowId = state.rowIdPrefix + '-' + (frozen?1:2) + '-' + index;
                table.push('<tr id="' + rowId + '" datagrid-row-index="' + index + '" ' + cls + ' ' + style + '>');
                table.push(this.renderRow.call(this, target, fields, frozen, index, row));
                table.push('</tr>');

                index++;
            }
            table.push('</tbody></table>');
            return table.join('');
        },
        onAfterRender:function(target){
            onAfterRender.call(this,target);
            var opts = $.data(target, 'datagrid').options;
            if (opts.finder.getRows(target).length == 0){
            	this.renderEmptyRow(target);
            }
        },
		renderEmptyRow: function(target){
			var dc = $.data(target, 'datagrid').dc;
			dc.body2.html(this.renderTable(target, 0, [{}], false));
			dc.body2.find('.datagrid-row').removeClass('datagrid-row').removeAttr('datagrid-row-index');
			dc.body2.find('tbody *').css({
				height: 1,
				borderColor: 'transparent',
				background: 'transparent'
			});
		}
    });
})(jQuery);
