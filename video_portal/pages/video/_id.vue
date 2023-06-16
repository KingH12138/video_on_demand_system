<template>
  <div id="videoList" class="bg-fa of">
    <section class="container">
      <div>
        <div class="content-box">
          <div class="fl">
            <img width="450" height="250" :src="courseContent.imgUrl" />
          </div>
          <div class="fl u-coursetitle">
            <h2>
              <span class="u-coursetitle_title">{{ courseContent.title }}</span>
            </h2>
            <div style="margin-top: 10px">
              <div class="fl">{{ courseContent.buyCount }}人学过</div>
              <div class="fl hot">
                <el-rate
                  v-model="value"
                  disabled
                  show-score
                  text-color="#ff9900"
                  score-template="{value}"
                >
                </el-rate>
              </div>
              <div class="clear"></div>
            </div>
            <!-- 添加的一些课程细节 -->
            <div class="u-course-detail" style="margin-top: 30px">
              <div style="color: gray">
                <span>开课时间：</span>
                <span>2023年2月23日~2023年7月21日</span>
              </div>
              <div style="color: gray">
                <span>学时安排：</span>
                <span>3~5小时每周</span>
              </div>
              <div style="color: aqua">进行至16周，共22周</div>
            </div>

            <div class="join">
              <a href="#" class="j-do-trailer" @click="doStudy()"
                ><span>立即学习</span>
              </a>
            </div>
          </div>
        </div>
        <div class="clear"></div>
      </div>
      <div class="mt20 c-infor-box">
        <article class="fl col-7">
          <section class="mr30">
            <el-tabs v-model="activeName" @tab-click="handleClick">
              <el-tab-pane
                style="padding-left: 20px"
                label="视频介绍"
                name="first"
              >
                <div>
                  <h6 class="c-i-content c-infor-title">
                    <span>课程介绍</span>
                  </h6>
                  <div class="course-txt-body-wrap">
                    <section class="course-txt-body" style="padding: 20px">
                      <div v-html="courseContent.description"></div>
                    </section>
                  </div>
                </div>
              </el-tab-pane>
              <el-tab-pane label="视频目录" name="second">
                <div>
                  <div class="f-cb">
                    <h2 class="u-ctit f-thide f-fl">目录</h2>
                  </div>
                  <div class="m-chapterList f-pr">
                    <div
                      class="chapter"
                      v-for="(chapter, index) in courseContent.chapterList"
                      :key="chapter.gmtCreate"
                    >
                      <div class="chapterhead">
                        <span class="f-fl f-thide chaptertitle"
                          >章节{{ index + 1 }}:</span
                        >
                        <span class="f-fl f-thide chaptername">{{
                          chapter.title
                        }}</span>
                      </div>
                      <div>
                        <div
                          class="section"
                          v-for="(video, i) in chapter.videoContentList"
                          :key="video ? video.id : i"
                        >
                          <!-- 跳转视频播放器 -->
                          <a href="#" @click="doStudy">
                            <span class="f-fl f-thide ks">小节{{ i + 1 }}</span
                            ><span class="f-fl type-title">视频</span>
                            <span class="f-fl f-thide ksname">{{
                              video.title
                            }}</span>
                            <a
                              class="f-fr ksjbtn j-hovershow"
                              style="display: none"
                            >
                              <span
                                class="f-fr"
                                v-if="
                                  video.isFree === true &&
                                  (Number(content.price) === 0 || isBuy == true)
                                "
                                >可试看</span
                              >
                              <div class="f-fr ksjicon-look"></div>
                            </a>
                            <span class="f-fr ksinfo j-hoverhide">
                              <span
                                class="f-fr f-thide kstime"
                                style="color: white !important"
                                >{{ video.duration }}</span
                              >
                              <span
                                class="f-fr ksinfoicon ksinfoicon-2"
                                title="视频"
                              ></span>
                              <span
                                class="f-fr canPreview"
                                v-if="
                                  video.isFree === true &&
                                  !(
                                    Number(content.price) === 0 || isBuy == true
                                  )
                                "
                                >可试看</span
                              >
                            </span>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </el-tab-pane>
            </el-tabs>
          </section>
        </article>
        <aside class="fl col-3">
          <div class="i-box">
            <div>
              <section class="c-infor-tabTitle c-tab-title">
                <a title href="javascript:void(0)">作者</a>
              </section>
              <section class="stud-act-list">
                <ul style="height: auto">
                  <li>
                    <div class="u-face">
                      <a :href="'/author/' + content.authorId">
                        <img :src="content.avatar" width="50" height="50" alt />
                      </a>
                    </div>
                    <section class="hLh30 txtOf">
                      <a class="c-333 fsize16 fl" href="#">{{
                        content.authorName
                      }}</a>
                    </section>
                    <section class="hLh20 txtOf">
                      <span class="c-999">{{ content.intro }}</span>
                    </section>
                  </li>
                </ul>
              </section>
            </div>
          </div>
        </aside>
        <div class="clear"></div>
      </div>
    </section>
  </div>
</template>

<script>
import axios from "axios";
import content from "@/api/content";
import order from "@/api/order";
import cookie from "js-cookie";

export default {
  data() {
    return {
      courseContent: {},
      content: {
        price: "0",
        avatar: require("../../assets/img/pic/v2.png"),
        authorName: "测试老师",
        intro: "这是一段测试课程介绍。。。",
      },
      contentId: "",
      activeName: "second",
      value: 4.5,
      isBuy: false,
      videoId: "",
    };
  },
  mounted() {
    let courseId = document.location.href.split("/")[4];
    //获取当前课程信息
    axios.get(`http://101.33.238.6:8003/course/${courseId}`).then(
      (success) => {
        // console.log("data", success.data);
        this.courseContent = success.data.data.course;
        this.$set(
          this.courseContent,
          "imgUrl",
          require("../../assets/img/course/c_course.jpg")
        );
        this.$set(this.courseContent, "buyCount", "345");
        this.$set(this.courseContent, "viewCount", "465");
        console.log("data", this.courseContent);
      },
      (error) => {
        console.log("error");
      }
    );
  },
  methods: {
    // 立即学习
    doStudy() {
      //跳转到对应视频播放页面  player/课程id/默认章节id
      window.location.href = "/player/" + this.courseContent.id;
    },
    handleClick(tab, event) {
      this.activeName = tab.paneName;
    },
    // 判断是否登陆
    isLogin() {
      let user = cookie.get("portal_user", { domain: "localhost" });
      return !!user;
    },
  },
};
</script>

<style scoped>
>>> .el-tabs__nav-scroll {
  background: white;
  height: 40px;
}

>>> .el-tabs__nav:first-child {
  margin-left: 15px;
}

>>> .el-tab-pane {
  background: white;
  border: 1px solid #f0f0f0;
}

>>> .el-tab-pane:first-child {
  padding-top: 20px;
}

>>> .el-rate__icon {
  margin-right: -2px;
}

a {
  text-decoration: none;
}

.m-chapterList .section:hover {
  background: #f5f5f5 !important;
}

.j-hoverhide {
  display: none;
}

.m-chapterList .section:hover .j-hoverhide {
  display: block;
}
</style>
