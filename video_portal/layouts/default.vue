<!--
  - Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
  -->
<template>
  <div class="in-wrap">
    <!--头部布局-->
    <header id="header">
      <section class="container">
        <h1 id="logo">
          <a href="#">
            <img src="~/assets/img/grouplogo.jpg" width="80" height="80" />
          </a>
        </h1>
        <div class="h-ResponseResult-nsl">
          <ul class="nav">
            <router-link to="/" tag="li" active-class="current" exact>
              <a>首页</a>
            </router-link>

            <router-link to="/video" tag="li" active-class="current">
              <a>全部视频</a>
            </router-link>
            <router-link to="/qa" tag="li" active-class="current">
              <a>学习交流</a>
            </router-link>
          </ul>
          <!-- / nav -->
          <ul class="h-ResponseResult-login">
            <li id="no-login" v-if="!loginInfo.id">
              <a href="/login">
                <em class="icon18 login-icon">&nbsp;</em>
                <span class="vam ml5">登录/注册</span>
              </a>
            </li>
            <!--退出-->
            <li
              v-if="loginInfo.id"
              id="is-login-two"
              class="h-ResponseResult-user"
            >
              <a href="#" title>
                <img
                  :src="loginInfo.avatar"
                  width="30"
                  height="30"
                  class="vam picImg"
                  alt
                />
                <span id="userName" class="vam disIb">{{
                  loginInfo.nickname
                }}</span>
              </a>
              <a
                href="javascript:void(0);"
                title="退出"
                @click="logout()"
                class="ml5"
                >退出</a
              >
            </li>
          </ul>
          <aside class="h-ResponseResult-search">
            <form action="#" method="post">
              <label class="h-ResponseResult-s-box">
                <input type="text" placeholder="搜索..." value />
                <button type="submit" class="s-btn">
                  <em class="icon18">&nbsp;</em>
                </button>
              </label>
            </form>
          </aside>
        </div>
        <aside class="mw-nav-btn">
          <div class="mw-nav-icon"></div>
        </aside>
        <div class="clear"></div>
      </section>
    </header>
    <!--引入主体-->
    <nuxt />
  </div>
</template>
<script>
import "~/assets/css/reset.css";
import "~/assets/css/theme.css";
import "~/assets/css/global.css";
import "~/assets/css/web.css";
import "~/assets/css/content.css";
import cookie from "js-cookie";
import loginApi from "~/api/login";

export default {
  data() {
    return {
      loginInfo: {
        id: "",
        avatar: "",
        nickname: "",
      },
    };
  },
  mounted() {
    /*获取token*/
    this.token = this.$route.query.token;
    if (this.token) {
      //判断路径是否有token值
      /*存储token*/
      cookie.set("login_token", this.token, { domain: "localhost" });
      /*获取用户信息*/
      loginApi.getLoginInfo().then((response) => {
        this.loginInfo = response.data.userInfo;
        cookie.set("portal_user", this.loginInfo, { domain: "localhost" });
      });
    }
    // 重新获取用户信息
    this.showInfo();
  },
  methods: {
    // 解析存储的用户信息
    showInfo() {
      let jsonStr = cookie.get("portal_user");
      if (jsonStr) {
        this.loginInfo = JSON.parse(jsonStr);
      }
    },
    // 退出
    logout() {
      cookie.set("portal_user", "", { domain: "localhost" });
      cookie.set("login_token", "", { domain: "localhost" });
      //跳转页面
      window.location.href = "/";
    },
  },
};
</script>
<style>
a {
  color: #919191;
  text-decoration: none !important;
}

a:hover {
  color: #787d82;
}

.h-ResponseResult-nsl .nav {
  display: flex;
  align-items: center;
  /* width: 180px; */
}
.nav a {
  text-align: center;
}

#logo {
  overflow: hidden;
  height: 80px;
  width: 80px;
}

#logo img {
  border-radius: 50%;
}
</style>
