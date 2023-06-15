
<template>
  <div class="app-container">
    <h1 style="text-align: center">添加课程</h1>

    <el-form label-width="120px" style="margin-top: 10px">
      <!--标题-->
      <el-form-item label="课程标题">
        <el-input v-model="contentVO.title" placeholder="请填写课程标题" />
      </el-form-item>
      <el-form-item label="简介">
        <el-input v-model="contentVO.description" :min="0" placeholder="请填写简介" />
      </el-form-item>
      <el-button style="margin-top: 12px;" type="primary" @click="saveAndNext">保存并返回</el-button>

    </el-form>

  </div>

</template>

<script>
import { creatcoure } from '@/api/video/chapter'

export default {

  data() {
    return {
      active: 0,
      contentVO: {
        title: '', // 标题
        bridescriptionfe: '' // 总视频数
      }
    }
  },
  mounted() {
  },
  methods: {
    saveAndNext() {
      creatcoure({ title: this.contentVO.title, description: this.contentVO.description }).then((res) => {
        // eslint-disable-next-line no-cond-assign, no-constant-condition
        if (res.success = true) {
          sessionStorage.setItem('courseId', res.data.courseId)
          alert(' 创建成功 ')
          this.$router.push('/list')
        }
      })
      //
    }

  }
}
</script>

<style scoped>

</style>
