<template>
  <div>
    <div class="md:grid md:grid-cols-3 md:gap-6">
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
                  공지사항 제목
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
                  공지사항 내용
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
              </div>
            </div>
            <div
              class="px-4 py-3 bg-gray-50 text-right sm:px-6"
              v-if="userInfo.username === 'admin'"
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
  name: "BoardView",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    http.get(`/notify/${this.$route.params.no}`).then(({ data }) => {
      this.article = data;
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "BoardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "BoardUpdate",
        params: { no: this.article.no },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "BoardDelete",
          params: { no: this.article.no },
        });
      }
    },
  },
};
</script>
