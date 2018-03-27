var login = {
	
	initSignForm:function() {
		
		$("#password").keypress(function (event ) {
			if(event.which==13) {
				$('#signForm').click();
			}
		});
		
		var options = {
            dataType : 'json',
            beforeSerialize : function(form, options) {
                options.url = config.baseURL + 'user/login';
            },
            beforeSubmit : function() {
            	if(!$('#userName').val()){
            		$('#loginError').html("用户名为空！");
            		$('.error_box').show();
                	return false;
                }
                if(!$('#password').val()){
            		$('#loginError').html("密码为空！");
            		$('.error_box').show();
                    return false;
                }
            	
                $('#focus').attr('action', config.baseURL + 'user/login');
                return true;
            },
            success : function(data) {
                if(data.result){ 
                    location.href = data.redirectURL+"?t="+new Date().getTime();
                }else{
            		$('#loginError').html("用户名或密码错误！");
            		$('.error_box').show();
                }
            }
        };
        
        $('#signForm').click(function() {
            $('#focus').ajaxSubmit(options);
            return false;
        });
	}
};