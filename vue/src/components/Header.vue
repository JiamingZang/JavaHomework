<template>
  <div style="font-size: 12px; display: flex; padding-left: 0; height: 60px">
    <div style="flex: 1; font-size: 20px">
      <span style="cursor: pointer" @click="collapse">
        <el-icon class="el-icon--right">
          <Menu />
        </el-icon>
      </span>

      <el-breadcrumb
        style="display: inline-block; margin-left: 10px"
        separator="/"
      >
        <el-breadcrumb-item style="font-size: 22px" :to="{ path: '/home' }"
          >首页</el-breadcrumb-item
        >
        <el-breadcrumb-item style="font-size: 22px" :to="{ path: '/user' }"
          >用户管理</el-breadcrumb-item
        >
        <el-breadcrumb-item style="font-size: 22px" :to="{ path: '/user' }"
          >课程管理</el-breadcrumb-item
        >
        <el-breadcrumb-item style="font-size: 22px"></el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <el-avatar
      :src="user.avatar"
      style="
        width: 30px;
        height: 30px;
        border-radius: 50%;
        margin-top: 15px;
        margin-right: 5px;
      "
    ></el-avatar>

    <el-dropdown style="width: 120px; line-height: 60px; cursor: pointer">
      <span class="el-dropdown-link">
        {{ user.username }}
        <el-icon class="el-icon--right">
          <ArrowDown />
        </el-icon>
      </span>

      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item @click="this.$router.push('/person')"
            >个人信息</el-dropdown-item
          >
          <el-dropdown-item @click="logout">退出系统</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapse: Boolean,
  },
  data() {
    return {
      user: localStorage.getItem("user")
        ? JSON.parse(localStorage.getItem("user"))
        : {},
    };
  },
  methods: {
    logout() {
      this.$router.push("/login");
      localStorage.removeItem("user");
      this.$message.success("退出系统");
    },
  },
};
</script>

<script setup>
import { ArrowDown, Menu } from "@element-plus/icons-vue";
</script>

<style scoped></style>
