<template>
  <el-card style="width: 500px;padding: 20px;margin-top: 10px">

    <el-form size="small" label-width="120px">

      <el-upload
          class="avatar-uploader"
          action="api/file/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
      >
        <img v-if="form.avatar" :src="form.avatar" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>

      <el-form-item label="用户名">
        <el-input v-model="form.username" disabled style="width: 70%"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.name" style="width: 70%"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" style="width: 70%"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" style="width: 70%"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input type="textarea" v-model="form.address" style="width: 70%"></el-input>
      </el-form-item>
      <div style="margin: 10px 0;text-align: center">
        <el-button  type="primary" size="normal" autocomplete="off" @click="save">确 定</el-button>
        <el-button  type="danger" style="margin-left: 70px" size="normal" autocomplete="off" @click="this.$router.push('/user')">返 回</el-button>
      </div>
    </el-form>
  </el-card>
</template>

<script setup>
import {
  Plus
} from '@element-plus/icons-vue'
</script>


<script>
import request from "@/utils/request";

export default {
  name: "Person",
  data() {
    return {
      form: {},
      user: localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
    }
  },
  created() {
    request.get("/user/username/"+this.user.username).then(res => {
      if(res.code === '200'){
        this.form = res.data
      }
    })
  },
  methods:{
    save(){
      request.post("/user/update",this.form).then(res => {
        if(res){
          this.$message({
            type:"success",
            message:"保存成功"
          })
        }else{
          this.$message.error("保存失败")
        }
      })
    },

    handleAvatarSuccess(res){
      this.form.avatar = res
    }
  }
}
</script>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>

<style>
.avatar-uploader{
  text-align: center;
  margin-bottom: 10px;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  text-align: center;
}
</style>
