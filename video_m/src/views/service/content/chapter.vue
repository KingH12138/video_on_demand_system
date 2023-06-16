
<template>
  <div class="app-container">
    <el-input v-model="zhangjietitle" placeholder="请填写章节标题" />
    <el-button type="primary" @click="chuangjianzhangjie()">创建章节后上传视频</el-button>
    <!-- 添加和修改i小节表单 -->
    <!-- 操作 -->
    <!-- <input ref="file" type="file" class="ggg">
    <el-button type="primary" @click="uploadfile()">上传章节视频</el-button> -->
  </div>

</template>

<script>
import { crzh } from '@/api/video/chapter'
export default {
  data() {
    return {
      zhangjieid: '',
      zhangjietitle: ''
    }
  },
  created() {
  },
  methods: {
    chuangjianzhangjie() {
      crzh({ courseId: sessionStorage.getItem('cI'), title: this.zhangjietitle }).then((res) => {
        // sessionStorage.setItem('chapid', res.data.chapterId)
        alert(' 创建成功 ')
        this.$router.push('/list')
      })
    },
    uploadfile() {
      var forms = new FormData()
      var configs = { headers: { 'Content-Type': 'multipart/form-data' }}
      forms.append('file', this.$refs.file.files[0])
      forms.append('courseId', sessionStorage.getItem('cI'))
      forms.append('chapterId', sessionStorage.getItem('chapid'))
      forms.append('title', 'shiping')
      const info = { courseId: sessionStorage.getItem('cI'), chapterId: sessionStorage.getItem('chapid'), title: 'shiping' }
      this.$axios.post('/vod/upload', forms, configs, info).then(res => {
        // eslint-disable-next-line eqeqeq
        if (res.data.code == 0) {
          this.$router.go(0)
        } else {
          this.$message.error('上传失败')
        }
      })
    }
  }
}
</script>

<style scoped>
.ggg {
  display: block;
  margin: 20px;
}
ul {
  list-style: none;
}

.chapterList {
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}

.chapterList li {
  position: relative;
}

.chapterList p {
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}

.chapterList .acts {
  float: right;
  font-size: 14px;
}

.sectionList {
  padding-left: 50px;
}

.sectionList p {
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dashed #DDD;
}

</style>
