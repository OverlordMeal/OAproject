var richeditor = {

	//初始化富文本控件
	init : function(textAreaId, sessionId, dataFormat, afterBlurHandle) {
		
		KindEditor.ready(function(K) {
			var options = {
					items : [
					         'undo', 'redo', '|', 'fontsize', 'forecolor', 'fontname', 'hilitecolor', 'bold', 'italic', '|',
					         'justifyleft', 'justifycenter', 'justifyright', '|',
					         'table', 'multiimage', '|', 
					         'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', '|',
					         'preview'
					 ],
					 imageFileTypes : '*.jpg;*.jpeg;*.gif;*.png',
					 imageSizeLimit : "2MB",
					 imageUploadLimit : 10,
					 uploadJson : richeditorConfig.simpleUploadUrl + "?d17_session_id=" + sessionId,
					 uploadSuccessReturnDataFormat : function(data) {
						 return dataFormat(data);
					 },
	                 afterBlur: function() {
	                	 this.sync();
	                	 if (afterBlurHandle) {
	                		 afterBlurHandle();
	                	 }
	                 },
	                 autoUpload : true,
	                 redirect : richeditorConfig.redirectUrl
					 
			};
            window.editor = K.create('#' + textAreaId, options);
		});
	}
};