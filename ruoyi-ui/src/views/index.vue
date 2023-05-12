<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <el-collapse v-model="activeNames">
          <el-collapse-item name="1">
            <template slot="title">
              <div style="color: rgb(248, 177, 110); font-size: 20px;">欢迎使用乐归系统</div>
            </template>
            <blockquote class="text-warning" style="font-size: 16px">
              <div>
                如果您是本小区住户，请
                <el-link href="http://localhost:82/" type="primary">点击此处</el-link>
                前往登录界面
              </div>
              <div style="margin-top: 13px;">
                如果您是访客，希望访问本小区的住户请
                <el-link href="http://localhost:82/guest" type="primary">点击此处</el-link>
                进行一些信息登记
              </div>
              <h4 class="text-danger">
                本系统收集的所有个人信息只供本系统作为功能必要数据所用！
              </h4>
            </blockquote>
          </el-collapse-item>
        </el-collapse>
        <hr/>
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>乐归小区访客管理系统</h2>
        <p>
          本小区访客系统采用人脸识别技术进行安全认证，当来访者进入小区时，系统通过人脸识别技术进行自动化认证：将根据预先在系统中登记的信息进行识别，实现智能化登记。而在来访者离开小区时，系统自动记录其离开时间，并将访问记录留存，实现访问记录的持久化。
        </p>
        <p>
          <b>当前版本:</b> <span>1.0.0</span>
        </p>
        <p>
          <el-button
            type="primary"
            icon="el-icon-cloudy"
            plain
            @click="goTarget('https://github.com/HeliumBoom/welcomeback')"
          >前往主页
          </el-button
          >
          <el-button
            icon="el-icon-s-home"
            plain
            @click="goTarget('http://localhost:82/user/profile')"
          >修改个人信息
          </el-button>
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>功能特点</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>时间上做到：</h4>
            <ul>
              <li>快速响应</li>
              <li>精确记录</li>
              <li>高效处理</li>
              <li>及时反馈</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>空间上做到：</h4>
            <ul>
              <li>精确到人</li>
              <li>深入到楼</li>
              <li>多种角度</li>
              <li>全面覆盖</li>
              <li>...</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider/>
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系管理人员</span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i> 官网：
              <el-link
                href="https://github.com/HeliumBoom/welcomeback"
                target="_blank"
              >https://github.com/HeliumBoom/welcomeback
              </el-link
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

import request from '@/utils/request'

export default {
  data() {
    return {
      activeNames: ['1'],
      infoList: []
    }
  },
  mounted() {
    this.init()
    this.getInfo()
  },
  methods: {
    init() {
      if (!this.$auth.hasRoleOr(['admin', 'common', 'guest', 'webcam'])) {
        this.$confirm('为了更好的使用本系统，请先补充一下个人信息', '提示', {
          confirmButtonText: '确定',
          type: 'warning'
        }).then(() => {
          location.href = '/user/profile'
        }).catch(() => {
        })
      }
    },
    getInfo() {
      request({
        url: '/system/notice/list',
        method: 'post'
      }).then(res => {
        this.infoList = res.data
      })
    },
    goTarget(href) {
      window.location = href;
    }
  }
}

</script>
