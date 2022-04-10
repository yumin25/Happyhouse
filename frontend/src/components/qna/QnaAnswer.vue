<template>
  <div>
    <div
      class="md:grid md:grid-cols-4 md:gap-6"
      style="margin-left: 600px; margin-top: 300px"
    >
      <!-- <div class="md:col-span-1"></div> -->

      <div class="mt-5 md:mt-0 md:col-span-2">
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
                >
                  답변
                </label>
                <div class="mt-1">
                  <textarea
                    id="answer"
                    name="answer"
                    v-model="article.answer"
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
                    placeholder="여기에 답변을 입력해주세요"
                  />
                </div>
                <p class="mt-2 text-sm text-gray-500">
                  <!-- 질문을 예쁘게 작성해 주세요. -->
                </p>
              </div>
            </div>
            <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
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
                @click.prevent="confirm"
              >
                답변질문 등록
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
  name: "QnaAnswer",
  data() {
    return {
      article: {
        no: 0,
        answer: "",
      },
    };
  },
  created() {
    http.get(`/qna/${this.$route.params.no}`).then(({ data }) => {
      this.article = data;
    });
  },
  methods: {
    async confirm() {
      let err = true;
      let msg = "";
      !this.article.answer &&
        ((msg = "답 입력해주세요"), (err = false), this.$refs.userid.focus());
      err;

      if (!err) alert(msg);
      this.answerQna();
    },
    onReset(event) {
      event.preventDefault();
      this.article.no = 0;
      this.article.answer = "";
      this.$router.push({ name: "QnaBoard" });
    },
    answerQna() {
      http
        .put(`/qna/answer`, {
          no: this.article.no,
          answer: this.article.answer,
        })
        .then(({ data }) => {
          console.log(data);
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data.data === "success") {
            msg = "답변이 등록되었습니다.";
          } else if (data.data === "notAdmin") {
            msg = "관리자가 아닙니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "QnaBoard" });
        });
    },
    moveList() {
      this.$router.push({ name: "QnaBoard" });
    },
  },
};
</script>
