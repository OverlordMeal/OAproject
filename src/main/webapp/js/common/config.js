var config = {
		baseURL : "${js.config.baseURL}",
		
		simpleUploadUrl : "${js.config.simpleUploadUrl}",
		
		init : function() {
			if (!config.simpleUploadUrl) {
				config.simpleUploadUrl = config.baseURL + "upload/simple";
			}
		}
};
config.init();