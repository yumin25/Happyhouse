import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
  console.log("여기옵니다");
}

async function klogin(codes, success, fail) {
  console.log(codes);
  await api
    .get(`/user/kakaologin/` + codes)
    .then(success)
    .catch(fail);
  console.log("카카오 로그인");
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  console.log("여기옵니다2");
  await api.get(`/user/info`).then(success).catch(fail);
}

// function logout(success, fail)

export { login, klogin, findById };
