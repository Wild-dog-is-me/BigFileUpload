import axios from 'axios'
import { ElMessage } from 'element-plus'
//使用axios下面的create([config])方法创建axios实例，其中config参数为axios最基本的配置信息。
const request = axios.create({
	baseURL:'http://localhost:9001', //请求后端数据的基本地址，自定义
	timeout: 50000                   //请求超时设置，单位ms
})

// 添加请求拦截器
request.interceptors.request.use(function (config) {
	//config.headers['Content-Type'] = 'application/json;charset=utf-8';
    let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null
    if (user) {
        config.headers['token'] = user.token;  // 设置请求头
	}
	// console.log(user);
	
    // 在发送请求之前做些什么
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });
request.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
	//console.log("响应拦截器拦截成功");
	if (response.data.code == 200) {
		ElMessage({
			showClose: true,
			message: response.data.message,
			type: 'success',
		})
	} else if (response.data.code == 400){
		ElMessage({
			showClose: true,
			message:response.data.message,
			type: 'error',
		})
	}
	
	
    return response;
  }, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
	console.log(error);
	if (error.code=='ERR_BAD_REQUEST') {
		ElMessage({
			showClose: true,
			message:"404请求失败",
			type: 'error',
		})
	} else {

	}
	
    return Promise.reject(error);
  });
export default request
