;(function($){
	function getBasicInfo(){
		var ViewH = $(window).height(); //视口高度
		var DocH = $("body")[0].scrollHeight; //documet高度
		var ScrollTop = $(window).scrollTop(); //滚动条下滑的长度
		var S_V = DocH - ViewH; //滚动条滚动的最大距离
		var Band_H = ScrollTop / (DocH - ViewH) * 100 ; //进度条的占视口高度的百分比
		return {
			ViewH: ViewH,
			DocH:DocH,
			ScrollTop: ScrollTop,
			Band_H: Band_H,
			S_V:S_V
		}
	}
	$.fn.nekoScroll = function(option){
		var defaultSetting = {
			top:'0',
			scroWidth:6+'px',
			bgcolor:"#2f3542",
			z_index:9999,
			zoom:0.9, //缩放值
			borderRadius:5+'px',
			right:32+'px',
			nekoImg: "../static/images/cat.png",
			scImg:"../static/images/rope.png",
			hoverMsg:"喵喵？",
			endMsg:"已经读完了",
			fontFamily:"",
			nekoname:"neko",
			fontSize:"",
			color:"#333",
			during:600
		}
		var setting = $.extend(defaultSetting,option);
		var getThis = this.prop("className") !== "" ? "." + this.prop("className") : this.prop("id") !== "" ? "#" + this.prop(
					"id") : this.prop("nodeName");
					
		this.after("<div class=\"neko\" id="+setting.nekoname+"></div>");
		// setInterval(function() {
		// 	$("#"+setting.nekoname).addClass("fontColor");
		// }, 1000);
		let basicInfo = getBasicInfo();
		
		$(getThis).css({
			'position':'fixed',
			'width':setting.scroWidth,
			'top':setting.top,
			'height':basicInfo.Band_H*setting.zoom+'%',
			'z-index':setting.z_index,
			'background-color':setting.bgcolor,
			"border-radius":setting.borderRadius,
			'right':setting.right,
			'background-image':'url('+setting.scImg+')',
			'background-size':'contain'
		});
		$("#"+setting.nekoname).css({
			'position': 'fixed',
			'top': (basicInfo.Band_H*setting.zoom*8/9)+'%',
			'z-index': setting.z_index*10,
			'right':setting.right,
			'background-image':'url('+setting.nekoImg+')',
			'font-family':setting.fontFamily,
			'font-size':setting.fontSize,
			'color':setting.color
		});
		$(window).scroll(function(){
			let basicInfo = getBasicInfo();
			
			$(getThis).css({
				'position':'fixed',
				'width':setting.scroWidth,
				'top':setting.top,
				'height':basicInfo.Band_H*setting.zoom+'%',
				'z-index':setting.z_index,
				'background-color':setting.bgcolor,
				"border-radius":setting.borderRadius,
				'right':setting.right,
				'background-image':'url('+setting.scImg+')',
				'background-size':'contain'
			});
			$("#"+setting.nekoname).css({
				'position': 'fixed',
				'top': (basicInfo.Band_H*setting.zoom*8/9)+'%',
				'z-index': setting.z_index*10,
				'right':setting.right,
				'background-image':'url('+setting.nekoImg+')',
				'font-family':setting.fontFamily,
				'font-size':setting.fontSize
			});
			if (basicInfo.ScrollTop == basicInfo.S_V) {
				//alert("已经读完了");
				//$("#neko").data("msg","已经读完了");
				$("#"+setting.nekoname).attr("data-msg", setting.endMsg);
				$("#"+setting.nekoname).addClass("showMsg")
			}
			// else if(ScoT_s<=basicInfo.S_V/2 && ScoT_s >= basicInfo.S_V/3){
			// 	$("#neko").attr("data-msg","你在看啥呢？");
			// 	$("#neko").addClass("showMsg")
			// }
			// else if(ScoT_s>=300 && ScoT_s <=400){
			// 	$("#neko").addClass("showMsg");
			// }
			else {
				$("#"+setting.nekoname).removeClass("showMsg");
				$("#"+setting.nekoname).attr("data-msg", setting.hoverMsg);
			}
		});
		this.click(function(e){
			//console.log("Hello");
			var basicInfo = getBasicInfo();
			var x = e.clientX;
			var y = e.clientY;
			var Band_H = basicInfo.Band_H*setting.zoom;
			var ViewH = basicInfo.ViewH;
			var S_V = basicInfo.S_V;
			var	move = 0;
			if(Band_H*ViewH/100>y){
			move = (Band_H*ViewH/100-y)/(ViewH*setting.zoom)*S_V;
			}
			var moveStr = "-="+move
			$("html,body").animate({
				scrollTop: moveStr
			}, setting.during/2);
		});
		$("#"+setting.nekoname).click(function() {
			var scroT = $(window).scrollTop();
			var scroH = $('body')[0].scrollHeight;
			var ViewH = $(window).height();
			var S_V = "+=" + (scroH - ViewH);
			var mv = "-=" + scroT;
			if (scroT == 0) {
				$("html,body").animate({
					scrollTop: S_V
				}, setting.during);
			} else {
				$("html,body").animate({
					scrollTop: mv
				}, setting.during);
		
			}
		});
		return this;
	}
})(jQuery);