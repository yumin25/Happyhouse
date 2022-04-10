import Vue from "vue";
import VueRouter from "vue-router";

import Home from "../views/Home.vue";
import About from "../views/About.vue";
import House from "@/views/House.vue";
import QnA from "../views/QnA.vue";
import QnaBoard from "@/components/qna/QnaBoard.vue";
import QnaRegister from "@/components/qna/QnaRegister.vue";
import QnaAnswer from "@/components/qna/QnaAnswer.vue";
import QnAView from "@/components/qna/QnaView.vue";
import QnADelete from "@/components/qna/QnaDelete.vue";
import QnAUpdate from "@/components/qna/QnaUpdate.vue";

import Member from "../views/Member.vue";
import MemberUpdate from "@/components/user/MemberUpdate.vue";
import MemberLogin from "@/components/user/MemberLogin.vue";
import MemberInfo from "@/components/user/MemberInfo.vue";
import MemberRegister from "@/components/user/MemberRegister.vue";
import KakaoLogin from "@/components/user/KakaoLogin.vue";

import Board from "@/views/Board.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardUpdate from "@/components/board/BoardUpdate.vue";
import BoardView from "@/components/board/BoardView.vue";
import BoardRegister from "@/components/board/BoardRegister.vue";
import BoardDelete from "@/components/board/BoardDelete.vue";

import Quiz from "@/views/Quiz.vue";

//import store from "@/store/index.js";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/about",
    name: "About",
    component: About,
  },

  {
    path: "/house",
    name: "House",
    component: House,
  },
  {
    path: "/quiz",
    name: "Quiz",
    component: Quiz,
  },
  {
    path: "/user",
    name: "Member",
    component: Member,
    redirect: "/user/update",
    children: [
      {
        path: "update",
        name: "MemberUpdate",
        component: MemberUpdate,
      },
      {
        path: "login",
        name: "MemberLogin",
        component: MemberLogin,
      },
      {
        path: "info",
        name: "MemberInfo",
        component: MemberInfo,
      },
      {
        path: "join",
        name: "MemberRegister",
        component: MemberRegister,
      },
      {
        path: "kakao",
        name: "KakaoLogin",
        component: KakaoLogin,
      },
    ],
  },
  {
    path: "/qna",
    name: "QnA",
    component: QnA,
    redirect: "/qna/list",
    children: [
      {
        path: "list",
        name: "QnaBoard",
        component: QnaBoard,
      },
      {
        path: "write",
        name: "QnaRegister",
        component: QnaRegister,
      },
      {
        path: "/answer",
        name: "QnaAnswer",
        component: QnaAnswer,
      },
      {
        path: "detail/:no",
        name: "QnAView",
        component: QnAView,
      },
      {
        path: "delete/:no",
        name: "QnADelete",
        component: QnADelete,
      },
      {
        path: "update/:no",
        name: "QnAUpdate",
        component: QnAUpdate,
      },
    ],
  },
  {
    path: "/notify",
    name: "Board",
    component: Board,
    redirect: "/notify/list",
    children: [
      {
        path: "list",
        name: "BoardList",
        component: BoardList,
      },
      {
        path: "update/:no",
        name: "BoardUpdate",
        component: BoardUpdate,
      },
      {
        path: "detail/:no",
        name: "BoardView",
        component: BoardView,
      },
      {
        path: "register",
        name: "BoardRegister",
        component: BoardRegister,
      },
      {
        path: "delete/:no",
        name: "BoardDelete",
        component: BoardDelete,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
