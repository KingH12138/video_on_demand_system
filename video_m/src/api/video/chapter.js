
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
// 获得章节名
export const getzhanglist = () => request({
  url: '/chapter/list',
  method: 'get'
})
// 删除章节
export const delchapter = (body) => request({
  url: '/chapter',
  method: 'delete',
  params: body
})
// 删除视频
export const delvidio = (body) => request({
  url: '/vod',
  method: 'delete',
  params: body
})
// 查看评论
export const getcom = (id) => request({
  url: `/comment/${id}`,
  method: 'get'

})
// 删除评论
export const delcom = (body) => request({
  url: '/comment',
  method: 'delete',
  params: body
})
