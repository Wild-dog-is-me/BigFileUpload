<script setup>
import { ref, nextTick, provide } from "vue";
import { RouterLink, RouterView } from 'vue-router'
//import HelloWorld from './components/HelloWorld.vue'

import FileIndex from './pages/FileIndex.vue'
// 局部组件刷新
    const isRouterAlive = ref(true);
    const reload = () => {
      isRouterAlive.value = false;
      nextTick(() => {
        isRouterAlive.value = true;
      });
    };
    provide("reload", reload);


</script>

<template>
<FileIndex/>
</template>

<style  scoped>
common-layout{
  

  height: 100%;
}
.el-header{
  /* position: fixed;  */
  padding: 0;
  width: 1200px;
 
  margin-top: 0;
}
.el-main{
  padding: 0;
  background-color: rgb(243, 244, 245);
  width: 1200px;
}


</style>
