<template>
  <div></div>
</template>

<script>
//import http from "@/util/http-common";
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "KakaoLogin",
  data() {
    return {
      codes: "",
      user: {
        username: null,
        password: null,
      },
    };
  },
  created() {
    this.codes = this.$route.query.code;
    console.log(this.codes);
    this.confirm();
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo", "userConfirm_k"]),
    async confirm() {
      console.log(this.user);
      await this.userConfirm_k(this.codes);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push({ name: "Home" });
      }
    },
    movePage() {
      this.$router.push({ name: "Home" });
    },
  },
};
</script>
