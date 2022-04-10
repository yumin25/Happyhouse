<template>
  <div>
    <p>QnA 등록</p>
    <br />
    <board-write-form type="register" />
  </div>
</template>

<script>
import BoardWriteForm from "./child/BoardWriteForm.vue";
export default {
  name: "BoardRegister",
  components: {
    BoardWriteForm,
  },
};
</script>
