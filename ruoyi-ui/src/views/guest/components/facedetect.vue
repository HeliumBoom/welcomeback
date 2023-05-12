<template>
  <div>
    <el-dialog v-bind="$attrs" v-on="$listeners" @open="onOpen" @close="onClose" title="人脸采集">
      <div style="margin-left: 30px;">
        <el-button type="primary" icon="el-icon-camera" size="medium" @click="callCamera"> 打开摄像头</el-button>
        <el-button type="danger" icon="el-icon-switch-button" size="medium" @click="closeCamera"> 关闭摄像头</el-button>
        <br>
        <i style="font-size: small;">请在采光良好的地方拍摄，当摄像头捕捉到人脸时将会自动拍摄</i>
      </div>
      <div style="text-align: center; margin-top: 15px;">
        <video width="640" height="480" ref="video" autoplay poster="/videoposter.png"></video>
      </div>
    </el-dialog>
    <canvas ref="canvas" width="640" height="480" style="display: none;"></canvas>
  </div>
</template>
<script>
import * as faceapi from '@vladmandic/face-api'

export default {
  inheritAttrs: false,
  components: {},
  props: [],
  data() {
    return {
      timer: '',
      stop: false,
      formData: {
        field101: undefined
      },
      rules: {}
    }
  },
  computed: {},
  watch: {},
  created() {
    faceapi.nets.tinyFaceDetector.loadFromUri('/model')
  },
  mounted() {
  },
  methods: {
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
    repaintCanvas() {
      this.$refs['canvas'].getContext('2d').drawImage(this.$refs['video'], 0, 0, 640, 480)
    },
    callCamera() {
      navigator.mediaDevices.getUserMedia({
        video: true
      }).then(success => {
        this.$refs.video.srcObject = success
        this.$refs.video.play()
        this.timer = setInterval(async() => {
          if (this.stop)
            return;
          this.repaintCanvas()
          const result = await faceapi.detectSingleFace(this.$refs.canvas)
          if (result !== undefined) {
            this.stop = true
            let url = this.$refs.canvas.toDataURL('image/png')
            this.$confirm('已成功采集到人脸信息，点击确认返回完成剩余信息采集，点击取消重新采集人脸信息。', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'info'
            }).then(() => {
              let picFile = this.base64ToFile(url, 'face_detect' + Date.now() + '.png')
              this.$parent.updateFileList(picFile)
              this.close()
            }).catch(() => {
              this.stop = false
            })
          }
        }, 1000)
      }).catch(error => {
        this.$modal.msgError('摄像头开启失败，请检查摄像头是否可用！')
      })
    },
    closeCamera() {
      if (!this.$refs['video'].srcObject) {
        return
      }
      let stream = this.$refs['video'].srcObject
      let tracks = stream.getTracks()
      tracks.forEach(track => {
        track.stop()
      })
      this.$refs['video'].srcObject = null
    },
    onOpen() {
    },
    onClose() {
      this.closeCamera()
    },
    close() {
      clearInterval(this.timer)
      this.$emit('update:visible', false)
    }
  }
}

</script>
<style>
</style>
