<template>
  <div>
    <p>QnA 등록</p>
    <br />
    <qna-write-form type="register" />
  </div>
</template>

<script>
import QnaWriteForm from "./child/QnaWriteForm.vue";
export default {
  name: "QnaRegister",
  components: {
    QnaWriteForm,
  },
};
</script>
