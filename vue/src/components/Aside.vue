<template>
  <el-scrollbar>
    <el-menu
      style="min-height: 100vh; overflow-x: hidden"
      background-color="rgb(48,65,86)"
      text-color="#fff"
      active-text-color="#ffd04b"
      :collapse-transition="false"
      :collapse="isCollapse"
      router
    >
      <div style="height: 60px; line-height: 60px; text-align: center">
        <img
          src="../assets/logo.png"
          alt=""
          style="width: 20px; position: relative; top: 5px; margin-right: 5px"
        />
        <b style="color: #cccccc" v-show="logoTextShow">学生选课系统</b>
      </div>

      <el-sub-menu index="system">
        <template #title>
          <el-icon><Location /></el-icon>
          <span>系统管理</span>
        </template>

        <el-menu-item index="/home">
          <template #title>
            <el-icon><House /></el-icon>
            <span>主页</span>
          </template>
        </el-menu-item>

        <el-menu-item index="/user">
          <template #title>
            <el-icon><User /></el-icon>
            <span>用户管理</span>
          </template>
        </el-menu-item>
        <el-menu-item index="/course">
          <template #title>
            <el-icon><Lollipop /></el-icon>
            <span>课程管理</span>
          </template>
        </el-menu-item>
      </el-sub-menu>
    </el-menu>
  </el-scrollbar>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean,
  },
  components: { Lollipop },
};
</script>

<script setup>
import {
  Location,
  House,
  User,
  Document,
  Lollipop,
} from "@element-plus/icons-vue";
</script>

<style scoped></style>
