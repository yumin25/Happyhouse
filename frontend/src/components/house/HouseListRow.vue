<template>
  <div
    class="
      bg-white
      px-4
      py-3
      flex
      items-center
      justify-between
      border-t border-gray-200
      sm:px-3
    "
    @click="selectHouse"
  >
    {{ house.아파트 }} <br />[{{ house.일련번호 }}]
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  computed: {
    ...mapState(houseStore, ["isDetail"]),
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse", "getIsDetail"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
      this.getIsDetail(true);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}
</style>
