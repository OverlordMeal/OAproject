$.ajaxSetup({
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	cache : false,
	dataFilter : function(data, type) {
		if (data && type == "json") {
			var jsonData = eval("(" + data + ")");
			if (jsonData.cloud_interceptor_check_success === false) {
				if (jsonData.cloud_interceptor_check_message) {
					alert(jsonData.cloud_interceptor_check_message);
				}
				if (jsonData.cloud_interceptor_check_url) {
					top.location.href = "/d17-demand-manage" + 
										(jsonData.cloud_interceptor_check_url.indexOf("/") == -1 ? "/" : "") +
										jsonData.cloud_interceptor_check_url + 
										(jsonData.cloud_interceptor_check_url.indexOf("?") == -1 ? "?" : "&") +
										"cloud_interceptor_check_message=" + jsonData.cloud_interceptor_check_message;
				}
				
				throw new InterceptorException("拦截器验证失败:" + jsonData.cloud_interceptor_check_message);
				
			}
		}
		return data;
	},
	error: function (XMLHttpRequest, textStatus, errorThrown) {
		if (!(errorThrown instanceof InterceptorException)) {
			alert("操作失败,请联系管理员");
			
			//重新请求token
//			var tokenInput = $('input[name="cloud_form_token_"]');
//			if (tokenInput.length == 1) {
//				$.ajax({
//			        type: "POST",
//			        url: "/d17/token/getToken",
//			        dataType: "json",
//			        data : {
//			        },
//			        success: function(data){ 
//			        	tokenInput.val(data.token);
//			        }
//			    });
//			}
		}
	}
});

function InterceptorException() {}  
InterceptorException.prototype = new Error();  
InterceptorException.prototype.constructor = InterceptorException;  
InterceptorException.prototype.toString = function () {  
    return this.message;  
};  
