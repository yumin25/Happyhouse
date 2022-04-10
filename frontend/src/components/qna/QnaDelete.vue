<script>
import http from "@/util/http-common";

export default {
  name: "QnADelete",
  created() {
    http.delete(`/qna/${this.$route.params.no}`).then(({ data }) => {
      let msg = "삭제 처리시 문제가 발생했습니다.";
      if (data.data === "success") {
        msg = "삭제가 완료되었습니다.";
      } else if (data.data === "notWriter") {
        msg = "글 작성자가 아닙니다. 삭제 불가.";
      }
      alert(msg);
      // 현재 route를 /list로 변경.
      this.$router.push({ name: "QnaBoard" });
    });
  },
};
</script>
