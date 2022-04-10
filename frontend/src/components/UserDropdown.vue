<template>
  <div>
    <a
      class="text-blueGray-500 block"
      href="#pablo"
      v-on:click="toggleDropdown($event)"
      ref="btnDropdownRef"
    >
      <div class="items-center flex">
        <span
          class="
            w-12
            h-12
            text-sm text-white
            bg-blueGray-200
            inline-flex
            items-center
            justify-center
            rounded-full
          "
        >
          <img
            alt="..."
            class="w-full rounded-full align-middle border-none shadow-lg"
            src="../assets/img/pg.png"
          />
        </span>
      </div>
    </a>
    <div
      ref="popoverDropdownRef"
      class="
        bg-white
        text-base
        z-50
        float-left
        py-2
        list-none
        text-left
        rounded
        shadow-lg
        mt-1
      "
      v-bind:class="{
        hidden: !dropdownPopoverShow,
        block: dropdownPopoverShow,
      }"
      style="min-width: 12rem"
    >
      <a
        href="#pablo"
        class="
          text-sm
          py-2
          px-4
          font-normal
          block
          w-full
          whitespace-nowrap
          bg-transparent
          text-blueGray-700
        "
        @click="moveUserInfo"
      >
        회원정보
      </a>
      <a
        href="#"
        class="
          text-sm
          py-2
          px-4
          font-normal
          block
          w-full
          whitespace-nowrap
          bg-transparent
          text-blueGray-700
        "
        @click="updateUser"
      >
        회원수정
      </a>
      <div class="h-0 my-2 border border-solid border-blueGray-100" />
      <a
        href="#"
        class="
          text-sm
          py-2
          px-4
          font-normal
          block
          w-full
          whitespace-nowrap
          bg-transparent
          text-blueGray-700
        "
        @click="onClickLogout"
      >
        로그아웃
      </a>
    </div>
  </div>
</template>
<script>
import { createPopper } from "@popperjs/core";
import { mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  data() {
    return {
      dropdownPopoverShow: false,
    };
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      //if (this.$route.path != "/") this.$router.push({ name: "Home" });
      this.$router.push({ name: "Home" });
    },
    toggleDropdown: function (event) {
      event.preventDefault();
      if (this.dropdownPopoverShow) {
        this.dropdownPopoverShow = false;
      } else {
        this.dropdownPopoverShow = true;
        createPopper(this.$refs.btnDropdownRef, this.$refs.popoverDropdownRef, {
          placement: "bottom-end",
        });
      }
    },
    moveUserInfo() {
      this.$router.push({ name: "MemberInfo" });
    },
    moveLogin() {
      this.$router.push({ name: "MemberLogin" });
    },
    updateUser() {
      this.$router.push({ name: "MemberUpdate" });
    },
  },
};
</script>
