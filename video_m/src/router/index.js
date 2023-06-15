
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/**
 * 常量路由-》所有人都可以访问
 */
export const constantRoutes =
  [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: () => import('@/views/login/index'),
      hidden: true
    },
    {
      path: '/main',
      name: '首页',
      // component: () => import('@/views/dashboard/index'),
      component: () => import('@/views/service/content/info')
    },
    {
      path: '/chapter',
      component: () => import('@/views/service/content/chapter')
    },
    {
      path: '/send',
      component: () => import('@/views/service/content/send')
    },
    {
      path: '/list',
      component: () => import('@/views/service/content/list1')
    }
  ]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
