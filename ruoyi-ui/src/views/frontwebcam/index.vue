<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :sm="12" :lg="12" type="flex" justify="center" align="middle">
        <div>
          <video width="640" height="480" ref="video" autoplay poster="/videoposter.png">
          </video>
        </div>
        <!--开启摄像头-->
        <el-button @click="callCamera" alt="摄像头">开始监控</el-button>
        <el-button type="danger" @click="closeCamera" alt="摄像头">关闭监控</el-button>
        <!--确认-->
        <el-button type="primary" @click="photograph">监控截图</el-button>
      </el-col>
      <el-col :sm="12" :lg="12">
        <!--        <el-button @click="addTimelineItems('测试事件')">增加事件</el-button>-->
        <el-divider></el-divider>
        <el-scrollbar ref="scroll" :style="{height: boundScrollbar()}">
          <el-timeline :reverse="reverse">
            <el-timeline-item
              v-for="(activity, index) in activities"
              :key="index"
              :timestamp="activity.timestamp"
            >
              {{ activity.content }}
            </el-timeline-item>
          </el-timeline>
        </el-scrollbar>
      </el-col>
    </el-row>
    <!--canvas截取流-->
    <canvas ref="canvas" width="640" height="480" style="display: none;"></canvas>
  </div>
</template>

<script>

import { getToken } from '@/utils/auth'
import request from '@/utils/request'
import * as faceapi from '@vladmandic/face-api'

export default {
  data() {
    return {
      reverse: false,
      timer: '',
      activities: [],
      headImgSrc: '',
      isUploading: false,
      utterance: undefined,
      upload: {
        headers: { Authorization: 'Bearer ' + getToken() },
        url: process.env.VUE_APP_BASE_API + '/webcam_detect/uploads'
      }
    }
  },
  created() {
    faceapi.nets.ssdMobilenetv1.loadFromUri('/model')
    faceapi.nets.faceLandmark68Net.loadFromUri('/model')
    if ('speechSynthesis' in window) {
      // 创建 SpeechSynthesisUtterance 对象，并设置文本
      this.utterance = new SpeechSynthesisUtterance()
      this.utterance.text = '默认朗读文字'
    } else {
      console.error('对不起，您的浏览器不支持 Web Speech API')
    }
  },
  methods: {
    speak(text) {
        this.utterance.text = text
        // 执行朗读，并捕捉异常
        try {
          window.speechSynthesis.speak(this.utterance)
        } catch (err) {
          console.error(`朗读出错: ${err}`)
        }
    },
    base64ToFile(urlData, fileName) {
      let arr = urlData.split(',')
      let mime = arr[0].match(/:(.*?);/)[1]
      let bytes = atob(arr[1])
      let n = bytes.length
      let ia = new Uint8Array(n)
      while (n--) {
        ia[n] = bytes.charCodeAt(n)
      }
      return new File([ia], fileName, { type: mime })
    },
    // 调用摄像头
    callCamera() {
      // H5调用电脑摄像头API
      navigator.mediaDevices.getUserMedia({
        video: true
      }).then(success => {
        // 摄像头开启成功
        this.$refs['video'].srcObject = success
        // 实时拍照效果
        this.$refs['video'].play()
        this.addTimelineItems('开始监控')
        this.timer = setInterval(async() => {
          this.repaintCanvas()
          const result = await faceapi.detectAllFaces(this.$refs.canvas).withFaceLandmarks()
          if (result !== undefined && result.length > 0) {
            await this.uploadImg(this.base64ToFile(this.$refs['canvas'].toDataURL('image/png'), 'face_detect' + Date.now() + '.png'))
          }
        }, 5000)
      }).catch(error => {
        this.$modal.msgError('摄像头开启失败，请检查摄像头是否可用！')
      })
    },
    repaintCanvas() {
      this.$refs['canvas'].getContext('2d').drawImage(this.$refs['video'], 0, 0, 640, 480)
    },
    takeShotAsFile() {
      this.repaintCanvas()
      let _this = this
      _this.imgSrc = this.$refs['canvas'].toDataURL('image/png')
      return _this.imgSrc
    },
    photograph() {
      let alink = document.createElement('a')
      alink.href = this.takeShotAsFile()
      alink.download = ''
      alink.click()
    },
    closeCamera() {
      if (!this.$refs['video'].srcObject) {
        this.dialogCamera = false
        return
      }
      let stream = this.$refs['video'].srcObject
      let tracks = stream.getTracks()
      tracks.forEach(track => {
        track.stop()
      })
      this.$refs['video'].srcObject = null
      clearInterval(this.timer)
      this.addTimelineItems('关闭监控')
    },
    getTime() {
      let date = new Date(Date.now())
      let Y = date.getFullYear() + '-'
      let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
      let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' '
      let h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':'
      let m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':'
      let s = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
      return Y + M + D + h + m + s
    },
    addTimelineItems(e) {
      this.activities.push({
        content: e,
        timestamp: this.getTime()
      })
      this.speak(e)
    },
    boundScrollbar() {
      if (this.activities.length > 5) {
        return '360px'
      } else {
        return 'auto'
      }
    },
    scrollDown() {
      this.$refs['scroll'].wrap.scrollTop = this.$refs['scroll'].wrap.scrollHeight
    },
    beforeDestroy() {
      clearInterval(this.timer)
    },
    uploadFace(data) {
      return request({
        url: '/webcam_detect/uploads',
        method: 'post',
        data: data
      })
    },
    async uploadImg(file) {
      let formData = new FormData()
      formData.append('face_detect_file', file)
      this.uploadFace(formData).then(res => {
        this.addTimelineItems(res.msg)
      })
    }
  },
  updated: function() {
    this.scrollDown()
  }
}
</script>
