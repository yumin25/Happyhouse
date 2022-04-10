<template>
  <div class="mt-12">
    <div class="md:grid md:grid-cols-3 md:gap-6">
      <div class="md:col-span-1">
        <div class="px-4 sm:px-0 ml-12">
          <h3 class="text-lg font-medium leading-6 text-gray-900">
            회원가입 페이지
          </h3>
          <p class="mt-1 text-sm text-gray-600">정보를 정확히 입력하시오</p>
        </div>
      </div>
      <div class="mt-5 md:mt-0 md:col-span-1" style="margin-top: 200px">
        <form action="#" method="POST">
          <div class="shadow overflow-hidden sm:rounded-md">
            <div class="px-4 py-5 bg-white sm:p-6">
              <div class="grid grid-cols-6 gap-6">
                <div class="col-span-6">
                  <label
                    for="first-name"
                    class="block text-sm font-medium text-gray-700"
                    >아이디</label
                  >
                  <input
                    type="text"
                    name="first-name"
                    id="first-name"
                    v-model="memberInfo.username"
                    class="
                      mt-1
                      focus:ring-indigo-500 focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border-gray-300
                      rounded-md
                    "
                  />
                </div>

                <div class="col-span-6">
                  <label
                    for="last-name"
                    class="block text-sm font-medium text-gray-700"
                    >패스워드</label
                  >
                  <input
                    type="text"
                    name="last-name"
                    id="last-name"
                    v-model="memberInfo.password"
                    class="
                      mt-1
                      focus:ring-indigo-500 focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border-gray-300
                      rounded-md
                    "
                  />
                </div>

                <div class="col-span-6">
                  <label
                    for="last-name"
                    class="block text-sm font-medium text-gray-700"
                    >이름</label
                  >
                  <input
                    type="text"
                    name="last-name"
                    id="last-name"
                    v-model="memberInfo.name"
                    autocomplete="family-name"
                    class="
                      mt-1
                      focus:ring-indigo-500 focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border-gray-300
                      rounded-md
                    "
                  />
                </div>

                <div class="col-span-6">
                  <label
                    for="email-address"
                    class="block text-sm font-medium text-gray-700"
                    >이메일</label
                  >
                  <input
                    type="text"
                    name="email-address"
                    id="email-address"
                    v-model="memberInfo.email"
                    autocomplete="email"
                    class="
                      mt-1
                      focus:ring-indigo-500 focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border-gray-300
                      rounded-md
                    "
                  />
                </div>

                <div class="col-span-6">
                  <label
                    for="street-address"
                    class="block text-sm font-medium text-gray-700"
                    >핸드폰 번호</label
                  >
                  <input
                    type="text"
                    name="street-address"
                    id="street-address"
                    v-model="memberInfo.phone"
                    autocomplete="street-address"
                    class="
                      mt-1
                      focus:ring-indigo-500 focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border-gray-300
                      rounded-md
                    "
                  />
                </div>

                <div class="col-span-6">
                  <label
                    for="city"
                    class="block text-sm font-medium text-gray-700"
                    >주소</label
                  >
                  <input
                    type="text"
                    name="city"
                    id="city"
                    v-model="memberInfo.address"
                    autocomplete="address-level2"
                    class="
                      mt-1
                      focus:ring-indigo-500 focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border-gray-300
                      rounded-md
                    "
                  />
                </div>

                <div class="col-span-6">
                  <label
                    for="region"
                    class="block text-sm font-medium text-gray-700"
                    >상세주소</label
                  >
                  <input
                    type="text"
                    name="region"
                    id="region"
                    v-model="memberInfo.address_detail"
                    autocomplete="address-level1"
                    class="
                      mt-1
                      focus:ring-indigo-500 focus:border-indigo-500
                      block
                      w-full
                      shadow-sm
                      sm:text-sm
                      border-gray-300
                      rounded-md
                    "
                  />
                </div>
              </div>
            </div>
            <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
              <button
                type="submit"
                class="
                  inline-flex
                  justify-center
                  py-2
                  px-4
                  border border-transparent
                  shadow-sm
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
                @click.prevent="registerMember"
              >
                회원가입하기
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
<script>
import http from "@/util/http-common";

export default {
  name: "MemberRegister",
  data() {
    return {
      memberInfo: {
        username: "",
        password: "",
        name: "",
        email: "",
        phone: "",
        address: "",
        address_detail: "",
      },
    };
  },
  props: {
    type: { type: String },
  },

  methods: {
    registerMember() {
      console.log(this.memberInfo.username + "멤버여기까지");
      http
        .post(`/user`, {
          username: this.memberInfo.username,
          password: this.memberInfo.password,
          name: this.memberInfo.name,
          email: this.memberInfo.email,
          phone: this.memberInfo.phone,
          address: this.memberInfo.address,
          address_detail: this.memberInfo.address_detail,
        })
        .then(({ data }) => {
          console.log(data);
          let msg = "등록 처리시 문제가 발생했습니다.";

          if (data == "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "Home" });
        });
    },
  },
};
</script>
