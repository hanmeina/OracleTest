var searchCategory = 'q';

// 获取URI参数
function getURLParam(strParamName) {
	var strReturn = "";
	var strHref = window.location.href;
	if (strHref.indexOf("?") > -1) {
		var strQueryString = strHref.substr(strHref.indexOf("?")).toLowerCase();
		var aQueryString = strQueryString.split("&");
		for ( var iParam = 0; iParam < aQueryString.length; iParam++) {
			if (aQueryString[iParam].indexOf(strParamName + "=") > -1) {
				var aParam = aQueryString[iParam].split("=");
				strReturn = aParam[1];
				break;
			}
		}
	}
	return strReturn;
}

// Ajax分页
var pageList = function(obj, url, target) {
	$.getJSON(url, function(json) {
		if (json.status == 200) {
			$('#' + target).html(json.content);
		}
	})
}

// 顶部下拉导航栏
var Navigate = function(obj, a, t) {
	var action = '';
	var n_class = '';
	switch (a) {
	case 'a':
		action = 'aboutUs';
		n_class = '.aboutUs';
		if ($('.aboutUs').css('display') == 'block') {
			$('.menuWrap').stop().slideUp('normal');
			$('#nav li').removeClass('active');
			$('#header .logoBt').removeClass('active');
			return;
		}
		break;
	case 'd':
		action = 'login';
		n_class = '.logMenu';
		if (document.getElementById("Loged").style.display == 'block') {
			$('.menuWrap').stop().slideUp('normal');
			$('#nav li').removeClass('active');
			$('#header .logoBt').removeClass('active');
			return;
		}
		break;
	case 'v':
		action = 'video';
		n_class = '.videoMenu';
		if ($('.videoMenu').css('display') == 'block') {
			$('.menuWrap').stop().slideUp('normal');
			$('#nav li').removeClass('active');
			$('#header .logoBt').removeClass('active');
			return;
		}
		break;
	case 's':
		action = 'search';
		n_class = '.srhMenu';
		if ($('.srhMenu').css('display') == 'block') {
			$('.menuWrap').stop().slideUp('normal');
			$('#nav li').removeClass('active');
			$('#header .logoBt').removeClass('active');
			return;
		}
		break;
	case 'l':
		action = 'login';
		n_class = '.logMenu';
		if ($('.logMenu').css('display') == 'block') {
			$('.menuWrap').stop().slideUp('normal');
			$('#nav li').removeClass('active');
			$('#header .logoBt').removeClass('active');
			return;
		}
		break;
	case 'r':
		action = 'regist';
		n_class = '.regist';
		if ($('.regist').css('display') == 'block') {
			$('.menuWrap').stop().slideUp('normal');
			$('#nav li').removeClass('active');
			$('#header .logoBt').removeClass('active');
			return;
		}
		break;
	case 'f':
		action = 'feed';
		n_class = '.logIn';
		if ($('.logIn').css('display') == 'block') {
			$('.menuWrap').stop().slideUp('normal');
			$('#nav li').removeClass('active');
			$('#header .logoBt').removeClass('active');
			return;
		}
		break;
	case 'c':
		// document.getElementById("srhMenu").style.display="none";
		action = 'check';
		n_class = '.logMenu';
		if ($('.logMenu').css('display') == 'block'
				|| $('.regist').css('display') == 'block'
				|| $('.logIn').css('display') == 'block') {
			$('.menuWrap').stop().slideUp('normal');
			$('#nav li').removeClass('active');
			$('#header .logoBt').removeClass('active');
			return;
		}
		break;
	case 'b':
		action = 'feedBack';
		n_class = '.feedBack';
		if ($('.feedBack').css('display') == 'block') {
			$('.menuWrap').stop().slideUp('normal');
			$('#nav li').removeClass('active');
			$('#header .logoBt').removeClass('active');
			return;
		}
		break;
	}

	if (action != '') {
		if ($(n_class).size() > 0) {
			$('.menuWrap').stop().slideDown('normal');
			$(obj).parent().addClass('active');
			if (a == 'l') {
				document.getElementById("srhMenu").style.display = "none";
				document.getElementById("regist").style.display = "none";
				document.getElementById("Loged").style.display = "none";
			} else if (a == 'd') {
				document.getElementById("srhMenu").style.display = "none";
				document.getElementById("regist").style.display = "none";
				document.getElementById("logMenu").style.display = "none";
			} else if (a == 's') {
				document.getElementById("logMenu").style.display = "none";
				document.getElementById("regist").style.display = "none";
				document.getElementById("Loged").style.display = "none";
			} else if (a == 'r') {
				document.getElementById("logMenu").style.display = "none";
				document.getElementById("srhMenu").style.display = "none";
				document.getElementById("Loged").style.display = "none";
			}

		} else {
			$.getJSON('/navigate/' + action, function(json) {
				$('#nav li').removeClass('active');
				$('#header .logoBt').removeClass('active');
				if (json.status == 200) {
					var flag = false;
					if ($('.menuWrap').css('display') == 'block') {
						flag = true;
					}
					$('#topMenu').html(json.content);
					if (t == true) {
						$('.menuWrap').stop().show();
						$(obj).parent().addClass('active');
					} else {
						if (flag) {
							$('.menuWrap').stop().show();
						} else {
							$('.menuWrap').stop().slideDown('normal');
						}
						$(obj).parent().addClass('active');
					}
					teamHover();
					if (action == 'regist') {
						registInit();
					}
				}
			})
		}
	}
}

// 关闭导航栏
var closeNavigate = function() {
	$('#nav li').removeClass('active');
	$('#header .logoBt').removeClass('active');
	$('.menuWrap').stop().slideUp('normal');
}

// 团队成员介绍
var teamHover = function() {
	$('.team a').hover(function() {
		var index = $(this).attr('rel');
		$('.mbInfo').hide();
		$('.mbInfo').eq(index).show();
	}, function() {
		// var index = $(this).attr('rel');
		// $('.mbInfo').eq(index).hide();
	})
}

// 注册规则初始化
var registInit = function() {
	$('.regist input[name=userName]').bind('focusout', function() {
		var value = $(this).val();
		var reg = /^[a-zA-Z0-9_[^\u4e00-\u9fa5]+$/gi;
		if (reg.test(value) == false) {
			$('#warning').html('用户名只允许1-20位中英文、数字、下划线的格式')
		} else {
			$('#warning').html('');
		}
	}).bind('focusin', function() {
		$('#warning').html('');
	})

	$('.regist input[name=passWord]').bind('focusout', function() {
		var value = $(this).val();
		if (value.length < 6 || value.length > 20) {
			$('#warning').html('密码格式为6-20位数字、英文');
		} else {
			$('#warning').html('');
		}
	})

	$('.regist input[name=email]')
			.bind(
					'focusout',
					function() {
						var value = $(this).val();
						var reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
						if (reg.test(value) == false) {
							$('#warning').html('请输入正确的邮箱');
						} else {
							$('#warning').html('');
						}
					})
}

// 提交注册
var postRegist = function(obj) {
	var username = $('.regist input[name=userName]').val();
	var password = $('.regist input[name=passWord]').val();
	var email = $('.regist input[name=email]').val();
	var repwd = $('.regist input[name=repwd]').val();
	var receives = $('.regist input[name=receives]:checked').size();

	if ($(obj).val() == '注册...') {
		return;
	} else if (username.length == 0) {
		alert('请输入用户名');
	} else if (email.length == 0
			|| !/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/
					.test(email)) {
		alert('请输入正确的邮箱');
	} else if (password.length < 6 || password.length > 20) {
		alert('密码不应小于6位，大于20位');
	} else if (password != repwd) {
		alert('确认密码不一致');
	} else if (receives == 0) {
		alert('请选择接受ZEALER会员注册条款');
	} else {
		$(obj).val('注册...');
		$.post('/index/regist', {
			username : username,
			password : password,
			repwd : repwd,
			email : email,
			receives : receives
		}, function(json) {
			if (json.status == 200) {
				alert(json.message);
				Navigate(obj, 'l', true);
			} else {
				alert(json.message);
			}
			$(obj).val('注册');
		}, 'json')
	}
}

// 回车登录
var enterSocialRegist = function(e) {
	var key = window.event ? e.keyCode : e.which;
	if (key == 13) {
		$('#sign a').click();
	}
}

// 提交注册(社交平台绑定)
var postSocialRegist = function(obj) {
	var username = $('#sign input[name=userName]').val();
	var password = $('#sign input[name=passWord]').val();
	var email = $('#sign input[name=email]').val();
	var repwd = $('#sign input[name=repwd]').val();
	var social_type = $('#sign input[name=social_type]').val();
	var token = $('#sign input[name=token]').val();
	var expires_in = $('#sign input[name=expires_in]').val();
	var social_user_id = $('#sign input[name=social_user_id]').val();

	if ($(obj).html() == '注册...') {
		return;
	} else if (username.length == 0) {
		alert('请输入用户名');
	} else if (email.length == 0
			|| !/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
					.test(email)) {
		alert('请输入正确的邮箱');
	} else if (password.length < 6 || password.length > 20) {
		alert('密码不应小于6位，大于20位');
	} else if (password != repwd) {
		alert('确认密码不一致');
	} else {
		$(obj).html('注册...');
		$.post('/index/regist', {
			username : username,
			password : password,
			repwd : repwd,
			email : email,
			social_type : social_type,
			token : token,
			expires_in : expires_in,
			social_user_id : social_user_id
		}, function(json) {
			if (json.status == 200) {
				alert(json.message);
				$('#inv_bg').hide();
				$('#inv').hide();
				Navigate(obj, 'l');
			} else {
				alert(json.message);
			}
			$(obj).html('注册');
		}, 'json')
	}
}
// 用户登录
var postLogin = function(obj) {
	var username = $('.logMenu input[name=userName]').val();
	var password = $('.logMenu input[name=passWord]').val();
	var remember = $('.logMenu input[name=remember]:checked').size();

	if ($(obj).val() == '登录...') {
		return;
	} else if (username.length == 0) {
		alert('请输入用户名');
	} else if (password.length == 0) {
		alert('请输入密码');
	} else {
		$(obj).val('登录...');
		$.post('/index/login', {
			username : username,
			password : password,
			remember : remember
		}, function(json) {
			if (json.status == 200) {
				Navigate(obj, 'f', true);
			} else {
				alert(json.message);
			}
			$(obj).val('登录');
		}, 'json')
	}
}

// 回车登录
var enterLogin = function(e) {
	var key = window.event ? e.keyCode : e.which;
	if (key == 13) {
		$('input[name=login]').click();
	}
}

// 全局搜索下拉分类
var searchCategoryDown = function(obj) {
	obj = $(obj).next().next();
	if (obj.css('display') == 'block') {
		obj.stop().slideUp('fast');
	} else {
		obj.stop().slideDown('fast');
	}
}

// 选择搜索分类
var searchCategoryChange = function(obj, category) {
	var name = $(obj).html();
	obj = $(obj).parent().parent();
	obj.find('h2').html(name);
	searchCategory = category;
	obj.find('.srhList').stop().slideUp('fast');
	obj.find('input[name=search]').focus();
}

// 搜索视频、问答
var postSearch = function(obj) {
	var q = $(obj).prev().prev().val();
	if (q.length > 0) {
		if (searchCategory == 'q') {
			var a = q.substring(0,1);
			var b = q.substring(2,3);
			window.location.href = 'room.jsp?build='+a+'&floor='+b;
		} else if (searchCategory == 'v') {
			window.location.href = 'searchbydate.jsp';
		} else {
			window.location.href = 'searchbykeyword_submit.jsp?keyword=' + q;
		}
	}
}

// 回车搜索
var enterSearch = function(e, id) {
	var key = window.event ? e.keyCode : e.which;
	if (key == 13) {
		$('#' + id).click();
	}

	return false;
}

// 视频筛选
var filterCategory = function() {
	var cid = '';
	var total = $('.videoMenu input[name=category]:checked').size();
	$('.videoMenu input[name=category]:checked').each(function(i, v) {
		cid += $(this).val();
		if (i < total - 1) {
			cid += ',';
		}
	})

	if (cid != '') {
		window.location.href = '/post/list?cid=' + cid;
	}
}

// 问题反馈
var bugListChange = function(obj) {
	var value = $(obj).html();
	$('.feedBack p').html(value);
	$('.feedBack .srhList').hide();
}

// 提交问题反馈
var postBug = function(obj) {
	var type = $('.feedBack p').html();
	var title = $('.feedBack input[name=title]').val();
	var content = $('.feedBack textarea[name=content]').val();

	if ($(obj).val() == '提交...') {
		return;
	}

	if (type == '选择分类' || type.length == 0) {
		alert('请选择分类');
	} else if (title.length == 0) {
		alert('请输入标题');
	} else if (content.length == 0) {
		alert('请输入问题描述');
	} else {
		$(obj).val('提交...');
		$.post('/index/postBug', {
			type : type,
			title : title,
			content : content
		}, function(json) {
			if (json.status == 200) {
				alert(json.message);
				$('.feedBack p').html('选择分类');
				$('.feedBack input[name=title]').val('');
				$('.feedBack textarea[name=content]').val('')
				$('.feedBack input[type=checkbox]').attr('checked', false);
				Navigate(obj, 'b');
			} else {
				alert(json.message);
			}
			$(obj).val('提交');
		}, 'json')
	}
}

// 关闭关注微信
var closeWeichat = function() {
	$('#inv_bg').fadeOut('fast');
	$('#weichat').fadeOut('fast');

}

// 显示注册条款
var openClause = function() {
	$('#regist').hide();
	$('#clause').show();
}

// 关闭注册条款
var closeClause = function() {
	$('#regist').show();
	$('#clause').hide();
}

// 侧边栏推荐标签关注Hover
var tipInfoInit = function() {
	$('.tipLi').hover(function() {
		$(this).find('.tipInfo2').show();
	}, function() {
		$(this).find('.tipInfo2').hide();
	})

	// 喜欢视频
	$('#side .like').bind(
			'click',
			function() {
				var obj = this;
				var id = $(obj).attr('qid');
				var type = $(obj).attr('rel');
				if ($(obj).hasClass('ever_minus')
						|| (!$(obj).hasClass('ever_minus')
								&& !$(obj).hasClass('ever_plus') && !$(obj)
								.hasClass('ever_cancel'))) {
					$.getJSON('/question/follow', {
						id : id,
						type : type
					}, function(json) {
						if (json.status == 200) {
							$(obj).toggleClass('ever_plus').removeClass(
									'ever_minus').removeClass('ever_cancel');
							setTimeout("clearEvers('" + id + "','" + type
									+ "', true)", 2000);
						} else {
							alert(json.message);
							if (json.status == 301) {
								Navigate(this, 'c');
							}
						}
					})
				} else {
					$.getJSON('/question/follow', {
						id : id,
						type : type
					}, function(json) {
						if (json.status == 200) {
							$(obj).toggleClass('ever_minus').removeClass(
									'ever_plus').removeClass('ever_cancel');
							setTimeout("clearEvers('" + id + "','" + type
									+ "', true)", 2000);
						} else {
							alert(json.message);
							if (json.status == 301) {
								Navigate(this, 'c');
							}
						}
					})
				}
			})

	$('#side .like').each(
			function() {
				var id = $(this).attr('qid');
				var type = $(this).attr('rel');
				$.getJSON('/question/ShowFollow', {
					id : id,
					type : type
				}, function(json) {
					if (json.status == 200) {
						if (json.message.status == 'N') {
							$(
									'#' + json.message.type + '_like_'
											+ json.message.id + '_side')
									.addClass('ever_cancel');
						}
					}
				})
			})
}

var clearEvers = function(id, type, flag) {
	var obj = '';
	if (type == 'q') {
		obj = '#q_like_' + id;
	} else {
		obj = '#t_like_' + id;
	}

	if (flag != null) {
		obj += '_side';
	}
	if ($(obj).hasClass('ever_plus')) {
		$(obj).removeClass('ever_plus').addClass('ever_cancel');
	} else if ($(obj).hasClass('ever_minus')) {
		$(obj).removeClass('ever_minus').removeClass('ever_cancel');
	}
}

$(function() {
	var socialCode = getURLParam('code');
	var type = getURLParam('type');
	if (socialCode != '' && type != '') {
		$.getJSON('/social/getToken', {
			type : type,
			code : socialCode
		}, function(json) {
			if (json.status == 301) {
				$('#inv_bg').show();
				$('#inv').show();
				$('input[name=social_type]').val(json.message.social_type);
				$('input[name=token]').val(json.message.token);
				$('input[name=expires_in]').val(json.message.expires_in);
				$('input[name=social_user_id]')
						.val(json.message.social_user_id);
			} else if (json.status == 304) {
				window.location = '/?l=s';
			} else {
				alert(json.message);
			}
		})
	}

	$('body').bind('click', closeNavigate);
	$('#Navigate').hover(function() {
		$('body').unbind('click');
	}, function() {
		$('body').bind('click', closeNavigate);
	})

	$.getJSON('/social/timeline', function(json) {
		if (json.status == 200) {
			$('#footer .ftRt ul').html(json.content);
			$('#footer .ftRt ul').jScrollPane({
				showArrows : true,
				arrowScrollOnHover : true,
				mouseWheelSpeed : 40
			})
			$('#footer .ftRt ul').attr('tabindex', '');
		}
	})

	// 回到顶部
	$(window).scroll(function() {
		if ($(window).scrollTop() > 100) {
			$("#back-to-top").fadeIn(1000);
		} else {
			$("#back-to-top").fadeOut(1000);
		}
	});

	$('#back-to-top').bind('click', function() {
		$('body,html').animate({
			scrollTop : 0
		}, 200);
		return false;
	})

	// 关注微信
	$('.followUs .rss').bind('click', function() {
		$('#inv_bg').fadeIn('fast');
		$('#weichat').fadeIn('fast');
	})

	// 导航栏自适合宽度
	$(window).resize(function() {
		width = parseInt($(window).width());
		if (width > 500 && width < 1250) {
			$('#top').css('width', width + 'px');
			$('#header').css('width', width + 'px');
		} else if (width >= 1250 && parseInt($('#top').css('width')) < 1250) {
			$('#top').css('width', '1250px');
			$('#header').css('width', '1250px');
		}
	})

})
