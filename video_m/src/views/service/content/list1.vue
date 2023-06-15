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
          <el-link type="primary" @click="changeit(scope.row)">修改课程</el-link>
          <el-link type="primary" @click="deleteit(scope.row)">删除课程</el-link>
        </template>
      </el-table-column>

    </el-table>
  </div>
</template>

<script>
import { getcourse, delco } from '@/api/video/chapter'
export default {
  data() {
    return {
      tableData: []
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
          this.$router.go(0)
        })
      })
    }
  },
  mounted() {
    getcourse().then((res) => {
      this.tableData = res.data.coursePage.records
    })
  }
}
</script>
