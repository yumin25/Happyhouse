<template>
  <div>
    <div
      class="md:grid md:grid-cols-4 md:gap-6"
      style="margin-left: 600px; margin-top: 300px"
    >
      <div class="mt-5 md:mt-0 md:col-span-2">
        <form action="#" method="POST">
          <div class="shadow sm:rounded-md sm:overflow-hidden">
            <div class="px-4 py-5 bg-white space-y-6 sm:p-6">
              <div class="grid grid-cols-3 gap-6">
                <div class="col-span-3 sm:col-span-2"></div>
              </div>
              <label
                for="title"
                class="block text-sm font-medium text-gray-700"
              >
                공지사항 제목
              </label>
              <div class="mt-1">
                <textarea
                  id="title"
                  name="title"
                  v-model="article.title"
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
                  placeholder="여기에 제목을 입력해주세요"
                />
              </div>
              <div>
                <label
                  for="content"
                  class="block text-sm font-medium text-gray-700"
                >
                  공지사항 내용
                </label>
                <div class="mt-1">
                  <textarea
                    id="content"
                    name="content"
                    v-model="article.content"
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
                    placeholder="여기에 질문을 입력해주세요"
                  />
                </div>
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
                v-if="this.type === 'register'"
              >
                공지사항 등록
              </button>
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
                v-else
              >
                공지사항 수정
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
  name: "BoardWriteForm",
  data() {
    return {
      article: {
        no: 0,
        writer: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      http.get(`/notify/${this.$route.params.no}`).then(({ data }) => {
        this.article = data;
      });
      this.isUserid = true;
    }
  },
  methods: {
    async confirm() {
      let err = true;
      let msg = "";
      !this.article.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      if (!err) alert(msg);
      else this.type === "register" ? this.registNotify() : this.modifyNotify();
    },
    onReset(event) {
      event.preventDefault();
      this.article.no = 0;
      this.article.title = "";
      this.article.content = "";
      this.$router.push({ name: "BoardList" });
    },
    registNotify() {
      http
        .post(`/notify`, {
          title: this.article.title,
          content: this.article.content,
          writer: "admin",
        })
        .then(({ data }) => {
          console.log(data);
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data.data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    modifyNotify() {
      http
        .put(`/notify`, {
          no: this.article.no,
          title: this.article.title,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data.data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "BoardList" });
    },
  },
};
</script>
