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
            <el-menu-item index="1-1">采控作业流水表</el-menu-item>
            <el-menu-item index="1-2" @click="task">采控任务流水表</el-menu-item>
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
          作业来源:
          <el-input style="width: 200px" placeholder="请输入作业来源" suffix-icon="el-icon-search"  v-model="plat"></el-input>
          请选择作业运行状态:
<!--          <el-input style="width: 200px" placeholder="请输入作业运行状态" suffix-icon="el-icon-search" class="ml-5" v-model="jobState"></el-input>-->
          <el-select v-model="jobState" placeholder="请选择">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
          请选择作业运行结果:
<!--          <el-input style="width: 200px" placeholder="请输入作业运行结果" suffix-icon="el-icon-search" class="ml-5" v-model="jobRlt"></el-input>-->
          <el-select v-model="jobRlt" placeholder="请选择">
            <el-option
                v-for="item in options1"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
          开始日期:
          <el-input style="width: 200px" type="date" v-model="startTime" class="ml-5">
          </el-input>
          <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          <el-button type="warning" @click="reset">重置</el-button>
        </div>
<!--        <div style="margin: 10px 0;">-->
<!--          开始日期:-->
<!--          <el-input style="width: 200px" type="date" v-model="startTime">-->
<!--          </el-input>-->
<!--          <el-button class="ml-5" type="primary" @click="load1">搜索</el-button>-->
<!--          <el-button type="warning" @click="reset1">重置</el-button>-->
<!--        </div>-->
        <el-table :data="tableData" border stripe height="550">
          <el-table-column label="序号" width="50">
            <template slot-scope="scope">
              {{ (scope.$index+1)+(pageNum-1)*pageSize }}
            </template>
          </el-table-column>
          <el-table-column prop="jobRunId" label="作业运行ID" width="85">
          </el-table-column>
          <el-table-column prop="jobId" label="作业ID" width="80">
          </el-table-column>
          <el-table-column prop="startTime" label="开始日期" width="120">
          </el-table-column>
          <el-table-column prop="endTime" label="结束日期" width="120">
          </el-table-column>
          <el-table-column prop="jobType" label="作业类型" width="80">
          </el-table-column>
          <el-table-column prop="plat" label="作业来源" width="80">
          </el-table-column>
          <el-table-column prop="jobUser" label="执行作业用户" width="100">
          </el-table-column>
          <el-table-column prop="jobRole" label="执行作业角色" width="100">
          </el-table-column>
          <el-table-column prop="jobTenant" label="执行作业租户" width="100">
          </el-table-column>
          <el-table-column prop="jobState" label="作业运行状态" width="100">
            <template slot-scope="scope">
              <!-- scope.row 包含表格里当前行的所有数据 -->
              <span v-if="scope.row.jobState === '0'">准备</span>
              <span v-if="scope.row.jobState === '1'">执行</span>
              <span v-if="scope.row.jobState === '2'">结束</span>
            </template>
          </el-table-column>
          <el-table-column prop="jobRlt" label="作业运行结果" width="100">
            <template slot-scope="scope">
              <!-- scope.row 包含表格里当前行的所有数据 -->
              <span v-if="scope.row.jobRlt === '0'">出错</span>
              <span v-if="scope.row.jobRlt === '1'">正常</span>
            </template>
          </el-table-column>
          <el-table-column prop="errInfo" label="错误信息" width="80">
          </el-table-column>
          <el-table-column prop="execAction" label="执行动作" width="80">
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
      options: [{
        value: '0',
        label: '准备'
      }, {
        value: '1',
        label: '执行'
      }, {
        value: '2',
        label: '结束'
      }],
      options1: [{
        value: '0',
        label: '出错'
      }, {
        value: '1',
        label: '正常'
      }],
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      startTime: "",
      plat: "",
      jobState: "",
      jobRlt: "",
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
      this.request.get("http://localhost:9090/job/uccpEngJobJnlList/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          plat: this.plat,
          jobState: this.jobState,
          jobRlt: this.jobRlt,
          startTime: this.startTime
        }
      })
          .then(res => {
            console.log(res)
            if (res.data == null){
              this.$message.error("展示数据错误！")
            }else{
              this.tableData = res.data
              this.total = res.total
            }
          })


      // fetch("http://localhost:9090/oid/queryUccpOidInfoList/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&OID="+this.OID)
      //     .then(res => res.json()).then(res =>{
      //   console.log(res)
      //   this.tableData = res.data
      //   this.total = res.total
      // })
    },
    // load1() {
    //   this.request.get("http://localhost:9090/job/queryUccpEngJobJnlByStartTime", {
    //     params: {
    //       pageNum: this.pageNum,
    //       pageSize: this.pageSize,
    //       startTime: this.startTime
    //     }
    //   })
    //       .then(res => {
    //         console.log(res)
    //
    //         this.tableData = res.data
    //         this.total = res.total
    //       })
    // },
    // reset1() {
    //   this.startTime = ""
    //   this.load()
    // },
    reset() {
      this.plat = ""
      this.jobState = ""
      this.jobRlt = ""
      this.startTime = ""
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
    home() {
      this.$router.push('/')
      this.$message({
        showClose: true,
        message: 'Welcome',
        type: 'success'
      });
    },
    task() {
      this.$router.push('/task')
      this.$message({
        showClose: true,
        message: 'Welcome',
        type: 'success'
      });
    }
  }
}
</script>

