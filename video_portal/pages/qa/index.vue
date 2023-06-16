<template>
  <div class="discuss-container">
    <div class="header">
      <div class="header-title">
        <h3 class="header-title-l">讨论区</h3>
        <div class="header-title-r"></div>
      </div>
      <div class="header-body">
        欢迎大家来到讨论区！本讨论区供各位同学就课程问题进行交流学习。请同学们认真阅读下面的【讨论区使用规则】，然后再进行相关发表，谢谢！
      </div>
    </div>
    <el-button type="success">发起主题</el-button>
    <!-- 子版块 -->
    <div class="sub-topic">
      <h3 class="sub-topic-title">子板块</h3>
      <div class="sub-topic-list">
        <div class="sub-topic-item">
          <!-- 左侧图像 -->
          <div class="sub-topic-item-l">
            <!-- 图像 -->
            <i class="el-icon-user"></i>
          </div>
          <!-- 右侧内容 -->
          <div class="sub-topic-item-r">
            <div class="sub-topic-item-r-title">老师答疑区</div>
            <div class="sub-topic-item-r-info">
              发表关于作业、测试、课件内容希望能够得到老师回答的疑问。
            </div>
          </div>
        </div>
        <div class="sub-topic-item">
          <!-- 左侧图像 -->
          <div class="sub-topic-item-l">
            <!-- 图像 -->
            <i class="el-icon-chat-line-square"></i>
          </div>
          <!-- 右侧内容 -->
          <div class="sub-topic-item-r">
            <div class="sub-topic-item-r-title">综合讨论区</div>
            <div class="sub-topic-item-r-info">
              发表任何想与大家分享的经验及想法！关于本课程、学习、工作、生活等话题
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 全部主题 -->
    <div class="total-topic">
      <div class="total-topic-top">
        <h3>全部主题</h3>
        <div class="topic-top-r">
          <div
            v-for="(item, index) in selectBtns"
            :key="index"
            @click="clickHandler(index)"
            :class="[currentIndex == index ? 'activeColor' : '']"
          >
            {{ item.name }}
          </div>
        </div>
      </div>

      <div class="total-topic-item">
        <div class="total-topic-item-title">
          历史唯心主义和历史唯物主义的区别是什么？
        </div>
        <div class="total-topic-item-body">
          <div class="item-body-l">
            <div class="item-body-l-name">计算机202001陈明</div>
            <div class="item-body-l-time">
              2023/06/12<span style="margin-left: 5px">发表</span>
            </div>
          </div>
          <div class="item-body-r">
            <div class="item-body-r-check">浏览：1</div>
            <div>回复：0</div>
          </div>
        </div>
      </div>
      <div class="total-topic-item">
        <div class="total-topic-item-title">
          唯心主义和唯物主义最大的区别是什么
        </div>
        <div class="total-topic-item-body">
          <div class="item-body-l">
            <div class="item-body-l-name">计算机202003胡海涛</div>
            <div class="item-body-l-time">
              2023/06/12<span style="margin-left: 5px">发表</span>
            </div>
          </div>
          <div class="item-body-r">
            <div class="item-body-r-check">浏览：0</div>
            <div>回复：0</div>
          </div>
        </div>
      </div>
      <div class="total-topic-item">
        <div class="total-topic-item-title">
          马克思主义对意识形态的看法是什么？
        </div>
        <div class="total-topic-item-body">
          <div class="item-body-l">
            <div class="item-body-l-name">计算机202003杨浩</div>
            <div class="item-body-l-time">
              2023/06/12<span style="margin-left: 5px">发表</span>
            </div>
          </div>
          <div class="item-body-r">
            <div class="item-body-r-check">浏览：0</div>
            <div>回复：0</div>
          </div>
        </div>
      </div>
      <div class="total-topic-item">
        <div class="total-topic-item-title">为什么选择马克思主义思想?</div>
        <div class="total-topic-item-body">
          <div class="item-body-l">
            <div class="item-body-l-name">计算机202002王悦</div>
            <div class="item-body-l-time">
              2023/06/12<span style="margin-left: 5px">发表</span>
            </div>
          </div>
          <div class="item-body-r">
            <div class="item-body-r-check">浏览：0</div>
            <div>回复：0</div>
          </div>
        </div>
      </div>
      <div class="total-topic-item">
        <div class="total-topic-item-title">
          为什么实践是检验真理的唯一标准？
        </div>
        <div class="total-topic-item-body">
          <div class="item-body-l">
            <div class="item-body-l-name">计算机202001陈一</div>
            <div class="item-body-l-time">
              2023/06/12<span style="margin-left: 5px">发表</span>
            </div>
          </div>
          <div class="item-body-r">
            <div class="item-body-r-check">浏览：0</div>
            <div>回复：0</div>
          </div>
        </div>
      </div>
    </div>

    <!-- 分页 -->
    <el-pagination background layout="prev, pager, next" :total="20">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      currentIndex: 0,
      selectBtns: [
        { id: "1", name: "最新发表" },
        { id: "2", name: "最后回复" },
        { id: "3", name: "回复数" },
      ],
    };
  },
  methods: {
    clickHandler(index) {
      this.currentIndex = index;
    },
  },
};
</script>

<style scoped>
.discuss-container {
  width: 1200px;
  margin: 10px auto;
  font-size: 14px;
}
.header .header-title {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.header-title-r {
  display: flex;
  align-items: center;
}
.header-search {
  padding: 5px 10px;
  border: 1px solid rgb(9, 128, 9);
  color: rgb(9, 128, 9);
  border-radius: 10px;
  margin-left: 10px;
  cursor: pointer;
}
.header-body {
  padding: 10px 0;
}

/* 发起主题 */
.send-topic {
  margin: 15px;
}
.send-topic-txt {
  padding: 15px 20px;
  color: white;
  background-color: rgb(13, 196, 13);
  border-radius: 10px;
}
/* 子版块 */
.sub-topic {
  margin: 15px 0;
}

.el-icon-user,
.el-icon-chat-line-square {
  font-size: 20px;
  color: aqua;
}

.sub-topic-item {
  display: flex;
  align-items: center;
  padding: 10px 0;
  line-height: 22px;
}
.sub-topic-item-r {
  margin-left: 10px;
  display: flex;
  flex-direction: column;
}
.sub-topic-title {
  margin: 10px 0;
}
.sub-topic-item-r-title {
  font-size: 16px;
  font-weight: bold;
}
.sub-topic-item-r-info {
  color: gray;
  font-size: 14px;
}

/* 全部主题 */
.total-topic {
  margin-bottom: 20px;
}
.total-topic-top {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.topic-top-r {
  display: flex;
  align-items: center;
}
.activeColor {
  background-color: rgb(9, 128, 9);
  color: #fff !important;
}
.topic-top-r > div {
  padding: 3px;
  margin-left: 8px;
  border: 1px solid gray;
  color: #000;
  width: 60px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  cursor: pointer;
}
.total-topic-item {
  padding: 10px;
  line-height: 22px;
}
.total-topic-item-title {
  margin: 10px 0;
  /* font-weight: bold; */
  font-size: 16px;
}
.total-topic-item-body {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.item-body-l {
  display: flex;
  align-items: center;
}
.item-body-l-name {
  color: rgb(9, 128, 9);
}
.item-body-l-time {
  margin-left: 20px;
  color: gray;
}

.item-body-r {
  display: flex;
  align-items: center;
  color: gray;
}

.item-body-r-check {
  margin-right: 20px;
}
</style>
