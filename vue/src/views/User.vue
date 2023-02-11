<template>
  <div>
    <div style="display: flex; height: 40px; margin-bottom: 10px">
      <div style="margin: 10px 0; width: 40%">
        <el-input
          v-model="username"
          placeholder="请输入用户名搜索"
          style="width: 250px"
          clearable
        ></el-input>
        <el-button style="margin-left: 5px" @click="load"
          >查询
          <el-icon class="el-icon--right">
            <Search />
          </el-icon>
        </el-button>
        <el-button style="margin-left: 5px" @click="reset"
          >重置
          <el-icon class="el-icon--right">
            <CircleClose />
          </el-icon>
        </el-button>
      </div>

      <div style="width: 30%" />
    </div>

    <el-scrollbar ref="scrollbarRef" height="78vh" always @scroll="scroll">
      <el-table
        :data="tableData"
        border
        style="width: 100%"
        header-cell-class-name="header"
        stripe="true"
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          type="selection"
          width="55"
          align="center"
        ></el-table-column>
        <el-table-column prop="username" label="学生名" align="center" />
        <el-table-column prop="id" label="学生id" align="center" />
        <el-table-column
          fixed="right"
          label="课程列表"
          width="200px"
          align="center"
        >
          <template #default="scope">
            <el-button size="mini" @click="getCourses(scope.row.id)"
              >查看
              <el-icon class="el-icon--right">
                <EditPen />
              </el-icon>
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-scrollbar>

    <div>
      <el-dialog
        v-model="dialogVisibleA"
        title="提示"
        width="40%"
        :showClose="false"
      >
        <el-scrollbar ref="scrollbarRef" height="60vh" always @scroll="scroll">
          <el-table
            :data="courseTable"
            border
            style="width: 100%"
            header-cell-class-name="header"
            stripe="true"
            @selection-change="handleSelectionChange"
          >
            <el-table-column
              type="selection"
              width="60"
              align="center"
            ></el-table-column>
            <el-table-column prop="id" label="课程id" align="center" />
            <el-table-column prop="name" label="课程名" align="center" />
            <el-table-column prop="teacher" label="教师" align="center" />
            <el-table-column fixed="right" label="操作" align="center">
              <template #default="scope">
                <el-popconfirm
                  title="确定删除吗?"
                  @confirm="deleteStudentcourse(scope.row.id)"
                >
                  <template #reference>
                    <el-button size="mini" type="danger"
                      >删除
                      <el-icon class="el-icon--right">
                        <DeleteFilled />
                      </el-icon>
                    </el-button>
                  </template>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>
        </el-scrollbar>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisibleA = false">取消</el-button>
            <el-button type="primary" @click="dialogVisibleA = false"
              >确认</el-button
            >
          </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script setup>
import {
  Upload,
  CirclePlus,
  CircleClose,
  EditPen,
  DeleteFilled,
  Search,
} from "@element-plus/icons-vue";
</script>

<script>
import request from "@/utils/request";

export default {
  name: "User",
  data() {
    return {
      deleteList: [],
      dialogVisibleA: false,
      dialogVisibleU: false,
      form: {},
      username: "",
      total: 0,
      currentPage: 1,
      pageSize: 10,
      tableData: [],
      courseTable: [],
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      request
        .get("/user/", {
          params: {
            pageNumber: this.currentPage,
            pageSize: this.pageSize,
            search: this.username,
          },
        })
        .then((res) => {
          this.tableData = res.data;
          this.total = res.total;
        });
    },

    getCourses(sid) {
      request
        .get("/studentcourse/getAllCoursesBySid", {
          params: {
            sid: sid,
          },
        })
        .then((res) => {
          this.courseTable = res.data;
          this.dialogVisibleA = true;
        });
    },

    deleteStudentcourse(cid) {
      var param = {
        cid: cid,
        sid: JSON.parse(localStorage.getItem("user"))["id"],
      };
      request.post("/studentcourse/deleteStudentcourse", param).then((res) => {
        if (res.code === 200) {
          this.$message.success("删除成功");
          this.dialogVisibleA = false;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
  },
};
</script>

<style scoped>
.header {
  background: ghostwhite !important;
}
</style>
