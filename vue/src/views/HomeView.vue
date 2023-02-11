<template>
  <div>
    <el-container class="layout-container-demo" style="min-height: 100vh">
      <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41)">
        <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
      </el-aside>

      <el-container>
        <el-header style="font-size: 12px; border-bottom: 1px solid #ccc;line-height: 60px;padding-left: 0">
          <Header :collapse="collapse"/>
        </el-header>

        <el-main style="padding-top: 0;padding-left: 10px;">
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>


<script>
import request from "@/utils/request";
import Header from "@/components/Header";
import Aside from "@/components/Aside";
export default {
  name: 'HomeView',
  components: {
    Aside,
    Header
  },
  data(){
    return{
      isCollapse:false,
      sideWidth:200,
      logoTextShow:true,
    }
  },


  methods:{
    collapse(){
      this.isCollapse=!this.isCollapse
      if(this.isCollapse){
        this.sideWidth = 64;
        this.logoTextShow = false
      }else{
        this.sideWidth = 200
        this.logoTextShow = true
      }
    },
  }
}
</script>

<style>

</style>