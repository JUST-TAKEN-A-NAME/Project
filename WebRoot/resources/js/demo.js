$(document).ready(function() {
	// Full featured editor
	$('#editor1').each(function(index, element) {
		$(element).wysiwyg({
			classes: 'some-more-classes',
			// 'selection'|'top'|'top-selection'|'bottom'|'bottom-selection'
			toolbar: index == 0 ? 'top-selection': (index == 1 ? 'bottom': 'selection'),
			buttons: {
				/*insertimage: {
                    title: '插入图片',
                    image: '<i class="fa fa-photo"></i>', 
                    //showstatic: true,    // wanted on the toolbar
                    showselection: false    // wanted on selection
                },*/
				insertlink: {
					title: '插入链接',
					image: '<i id="font_link" class="fa fa-link"></i>',
					showselection: false
				},
				// Fontsize plugin
				fontsize: index == 1 ? false: {
					title: '字体大小',
					image: '\uf034',
					popup: function($popup, $button) {
						var list_fontsizes = {
							// Name : Size
							'Huge': 7,
							'Larger': 6,
							'Large': 5,
							'Normal': 4,
							'Small': 3,
							'Smaller': 2,
							'Tiny': 1
						};
						var $list = $('<div/>').addClass('wysiwyg-toolbar-list').attr('unselectable', 'on');
						$.each(list_fontsizes,
						function(name, size) {
							var $link = $('<a/>').attr('href', '#').css('font-size', (8 + (size * 3)) + 'px').html(name).click(function(event) {
								$(element).wysiwyg('shell').fontSize(size).closePopup();
								// prevent link-href-#
								event.stopPropagation();
								event.preventDefault();
								return false;
							});
							$list.append($link);
						});
						$popup.append($list);
					}
					//showstatic: true,    // wanted on the toolbar
					//showselection: true    // wanted on selection
				},
				bold: {
					title: '字体加粗(Ctrl+B)',
					image: '<i id="font_bold" class="fa fa-bold"></i>',
					hotkey: 'b'
				},
				italic: {
					title: '字体斜体(Ctrl+I)',
					image: '<i id="font_italic" class="fa fa-italic"></i>',
					hotkey: 'i'
				},
				/*underline: {
                    title: '�»��� (Ctrl+U)',
                    image: '<i id="font_underline" class="fa fa-underline"></i>', 
                    hotkey: 'u' 
                },
                strikethrough: {
                    title: '���� (Ctrl+S)',
                    image: '<i id="font_strikethrough" class="fa fa-strikethrough"></i>', 
                    hotkey: 's'
                },*/
				forecolor: {
					title: '字体颜色',
					image: '\uf1fc'
				},
				alignleft: index != 0 ? false: {
					title: '居左',
					image: '<i id="font_align_left" class="fa fa-align-left"></i>',
					//showstatic: true,    // wanted on the toolbar
					showselection: false // wanted on selection
				},
				aligncenter: index != 0 ? false: {
					title: '居中',
					image: '<i id="font_align_center" class="fa fa-align-center"></i>',
					//showstatic: true,    // wanted on the toolbar
					showselection: false // wanted on selection
				},
				alignright: index != 0 ? false: {
					title: '居右',
					image: '<i id="font_align_right" class="fa fa-align-right"></i>',
					//showstatic: true,    // wanted on the toolbar
					showselection: false // wanted on selection
				},
				orderedList: index != 0 ? false: {
					title: '有序列表',
					image: '<i id="font_list_ol" class="fa fa-list-ol"></i>',
					//showstatic: true,    // wanted on the toolbar
					showselection: false // wanted on selection
				},
				unorderedList: index != 0 ? false: {
					title: '无序列表',
					image: '<i id="font_list_ul" class="fa fa-list-ul"></i>',
					//showstatic: true,    // wanted on the toolbar
					showselection: false // wanted on selection
				},
				indent: {
					title: '左缩进',
					image: '<i id="font_list_ul" class="fa fa-indent"></i>'
				},
				outdent: {
					title: '右缩进',
					image: '<i id="font_list_ul" class="fa fa-dedent"></i>'
				}
			},
			// Submit-Button
			submit: {
				title: 'Submit',
				image: '\uf00c'
			},
			// Other properties
			dropfileclick: ' 插入图片 ',
			placeholderUrl: 'www.xxxxxx.com',
			maxImageSize: [600, 200]
		});
	});

	$(".wysiwyg-editor").blur();
	// 加入自定义功能   【全屏/插入代码/插入图片】
	var quanpinbtn = "<a id='insertCode' href='javascript:insertCode()' class='wysiwyg-toolbar-icon' title='插入代码'><i class='fa fa-code'></i></a>";
	quanpinbtn += "<a id='quanpin' href='javascript:changeStyle()' class='wysiwyg-toolbar-icon' title='全屏' style='float:right;'><i class='fa fa-expand'></i></a>";
	$(".wysiwyg-toolbar").append(quanpinbtn);
	$(".wysiwyg-toolbar").prepend("<a id='showUploadBox' href='javascript:showUploadBox()' class='wysiwyg-toolbar-icon' title='插入图片'><i class='fa fa-photo'></i></a>");

	////////////////////////////////////上面是初始化富文本编辑框///////////////////////////////////////////////
	////////////////////////////////////华丽分界线///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////华丽分界线///////////////////////////////////////////////////////////
	/// 点击插入图片，出现自定义插入图片div，点击其他地方，div隐藏
	document.onclick = function(event) {
		var e = event || window.event;
		var elem = e.srcElement || e.target;

		while (elem) {
			if (elem.id == "uploadFile_box") {
				return;
			};
			elem = elem.parentNode;
		};
		$("#uploadFile_box").css("display", "none");
	}

	// 点击上传图片 触发input【file】触发事件
	$("#uploadimgbtn").click(function() {
		$("#uploadImg").click();
	});

	// input[file] 改变触发事件
	$("#uploadImg").change(function(e) {
		var file = e.target.files[0]; 
		inintimg(file);

	});

	// 判断选择的文件类型以及是否为空和大小范围，之后预览在div中！
	function inintimg(file) {

		if (checkUploadFile()) {

			var img = new Image(),url = img.src = URL.createObjectURL(file);
			var $img = $(img);

			var max_size = parseInt($("#uploadImg").attr('data-max_size'));
			if (file.size > max_size) {
				alert("图片不大于3MB。");
				return false;
			};

			img.onload = function() {
				URL.revokeObjectURL(url);
				$("#removeImage").css("display", "block");
				$('#uploadimgbtn').empty().append($img);
			};
		}

	};

	// 确认上传！！ 上传图片~~
	$("#confimbtn").click(function() {
		$(this).addClass("active");
		$(this).html('<i class="fa fa-spinner fa-spin"></i>上传图片中~');
		$(this).attr("disabled", "disabled");

		var webPath = "/" + window.location.pathname.split("/")[1];
		if (checkUploadFile()) {

			$.ajaxFileUpload({
				url: webPath+"/bolg/insertImg",
				secureuri: false,
				fileElementId: 'uploadImg',
				dataType: 'json',
				data: {
					username: "嘻嘻"
				},
				success: function(data) {
					data = eval("(" + data + ")");
					if (data.state == "666") {
						$("#uploadImg").on("change",
						function(e) {
							var file = e.target.files[0];
							inintimg(file);
						}); 
						var webPath = "/" + window.location.pathname.split("/")[1];
						var html = '<img src="' + webPath + data.imgpath + '" id="' + data.id + '"></img>';
						$(".wysiwyg-editor").append(html);
						$("#uploadFile_box").css("display", "none");
					} else {
						alert("State:" + data.state + ",Msg:" + data.msg);
					}
					hideUploadBox();

				},
				error: function(data, status, e) {
					alert('上传出错');
				}
			});
		};
		$(this).removeClass("active");
		$(this).text(' 确 认 ');
		$(this).removeAttr("disabled");

	});
	
	// 在这里发布博客~
	$("#fabu").click(function() {

		$(this).css("background-color","rgba(113, 120, 125, 0.7)");
		$(this).text('发 布 中...');
		$(this).attr("disabled", "disabled");
		
		
		var html = $(".wysiwyg-editor").html();
		var title = $("#caonima").val();
		var type = $("#bolgType").val();
		var summary = $("#summary").val();
		var content_img = $(".wysiwyg-editor").find("img");

		if(title == ""){
			return false;
		}
		if(html == ""){
			return false;
		}
		if(type == ""){
			return false;
		}
		if(summary == ""){
			return false;
		}
		
		var imgIds = "";
		if (content_img.length > 0) {
			content_img.each(function() {
				imgIds += $(this).attr("id") + ",";
			})
		};

		var tags = "";
		if ($(".confom_tags_box span").length > 0) {
			$(".confom_tags_box span").each(function() {
				tags += $(this).attr("id") + ",";
			})
		} else {
			return false;
		};

		var webPath = "/" + window.location.pathname.split("/")[1];
		$.ajax({
			type: 'POST',
			data: {
				bolgSummary: summary,
				bolgContent: html,
				bolgTitle: title,
				bolgType: type,
				bolgTags: tags,
				UploadImgIds: imgIds
			},
			url: webPath + "/bolg/add?v=" + new Date().getTime(),
			success: function(data) {
				window.location.href= webPath + "/bolg/index";
			}
		});

		$(this).css("background-color","rgba(34, 96, 150, 0.7)");
		$(this).text('发 布');
		$(this).removeAttr("disabled");

	});

	// 关闭tag div 
	$("#close_choose_tags").click(function() {
		$(".suspend_tags_box").removeClass("suspend_tags_box_after");
		$(".suspend_choose_box").css("display", "none");
	});

	$(".tags_box_ul li").click(function() {

		if ($(this).hasClass("active")) {
			$(this).removeClass("active");
		} else {
			if ($(".tags_box_ul li.active").length > 4) {
				$("#choose_tags_msg").addClass("show_it");
				return false;
			} else {
				$("#choose_tags_msg").removeClass("show_it");
			}
			$(this).addClass("active");
		}
	});

	$("#confim_tags_btn").click(function() {
		if ($(".tags_box_ul li.active").length > 0) {
			var tags_map = "";
			$(".tags_box_ul li.active span").each(function() {
				tags_map += '<span id="' + $(this).attr("id") + '">' + $(this).text() + '<i onclick="drop_tag(' + $(this).attr("id") + ')" class="fa fa-close"></i></span>';
			});
			$(".prompt").css("display", "none");
			$(".confom_tags_box").append(tags_map);
		}
		$(".suspend_tags_box").removeClass("suspend_tags_box_after");
		$(".suspend_choose_box").css("display", "none");
	});

	// 图片预览时，有个X，点击移除预览
	$("#removeImage").click(function() {
		hideUploadBox();
	})
});

//全屏切换
function changeStyle() {
	var sysiwyg = $(".wysiwyg-container");
	if (sysiwyg.hasClass("quanpinSty")) {
		$("#quanpin").attr("title", "全屏模式").html("<i class='fa fa-expand'></i>");
		sysiwyg.removeClass("quanpinSty");
		sysiwyg.parent().css("margin-top", "30px");
		sysiwyg.css("min-height", "350px");
	} else {
		$("#quanpin").attr("title", "正常模式").html("<i class='fa fa-compress'></i>");
		sysiwyg.addClass("quanpinSty");
		sysiwyg.parent().css("margin-top", "0px");
		sysiwyg.css("min-height", document.documentElement.clientHeight);
	}
};

// 插入代码之前，出现div
function insertCode() {
	var insertbox = $(".insertCode_box");
	insertbox.css("display", "block");
};

// 关闭插入代码的div
function close_insert() {
	var insertbox = $(".insertCode_box");
	insertbox.css("display", "none");
};

// 点击确认，获取输入的代码
function is_commit() {
	var codeStr = '<div><pre class="brush: c-sharp">';
	var context = $("#code").val();
	codeStr += context;
	codeStr += "</pre></div><div><br></div>";
	alert(codeStr);
	$(".wysiwyg-editor").append(codeStr);
	$("#code").val("");
	var insertbox = $(".insertCode_box");
	insertbox.css("display", "none");
};

// 固定上传图片的div 位置
function showUploadBox() {
	$("#uploadFile_box").css({
		"display": "block",
		"top": $(".wysiwyg-toolbar-top").offset().top + 40 + "px",
		"left": "50px"
	});
};

// 上传图片之后，或者取消上传等一系列操作！
function hideUploadBox() {
	var uploadimgbtn = $('#uploadimgbtn');
	uploadimgbtn.find("img").remove();
	uploadimgbtn.html('<i class="fa fa-plus"></i>');
	$("#removeImage").css("display", "none");
	$("#uploadImg").val("");
};

// 校验input[file]选中的文件类型，是否为空！！
function checkUploadFile() {
	var uploadfile = $("#uploadImg").val();
	if (uploadfile.trim() == "") {
		alert("请选择图片！！！");
		return false;
	}
	var extStart = uploadfile.lastIndexOf(".");
	var ext = uploadfile.substring(extStart, uploadfile.length).toUpperCase();
	if (ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
		alert("只能插入BMP,PNG,GIF,JPG,JPEG格式的图片~");
		return false;
	}
	return true;
};

function open_choose_tags() {
	$(".suspend_choose_box").css("display", "block");
	$(".suspend_choose_box").css("height", document.body.scrollHeight + "px");
	$(".suspend_tags_box").addClass("suspend_tags_box_after");
};
function drop_tag(id) {
	$(".confom_tags_box").find("#" + id).remove();
	if ($(".confom_tags_box span").length < 1) {
		$(".prompt").css("display", "inline");
	}
};

function cancel() {
	var webPath = "/" + window.location.pathname.split("/")[1];
	var bolgId = $("#bolgId").val();
	if (confirm("取消之后，将不会保存已编辑的，确定取消吗？")) {
		$.ajax({
			type: 'POST',
			url: webPath + "/bolg/cancel?bolgId=" + bolgId,
			success: function(data) {
				if (data.result == 666) {
					alert("操作成功！");
					window.location.href = webPath+"/bolg/index";
				} else {
					alert("异常，操作失败！");
				}
			}
		});
	};
};