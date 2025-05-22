// src/main/resources/static/js/lobby.js

document.addEventListener("DOMContentLoaded", function () {
  console.log("lobby.js 読み込み完了");

  const punchInBtn = document.getElementById("punch-in-btn");
  const punchOutBtn = document.getElementById("punch-out-btn");

  punchInBtn?.addEventListener("click", function () {
    alert("出勤打刻しました！");
  });

  punchOutBtn?.addEventListener("click", function () {
    alert("退勤打刻しました！");
  });
});
