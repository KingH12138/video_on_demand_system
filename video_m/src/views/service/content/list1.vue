<template>
  <div>
    <el-button type="primary" @click="create()">添加课程</el-button>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="title"
        label="课程名"
        width="180"/>
      <el-table-column
        prop="description"
        label="简介"
        width="800"/>
      <el-table-column
        prop="address"
        label="操作">
        <template slot-scope="scope">
          <el-link type="primary" @click="addzhang(scope.row)">添加章节&nbsp;</el-link>
          <el-link type="primary" @click="showcomment(scope.row)">查看评论</el-link>
          <el-link type="primary" @click="deleteit(scope.row)">删除课程</el-link>
        </template>
      </el-table-column>

    </el-table>
    <el-table
      :data="tableData2"
      style="width: 100%">
      <el-table-column
        prop="title"
        label="章节名"
        width="180"/>
      <el-table-column
        prop="courseId"
        label="课程id"
        width="800"/>
      <el-table-column
        prop="address"
        label="操作">
        <template slot-scope="scope">
          <el-link type="primary" @click="showvich(scope.row)">查看详情&nbsp;</el-link>
          <el-link type="primary" @click="delch(scope.row)">删除章节</el-link>
        </template>
      </el-table-column>

    </el-table>
    <!-- 视频详情 -->
    <el-dialog title="章节视频详情" :visible.sync="dialogFormVisible">
      <input ref="file" type="file" class="ggg">
      <el-input v-model="vidiotitle" placeholder="请填写视频标题" />
      <el-button type="primary" @click="uploadfile()">给本章上传视频</el-button>
      <el-table
        :data="chap_v.videoContentList"
        style="width: 100%">
        <el-table-column
          prop="title"
          label="章节名"
          width="180"/>
        <el-table-column
          prop="videoOriginalName"
          label="videoOriginalName"
        />
        <el-table-column
          prop="address"
          label="操作">
          <template slot-scope="scope">
            <el-link type="primary" @click="delvi(scope.row)">删除视频</el-link>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import { getcourse, delco, getzhanglist, delchapter, delvidio } from '@/api/video/chapter'
export default {
  data() {
    return {
      tableData: [],
      tableData2: [],
      dialogFormVisible: false,
      chap_v: [],
      vidiotitle: ''
    }
  },
  methods: {
    create() {
      this.$router.push('/main')
    },
    addzhang(roe) {
      sessionStorage.setItem('cI', roe.id)
      this.$router.push('/chapter')
    },
    deleteit(row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delco({ id: row.id }).then((res) => {
          alert('删除成功')
          this.$router.go(0)
        })
      })
    },
    delch(row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delchapter({ id: row.id }).then((res) => {
          alert('删除成功')
          this.$router.go(0)
        })
      })
    },
    showvich(row) {
      this.chap_v = row
      this.dialogFormVisible = true
    },
    uploadfile() {
      var forms = new FormData()
      var configs = { headers: { 'Content-Type': 'multipart/form-data' }}
      forms.append('file', this.$refs.file.files[0])
      forms.append('courseId', this.chap_v.courseId)
      forms.append('chapterId', this.chap_v.id)
      forms.append('title', this.vidiotitle)
      const info = {}
      this.$axios.post('/vod/upload', forms, configs, info).then(res => {
        // eslint-disable-next-line eqeqeq
        if (res.data.code == 0) {
          this.$router.go(0)
        } else {
          this.$message.error('上传失败')
        }
      })
    },
    delvi(row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        delvidio({ videoId: row.videoSourceId }).then((res) => {
          alert('删除成功')
          this.$router.go(0)
        })
      })
    },
    showcomment(row) {
      sessionStorage.setItem('couseid', row.id)
      this.$router.push('/comment')
    }
  },
  mounted() {
    getcourse().then((res) => {
      this.tableData = res.data.coursePage.records
    // eslint-disable-next-line no-sequences
    }),
    getzhanglist().then((res) => {
      this.tableData2 = res.data.chapterList
    })
  }
}
</script>

<style scoped>
  .ggg {
  display: block;
  margin: 20px;
}
</style>
