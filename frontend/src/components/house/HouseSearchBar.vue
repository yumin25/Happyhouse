<template>
  <div>
    <select class="form-control1" v-model="sidoCode" @change="gugunList">
      <option :key="i" :value="sido.value" v-for="(sido, i) in sidos">
        {{ sido.text }}
      </option>
    </select>
    <select class="form-control2" v-model="gugunCode" @change="searchApt">
      <option :key="i" :value="gugun.value" v-for="(gugun, i) in guguns">
        {{ gugun.text }}
      </option>
    </select>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      options: [
        { v: "R", t: "Red" },
        { v: "B", t: "Blue" },
        { v: "G", t: "Green" },
      ],
      color: "R",
    };
  },
  computed: {
    ...mapState(houseStore, ["sido", "gugun", "sidos", "guguns"]),
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.getSido();
    console.log(this.sido);
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getHouseList",
      "getSidoName",
      "getGugunName",
    ]),
    ...mapMutations(houseStore, ["CLEAR_SIDO_LIST", "CLEAR_GUGUN_LIST"]),
    getData() {
      alert(this.input);
    },
    setData() {
      this.input = "data";
    },
    changeColor() {
      alert(this.color);
    },
    gugunList() {
      console.log(this.sidos.find((x) => x.value === this.sidoCode).text);
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) {
        this.getGugun(this.sidoCode);
        this.getSidoName(
          this.sidos.find((x) => x.value === this.sidoCode).text
        );
      }
    },
    searchApt() {
      if (this.gugunCode) {
        this.getHouseList(this.gugunCode);
        this.getGugunName(
          this.guguns.find((x) => x.value === this.gugunCode).text
        );
      }
    },
  },
};
</script>
