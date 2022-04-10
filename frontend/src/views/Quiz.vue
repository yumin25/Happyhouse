<template>
  <div class="container123">
    <div v-if="this.isStart === true">
      당신이 선호하는 거주 형태는?<br /><br /><br />
    </div>
    <div
      id="question-container"
      v-if="this.isStart === false && this.isEnd === false"
    >
      <div id="question">{{ quiz.question }}</div>
      <div id="answer-buttons" class="btn-grid">
        <button class="btn" @click="setNextQuestion1">
          {{ quiz.answer1 }}
        </button>
        <button class="btn" @click="setNextQuestion2">
          {{ quiz.answer2 }}
        </button>
        <button class="btn" @click="setNextQuestion3">
          {{ quiz.answer3 }}
        </button>
        <button class="btn" @click="setNextQuestion4">
          {{ quiz.answer4 }}
        </button>
      </div>
    </div>
    <div id="question-container2" v-if="this.isEnd === true">
      당신이 살고 싶은 집은 {{ answer }}입니다.
    </div>
    <div class="controls">
      <button
        id="start-btn"
        class="start-btn btn"
        @click="startGame"
        v-if="this.isStart === true"
      >
        Start
      </button>
      <!-- <button
        id="next-btn"
        class="next-btn btn"
        @click="setNextQuestion"
        v-else
      >
        Next
      </button> -->
    </div>
  </div>
</template>
<script>
import http from "@/util/http-common";
export default {
  name: "Quiz",

  data() {
    return {
      isStart: true,
      isEnd: false,
      quizs: [],
      quiz: {},
      index: 0,
      answer: "",
    };
  },
  created() {
    http.get(`/quiz`).then(({ data }) => {
      this.quizs = data;
      console.log(this.quizs);
    });
  },
  methods: {
    startGame() {
      this.isStart = false;
      this.quiz = this.quizs[0];
      console.log(this.quiz.question + " 문제입니다");
    },
    setNextQuestion1() {
      this.index = this.index + 1;
      this.quiz = this.quizs[this.index];
      if (this.index === 11) {
        this.answer = "주택";
      } else if (this.index === 15) {
        this.isEnd = true;
      }
    },
    setNextQuestion2() {
      this.index = this.index + 1;
      console.log(this.index + " 번째 문제");
      this.quiz = this.quizs[this.index];
      console.log(this.quiz + " 문제입니다");
      if (this.index === 11) {
        this.answer = "빌라";
      } else if (this.index === 15) {
        this.isEnd = true;
      }
    },
    setNextQuestion3() {
      this.index = this.index + 1;
      console.log(this.index + " 번째 문제");
      this.quiz = this.quizs[this.index];
      console.log(this.quiz + " 문제입니다");
      if (this.index === 11) {
        this.answer = "오피스텔";
      } else if (this.index === 15) {
        this.isEnd = true;
      }
    },
    setNextQuestion4() {
      this.index = this.index + 1;
      console.log(this.index + " 번째 문제");
      this.quiz = this.quizs[this.index];
      console.log(this.quiz + " 문제입니다");
      if (this.index === 11) {
        this.answer = "아파트";
      } else if (this.index === 15) {
        this.isEnd = true;
      }
    },
  },
};
</script>

<style>
*,
*::before,
*::after {
  box-sizing: border-box;
}

:root {
  --hue-neutral: 200;
  --hue-wrong: 0;
  --hue-correct: 145;
}

/* body {
  --hue: var(--hue-neutral);
  padding: 0;
  margin: 0;
  display: flex;
  width: 100vw;
  height: 100vh;
  justify-content: center;
  align-items: center;
  background-color: hsl(var(--hue), 100%, 20%);
}

body.correct {
  --hue: var(--hue-correct);
}

body.wrong {
  --hue: var(--hue-wrong);
} */

.container123 {
  width: 1000px;
  max-width: 80%;
  background-color: white;
  border-radius: 5px;
  padding: 10px;
  box-shadow: 0 0 10px 2px;
  display: block;
  margin-left: auto;
  margin-right: auto;
  margin-top: 450px;
  text-align: center;
  font-size: 50px;
}

.btn-grid {
  display: grid;
  grid-template-columns: repeat(2, auto);
  gap: 10px;
  margin: 20px 0;
}

.btn {
  --hue: var(--hue-neutral);
  border: 1px solid hsl(var(--hue), 100%, 30%);
  background-color: hsl(var(--hue), 100%, 50%);
  border-radius: 5px;
  padding: 5px 10px;
  color: white;
  outline: none;
}

.btn:hover {
  border-color: black;
}

.btn.correct {
  --hue: var(--hue-correct);
  color: black;
}

.btn.wrong {
  --hue: var(--hue-wrong);
}

.start-btn,
.next-btn {
  font-size: 1.5rem;
  font-weight: bold;
  padding: 10px 20px;
}

.controls {
  display: flex;
  justify-content: center;
  align-items: center;
}

.hide {
  display: none;
}

/* Background Styles Only */

@import url("https://fonts.googleapis.com/css?family=Raleway");

/* html {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #dfdfdf;
} */

.side-links {
  position: absolute;
  top: 15px;
  right: 15px;
}

.side-link {
  display: flex;
  align-items: center;
  justify-content: center;
  text-decoration: none;
  margin-bottom: 10px;
  color: white;
  width: 180px;
  padding: 10px 0;
  border-radius: 10px;
}

.side-link-youtube {
  background-color: red;
}

.side-link-twitter {
  background-color: #1da1f2;
}

.side-link-github {
  background-color: #6e5494;
}

.side-link-text {
  margin-left: 10px;
  font-size: 18px;
}

.side-link-icon {
  color: white;
  font-size: 30px;
}
</style>
