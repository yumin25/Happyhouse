<!-- This example requires Tailwind CSS v2.0+ -->
<template>
  <div class="flex flex-col">
    <div class="-my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
      <div class="py-2 align-middle inline-block min-w-full sm:px-6 lg:px-8">
        <div
          class="shadow overflow-hidden border-b border-gray-200 sm:rounded-lg"
        >
          <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
              <tr>
                <th
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium
                    text-gray-500
                    uppercase
                    tracking-wider
                  "
                >
                  제목
                </th>
                <th
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium
                    text-gray-500
                    uppercase
                    tracking-wider
                  "
                >
                  작성자
                </th>
                <th
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium
                    text-gray-500
                    uppercase
                    tracking-wider
                  "
                >
                  Status
                </th>
                <!-- <th
                  scope="col"
                  class="
                    px-6
                    py-3
                    text-left text-xs
                    font-medium
                    text-gray-500
                    uppercase
                    tracking-wider
                  "
                >
                  Role
                </th> -->
                <th scope="col" class="relative px-6 py-3">
                  <span class="sr-only">Edit</span>
                </th>
              </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
              <tr v-for="article in this.articles" :key="article.no">
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="flex items-center">
                    <!-- <div class="flex-shrink-0 h-10 w-10">
                      <img
                        class="h-10 w-10 rounded-full"
                        :src="person.image"
                        alt=""
                      />
                    </div> -->
                    <div class="ml-4">
                      <div class="text-sm font-medium text-gray-900">
                        <router-link
                          :to="{
                            name: 'QnAView',
                            params: { no: article.no },
                          }"
                          >{{ article.title }}</router-link
                        >
                      </div>
                    </div>
                  </div>
                </td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <div class="text-sm text-gray-900">
                    {{ article.username }}
                  </div>
                  <div class="text-sm text-gray-500">
                    {{ article.regtime }}
                  </div>
                </td>
                <td
                  class="px-6 py-4 whitespace-nowrap"
                  v-if="article.answer != null"
                >
                  <span
                    class="
                      px-2
                      inline-flex
                      text-xs
                      leading-5
                      font-semibold
                      rounded-full
                      bg-green-100
                      text-green-800
                    "
                  >
                    답변완료
                  </span>
                </td>
                <td class="px-6 py-4 whitespace-nowrap" v-else>
                  <span
                    class="
                      px-2
                      inline-flex
                      text-xs
                      leading-5
                      font-semibold
                      rounded-full
                      bg-red-100
                      text-green-800
                    "
                  >
                    답변 대기 중
                  </span>
                </td>
                <!-- <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
                  {{ article.username }}
                </td> -->
                <td
                  class="
                    px-6
                    py-4
                    whitespace-nowrap
                    text-right text-sm
                    font-medium
                  "
                >
                  <div
                    v-if="userInfo && userInfo.username === 'admin'"
                    @click="moveQnaAnswerForm"
                  >
                    <router-link
                      :to="{
                        name: 'QnaAnswer',
                        params: { no: article.no },
                      }"
                      ><a href="" class="text-indigo-600 hover:text-indigo-900"
                        >답변</a
                      ></router-link
                    >
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
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
            @click="moveQnARegister"
            v-if="userInfo"
          >
            질문 등록
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/util/http-common";

const memberStore = "memberStore";

export default {
  name: "QnaBoard",
  components: {},
  data() {
    return {
      articles: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    http.get(`/qna`).then(({ data }) => {
      this.articles = data;
    });
    console.log(this.userInfo);
  },
  methods: {
    moveQnARegister() {
      this.$router.push({ name: "QnaRegister" });
    },
    // moveQnaAnswerForm() {
    //   this.$router.push({ name: "QnaAnswer", params: { articleno: no } });
    // },
  },
};
</script>
