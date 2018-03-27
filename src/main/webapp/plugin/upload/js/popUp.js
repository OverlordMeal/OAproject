/*
 * $.getCss(string)	加载css
 * $(dom).omit(int[长度],string[替换字符串])	按长度px截取超出的字符串并把超出部分替换成指定字符串,元素必须style="display:inline;"不然无效	return jqueryObject
 * $.request(obj)	封装$.ajax请求，对登陆超时response.timeout和请求失败做了统一处理，error和$.ajax的error不同，error=function(前台显示的文本)
 * $.singleRequest(obj) 封装$.request请求，保证当前只会有一个ajax请求在处理，上一次的ajax中断	
 */

/*
 * $.msgBox
 * 			|-alert //生成只有“确定”按钮的弹出框
 * 			|	|-title：标题，默认为“警告”；
 * 			|	|-msg：消息体，可以是任意HTML代码，可选项，但与html之间必须一个，与html的区别就是少了个P标签；
 * 			|	|-html：消息体，可以是任意HTML代码，可选项，但与msg之间必须一个；；
 * 			|	|-cls：自定义class，字符串，可选项；
 * 			|	|-width：自定义宽度，数字，可选项；
 *         |   |-time：自动关闭等待的时间，为空是则不自动关闭，可选项；
 *         |   |-timeoutFun:定时关闭弹出框的回调函数
 * 			|	|-icon：自定义显示图标，布尔或字符串，可选项。用于提示框显示图标，默认是false，不显示图标；如果传入true，alert窗体就显示感叹号，prompt窗体就显示问号，window窗体此参数无效。也可以传入字符串，进行自定义，可选的有：“error”-错误、“question”-问号、“warning”-感叹号。当然也可以是其他，但必须设置好样式；
 * 			|	|-autoClose：点击确定按钮是否默认关闭窗体，布尔值，true或者false，默认为true；
 * 			|	|-beforClose：在点击关闭按钮关闭窗体之前执行的方法，传入项应该为一个方法体。
 * 			|	|-textYes：确定按钮上面的文字，默认为“确定”；
 * 			|	|-yesFun：点击确定按钮的执行的回调方法。
 *          |   |-showMask：显示遮罩层，布尔值，true或者false，默认为true；。
 *          |   |-isDrag : 是否可以拖动，true为可以，false为不可以。
 *          |   |-downToup：显示从下到上的缓动效果，布尔值，true或者false，true为无效果 false或不填为有效果；。
 * 			|
 * 			|-prompt //生成具有“确定”和“取消”按钮的弹出框
 * 			|	|-title：标题，默认为“消息”；
 * 			|	|-msg：消息体，可以是任意HTML代码，必选项；
 * 			|	|-cls：自定义class，字符串，可选项；
 * 			|	|-width：自定义宽度，数字，可选项；
 *          |   |-time：自动关闭等待的时间，为空是则不自动关闭，可选项；
 * 			|	|-icon：自定义显示图标，布尔或字符串，可选项。用于提示框显示图标，默认是false，不显示图标；如果传入true，alert窗体就显示感叹号，prompt窗体就显示问号，window窗体此参数无效。也可以传入字符串，进行自定义，可选的有：“error”-错误、“question”-问号、“warning”-感叹号。当然也可以是其他，但必须设置好样式；
 * 			|	|-autoClose：点击确定按钮是否默认关闭窗体，布尔值，true或者false，默认为true；
 * 			|	|-textYes：确定按钮上面的文字，默认为“确定”；
 * 			|	|-textNo：取消按钮上的文字，默认为“取消”；
 * 			|	|-yesFun：点击确定按钮的执行的回调方法；
 * 			|	|-noFun：点击确定按钮的回调方法。
 * 			|	|-beforClose：在点击关闭按钮关闭窗体之前执行的方法，传入项应该为一个方法体。
 *          |   |-showMask：显示遮罩层，布尔值，true或者false，默认为true；。
 *          |   |-isDrag : 是否可以拖动，true为可以，false为不可以。
 *          |   |-downToup：显示从下到上的缓动效果，布尔值，true或者false，true为无效果 false或不填为有效果；。
 *          |   
 * 			|-window //生成简单的弹出框
 * 			|	|-title：标题，默认为“消息”；
 * 			|	|-html：窗体内容，可以是任意HTML代码，必选项；
 * 			|	|-msg：显示消息内容，可以是任意HTML代码，必选项；
 * 			|	|-cls：自定义class，字符串，可选项；
 * 			|	|-width：自定义宽度，数字，可选项；
 *          |   |-time：自动关闭等待的时间，为空是则不自动关闭，可选项；
 * 			|	|-beforClose：在点击关闭按钮关闭窗体之前执行的方法，传入项应该为一个方法体。
 *          |   |-showMask：显示遮罩层，布尔值，true或者false，默认为true；。
 *          |   |-isDrag : 是否可以拖动，true为可以，false为不可以。
 *          |   |-downToup：显示从下到上的缓动效果，布尔值，true或者false，true为无效果 false或不填为有效果；。
 *			|
 *          |destroyTimeout //取消的延迟对话框消失
*           |
 *			|-setDisable //可以选择性地使按钮失效或起效。
 *			|	|-ok：设置OK按钮，true或者false
 *			|	|-no：设置cancel按钮，true或者false
 *			|-maskDiv //生成屏遮层
 *				|-show：开启屏遮层方法
 *				|	|-str：可选参数，用于单独弹出屏遮层时，在屏遮层上显示等待信息！
 *				|-close：关闭屏遮层的方法
 *
 */

(function($){
	$.fn.extend({
		omit: function(c,r){
			return this.each(function(){
				var $this = $(this);
				if ($this.css("display") == "inline") {
					var w = $this.width();
					if (w > c) {
						while(w>c){
							var txt = $this.text();
							$this.text(txt.substring(0,txt.length-1));
							w = $this.width();
						}
						if(r)
							$this.text($this.text()+r);
					}
				}
			});
		}		
	});
	$.extend({
		getCss:function(url){
			var css = document.createElement("link");
			css.setAttribute("rel", "stylesheet");
			css.setAttribute("type", "text/css");
			css.setAttribute("href", url);
			document.getElementsByTagName("head")[0].appendChild(css);
		},
		iSpaceAjax:{
			success:function(response,cb){
				if (response.timeout){
					window.location.href = "/";
				}else{
					if (cb)
						cb.call(null,response);
				}
			},
			error:function(response,resTxt,cb){
				if (cb){
					switch (resTxt){
						case 'timeout': resTxt = "请求超时，系统繁忙，请稍后再试。"; 	break;
						default:
							resTxt = "系统繁忙，请稍后再试。";
					}
					cb.call(null,resTxt);
				}	
			}
		},
		request:function(obj){
			var t_success = obj.success;
			var t_error = obj.error;
			obj.success = function(response){$.iSpaceAjax.success(response,t_success);};
			obj.error = function(response,resTxt){$.iSpaceAjax.error(response,resTxt,t_error);};
			return $.ajax(obj);
		},
		singleRequest:function(obj){
			if ($.iSpaceAjax.single != undefined){
				$.iSpaceAjax.single.abort();
			}
			$.iSpaceAjax.single = $.request(obj);
		},
		//获取鼠标当前坐标
		mouseCoords:function(ev){
			if(ev.pageX || ev.pageY){
				return {x:ev.pageX, y:ev.pageY};}
			return {
				x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
				y:ev.clientY + document.body.scrollTop  - document.body.clientTop
			};
		},
		//获取样式值
		getStyle:function(obj,styleName){
			return obj.currentStyle ? obj.currentStyle[styleName] : document.defaultView.getComputedStyle(obj,null)[styleName];
//                return obj.currentStyle ? obj.currentStyle[styleName] : document.defaultView.getComputedStyle(obj,null).getPropertyValue(styleName);
		}
	});
})(jQuery);




(function($){
	$.extend({
	  	msgBox:{
		width:'300px',//提示框的宽度
		height:'',//提示框的高度
		zIndex:1,
        showMask:true,// 显示遮罩层
		cls:'',//提示框的自定义样式
		boxId:'msgBox_div',
		title:'',//提示框的标题
		time:'',//自动关闭等待的时间，为空是则不自动关闭
		arrow:false,
		contClose:false,
		arrowLeft:'',
		offset : null,
		downToup:null,
		isDrag:true,
		ck_ontime:false, //按钮底部是否有左侧
		ck_content:'', //按钮底部左侧传入的内容
		getIcon:function(p){ //取得图标的class字符串
		   var iconCls="";
		   if(typeof(p.icon)=="boolean")//判断p.icon是否布尔值
			  {
			  	if(p.msgtype=="alert") //判断是否alert窗体
					iconCls=(p.icon)?"class='da-icon da-icon-warning'":"";	//是true就默认给感叹号
				  else if (p.msgtype=="prompt")	//判断是否prompt窗体
					iconCls=(p.icon)?"class='da-icon da-icon-question'":"";	//是true就默认给问号
			  }
			else  if(typeof(p.icon)=="string")//判断是否字符串
				iconCls="class='da-icon da-icon-"+p.icon+"'";//如果是就传给iconCls
			return 	iconCls; //返回值，如果以上条件都不符合，就传出空字符串。
		},
		closeAction:function(p){
			$.msgBox.boxId = p.boxId?p.boxId:"msgBox_div";
			if(p.beforClose)
			{
				if (p.beforClose()) {
					$.msgBox.close();
				}
			} else {
				$.msgBox.close();
			}
		},
		html:function(p){ //返回提示框窗口的消息内容
			var h;
			h=(!p.msg)?"<p "+this.getIcon(p)+">警告框内容显示区</p>":"<p "+this.getIcon(p)+">"+p.msg+"</p>";//设置消息内容
			h=(!p.html)?h:p.html;//设置提示框HTML
			return h;
		},
		textYes:'',//提示框ok按钮要显示的文字
		textNo:'',//提示框cancel按钮要显示的文字
		fun_ok:function(p){//提示框点击ok要调用的方法
			if(p.yesFun!=null){
				p.yesFun();
			}

			if (p.autoClose) {
				$.msgBox.destroy();
			}
			
		},
		fun_no:function(p){//提示框点击cancel要调用的方法
			$.msgBox.boxId = p.boxId?p.boxId:"msgBox_div";
			$.msgBox.destroy();
			if(p.noFun!=null){
				p.noFun();
			}
		},
		destroy:function()//销毁窗口 
		{
			$("#"+$.msgBox.boxId).remove();
			$.msgBox.zIndex=1;
			if($('.da').length==0){
				$.msgBox.maskDiv.close();
				$('.TB_HideSelect').remove();
			}
		},
		close:function()//关闭弹出层
		{			
			/*if(_this){
				
			}*/
			$.msgBox.destroy();
		},
		returnCode:function(){
			return $(this).html();
		},
		bind_funs:function(p){
			if(p.msgtype!="win"){
				//具有“确定”按钮
				$("#"+$.msgBox.boxId+"_btn_ok").bind("click",function(){
						$.msgBox.fun_ok(p);
					});
				//具有“确定”和“取消”两个按钮
				if(p.msgtype=="prompt"){
					$("#"+$.msgBox.boxId+"_btn_no").bind("click",function(){
						$.msgBox.fun_no(p);
					});
				}
			}
			$("#"+$.msgBox.boxId+" span.close").bind("click",function(){   //给窗体的关闭按钮注册事件
						$.msgBox.closeAction(p);
					});
			$("#kkk a.img_closeTOp").bind("click",function(){   //给窗体的关闭事件
				$.msgBox.close();
			});
		},
		enabledDrag:function(mleft,mtop,isDrag){//拖动设置
			var mleft = parseInt(mleft) || 0;
			var mtop = parseInt(mtop) || 0;
			//var w = $('#msgBox_div').width();
			if(isDrag){//设置区域
				$('#msgBox_div').dragDrop({fixarea:[-mleft,$(window).width()+mleft,-mtop,$(window).height()+mtop],focuEle:$('#msgBox_div .da-tl')});
			}
		},
		alert:function(para)//传入配置项：title：标题，默认为“警告”；msg：消息体，必选项；textYes：确定按钮上面的文字，默认为“确定”；yesFun：点击确定按钮的执行的回调方法。
		{
			para.cls='msdTip';//2012-3-10 qing加入样式
			para.msgtype="alert";
			$.msgBox.show(para);
		},
		prompt:function(para)//传入配置项：title：标题；msg：消息体，可以是任意HTML代码；textYes：确定按钮上面的文字，默认为“确定”；textNo：取消按钮上的文字，默认为“取消”；yesFun：点击确定按钮的执行的回调方法；noFun：点击确定按钮的回调方法。
		{
			para.msgtype="prompt";
			if(para.title==""){
				para.cls='msdTip';//2012-3-10 qing加入样式
			}
			$.msgBox.textNo=(!para.textNo)?"取消":para.textNo;
			//$.msgBox.fun_no=(!para.noFun)?function(){$.msgBox.close();}:function(){para.noFun;$.msgBox.close();};//设置点击取消事件
			$.msgBox.show(para);
		},
		window:function(para)//传入配置项：title：标题；msg：消息体，可以是任意HTML代码。
		{
			para.msgtype="win";
			//$.msgBox.textNo=(!para.textNo)?"取消":para.textNo;
			//$.msgBox.fun_no=(!para.noFun)?function(){$.msgBox.close();}:function(){para.noFun;$.msgBox.close();};//设置点击取消事件
			$.msgBox.show(para);
		},
		show:function(para)//传入生成窗体类型标记，1为警告窗；2为确定取消窗
		{
			//--<<<传入参数的判断
			$.msgBox.boxId=(!para.boxId)?"msgBox_div":para.boxId;
			$.msgBox.cls=(!para.cls)?"":para.cls;//设置提示框自定义样式
			$.msgBox.width=(!para.width)?"300px":para.width+"px";//设置提示框自定义宽度
			$.msgBox.height=(!para.height)?"auto":para.height+"px";//设置提示框自定义高度
			$.msgBox.title=(!para.title)?"":para.title;//设置标题
			$.msgBox.arrow=(!para.arrow)?false:para.arrow;//设置箭头
			$.msgBox.contClose=(!para.contClose)?false:para.contClose;//关闭
			$.msgBox.arrowLeft=(!para.arrowLeft)?"20px":para.arrowLeft+"px";//设置箭头左外圆内方偏移
			$.msgBox.textYes=(!para.textYes)?"确定":para.textYes;
			$.msgBox.ck_ontime=(!para.ck_ontime)?false:para.ck_ontime;
			$.msgBox.timeoutFun=(!para.timeoutFun)?null:para.timeoutFun;//定时关闭弹出框的回调函数
			$.msgBox.isDrag=(!para.isDrag)?false:para.isDrag;
			$.msgBox.ck_content=(!para.ck_content)?'':para.ck_content;
			//$.msgBox.fun_ok=(!para.yesFun)?function(){$.msgBox.close();}:para.yesFun;//设置点击事件 
			para.autoClose=(para.autoClose!=null)?para.autoClose:true;//设置自动关闭窗口参数
			
			
			//传入参数的判 断-->>>
			try{this.destroy();}catch(e){};// - 只允许弹出一个框，不允许多个弹出框共存的场景出现。
			
			if(para.showMask!=false){
			    $.msgBox.maskDiv.show();//打开屏遮层
			}
			
			$('body').append($.msgBox.getHtml(para));  //往body里头注入层
			var msgdiv=$('#'+$.msgBox.boxId);
			if($.msgBox.height!="auto")
			{
				$.msgBox.height=parseInt($.msgBox.height)-msgdiv.find(".da-tl").height()-msgdiv.find(".da-bl").height();
				msgdiv.find(".da-mc").height($.msgBox.height);
			}
            msgdiv.find('#'+$.msgBox.boxId+'_table').width($.msgBox.width);
            msgdiv.width($.msgBox.width);
            
			//取提示框的宽度
			var msgBoxWidth=parseInt($.msgBox.width);

			//--<<<给窗体定位
			var t=Math.max(window.document.documentElement.scrollTop, window.document.body.scrollTop)-msgdiv.height()/2;
			var l=Math.max(window.document.documentElement.scrollLeft, window.document.body.scrollLeft)-(parseInt($.msgBox.width)/2);
			
			if(para.offset){
			    t = para.offset.top;
			    l = para.offset.left-msgBoxWidth;	
			   
			    msgdiv.css({
                    left:l,
                    top:t,
                    zIndex:$.msgBox.zIndex*1000
                });//定位提示框到鼠标位置
			    //效果			    
			    var _t = 0;
			    var _d = 30;//步长
			    var _b = para.offset.top;//起始
			    var _c = -msgdiv.height()-10;//结束 由下往上
			    if(!para.downToup){
    			    msgdiv.css('opacity',1);
    		        /*var _vetten = setInterval(function(){
    		            if(_t < _d){ 
    	                    _t++;
                            msgdiv.css('top', Tween.Back.easeInOut(_t,_b,_c,_d)).css('opacity',parseInt(100/_d)*0.01*_t);
    		            }else{
    		                clearInterval(_vetten);
    		                msgdiv.css('opacity',1);
    		            }
    		        },10);*/
			    }else{			        
			        msgdiv.css('top',t);
			    };
				
				//设置拖动起始位置
				$.msgBox.enabledDrag(0,0,para.isDrag);
			    /*
	            msgdiv.animate({
	                top:t,
	                left:l,
	                opacity: 'show'
	            },{ duration: 400});
	            */ 
			}else{
			    msgdiv.css({
	                left:"50%",
	                top:"50%",
	                marginLeft:l,
	                marginTop:t,
                    zIndex:$.msgBox.zIndex*99999
                });//定位提示框到页面的中心位置
			    //效果
			    msgdiv.animate({opacity:'show'},'fast');
				
				//设置拖动起始位置
				$.msgBox.enabledDrag(l,t,para.isDrag);
			};
			//给窗体定位>>>--
			
			msgdiv.css("filter",''); 
			
			/*//ie中添加父标签iframe防止select穿透
            if(navigator.userAgent.indexOf("MSIE")>0){
				var ifam = '<iframe class="" id="boxShade" style="z-index:'+(msgdiv.css('z-index')-1)+';height:'+msgdiv.height()+'px;width:'+msgdiv.width()+'px;"></iframe>';
				msgdiv.warp($(ifam));
			}*/
            if( para.time == "" || typeof(para.time) == "undefined") {
                 
               }else { 
                   //alert(para.time);
                   //$.msgBox.timerout = setTimeout($.msgBox.destroy,para.time);
                   $.msgBox.timerout = setTimeout(function(){
                	   $.msgBox.destroy();
                	   if($.msgBox.timeoutFun){
           				$.msgBox.timeoutFun();
                       }
                   },para.time);
                   $('.da .da-tl .close').hide();
               };
			
			$('#'+$.msgBox.boxId+'_btn_ok').focus();//默认让确定按钮获取焦点！
			$.msgBox.bind_funs(para);
			
			
		},
		
		//取消的延迟对话框消失
        destroyTimeout:function(){
            if($.msgBox.timerout){
                clearTimeout($.msgBox.timerout);
            }
        },
		
		//获取按钮的html：传入字符串"ok"或者"no"
		getBtn:function(t)
		{
			var h;
			switch (t) {
				case "ok" :
					{
						h="<a class='btnSure' id='"+$.msgBox.boxId+"_btn_ok'><span>"+$.msgBox.textYes+"</span></a>";
					}
					break;
				case "no" :
					{
						h="<a class='btnCancel' id='"+$.msgBox.boxId+"_btn_no'><span>"+$.msgBox.textNo+"</span></a>";
					}
					break;
				default :
					return false;
			}
			
			return h;
		},
		setDisable:function(p){
			/*
			var dis=function(t){
				if(!t.y)
					$("#"+$.msgBox.boxId+"_btn_"+t.n).addClass("da-btn-disable").find("input").attr("disabled",true);
				else 
					$("#"+$.msgBox.boxId+"_btn_"+t.n).attr("class","da-btn").find("input").attr("disabled",false);
			};
			if(p.ok!=null)dis({n:"ok",y:p.ok});
			if(p.no!=null)dis({n:"no",y:p.no});
			*/
		},
		getHtml:function(p)
		{
			var htm= "";
			var iput="";
			var btn="";
			switch (p.msgtype) {
				case "alert":{//只有“确定”按钮
					//btn=$.msgBox.getBtn("ok");
				}
				break;
				case "prompt" :{//具有“确定”和“取消”两个按钮
					btn=$.msgBox.getBtn("ok")+$.msgBox.getBtn("no");
				}
				break;
			}
            htm='<table id="'+$.msgBox.boxId+'" class="mBlogLayer da '+$.msgBox.cls+'">'
            + '       <tbody>'
            + '     <tr>'
            + '       <td class="top_l"></td>'
            + '       <td class="top_c"></td>'
            + '       <td class="top_r"></td>'
            + '     </tr>'
            + '     <tr>'
            + '       <td class="mid_l"></td>'
            + '       <td class="mid_c">';
					if($.msgBox.arrow)
					{
						htm+='<div class="arrowUp" style="left:'+$.msgBox.arrowLeft+'"></div>';
					}
					
			        if($.msgBox.title!="")
			        {
                    htm+= '<div class="da-tl">'
                    +'   <span class="pop_title">'+$.msgBox.title+'</span>'
                    +'   <span class="close" title="关闭"><!--关闭--></span>'                  
                    +'</div>';
                    }else{				
						if($.msgBox.contClose)
						{
							htm+='<span class="close" title="关闭"><!--关闭--></span>';
						}	
						if(p.msgtype=='alert'){
							htm+= '<div class="da-tl" style="background:none;height:20px;">'
			                    +'   <span class="close alertClose" title="关闭"><!--关闭--></span>'                  
			                    +'</div>';
						}
					}
                   htm +='<div class="da-mc">'
				   
                          +$.msgBox.html(p);
                  if(p.msgtype!="win")
                      {
						  if(p.ck_ontime&&p.ck_content){
                          htm+='<div class="da-buttons"><span class="zf_send" style="line-height:24px;margin-left:5px;"><input type="checkbox" id="sendComment" style="vertical-align: middle;" />&nbsp;'+p.ck_content+'</span>'
                              +btn
                              +'</div>';
						  }else{
							  htm+='<div class="da-buttons">'+btn+'</div>';
						   }
                      }
                  htm+='</div>'
            //      +'<div class="da-bl"></div>'
          
            + '       </td>'
            + '       <td class="mid_r"></td>'
            + '     </tr>'
            + '     <tr>'
            + '       <td class="bottom_l"></td>'
            + '       <td class="bottom_c"></td>'
            + '       <td class="bottom_r"></td>'
            + '     </tr>'
            + '   </tbody>'
            + ' </table>';
          
      
			return htm;
		},
		maskDiv:{
			html:'<iframe class="TB_HideSelect" style="z-index:92;"></iframe><div id="ScreenDiv" class="ScreenCss"></div>',
			browser: (navigator.userAgent.indexOf("MSIE")>0),
			show:function(str)
			{
				var $body=$('body');
				var $window=$(window);
			//	var h=Math.max(document.documentElement.scrollHeight,document.documentElement.clientHeight);
				var h=$(document).height();
//				var w=($window.width()>$body.width())?$window.width():$body.width();//修改了屏遮层的宽度获取方
				
				$body.append($.msgBox.maskDiv.html);
				var os=$('#ScreenDiv');
				if(str!=null)//如果有传入值，就在屏遮层里面生成一个div来显示传入值。
				{
					os.after('<div class="iLoading" id=iLoadingMsg style="z-index:'+$.msgBox.zIndex*1000+'"><img src="/web4s/images/ajax-loader.gif" style="vertical-align:middle;"/>'+str+'</div>');
				}
				os.css({width:'100%',height:h+'px',zIndex:$.msgBox.zIndex*9999});
				/*
				if ($.msgBox.maskDiv.browser){
						//$("iframe.TB_HideSelect").hide();
						$('#ScreenDiv').hide();
					}
				*/
				//$("iframe.TB_HideSelect").hide();
			},
			close:function()
			{
				$('#ScreenDiv').remove();
				$('.TB_HideSelect').remove();
//				if ($.msgBox.maskDiv.browser){
//						$("select").show();
//					}
				if($("#iLoadingMsg").length)$("#iLoadingMsg").remove();
			}
		}
	},
	createEM:function(msg){
	 		var $this = $(this);
			var nextObj = $this.next();
			if (nextObj.length == 0){
				$this.after('<em class="error">'+msg+'</em>');
			}else{
				var e = nextObj.get(0);
				
				if (e.tagName == ('EM')){
					$(e).text(msg);
				}else{
					$this.append('<em class="error">'+msg+"</em>");
				}
			}
  		},
	cancelEM:function(){
  		var $this = $(this);
		var nextObj = $this.next();
		if (nextObj.length > 0 && nextObj.get(0).tagName == 'EM')
			$(nextObj.get(0)).remove();
	}
	
  });
	
	/*
	算法来源：http://www.robertpenner.com/easing/
	*/
	var Tween = {
	 Linear: function(t,b,c,d){ return c*t/d + b; },
	 Quad: {
	  easeIn: function(t,b,c,d){
	   return c*(t/=d)*t + b;
	  },
	  easeOut: function(t,b,c,d){
	   return -c *(t/=d)*(t-2) + b;
	  },
	  easeInOut: function(t,b,c,d){
	   if ((t/=d/2) < 1) return c/2*t*t + b;
	   return -c/2 * ((--t)*(t-2) - 1) + b;
	  }
	 },
	 Cubic: {
	  easeIn: function(t,b,c,d){
	   return c*(t/=d)*t*t + b;
	  },
	  easeOut: function(t,b,c,d){
	   return c*((t=t/d-1)*t*t + 1) + b;
	  },
	  easeInOut: function(t,b,c,d){
	   if ((t/=d/2) < 1) return c/2*t*t*t + b;
	   return c/2*((t-=2)*t*t + 2) + b;
	  }
	 },
	 Quart: {
	  easeIn: function(t,b,c,d){
	   return c*(t/=d)*t*t*t + b;
	  },
	  easeOut: function(t,b,c,d){
	   return -c * ((t=t/d-1)*t*t*t - 1) + b;
	  },
	  easeInOut: function(t,b,c,d){
	   if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
	   return -c/2 * ((t-=2)*t*t*t - 2) + b;
	  }
	 },
	 Quint: {
	  easeIn: function(t,b,c,d){
	   return c*(t/=d)*t*t*t*t + b;
	  },
	  easeOut: function(t,b,c,d){
	   return c*((t=t/d-1)*t*t*t*t + 1) + b;
	  },
	  easeInOut: function(t,b,c,d){
	   if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
	   return c/2*((t-=2)*t*t*t*t + 2) + b;
	  }
	 },
	 Sine: {
	  easeIn: function(t,b,c,d){
	   return -c * Math.cos(t/d * (Math.PI/2)) + c + b;
	  },
	  easeOut: function(t,b,c,d){
	   return c * Math.sin(t/d * (Math.PI/2)) + b;
	  },
	  easeInOut: function(t,b,c,d){
	   return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;
	  }
	 },
	 Expo: {
	  easeIn: function(t,b,c,d){
	   return (t==0) ? b : c * Math.pow(2, 10 * (t/d - 1)) + b;
	  },
	  easeOut: function(t,b,c,d){
	   return (t==d) ? b+c : c * (-Math.pow(2, -10 * t/d) + 1) + b;
	  },
	  easeInOut: function(t,b,c,d){
	   if (t==0) return b;
	   if (t==d) return b+c;
	   if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
	   return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
	  }
	 },
	 Circ: {
	  easeIn: function(t,b,c,d){
	   return -c * (Math.sqrt(1 - (t/=d)*t) - 1) + b;
	  },
	  easeOut: function(t,b,c,d){
	   return c * Math.sqrt(1 - (t=t/d-1)*t) + b;
	  },
	  easeInOut: function(t,b,c,d){
	   if ((t/=d/2) < 1) return -c/2 * (Math.sqrt(1 - t*t) - 1) + b;
	   return c/2 * (Math.sqrt(1 - (t-=2)*t) + 1) + b;
	  }
	 },
	 Elastic: {
	  easeIn: function(t,b,c,d,a,p){
	   if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
	   if (!a || a < Math.abs(c)) { a=c; var s=p/4; }
	   else var s = p/(2*Math.PI) * Math.asin (c/a);
	   return -(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
	  },
	  easeOut: function(t,b,c,d,a,p){
	   if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
	   if (!a || a < Math.abs(c)) { a=c; var s=p/4; }
	   else var s = p/(2*Math.PI) * Math.asin (c/a);
	   return (a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b);
	  },
	  easeInOut: function(t,b,c,d,a,p){
	   if (t==0) return b;  if ((t/=d/2)==2) return b+c;  if (!p) p=d*(.3*1.5);
	   if (!a || a < Math.abs(c)) { a=c; var s=p/4; }
	   else var s = p/(2*Math.PI) * Math.asin (c/a);
	   if (t < 1) return -.5*(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
	   return a*Math.pow(2,-10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )*.5 + c + b;
	  }
	 },
	 Back: {
	  easeIn: function(t,b,c,d,s){
	   if (s == undefined) s = 1.70158;
	   return c*(t/=d)*t*((s+1)*t - s) + b;
	  },
	  easeOut: function(t,b,c,d,s){
	   if (s == undefined) s = 1.70158;
	   return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
	  },
	  easeInOut: function(t,b,c,d,s){
	   if (s == undefined) s = 1.70158; 
	   if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525))+1)*t - s)) + b;
	   return c/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2) + b;
	  }
	 },
	 Bounce: {
	  easeIn: function(t,b,c,d){
	   return c - Tween.Bounce.easeOut(d-t, 0, c, d) + b;
	  },
	  easeOut: function(t,b,c,d){
	   if ((t/=d) < (1/2.75)) {
	    return c*(7.5625*t*t) + b;
	   } else if (t < (2/2.75)) {
	    return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
	   } else if (t < (2.5/2.75)) {
	    return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
	   } else {
	    return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
	   }
	  },
	  easeInOut: function(t,b,c,d){
	   if (t < d/2) return Tween.Bounce.easeIn(t*2, 0, c, d) * .5 + b;
	   else return Tween.Bounce.easeOut(t*2-d, 0, c, d) * .5 + c*.5 + b;
	  }
	 }
	};

	//////////////////////////////////////////////////////////
	// 元素拖拽插件
    $.fn.dragDrop = function(options){
		var opts = $.extend({},$.fn.dragDrop.defaults,options);
		return this.each(function(){
			//是否正在拖动
	        var bDraging = false;   
			//移动的元素
	        var moveEle = $(this);
			//点击哪个元素，以触发移动。
	        //该元素需要是被移动元素的子元素（比如标题等）
	        var focuEle = opts.focuEle ? $(opts.focuEle,moveEle) : moveEle ;
			if(!focuEle || focuEle.length<=0)
			{
				alert('focuEle is not found! the element must be a child of '+this.id);
				return false;
			}                

			// initDiffX|Y : 初始时，鼠标与被移动元素原点的距离
			// moveX|Y : 移动时，被移动元素定位位置 (新鼠标位置与initDiffX|Y的差值)
			// 如果定义了移动中的回调函数，该对象将以参数传入回调函数。
			var dragParams = {initDiffX:'',initDiffY:'',moveX:'',moveY:''}; 
			//被移动元素，需要设置定位样式，否则拖拽效果将无效。
			//var lt = opts.left ;
			//var tp = opts.top;
	       	//moveEle.css({'position':'absolute','left':'0','top':'0'});
			//点击时，记录鼠标位置
	        //DOM写法： getElementById('***').onmousedown= function(event);
	        focuEle.bind('mousedown',function(e){                
				//标记开始移动
	            bDraging = true;
				//改变鼠标形状
				moveEle.css({'cursor':'move'});
				//捕获事件。（该用法，还有个好处，就是防止移动太快导致鼠标跑出被移动元素之外）
	            if(focuEle.get(0).setCapture)
			    {  
					focuEle.get(0).setCapture();  
			    } 	

				//（实际上是鼠标当前位置相对于被移动元素原点的距离）
                   		 // DOM写法：(ev.clientX + document.body.scrollLeft - document.body.clientLeft) - document.getElementById('***').style.left;

                dragParams.initDiffX = $.mouseCoords(e).x - moveEle.position().left;
				dragParams.initDiffY = $.mouseCoords(e).y - moveEle.position().top;

			 });

			//移动过程
	        focuEle.bind('mousemove',function(e){
				if(bDraging)
				{    
					//被移动元素的新位置，实际上鼠标当前位置与原位置之差
		            //实际上，被移动元素的新位置，也可以直接是鼠标位置，这也能体现拖拽，但是元素的位置就不会精确。
		            dragParams.moveX = $.mouseCoords(e).x - dragParams.initDiffX;
					dragParams.moveY = $.mouseCoords(e).y - dragParams.initDiffY;
					//是否限定在某个区域中移动.
		            //fixarea格式: [x轴最小值,x轴最大值,y轴最小值,y轴最大值]
		            if(opts.fixarea)
					{	
						if(dragParams.moveX<opts.fixarea[0])
						{
							dragParams.moveX=opts.fixarea[0]
						}
						if(dragParams.moveX>opts.fixarea[1])
						{
							dragParams.moveX=opts.fixarea[1]
						}
						if(dragParams.moveY<opts.fixarea[2])
						{
							dragParams.moveY=opts.fixarea[2]
						}
						if(dragParams.moveY>opts.fixarea[3])
						{
							dragParams.moveY=opts.fixarea[3]
						}	
					}

					//移动方向：可以是不限定、垂直、水平。
		            if(opts.dragDirection=='all')
					{
						//DOM写法： document.getElementById('***').style.left = '***px'; 
			             moveEle.css({'left':dragParams.moveX,'top':dragParams.moveY});
					}
					else if (opts.dragDirection=='vertical')
					{
						moveEle.css({'top':dragParams.moveY});
					}
					else if(opts.dragDirection=='horizontal')
					{
						moveEle.css({'left':dragParams.moveX});
					}
					
					//moveEle.css({'margin-left':0,'margin-top':0});
					//如果有回调
		            if(opts.callback){
						//将dragParams作为参数传递
			            opts.callback.call(opts.callback,dragParams);
					}
				}

			 });

			//鼠标弹起时，标记为取消移动
               	focuEle.bind('mouseup',function(e){
				bDraging=false;
				moveEle.css({'cursor':'default'});
				if(focuEle.get(0).releaseCapture){
					focuEle.get(0).releaseCapture();
				}
			});
		});
	};
	//默认配置
    $.fn.dragDrop.defaults = {
		left:'0',
		top:'0',
		focuEle:null,            //点击哪个元素开始拖动,可为空。不为空时，需要为被拖动元素的子元素。
	    callback:null,            //拖动时触发的回调。
        dragDirection:'all',    //拖动方向：['all','vertical','horizontal']
        fixarea:null            //限制在哪个区域拖动,以数组形式提供[minX,maxX,minY,maxY]
    };
})(jQuery);