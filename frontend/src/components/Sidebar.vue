<template>
  <nav
    class="
      md:left-0
      md:block
      md:fixed
      md:top-0
      md:bottom-0
      md:overflow-y-auto
      md:flex-row
      md:flex-nowrap
      md:overflow-hidden
      shadow-xl
      bg-white
      flex flex-wrap
      items-center
      justify-between
      relative
      md:w-64
      z-10
      py-4
      px-6
    "
  >
    <div
      class="
        md:flex-col md:items-stretch md:min-h-full md:flex-nowrap
        px-0
        flex flex-wrap
        items-center
        justify-between
        w-full
        mx-auto
      "
    >
      <!-- Toggler -->
      <button
        class="
          cursor-pointer
          text-black
          opacity-50
          md:hidden
          px-3
          py-1
          text-xl
          leading-none
          bg-transparent
          rounded
          border border-solid border-transparent
        "
        type="button"
        v-on:click="toggleCollapseShow('bg-white m-2 py-3 px-6')"
      >
        <i class="fas fa-bars"></i>
      </button>
      <!-- Brand -->
      <a
        class="
          md:block
          text-left
          md:pb-2
          text-blueGray-600
          mr-0
          inline-block
          whitespace-nowrap
          text-sm
          uppercase
          font-bold
          p-4
          px-0
        "
        @click="moveDashboard"
      >
        해피하우스
      </a>
      <!-- User -->
      <ul class="md:hidden items-center flex flex-wrap list-none">
        <li class="inline-block relative">
          <notification-dropdown-component></notification-dropdown-component>
        </li>
        <li class="inline-block relative">
          <user-dropdown-component></user-dropdown-component>
        </li>
      </ul>
      <!-- Collapse -->
      <div
        class="
          md:flex
          md:flex-col
          md:items-stretch
          md:opacity-100
          md:relative
          md:mt-4
          md:shadow-none
          shadow
          absolute
          top-0
          left-0
          right-0
          z-40
          overflow-y-auto overflow-x-hidden
          h-auto
          items-center
          flex-1
          rounded
        "
        v-bind:class="collapseShow"
      >
        <!-- Collapse header -->
        <div
          class="
            md:min-w-full md:hidden
            block
            pb-4
            mb-4
            border-b border-solid border-blueGray-200
          "
        >
          <div class="flex flex-wrap">
            <div class="w-6/12">
              <a
                class="
                  md:block
                  text-left
                  md:pb-2
                  text-blueGray-600
                  mr-0
                  inline-block
                  whitespace-nowrap
                  text-sm
                  uppercase
                  font-bold
                  p-4
                  px-0
                "
                @click="moveDashboard"
              >
                해피하우스
              </a>
            </div>

            <div class="w-6/12 flex justify-end">
              <button
                type="button"
                class="
                  cursor-pointer
                  text-black
                  opacity-50
                  md:hidden
                  px-3
                  py-1
                  text-xl
                  leading-none
                  bg-transparent
                  rounded
                  border border-solid border-transparent
                "
                v-on:click="toggleCollapseShow('hidden')"
              >
                <i class="fas fa-times"></i>
              </button>
            </div>
          </div>
        </div>
        <!-- Form -->
        <form class="mt-6 mb-4 md:hidden">
          <div class="mb-3 pt-0">
            <input
              type="text"
              placeholder="Search"
              class="
                border-0
                px-3
                py-2
                h-12
                border border-solid border-blueGray-500
                placeholder-blueGray-300
                text-blueGray-600
                bg-white
                rounded
                text-base
                leading-snug
                shadow-none
                outline-none
                focus:outline-none
                w-full
                font-normal
              "
            />
          </div>
        </form>
        <!-- Navigation -->
        <ul class="md:flex-col md:min-w-full flex flex-col list-none">
          <li class="items-center">
            <a
              class="
                text-pink-500
                hover:text-pink-600
                text-xs
                uppercase
                py-3
                font-bold
                block
              "
              href=""
              @click="moveHouse"
              ><i class="fas fa-tv opacity-75 mr-2 text-sm"></i> 집 찾기</a
            >
          </li>
          <li class="items-center">
            <a
              class="
                text-blueGray-700
                hover:text-blueGray-500
                text-xs
                uppercase
                py-3
                font-bold
                block
              "
              href=""
              @click="moveNotify"
              ><i class="fas fa-newspaper text-blueGray-400 mr-2 text-sm"></i>
              공지사항</a
            >
          </li>
          <li class="items-center">
            <a
              class="
                text-blueGray-700
                hover:text-blueGray-500
                text-xs
                uppercase
                py-3
                font-bold
                block
              "
              href=""
              @click="moveQna"
              ><i class="fas fa-user-circle text-blueGray-400 mr-2 text-sm"></i>
              QnA</a
            >
          </li>
          <div v-if="userInfo">
            <li class="items-center">
              <a
                class="text-blueGray-300 text-xs uppercase py-3 font-bold block"
                href="#pablo"
                @click="moveQuiz"
                ><i class="fas fa-tools text-blueGray-300 mr-2 text-sm"></i> 내
                성향 찾기</a
              >
            </li>
            <li class="items-center">
              <a
                class="
                  text-blueGray-700
                  hover:text-blueGray-500
                  text-xs
                  uppercase
                  py-3
                  font-bold
                  block
                "
                href="#/profile"
                @click="moveUserInfo"
                ><i
                  class="fas fa-user-circle text-blueGray-400 mr-2 text-sm"
                ></i>
                내 정보</a
              >
            </li>
            <li class="items-center" v-if="userInfo">
              <a
                class="
                  text-blueGray-700
                  hover:text-blueGray-500
                  text-xs
                  uppercase
                  py-3
                  font-bold
                  block
                "
                href="#/profile"
                @click="onClickLogout"
                ><i
                  class="fas fa-user-circle text-blueGray-400 mr-2 text-sm"
                ></i>
                로그아웃</a
              >
            </li>
          </div>
          <div v-else>
            <li class="items-center">
              <a
                class="
                  text-blueGray-700
                  hover:text-blueGray-500
                  text-xs
                  uppercase
                  py-3
                  font-bold
                  block
                "
                href=""
                @click="moveLogin"
                ><i
                  class="fas fa-fingerprint text-blueGray-400 mr-2 text-sm"
                ></i>
                로그인</a
              >
            </li>
            <li class="items-center">
              <a
                class="text-blueGray-300 text-xs uppercase py-3 font-bold block"
                href=""
                @click="moveJoin"
                ><i
                  class="fas fa-clipboard-list text-blueGray-300 mr-2 text-sm"
                ></i>
                회원가입</a
              >
            </li>
          </div>
        </ul>
        <!-- Divider -->
        <hr class="my-4 md:min-w-full" />
        <!-- Heading -->

        <!-- Navigation -->
        <div v-if="userInfo">
          <user-dropdown-component></user-dropdown-component>
        </div>
      </div>
    </div>
  </nav>
</template>
<script>
import NotificationDropdownComponent from "./NotificationDropdown.vue";
import UserDropdownComponent from "./UserDropdown.vue";

import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  data() {
    return {
      collapseShow: "hidden",
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
    toggleCollapseShow: function (classes) {
      this.collapseShow = classes;
    },
    moveHouse() {
      this.$router.push({ name: "House" });
    },
    moveLogin() {
      this.$router.push({ name: "MemberLogin" });
    },
    moveQuiz() {
      this.$router.push({ name: "Quiz" });
    },
    moveQna() {
      this.$router.push({ name: "QnA" });
    },
    moveDashboard() {
      this.$router.push({ name: "Home" });
    },
    moveNotify() {
      this.$router.push({ name: "Board" });
    },
    moveUserInfo() {
      this.$router.push({ name: "MemberInfo" });
    },
    moveJoin() {
      this.$router.push({ name: "MemberRegister" });
    },
  },
  components: {
    NotificationDropdownComponent,
    UserDropdownComponent,
  },
};
</script>
