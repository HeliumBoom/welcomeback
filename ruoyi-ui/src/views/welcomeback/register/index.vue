<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="130px">
      <el-form-item label="相关用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入相关用户id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户真名" prop="realName">
        <el-input
          v-model="queryParams.realName"
          placeholder="请输入用户真名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话号码" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入电话号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预计来访日期" prop="beginDate">
        <el-date-picker clearable
          v-model="queryParams.beginDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预计来访日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预计离开日期" prop="endDate">
        <el-date-picker clearable
          v-model="queryParams.endDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预计离开日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="人脸照片存贮路径" prop="path">
        <el-input
          v-model="queryParams.path"
          placeholder="请输入人脸照片存贮路径"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['welcomeback:register:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['welcomeback:register:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['welcomeback:register:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['welcomeback:register:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="registerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="记录id" align="center" prop="registerId" />
      <el-table-column label="相关用户id" align="center" prop="userId" />
      <el-table-column label="用户真名" align="center" prop="realName" />
      <el-table-column label="电话号码" align="center" prop="phoneNumber" />
      <el-table-column label="预计来访日期" align="center" prop="beginDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.beginDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预计离开日期" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="人脸照片存贮路径" align="center" prop="path" />
      <el-table-column label="是否通过审核" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['welcomeback:register:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="handlePossess(scope.row)"
            v-has-permi="['welcomeback:register:edit']"
          >通过居民认证</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['welcomeback:register:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改信息登记记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="相关用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入相关用户id" />
        </el-form-item>
        <el-form-item label="用户真名" prop="realName">
          <el-input v-model="form.realName" placeholder="请输入用户真名" />
        </el-form-item>
        <el-form-item label="电话号码" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入电话号码" />
        </el-form-item>
        <el-form-item label="预计来访日期" prop="beginDate">
          <el-date-picker clearable
            v-model="form.beginDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预计来访日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预计离开日期" prop="endDate">
          <el-date-picker clearable
            v-model="form.endDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预计离开日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="人脸照片存贮路径" prop="path">
          <el-input v-model="form.path" placeholder="请输入人脸照片存贮路径" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRegister, getRegister, delRegister, addRegister, updateRegister } from "@/api/welcomeback/register";
import request from '../../../utils/request'

export default {
  name: "Register",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 信息登记记录表格数据
      registerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        realName: null,
        phoneNumber: null,
        beginDate: null,
        endDate: null,
        path: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "相关用户id不能为空", trigger: "blur" }
        ],
        realName: [
          { required: true, message: "用户真名不能为空", trigger: "blur" }
        ],
        phoneNumber: [
          { required: true, message: "电话号码不能为空", trigger: "blur" }
        ],
        path: [
          { required: true, message: "人脸照片存贮路径不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handlePossess(row) {
      console.log("尝试通过居民认证")
      this.$confirm('确定让改用户成为正式居民吗？', '提示' ,{
          confirmButtonText: '确定',
          type: 'warning'
      }).then(() => {
        const registerId = row.registerId || this.ids
        console.log("用户id为：", registerId)
        console.log("成功成为正式居民")
        request({
          url: "/register/check?registerId=" + registerId,
          method: 'get'
        })
      }).catch(() => {
        console.log("没能成功成为正式居民")
      })
    },
    /** 查询信息登记记录列表 */
    getList() {
      this.loading = true;
      listRegister(this.queryParams).then(response => {
        this.registerList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        registerId: null,
        userId: null,
        realName: null,
        phoneNumber: null,
        beginDate: null,
        endDate: null,
        path: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.registerId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加信息登记记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const registerId = row.registerId || this.ids
      getRegister(registerId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改信息登记记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.registerId != null) {
            updateRegister(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRegister(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const registerIds = row.registerId || this.ids;
      this.$modal.confirm('是否确认删除信息登记记录编号为"' + registerIds + '"的数据项？').then(function() {
        return delRegister(registerIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('welcomeback/register/export', {
        ...this.queryParams
      }, `register_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
