<template>
  <div class="wrapper">
    <div
      style="
        margin: 200px auto;
        width: 350px;
        background-color: #fff;
        height: 330px;
        padding: 20px;
        border-radius: 10px;
      "
    >
      <div
        style="color: #000; font-size: 30px; text-align: center; padding: 20px"
      >
        注 册
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
        <el-form-item prop="repeat">
          <el-input
            placeholder="请确认密码"
            v-model="form.repeat"
            show-password
          >
            <template #prefix>
              <el-icon class="el-input__icon"><Lock /></el-icon>
            </template>
          </el-input>
        </el-form-item>
        <div style="margin: 10px 0; text-align: center; padding: 10px">
          <el-button
            type="primary"
            size="normal"
            autocomplete="off"
            @click="register"
            >确 定</el-button
          >
          <el-button
            type="danger"
            style="margin-left: 70px"
            size="normal"
            autocomplete="off"
            @click="this.$router.push('/login')"
            >取 消</el-button
          >
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Register",
  data() {
    return {
      form: {},
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 5, max: 20, message: "长度在5到20个字符", trigger: "blur" },
        ],
        repeat: [
          { required: true, message: "请输入密码", trigger: "blur" },
          { min: 5, max: 20, message: "长度在5到20个字符", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    register() {
      this.$refs["userForm"].validate((valid) => {
        if (valid) {
          if (this.form.password !== this.form.repeat) {
            this.$message.error("两次输入密码不一致");
            return false;
          }
          request.post("/user/register", this.form).then((res) => {
            if (res.code === 200) {
              this.$message({
                type: "success",
                message: "注册成功",
              });
              this.$router.push("/login");
            } else if (res.code === 400) {
              this.$message.error(res.msg);
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
