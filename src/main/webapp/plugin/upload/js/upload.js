var upload = {
		
	projectName : "web-test",
		
	loadUploadFileJs : false,
	
	/**
	 * 初始化上传文件组件
	 * @param inputFileObj
	 * 			input对象
	 * @param formData
	 * 			附加参数
	 * @param callback
	 * 			成功回调函数
	 * @param onchange
	 * 			上传文件修改回调函数
	 */
	upload : function(inputFileObj, formData, callback, onchange) {
		
		if(!$.support.xhrFormDataFileUpload && !upload.loadUploadFileJs) { //IE10以下
			upload.loadUploadFileJs = true;
			upload.loadJS(uploadConfig.jqueryXdrTransportPath, function() {
				upload.loadJS(uploadConfig.jqueryIframeTransportPath, function() {
					upload.uploadInit(inputFileObj, formData, callback, onchange);
				});
			});
		} else {
			upload.uploadInit(inputFileObj, formData, callback, onchange);
		}
	},
	
	uploadInit : function(inputFileObj, formData, callback, onchange) {
		
		upload._uploadInit(uploadConfig.simpleUploadUrl, inputFileObj, formData, callback, onchange);
	},
	
	_uploadInit : function(url, inputFileObj, formData, callback, onchange) {
		
		inputFileObj.fileupload({
	    	url: url,
	    	sequentialUploads: true,
	    	autoUpload : false,
	    	redirect : uploadConfig.redirectHtmlPath,
	        dataType: 'json',
	        formData:formData,
	        xhrFields: {
	            withCredentials: true //Ajax跨域上传允许Cookie
	        },
	        progress: function (e, data) {
	        	var processId = getProcessId(data.files[0].name);
	            var progress = parseInt(data.loaded / data.total * 100);
	            $('#' + processId + ' .process_bar').css('width', progress + '%');
	            $('#' + processId + ' .process_text').text(parseInt(data.bitrate / 8 / 1024) + " kb/s");
	            if (progress == 100) {
	            	$('#' + processId + ' .process_text').text('处理中...');
	            }
	        },
	        add: function (e, data) {
	        	if ($('#fileuploadWin').length <= 0) {
		        	$.msgBox.window({
		    			boxId : 'fileuploadWin',
		    			title : '上传',
		    			html : '<div id="uploadDiv"></div>',
		    			beforClose : function() {
		    				var flag = true;
		    				$('.process_text').each(function() {
		    					var text = $(this).text();
		    					if (text != "完成" && text != "失败") {
		    						alert("上传未完成,请稍等");
		    						flag = false;
		    						return false; 
		    					} 
		    				});
		    				return flag;
		    			}
		    		});
	        	}
	        	
	        	var fileName = data.files[0].name;
	        	var _html = '<div id="' + getProcessId(fileName) + '">';
	        	_html += '<table style="width:100%; table-layout:fixed">';
	        	_html += '<tr>';
	        	_html += '<td nowrap align="left" style="width:22%;"><div class="process_file_name">'+fileName+'</div></td>';
	        	_html += '<td nowrap align="center" style="width:50%"><div class="process_bar" style="width: 0%;"/></td>';
	        	_html += '<td nowrap align="right" style="width:22%"><div class="process_text">上传中...</div></td>';
	        	_html += '</tr>';
	        	_html += '</table>';
	        	_html += '</div>';
	        	
	        	$("#uploadDiv").append(_html);
	        	data.submit();
	        },
	        done: function (e, data) {
	        	if (data.result) {
	        		var fileName = data.files[0].name;
	        		var processId = getProcessId(fileName);
	        		$('#' + processId + ' .process_text').text('完成');
	        		callback(data.result);
	        		var canClose = true;
	        		$("div .process_text").each(function(index){
	        			if ($(this).text() != '完成') {
	        				canClose = false;
	        				return false;
	        			}
	        		});
	        		
	        		if (canClose) {
	        			$.msgBox.close();
	        		}
	        		
	        	} else {
	        		data.fail(e, data);
	        	}
	        },
	        fail : function(e, data) {
	        	alert("文件上传失败");
	        	var processId = getProcessId(data.files[0].name);
	        	$('#' + processId + ' .process_text').text('失败');
	        },
	        change : function(e, data) {
	        	if (onchange) {
	        		return onchange(data.files, data.formData);
	        	} else {
	        		return true;
	        	}
	        }
	    });
		
		function getProcessId(fileName) {
			
			return 'progress_'+ fileName.replace(/[^a-zA-Z0-9]/g, "");
		}
	},
	
	//通用图片格式检查
	commonCheckUploadFile : function(files) {
		for (var i = 0; i < files.length; i++) {
			var fileName = files[i].name;
			var fileExtention = fileName.split('.')[fileName.split('.').length-1].toUpperCase();
			if(!(fileExtention == 'JPG' ||fileExtention == 'JPEG' ||  fileExtention == 'GIF' || fileExtention == 'PNG')){
				alert('请上传JPG,JPEG,GIF,PNG图片');
				return false;
			}
		}
		
		return true;
	},
	
	loadJS : function(url, success) {
	  var domScript = document.createElement('script');
	  domScript.src = url;
	  success = success || function(){};
	  domScript.onload = domScript.onreadystatechange = function() {
	    if (!this.readyState || 'loaded' === this.readyState || 'complete' === this.readyState) {
	      success();
	      this.onload = this.onreadystatechange = null;
	    }
	  };
	  document.getElementsByTagName('head')[0].appendChild(domScript);
	}
};