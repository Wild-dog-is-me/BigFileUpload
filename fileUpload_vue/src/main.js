import { createApp } from 'vue'
import App from './App.vue'


// import './assets/main.css'

import ElementPlus from 'element-plus'

import 'element-plus/dist/index.css'
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import * as ElIconModules from '@element-plus/icons-vue';//导入所有element icon图标
import request from "./utils/request";
 // 字典标签组件

const app = createApp(App)
// // 全局方法挂载
app.config.globalProperties.request = request




app.use(ElementPlus, {
    locale: zhCn,
})

  // 全局注册element-plus icon图标组件
Object.keys(ElIconModules).forEach((key) => {
    app.component(key, ElIconModules[key]);
});

app.mount('#app')






