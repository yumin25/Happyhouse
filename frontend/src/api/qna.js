import { apiInstance } from "./index.js";

const api = apiInstance();

async function writeQna(qna, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.post(`/qna`).then(success).catch(fail);
}

export { writeQna };
