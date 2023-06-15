
import request from '@/utils/request'

// 创建课程
export const creatcoure = (body) => request({
  url: '/course',
  method: 'POST',
  data: body
})
// 查看课程
export const getcourse = (body) => request({
  url: 'course/page?currentPage=1',
  method: 'get',
  params: body
})
// 删除课程
export const delco = (body) => request({
  url: 'course',
  method: 'delete',
  params: body
})
// 创建章节
export const crzh = (body) => request({
  url: 'chapter',
  method: 'post',
  data: body
})
