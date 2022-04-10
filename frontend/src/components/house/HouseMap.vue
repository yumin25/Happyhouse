<template>
  <div>
    <div>
      <br /><button @click="category('BK9')">
        <i class="fas fa-fingerprint text-blueGray-400 ml-3 mr-2 text-sm"></i
        >은행</button
      >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button @click="category('MT1')">
        <i class="fas fa-shopping-cart text-blueGray-400 mr-2 text-sm"></i
        >마트</button
      >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button @click="category('PM9')">
        약국</button
      >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button @click="category('OL7')">
        주유소</button
      >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button @click="category('CE7')">
        카페</button
      >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button @click="category('CS2')">
        편의점
      </button>
    </div>
    <div id="map"></div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseMap",
  data() {
    return {
      map: null,
      clusterer: null,
      list: [],
      positions: [],
      placeOverlay: null,
    };
  },
  props: {
    house: Object,
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" +
        process.env.VUE_APP_KAKAO_API_KEY +
        "&libraries=services,clusterer";
      document.head.appendChild(script);
    }
  },
  computed: {
    ...mapState(houseStore, ["sido", "gugun", "houses", "isDetail"]),
  },
  watch: {
    houses: function () {
      this.marker();
    },
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse", "getIsDetail"]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 10, // 클러스터 할 최소 지도 레벨
        disableClickZoom: true, // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
      });
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.marker();
    },
    marker() {
      this.clusterer.clear();
      this.getIsDetail(false);

      //구 군 - 법정동 지번
      this.list.length = 0;

      for (const house of this.houses) {
        const resultLocation =
          this.sido + " " + this.gugun + " " + house.법정동 + " " + house.지번;
        this.list.push(resultLocation);
      }

      console.log(this.list);
      console.log("여기");

      var geocoder = new kakao.maps.services.Geocoder();

      const addressSearch = (address) => {
        return new Promise((resolve, reject) => {
          geocoder.addressSearch(address, function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
              resolve({ lat: result[0].y, lng: result[0].x });
            } else {
              reject(status);
            }
          });
        });
      };

      // async-await
      (async () => {
        try {
          this.positions.length = 0;
          for (const address of this.list) {
            const result = await addressSearch(address);
            this.positions.push(result);
          }

          //지도 위치 변경
          var moveLatLon = new kakao.maps.LatLng(
            this.positions[0].lat,
            this.positions[0].lng
          );
          this.map.panTo(moveLatLon);

          var num = -1;
          console.log("여기1");
          console.log(this.houses);

          var markers = this.positions.map((position) => {
            console.log("여기2");
            console.log(this.houses);
            const marker = new kakao.maps.Marker({
              position: new kakao.maps.LatLng(position.lat, position.lng),
              clickable: true, // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
            });

            const myNum = num + 1;
            num = num + 1;

            kakao.maps.event.addListener(marker, "click", () => {
              this.detailHouse(this.houses[myNum]);
              this.getIsDetail(true);

              //지도 위치 변경
              var moveLatLon = new kakao.maps.LatLng(
                this.positions[myNum].lat,
                this.positions[myNum].lng
              );
              this.map.panTo(moveLatLon);
            });
            return marker;
          });

          // 클러스터러에 마커들을 추가합니다
          this.clusterer.addMarkers(markers);
        } catch (e) {
          console.log(e);
        }
      })();
    },
    category(category) {
      // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다

      var contentNode = document.createElement("div");
      this.positions.length = 0;
      this.clusterer.clear();

      this.placeOverlay.setContent(contentNode);

      // 장소 검색 객체를 생성
      var ps = new kakao.maps.services.Places(this.map);

      // 카테고리로 은행을 검색
      ps.categorySearch(
        category,
        (data, status) => {
          if (status === kakao.maps.services.Status.OK) {
            data.map((data_detail) => {
              this.positions.push(data_detail);
              console.log(data_detail);
            });

            var markers = this.positions.map((position) => {
              const marker = new kakao.maps.Marker({
                position: new kakao.maps.LatLng(position.y, position.x),
                clickable: true, // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
              });

              kakao.maps.event.addListener(marker, "click", () => {
                this.placeOverlay.setMap(null);
                //지도 위치 변경
                var moveLatLon = new kakao.maps.LatLng(position.y, position.x);
                this.map.panTo(moveLatLon);

                var content =
                  '<div class="placeinfo">' +
                  '   <a class="title" href="' +
                  position.place_url +
                  '" target="_blank" title="' +
                  position.place_name +
                  '">' +
                  position.place_name +
                  "<br/></a>";

                if (position.road_address_name) {
                  content +=
                    '    <span title="' +
                    position.road_address_name +
                    '">' +
                    position.road_address_name +
                    "<br/></span>" +
                    '  <span class="jibun" title="' +
                    position.address_name +
                    '">(지번 : ' +
                    position.address_name +
                    ")<br/></span>";
                } else {
                  content +=
                    '    <span title="' +
                    position.address_name +
                    '">' +
                    position.address_name +
                    "</span>";
                }
                content +=
                  '    <span class="tel">' +
                  position.phone +
                  "</span>" +
                  "</div>" +
                  '<div class="after"></div>' +
                  '<style>.placeinfo_wrap {position: absolute; bottom: 28px; left: -150px; width: 300px;  }  .placeinfo { position: relative;    width: 100%;    border-radius: 6px;   border: 1px solid #ccc;    border-bottom: 2px solid #ddd; padding-bottom: 10px; background: #fff; }  .placeinfo:nth-of-type(n) { border: 0; box-shadow: 0px 1px 2px #888; } .placeinfo_wrap .after { content: ""; position: relative; margin-left: -12px; left: 50%;  width: 22px; height: 12px; background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");}.placeinfo a,.placeinfo a:hover,.placeinfo a:active {color: #fff;text-decoration: none;} .placeinfo a,.placeinfo span { display: block; text-overflow: ellipsis; overflow: hidden; white-space: nowrap; } .placeinfo span { margin: 5px 5px 0 5px; cursor: default; font-size: 13px; } .placeinfo .title { font-weight: bold; font-size: 14px; border-radius: 6px 6px 0 0; margin: -1px -1px 0 -1px; padding: 10px; color: #fff; background: #1bde4f; background: #fc74c6 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center; } .placeinfo .tel { color: #0f7833; } .placeinfo .jibun { color: #999; font-size: 11px; margin-top: 0; }</style>';
                console.log("content");
                console.log(content);

                contentNode.innerHTML = content;
                this.placeOverlay.setPosition(
                  new kakao.maps.LatLng(position.y, position.x)
                );
                this.placeOverlay.setMap(this.map);
              });

              return marker;
            });

            this.clusterer.addMarkers(markers);
          }
        },
        { useMapBounds: true }
      );
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 1250px;
}
</style>
