function fn(){
	
	var config = {
		
		name : "Akshay" ,
		tokenID : "fa100e7b3ed6a1f7203c4e370469c0dbdfb794bfbe74e1b547ae77bb2a12a1fd" ,
		baseUrl : "https://gorest.co.in"
		
	};
	
	var env = karate.env
	karate.log('The value of env is : ', env);
	
	if(env == 'qa'){
		
		config.baseUrl = "https://gorest.co.in"
		
	} else if(env == 'dev')
	{
		config.baseUrl = "https://gorest.co.in"
	}else
	{
		config.baseUrl = "https://gorest.co.in"
	}

	karate.configure('connectTimeout', 6000);
	karate.configure('readTimeout', 6000);
	
	return config;
}