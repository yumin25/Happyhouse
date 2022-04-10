<template>
  <div>
    <div class="md:grid md:grid-cols-3 md:gap-6">
      <!-- <div class="md:col-span-1"></div> -->
      <div
        class="mt-5 md:mt-0 md:col-span-2"
        style="margin-left: 600px; margin-top: 300px"
      >
        <form action="#" method="POST">
          <div class="shadow sm:rounded-md sm:overflow-hidden">
            <div class="px-4 py-5 bg-white space-y-6 sm:p-6">
              <div class="grid grid-cols-3 gap-6"></div>
              <div class="col-span-3 sm:col-span-2">
                <label
                  for="title"
                  class="block text-sm font-medium text-gray-700"
                >
                  질문 제목
                </label>
                <div class="mt-1">
                  <textarea
                    id="title"
                    name="title"
                    v-model="article.title"
                    :disabled="true"
                    rows="3"
                    class="
                      shadow-sm
                      focus:ring-indigo-500 focus:border-indigo-500
                      mt-1
                      block
                      w-full
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                  />
                </div>
              </div>
              <div>
                <label
                  for="content"
                  class="block text-sm font-medium text-gray-700"
                >
                  질문 내용
                </label>
                <div class="mt-1">
                  <textarea
                    id="content"
                    name="content"
                    v-model="article.content"
                    :disabled="true"
                    rows="3"
                    class="
                      shadow-sm
                      focus:ring-indigo-500 focus:border-indigo-500
                      mt-1
                      block
                      w-full
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                  />
                </div>
                <p class="mt-2 text-sm text-gray-500">
                  <!-- 질문을 예쁘게 작성해 주세요. -->
                </p>
              </div>
              <div>
                <label
                  for="content"
                  class="block text-sm font-medium text-gray-700"
                  v-if="article.answer != null"
                >
                  답변
                </label>
                <label
                  for="content"
                  class="block text-sm font-medium text-gray-700"
                  v-else
                >
                  답변 대기 중
                </label>
                <div class="mt-1">
                  <textarea
                    id="answer"
                    name="answer"
                    v-model="article.answer"
                    rows="3"
                    :disabled="true"
                    v-if="article.answer != null"
                    class="
                      shadow-sm
                      focus:ring-indigo-500 focus:border-indigo-500
                      mt-1
                      block
                      w-full
                      sm:text-sm
                      border border-gray-300
                      rounded-md
                    "
                    placeholder="여기에 답변을 입력해주세요"
                  />
                </div>
                <p class="mt-2 text-sm text-gray-500">
                  <!-- 질문을 예쁘게 작성해 주세요. -->
                </p>
              </div>
            </div>
            <div
              class="px-4 py-3 bg-gray-50 text-right sm:px-6"
              v-if="userInfo.username === this.article.username"
            >
              <button
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
                @click.prevent="moveModifyArticle"
              >
                수정
              </button>
              &nbsp;
              <button
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
                @click.prevent="deleteArticle"
              >
                삭제
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/util/http-common";

const memberStore = "memberStore";

export default {
  name: "QnAView",
  data() {
    return {
      article: {},
      isWriter: false,
      isAdmin: false,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    answer() {
      if (this.article.answer)
        return this.article.answer.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    console.log(this.userInfo);
    http.get(`/qna/${this.$route.params.no}`).then(({ data }) => {
      this.article = data;
      console.log(this.article.username);

      if (this.article.username === this.userInfo.username) {
        this.isWriter = true;
      }
      if (this.userInfo.username === "admin") {
        this.isAdmin = true;
      }
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "QnAList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "QnAUpdate",
        params: { no: this.article.no },
      });
    },
    moveAnswerQna() {
      this.$router.replace({
        name: "QnaAnswer",
        params: { no: this.article.no },
      });
    },
    deleteArticle() {
      if (confirm("Qna를 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "QnADelete",
          params: { articleno: this.article.no },
        });
      }
    },
  },
};
</script>
