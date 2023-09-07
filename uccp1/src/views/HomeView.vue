<template>
  <el-container style="height: 100%">
    <!--    侧边栏-->
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
            <el-menu-item index="1-2" @click="task">采控任务流水表</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">OID管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="2-1">OID基本信息表</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>

    <el-container>
      <!--      表头-->
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
        <!--          第一行搜索框-->
        <div style="margin: 10px 0;">
          OID:
          <el-input style="width: 200px" placeholder="请输入OID" suffix-icon="el-icon-search" v-model="oid"></el-input>
          <el-button class="ml-5" type="primary" @click="load1">搜索</el-button>
          <el-button type="warning" @click="reset">重置</el-button>
        </div>
        <!--        第二行操作框-->
        <div style="margin: 10px 0">
          <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="你确定删除这些OID信息吗？"
              @confirm="delBatch">
            <el-button type="danger" slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
            <!--            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>-->
          </el-popconfirm>
          <!--          <el-button type="danger" @click="delBatch">批量删除<i class="el-icon-remove-outline"></i></el-button>-->
          <el-button type="primary" @click="handleAdd" class="ml-5">导入<i class="el-icon-download"></i></el-button>
          <el-button type="primary" @click="exportOID" class="ml-5">导出<i class="el-icon-upload2"></i></el-button>
        </div>
        <!--        展示表格-->
        <el-table :data="tableData" border stripe height="550" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55">
          </el-table-column>
          <el-table-column label="序号" width="50">
            <template slot-scope="scope">
              {{ (scope.$index + 1) + (pageNum - 1) * pageSize }}
            </template>
          </el-table-column>
          <el-table-column prop="oid" label="OID" width="120">
          </el-table-column>
          <el-table-column prop="oidName" label="OID名称" width="120">
          </el-table-column>
          <el-table-column prop="pubFlag" label="是否公有" width="120">
            <template slot-scope="scope">
              <!-- scope.row 包含表格里当前行的所有数据 -->
              <span v-if="scope.row.pubFlag === '0'">否</span>
              <span v-if="scope.row.pubFlag === '1'">是</span>
            </template>
          </el-table-column>
          <el-table-column prop="oidDeviceType" label="OID类型" width="120">
            <template slot-scope="scope">
              <!-- scope.row 包含表格里当前行的所有数据 -->
              <span v-if="scope.row.oidDeviceType === 'A'">计算机</span>
              <span v-if="scope.row.oidDeviceType === 'B'">交换机</span>
              <span v-if="scope.row.oidDeviceType === 'C'">路由器</span>
              <span v-if="scope.row.oidDeviceType === 'D'">防火墙</span>
            </template>
          </el-table-column>
          <el-table-column prop="enterprise" label="厂商" width="120">
          </el-table-column>
          <el-table-column prop="oidDeviceCode" label="设备型号" width="120">
          </el-table-column>
          <el-table-column prop="oidDeviceVersion" label="版本" width="120">
          </el-table-column>
          <el-table-column prop="description" label="描述" width="120">
          </el-table-column>
          <el-table-column label="操作" width="200">
            <template v-slot="scope">
              <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
              <el-popconfirm
                  class="ml-5"
                  confirm-button-text='确定'
                  cancel-button-text='我再想想'
                  icon="el-icon-info"
                  icon-color="red"
                  title="你确定删除该OID信息吗？"
                  @confirm="del(scope.row.oid)">
                <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
              </el-popconfirm>
            </template>
          </el-table-column>
        </el-table>
        <!--        页码部件-->
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

        <!--        form信息表-->
        <el-dialog title="OID信息" :visible.sync="dialogFormVisible" width="30%">
          <el-form :model="form" label-width="100px">
            <el-form-item label="OID" prop="oid">
              <el-input v-model="form.oid" autocomplete="off" :readonly="isUpdate"></el-input>
            </el-form-item>
            <el-form-item label="OID名称">
              <el-input v-model="form.oidName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="是否公有">
              <!--                <el-input v-model="form.PUB_FLAG" autocomplete="off"></el-input>-->
              <el-select v-model="form.pubFlag" placeholder="请选择是否公有">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="OID类型">
              <!--              <el-input v-model="form.oidDeviceType" autocomplete="off" maxlength="2" show-word-limit oninput="value=value.replace(/[^A-Z]/g,'');"></el-input>-->
              <el-select v-model="form.oidDeviceType" placeholder="请选择OID类型">
                <el-option
                    v-for="item in options1"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="厂商">
              <el-input v-model="form.enterprise" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="设备型号">
              <el-input v-model="form.oidDeviceCode" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="版本">
              <el-input v-model="form.oidDeviceVersion" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="描述">
              <el-input v-model="form.description" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </div>
        </el-dialog>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
// @ is an alias to /src

export default {
  components: {},
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      oid: "",
      oidName: "",
      pubFlag: "",
      oidDeviceType: "",
      enterprise: "",
      oidDeviceCode: "",
      oidDeviceVersion: "",
      description: "",
      form: {},
      isUpdate: false,
      allid: "",
      multipleSelection: [],
      dialogFormVisible: false,
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWith: 200,
      logoTextShow: true,
      options: [{
        value: '0',
        label: '否'
      }, {
        value: '1',
        label: '是'
      }],
      options1: [{
        value: 'A',
        label: '计算机'
      }, {
        value: 'B',
        label: '交换机'
      }, {
        value: 'C',
        label: '路由器'
      }, {
        value: 'D',
        label: '防火墙'
      }]
    }
  },
  created() {
    //请求分页查询数据
    this.load()
  },
  methods: {
    collapse() {//点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.sideWith = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {
        this.sideWith = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    //展示界面
    load() {
      this.request.get("http://localhost:9090/oid/queryUccpOidInfoList/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          oid: this.oid
        }
      })
          .then(res => {
            console.log(res)

            this.tableData = res.data
            this.total = res.total
            this.pageNum = res.pageNum
          })
      // fetch("http://localhost:9090/oid/queryUccpOidInfoList/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&OID="+this.OID)
      //     .then(res => res.json()).then(res =>{
      //   console.log(res)
      //   this.tableData = res.data
      //   this.total = res.total
      // })
    },
    load1() {
      this.pageNum = 1
      this.load()
    },
    reset() {
      this.oid = ""
      this.pageNum = 1
      this.load()
    },
    save() {
      if (this.form.oid == undefined || this.form.oid == null || this.form.oid == '') {
        this.$alert('OID不能为空', '错误信息', {
          confirmButtonText: '确定',
        });
      } else {
        if (!this.allid) {
          this.request.post("http://localhost:9090/oid/addOID", this.form).then(res => {
            if (res.code == 200) {
              this.$message.success(res.message)
              this.dialogFormVisible = false
              this.load()
            } else {
              this.$message.error(res.message)
              this.dialogFormVisible = false
            }
          })
        } else {
          this.request.post("http://localhost:9090/oid/updateOID", this.form).then(res => {
            if (res.code == 200) {
              this.$message.success(res.message)
              this.dialogFormVisible = false
              this.load()
            } else {
              this.$message.error(res.message)
              this.dialogFormVisible = false
            }
          })
        }
      }
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    handleAdd() {
      this.dialogFormVisible = true
      this.form = {}
      this.allid = ""
      this.isUpdate = false
    },
    handleEdit(row) {
      this.isUpdate = true
      this.allid = row.oid
      this.form.oid = row.oid
      this.$set(this.form, 'oidName', row.oidName)
      this.$set(this.form, 'pubFlag', row.pubFlag)
      this.$set(this.form, 'oidDeviceType', row.oidDeviceType)
      this.$set(this.form, 'enterprise', row.enterprise)
      this.$set(this.form, 'oidDeviceCode', row.oidDeviceCode)
      this.$set(this.form, 'oidDeviceVersion', row.oidDeviceVersion)
      this.$set(this.form, 'description', row.description)
      this.dialogFormVisible = true
    },
    del(oid) {
      this.request.delete("http://localhost:9090/oid/" + oid).then(res => {
        if (res.code == 200) {
          this.$message.success(res.message)
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("删除失败")
          this.dialogFormVisible = false
        }
      })
    },
    handleSelectionChange(val) {
      console.log(val)
      this.multipleSelection = val
    },
    delBatch() {
      let oid = this.multipleSelection.map(v => v.oid)//[{},{},{}] => [1,2,3]
      if (this.multipleSelection == null || this.multipleSelection == '') {
        this.$message.error("未找到要删除的数据!")
      } else {
        this.request.post("http://localhost:9090/oid/delBatch", oid).then(res => {
          if (res.code == 200) {
            this.$message.success("批量删除成功")
            this.dialogFormVisible = false
            this.load()
          } else {
            this.$message.error(res.message)
            this.dialogFormVisible = false
          }
        })
      }
    },
    exportOID() {
      window.open("http://localhost:9090/oid/export")
      this.$message.success("导出成功！")
    },
    about() {
      this.$router.push('/about')
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
