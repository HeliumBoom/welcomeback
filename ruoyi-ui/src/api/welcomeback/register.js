import request from '@/utils/request'

// 查询信息登记记录列表
export function listRegister(query) {
  return request({
    url: '/welcomeback/register/list',
    method: 'get',
    params: query
  })
}

// 查询信息登记记录详细
export function getRegister(registerId) {
  return request({
    url: '/welcomeback/register/' + registerId,
    method: 'get'
  })
}

// 新增信息登记记录
export function addRegister(data) {
  return request({
    url: '/welcomeback/register',
    method: 'post',
    data: data
  })
}

// 修改信息登记记录
export function updateRegister(data) {
  return request({
    url: '/welcomeback/register',
    method: 'put',
    data: data
  })
}

// 删除信息登记记录
export function delRegister(registerId) {
  return request({
    url: '/welcomeback/register/' + registerId,
    method: 'delete'
  })
}
