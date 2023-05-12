<template>
  <div>
    <el-form ref="guestForm" :model="formData" :rules="rules_guest" size="medium" label-width="80px"
             label-position="left"
    >
      <el-form-item label="姓名" prop="realName">
        <el-input v-model="formData.realName" placeholder="请输入您的真实姓名" clearable :maxlength="30"
                  prefix-icon="el-icon-user-solid" :style="{width: '100%'}"
        ></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phonenumber">
        <el-input v-model="formData.phonenumber" placeholder="请输入您的手机号" :maxlength="11" show-word-limit
                  clearable prefix-icon="el-icon-mobile" :style="{width: '100%'}"
        ></el-input>
      </el-form-item>
      <el-form-item label="日期范围" prop="visitDate">
        <el-date-picker type="daterange" v-model="formData.visitDate" format="yyyy-MM-dd"
                        value-format="yyyy-MM-dd" :style="{width: '100%'}" start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        range-separator="至" clearable
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="您的照片" prop="facePic" required>
        <el-upload ref="facePic" :file-list="facePicFileList" :action="facePicAction" :auto-upload="false"
                   :before-upload="facePicBeforeUpload" list-type="picture" accept="image/*" name="正面人脸照片"
                   :on-change="handleChange"
        >
          <el-button size="small" type="primary" icon="el-icon-upload">点击上传</el-button>
          <el-button size="small" type="success" icon="el-icon-camera" @click.stop.prevent="takeByCamera">拍照上传
          </el-button>
          <div slot="tip" class="el-upload__tip">只能上传不超过 2MB 的image/*文件</div>
        </el-upload>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
    <facedetect :visible.sync="showFaceDetector"></facedetect>
  </div>
</template>
<script>
import Facedetect from '@/views/guest/components/facedetect.vue'
import request from '@/utils/request'

export default {
  components: { Facedetect },
  props: [],
  data() {
    return {
      showFaceDetector: false,
      formData: {
        realName: undefined,
        phonenumber: '',
        visitDate: null,
        facePic: null
      },
      rules_guest: {
        realName: [{
          required: true,
          message: '请输入您的真实姓名',
          trigger: 'blur'
        }],
        phonenumber: [{
          required: true,
          message: '请输入您的手机号',
          trigger: 'blur'
        }, {
          pattern: /^1(3|4|5|7|8|9)\d{9}$/,
          message: '手机号格式错误',
          trigger: 'blur'
        }],
        visitDate: [{
          required: true,
          message: '日期范围不能为空',
          trigger: 'change'
        }]
      },
      facePicAction: 'https://jsonplaceholder.typicode.com/posts/',
      facePicFileList: []
    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() {
  },
  methods: {
    doGuestRegister(data) {
      return request({
        url: '/register/guest',
        method: 'post',
        data: data
      })
    },
    takeByCamera() {
      this.showFaceDetector = true
      return false
    },
    handleChange(file, fileList) {
      this.updateFileList(file.raw)
    },
    updateFileList(file) {
      if (!this.facePicBeforeUpload(file)) {
        this.facePicFileList.push(file)
        this.facePicFileList.pop()
        return
      }
      if (this.facePicFileList.length > 0) {
        this.facePicFileList.pop()
      }
      this.facePicFileList.push(file)
      this.formData.facePic = file
    },
    submitForm() {
      this.$refs['guestForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
        let formData = new FormData()
        formData.append('realName', this.formData.realName)
        formData.append('phoneNumber', this.formData.phonenumber)
        formData.append('visitDate', this.formData.visitDate)
        formData.append('facePic', this.formData.facePic)
        this.doGuestRegister(formData).then(res => {
          this.$modal.msgSuccess(res.msg)
        })
      })
    },
    resetForm() {
      this.$refs['guestForm'].resetFields()
      this.facePicFileList = []
    },
    facePicBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 2
      if (!isRightSize) {
        this.$message.error('文件大小超过 2MB')
      }
      let isAccept = new RegExp('image/*').test(file.type)
      if (!isAccept) {
        this.$message.error('应该选择image/*类型的文件')
      }
      return isRightSize && isAccept
    },
    submitUpload() {
      this.$refs['facePic'].submit()
    }
  }
}

</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}

</style>
