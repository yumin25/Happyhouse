<template>
  <nav
    class="
      md:right-0
      md:block
      md:fixed
      md:top-0
      md:bottom-0
      md:overflow-y-auto
      md:flex-row
      md:flex-nowrap
      md:overflow-hidden
      shadow-xl
      bg-white
      flex flex-wrap
      items-center
      justify-between
      relative
      md:w-64
      z-10
      py-4
      px-6
    "
  >
    <div class="container mx-auto">
      <house-list-row
        v-for="(house, index) in houses"
        :key="index"
        :house="house"
      ></house-list-row>
    </div>
  </nav>
</template>

<script>
import HouseListRow from "@/components/house/HouseListRow.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>
