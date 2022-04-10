<template>
  <div>
    <p>QnA 수정</p>
    <br />
    <qna-write-form type="modify" />
  </div>
</template>

<script>
import QnaWriteForm from "./child/QnaWriteForm.vue";

export default {
  name: "QnAUpdate",
  components: {
    QnaWriteForm,
  },
};
</script>
