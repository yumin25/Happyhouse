<template>
  <div
    class="
      min-h-full
      flex
      items-center
      justify-center
      py-12
      px-4
      sm:px-6
      lg:px-8
    "
    style="margin-top: 200px"
  >
    <div class="max-w-md w-full space-y-8">
      <div>
        <h2 class="mt-6 text-center text-3xl font-extrabold text-gray-900">
          로그인 해주세요
        </h2>
      </div>
      <form class="mt-8 space-y-6" action="#" method="POST">
        <input type="hidden" name="remember" value="true" />
        <div class="rounded-md shadow-sm -space-y-px">
          <div>
            <label for="userid" class="sr-only">아이디</label>
            <input
              id="userid"
              v-model="user.username"
              required=""
              @keyup.enter="confirm"
              class="
                appearance-none
                rounded-none
                relative
                block
                w-full
                px-3
                py-2
                border border-gray-300
                placeholder-gray-500
                text-gray-900
                rounded-t-md
                focus:outline-none
                focus:ring-indigo-500
                focus:border-indigo-500
                focus:z-10
                sm:text-sm
              "
              placeholder="아이디"
            />
          </div>
          <div>
            <label for="userpwd" class="sr-only">Password</label>
            <input
              id="userpwd"
              v-model="user.password"
              name="password"
              type="password"
              autocomplete="current-password"
              required=""
              class="
                appearance-none
                rounded-none
                relative
                block
                w-full
                px-3
                py-2
                border border-gray-300
                placeholder-gray-500
                text-gray-900
                rounded-b-md
                focus:outline-none
                focus:ring-indigo-500
                focus:border-indigo-500
                focus:z-10
                sm:text-sm
              "
              placeholder="패스워드"
            />
          </div>
        </div>
        <div>
          <button
            class="
              group
              relative
              w-full
              flex
              justify-center
              py-2
              px-4
              border border-transparent
              text-sm
              font-medium
              rounded-md
              text-white
              bg-indigo-600
              hover:bg-indigo-700
              focus:outline-none
              focus:ring-2
              focus:ring-offset-2
              focus:ring-indigo-500
            "
            @click.prevent="confirm"
          >
            <span class="absolute left-0 inset-y-0 flex items-center pl-3">
            </span>
            로그인
          </button>
        </div>
        <div class="relative" style="height: 300px">
          <img
            src="../../assets/img/kakaoLoginBtn.png"
            style="
              width: 250px;
              display: block;
              margin: auto;
              border-radius: 70px;
            "
            @click="kakaoLogin"
          />
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberLogin",
  data() {
    return {
      user: {
        username: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      console.log(this.user);
      console.log("123123123");
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        console.log("24324234234234234234234234234234234234234234");
        await this.getUserInfo(token);
        this.$router.push({ name: "Home" });
      }
    },
    movePage() {
      this.$router.push({ name: "Home" });
    },
    kakaoLogin() {
      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=4ff681f8072ca877106b80da365ad436&redirect_uri=http://localhost:8080/user/kakao&response_type=code"
      );
    },
  },
};
</script>
