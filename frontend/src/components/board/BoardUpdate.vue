<template>
  <div>
    <p>QnA 수정</p>
    <br />
    <board-write-form type="modify" />
  </div>
</template>

<script>
import BoardWriteForm from "./child/BoardWriteForm.vue";

export default {
  name: "BoardUpdate",
  components: {
    BoardWriteForm,
  },
};
</script>
