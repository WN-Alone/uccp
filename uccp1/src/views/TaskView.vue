<template>
  <el-container style="height: 100%">
    <el-aside width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246)">
      <el-menu :default-openeds="['1', '2']" style="height: 100%; overflow-x: hidden"
               background-color="rgb(48, 65, 86)" text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="isCollapse"
      >
        <div style="height: 60px; line-height: 60px; text-align: center">
          <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 3px; margin-right: 5px">
          <b style="color: white" v-show="logoTextShow">后台管理系统</b>
        </div>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-message"></i>
            <span slot="title">采控调度任务</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1" @click="about">采控作业流水表</el-menu-item>
            <el-menu-item index="1-2">采控任务流水表</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">OID管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="2-1" @click="home">OID基本信息表</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="font-size: 20px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">
        <div style="flex: 1; font-size: 18px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <span>张国浩</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>个人信息</el-dropdown-item>
            <el-dropdown-item>退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>

      <el-main>
        <div style="margin: 10px 0;">
          任务运行ID:
          <el-input style="width: 200px" placeholder="请输入任务运行ID" suffix-icon="el-icon-search" v-model="taskId"></el-input>
          <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          <el-button type="warning" @click="reset">重置</el-button>
        </div>

        <el-table :data="tableData" border stripe height="550">
          <el-table-column label="序号" width="50">
            <template slot-scope="scope">
              {{ (scope.$index+1)+(pageNum-1)*pageSize }}
            </template>
          </el-table-column>
          <el-table-column prop="taskId" label="任务运行ID" width="120">
          </el-table-column>
          <el-table-column prop="jobRunId" label="作业运行ID" width="120">
          </el-table-column>
          <el-table-column prop="jobId" label="作业ID" width="120">
          </el-table-column>
          <el-table-column prop="devIp" label="设备IP" width="120">
          </el-table-column>
          <el-table-column prop="startTime" label="开始日期" width="120">
          </el-table-column>
          <el-table-column prop="endTime" label="结束日期" width="120">
          </el-table-column>
          <el-table-column prop="taskState" label="任务运行阶段" width="120">
            <template slot-scope="scope">
              <!-- scope.row 包含表格里当前行的所有数据 -->
              <span v-if="scope.row.taskState === '0'">准备</span>
              <span v-if="scope.row.taskState === '1'">执行</span>
              <span v-if="scope.row.taskState === '2'">结束</span>
            </template>
          </el-table-column>
          <el-table-column prop="taskRlt" label="任务运行结果" width="120">
            <template slot-scope="scope">
              <!-- scope.row 包含表格里当前行的所有数据 -->
              <span v-if="scope.row.taskRlt === '0'">出错</span>
              <span v-if="scope.row.taskRlt === '1'">正常</span>
            </template>
          </el-table-column>
          <el-table-column prop="errInfo" label="错误信息" width="120">
          </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      taskId: "",
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWith: 200,
      logoTextShow: true
    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods: {
    collapse() {//点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse){
        this.sideWith = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      }else{
        this.sideWith = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    //展示界面
    load() {

      this.request.get("http://localhost:9090/task/queryUccpEngTaskJnlList/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          taskId: this.taskId
        }
      })
          .then(res => {
            console.log(res)

            this.tableData = res.data
            this.total = res.total
          })

      // fetch("http://localhost:9090/oid/queryUccpOidInfoList/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&OID="+this.OID)
      //     .then(res => res.json()).then(res =>{
      //   console.log(res)
      //   this.tableData = res.data
      //   this.total = res.total
      // })
    },
    reset() {
      this.taskId = ""
      this.load()
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    about() {
      this.$router.push('/about')
      this.$message({
        showClose: true,
        message: 'Welcome',
        type: 'success'
      });
    },
    home() {
      this.$router.push('/')
      this.$message({
        showClose: true,
        message: 'Welcome',
        type: 'success'
      });
    },
  }
}
</script>

