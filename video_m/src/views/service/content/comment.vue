<template>
  <div class="app-container">
    <div v-for="i in comment" :key="i.id" class="one_com">
      <span>{{ i.userId }}:&nbsp;{{ i.commentContent }}</span><el-link type="primary" @click="yesdelcom(i.id)">删除评论</el-link>
      <div v-for="j in i.replyList" :key="j.id" class="son">
        <span>{{ j.userId }}:&nbsp; {{ j.commentContent }} &nbsp;<span class="reply">回复:{{ j.replyId }}</span></span><el-link type="primary" @click="yesdelcom(j.id)">删除评论</el-link>
        <div v-for="l in j.replyList" :key="l.id" class="son">
          <span>{{ l.userId }}:&nbsp; {{ l.commentContent }} &nbsp;<span class="reply">回复:{{ l.replyId }}</span></span><el-link type="primary" @click="yesdelcom(l.id)">删除评论</el-link>
          <div v-for="m in l.replyList" :key="m.id" class="son">
            <span>{{ m.userId }}:&nbsp; {{ m.commentContent }} &nbsp;<span class="reply">回复:{{ m.replyId }}</span></span><el-link type="primary" @click="yesdelcom(m.id)">删除评论</el-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getcom, delcom } from '@/api/video/chapter'
export default {
  data() {
    return {
      comment: []
    }
  },
  created() {
    // eslint-disable-next-line no-undef
    const courseid = sessionStorage.getItem('couseid')
    // eslint-disable-next-line no-undef
    getcom(courseid).then((res) => {
      console.log(res)
      this.comment = res.data.commentPage.records
    })
  },
  methods: {
    yesdelcom(cid) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delcom({ id: cid }).then((res) => {
          alert('删除成功')
          this.$router.go(0)
        })
      })
    }
  }
}
</script>

<style scoped>
  .one_com{
    margin: 10px 0;
  }
  .son{
    margin-left: 10px;
  }
  .reply{
    font-size: 10px;
    color: rgb(12, 247, 169);
  }
  .app-container{
    border-color: yellow;
    border: 2px solid yellowgreen;
  }
  .el-link{
    color: red;
    font-size: 2px;
    margin-left: 20px;
  }
</style>
