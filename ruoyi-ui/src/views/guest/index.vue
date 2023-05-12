<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <hr />
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-card>
        <div slot="header" class="clearfix">
          <span>信息登记（请注意区分登记类型）</span>
        </div>
        <el-tabs v-model="formType">
          <el-tab-pane label="访客登记" name="guest">
            <GuestInfo></GuestInfo>
          </el-tab-pane>
          <el-tab-pane label="居民登记" name="resident">
            <ResidentInfo></ResidentInfo>
          </el-tab-pane>
        </el-tabs>
      </el-card>

    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系管理人员</span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i> 官网：<el-link
              href="https://github.com/HeliumBoom/welcomeback"
              target="_blank"
            >https://github.com/HeliumBoom/welcomeback</el-link
            >
            </p>
            <p>
              <i class="el-icon-user-solid"></i> QQ：747367636
            </p>
            <p>
              <i class="el-icon-mobile-phone"></i> 电话：<a
              href="javascript:;"
            >18874122828</a
            >
            </p>
            <p>
              <i class="el-icon-money"></i> 微信：<a
              href="javascript:;"
            >xulingxiang2002</a
            >
            </p>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>最新公告</span>
          </div>
          <el-collapse accordion v-for="(item, index) in infoList" :key="index">
            <el-collapse-item :title="item.noticeTitle">
              <div>
                {{item.noticeContent}}
              </div>
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>扫码访问</span>
          </div>
          <div class="body">
            <img
              src="/github.png"
              alt="donate"
              width="50%"
            />
            <img
              src="/homepage.png"
              alt="donate"
              width="50%"
            />
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import GuestInfo from '@/views/guest/info/guestInfo.vue'
import ResidentInfo from '@/views/guest/info/residentInfo.vue'
import * as faceapi from '@vladmandic/face-api'
import request from '@/utils/request'

export default {
  components: { ResidentInfo, GuestInfo },
  created() {
    faceapi.nets.ssdMobilenetv1.loadFromUri('/model')
    faceapi.nets.faceLandmark68Net.loadFromUri('/model')
  },
  mounted() {
    this.getInfo()
  },
  data() {
    return {
      showDialog: false,
      formType: 'guest',
      face: faceapi,
      infoList: []
    }
  },
  methods: {
    getInfo() {
      request({
        url: '/system/notice/list',
        method: 'post'
      }).then(res => {
        this.infoList = res.data
      })
    }
  }
}

</script>
