<template>
  <div class="wrapper">
    <div
      style="
        margin: 200px auto;
        width: 350px;
        background-color: #fff;
        height: 280px;
        padding: 20px;
        border-radius: 10px;
      "
    >
      <div
        style="color: #000; font-size: 30px; text-align: center; padding: 30px"
      >
        欢迎登录
      </div>
      <el-form ref="userForm" :model="form" size="normal" :rules="rules">
        <el-form-item prop="username">
          <el-input placeholder="请输入用户名" v-model="form.username">
            <template #prefix>
              <el-icon class="el-input__icon"><Avatar /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            placeholder="请输入密码"
            v-model="form.password"
            show-password
          >
            <template #prefix>
              <el-icon class="el-input__icon"><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <div style="margin: 10px 0; text-align: center">
          <el-button
            type="primary"
            size="normal"
            autocomplete="off"
            @click="login"
            >登 录</el-button
          >
          <el-button
            type="danger"
            style="margin-left: 70px"
            size="normal"
            autocomplete="off"
            @click="this.$router.push('/register')"
            >注 册</el-button
          >
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Login",
  data() {
    return {
      form: {},
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    login() {
      this.$refs["userForm"].validate((valid) => {
        if (valid) {
          request.post("/user/login", this.form).then((res) => {
            if (res.code === 200) {
              this.$message({
                type: "success",
                message: "登录成功",
              });
              localStorage.setItem("user", JSON.stringify(res.data));
              this.$router.push("/home");
            } else {
              this.$message.error("用户名或密码错误");
            }
          });
        }
      });
    },
  },
};
</script>

<script setup>
import { Avatar, Lock } from "@element-plus/icons-vue";
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #fc466b, #3f5efb);
  overflow: hidden;
}
</style>
