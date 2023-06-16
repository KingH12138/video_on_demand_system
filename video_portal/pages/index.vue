<template>
  <div style="overflow: hidden">
    <!-- 轮播图 开始 -->
    <div class="warper" style="position: relative">
      <div class="warper_content">
        <!-- 侧边栏分类 -->
        <div class="category">
          <ul>
            <li v-for="(cate, index) in categoryList" v-bind:key="index">
              <div class="coursex-item1">
                <a class="shuang">{{ cate.title }}</a>
              </div>
            </li>
          </ul>
        </div>
        <!-- 右侧轮播图 -->
        <swiper :options="swiperOptions" class="swiper-container swiper-cus">
          <swiper-slide
            v-for="banner in bannerList"
            :key="banner.id"
            :style="{ background: banner.imageBg }"
          >
            <a target="_blank" :href="banner.linkUrl">
              <img
                style="height: 300px"
                :src="banner.imageUrl"
                :alt="banner.title"
              />
            </a>
          </swiper-slide>
          <div
            class="swiper-pagination swiper-pagination-white pagination"
            slot="pagination"
          ></div>
          <div
            style="left: 200px"
            class="swiper-button-prev swiper-button-white arrow-left"
            slot="button-prev"
          ></div>
          <div
            style="right: 200px"
            class="swiper-button-next swiper-button-white arrow-right"
            slot="button-next"
          ></div>
        </swiper>
      </div>
      <!-- <div class="">剩余的容器</div> -->
    </div>

    <!-- 轮播图 结束 -->
    <!--列表-->
    <div id="videoList">
      <div>
        <section class="container">
          <header class="comm-title">
            <h2 class="tac">
              <span
                class="c-333"
                style="
                  float: left;
                  border-left: 3px solid red;
                  padding-left: 10px;
                "
                >热门视频</span
              >
              <span class="c-333" style="float: right; font-size: 18px"
                ><a href="#">更多</a></span
              >
            </h2>
          </header>
          <div>
            <article class="comm-video-list">
              <ul>
                <li
                  v-for="(content, index) in courseList"
                  class="fl"
                  v-bind:key="index"
                >
                  <div class="cc-l-wrap">
                    <section class="video-img">
                      <img
                        style="width: 268px; height: 148px"
                        :src="content.imgUrl"
                        class="img-responsive"
                        alt="图片"
                      />
                      <div class="cc-mask">
                        <!--            跳转视频详情            -->
                        <a
                          :href="'/video/' + content.id"
                          class="comm-btn c-btn-1"
                          >播放</a
                        >
                      </div>
                    </section>
                    <h3 class="hLh30 txtOf mt10">
                      <a
                        href="#"
                        class="course-title fsize18 c-333"
                        style="font-weight: bold"
                        >{{ content.title }}</a
                      >
                    </h3>
                    <section class="mt10 hLh20 of">
                      <span class="fr jgTag bg-orangered">
                        <i class="c-fff fsize12 f-fA">免费</i>
                      </span>
                      <span class="fl jgAttr c-ccc f-fA">
                        <i class="c-999 f-fA">{{ content.buyCount }}人学习</i>|
                        <i class="c-999 f-fA">{{ content.viewCount }} 人浏览</i>
                      </span>
                    </section>
                  </div>
                </li>
              </ul>
            </article>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<script>
// import MySwiper from "../components/MySwiper";
import axios from "axios";

import banner from "@/api/banner";
import index from "@/api/index";
import { Swiper, SwiperSlide } from "vue-awesome-swiper";

export default {
  components: { Swiper, SwiperSlide },
  data() {
    return {
      courseList: [],
      bannerList: [
        {
          id: "1",
          imageBg: "",
          imageUrl: require("../assets/img/pic/banner01.png"),
          linkUrl: "#",
        },
      ],
      categoryList: [
        {
          id: "1",
          title: "计算机 大数据与人工智能",
        },
        {
          id: "2",
          title: "外语 听力/口语/写作",
        },
        {
          id: "3",
          title: "理学工学农学 数学/物理",
        },
        {
          id: "4",
          title: "考研 数学/英语/政治/408",
        },
        {
          id: "5",
          title: "四六级",
        },
        {
          id: "6",
          title: "大学应试英语 四六级",
        },
      ],
      //轮播图设置 -- 我没动 但是有问题！
      swiperOptions: {
        spaceBetween: 30,
        centeredSlides: true,
        autoplay: {
          delay: 2500,
          disableOnInteraction: true,
        },
        pagination: {
          el: ".swiper-pagination",
          clickable: true,
        },
        //配置导航
        navigation: {
          nextEl: ".swiper-button-next", //下一页dom节点
          prevEl: ".swiper-button-prev", //前一页dom节点
        },
      },
    };
  },

  mounted() {
    //获得全部课程
    this.getAllCourse();
  },
  methods: {
    //获得全部课程
    getAllCourse() {
      axios.get("http://101.33.238.6:8003/course/page").then(
        (success) => {
          let obj = success.data.data.coursePage.records[0];
          this.$set(
            obj,
            "imgUrl",
            require("../assets/img/course/c_course.jpg")
          );
          this.$set(obj, "buyCount", "345");
          this.$set(obj, "viewCount", "465");

          let courseArr = [
            {
              id: "1668186640419450882",
              title: "数据库原理及应用",
              description:
                "11C语言是富有活力的编程语言，广泛用于多种编程领域，在各类编程语言排行榜上常据榜首，其魅力毋庸置疑。 这是一门实践性很强的课程，​这里提供了基础的知识点讲解，配合编程训练，帮助你在实践中掌握编程知识，培养编程能力",
              gmtCreate: "2023-06-12 17:21:03",
              gmtModified: "2023-06-13 12:12:45",
              chapterList: null,
              imgUrl: require("../assets/img/course/rg_course.jpg"),
              buyCount: "237",
              viewCount: "339",
            },
            {
              id: "1668186640419450882",
              title: "微机原理与接口技术",
              description:
                "11C语言是富有活力的编程语言，广泛用于多种编程领域，在各类编程语言排行榜上常据榜首，其魅力毋庸置疑。 这是一门实践性很强的课程，​这里提供了基础的知识点讲解，配合编程训练，帮助你在实践中掌握编程知识，培养编程能力",
              gmtCreate: "2023-06-12 17:21:03",
              gmtModified: "2023-06-13 12:12:45",
              chapterList: null,
              imgUrl: require("../assets/img/course/wj_course.png"),
              buyCount: "332",
              viewCount: "417",
            },
            {
              id: "1668186640419450882",
              title: "数据挖掘原理",
              description:
                "11C语言是富有活力的编程语言，广泛用于多种编程领域，在各类编程语言排行榜上常据榜首，其魅力毋庸置疑。 这是一门实践性很强的课程，​这里提供了基础的知识点讲解，配合编程训练，帮助你在实践中掌握编程知识，培养编程能力",
              gmtCreate: "2023-06-12 17:21:03",
              gmtModified: "2023-06-13 12:12:45",
              chapterList: null,
              imgUrl: require("../assets/img/course/sj_course.jpg"),
              buyCount: "256",
              viewCount: "413",
            },
          ];
          courseArr.unshift(obj);
          this.courseList = courseArr;
        },
        (error) => {
          console.log("error!");
        }
      );
    },
  },
};
</script>
<!-- lang="stylus"  -->
<style scoped>
.yd-open-course-card_intro {
  font-size: 16px;
  font-weight: bold;
  margin: 5px 0;
}
</style>
