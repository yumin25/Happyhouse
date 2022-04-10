<template>
  <div>
    <div v-if="this.isDetail === false"><house-list></house-list></div>
    <div v-else><house-detail></house-detail></div>
    <div>
      <house-search-bar></house-search-bar>
    </div>
    <div>
      <house-map></house-map>
    </div>
  </div>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseMap from "@/components/house/HouseMap.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";

import { mapState } from "vuex";
const houseStore = "houseStore";

export default {
  name: "House",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
    HouseMap,
  },
  computed: {
    ...mapState(houseStore, ["isDetail"]),
  },
};
</script>
