import request from '@/utils/request'

// 查询监控端列表
export function listWebcam(query) {
  return request({
    url: '/welcomeback/webcam/list',
    method: 'get',
    params: query
  })
}

// 查询监控端详细
export function getWebcam(monitorId) {
  return request({
    url: '/welcomeback/webcam/' + monitorId,
    method: 'get'
  })
}

// 新增监控端
export function addWebcam(data) {
  return request({
    url: '/welcomeback/webcam',
    method: 'post',
    data: data
  })
}

// 修改监控端
export function updateWebcam(data) {
  return request({
    url: '/welcomeback/webcam',
    method: 'put',
    data: data
  })
}

// 删除监控端
export function delWebcam(monitorId) {
  return request({
    url: '/welcomeback/webcam/' + monitorId,
    method: 'delete'
  })
}
