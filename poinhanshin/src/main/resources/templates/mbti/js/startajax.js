document.addEventListener("DOMContentLoaded", function () {
    const main = document.querySelector("#main");
    const qna = document.querySelector("#qna");
    const result = document.querySelector("#result");
    const startButton = document.querySelector("#startButton");

    const endPoint = 16;
    let select = new Array(endPoint).fill(0);

    let qIdx = 0;

    // AJAX를 사용하여 질문과 답변 데이터를 가져오는 함수
    function getQuestions(callback) {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", "questions.json", true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                const questions = JSON.parse(xhr.responseText);
                callback(questions);
            }
        };
        xhr.send();
    }

    // 테스트 시작 버튼 클릭 시 실행되는 함수
    startButton.addEventListener("click", function () {
        main.style.display = "none";
        qna.style.display = "block";
        getQuestions(function (questions) {
            goNext(qIdx, questions);
        });
    });

    function calResult() {
        let result = 0;
        for (let i = 0; i < endPoint; i++) {
            result += select[i];
        }
        return select.indexOf(Math.max(...select));
    }

    function setResult() {
        let point = calResult();
        const resultName = document.querySelector('.resultname');
        resultName.innerHTML = infoList[point].name;

        var resultImg = document.createElement('img');
        const imgDiv = document.querySelector('#resultImg');
        var imgURL = 'img/image-' + point + '.png';
        resultImg.src = imgURL;
        resultImg.alt = point;
        resultImg.classList.add('img-fluid');
        imgDiv.appendChild(resultImg);

        const resultDesc = document.querySelector('.resultDesc');
        resultDesc.innerHTML = infoList[point].desc;
    }

    function goResult() {
        qna.style.WebkitAnimation = "fadeOut 1s";
        qna.style.animation = "fadeOut 1s";
        setTimeout(() => {
            result.style.WebkitAnimation = "fadeIn 1s";
            result.style.animation = "fadeIn 1s";
            setTimeout(() => {
                qna.style.display = "none";
                result.style.display = "block";
            }, 450);
            setResult();
        }, 450);
    }

    function addAnswer(answerText, idx) {
        var a = document.querySelector('.answerBox');
        var answer = document.createElement('button');
        answer.classList.add('answerList');
        answer.classList.add('my-3');
        answer.classList.add('py-3');
        answer.classList.add('mx-auto');
        answer.classList.add('fadeIn');

        a.appendChild(answer);
        answer.innerHTML = answerText;

        answer.addEventListener("click", function () {
            var children = document.querySelectorAll('.answerList');
            for (let i = 0; i < children.length; i++) {
                children[i].disabled = true;
                children[i].style.WebkitAnimation = "fadeOut 0.5s";
                children[i].style.animation = "fadeOut 0.5s";
            }
            setTimeout(() => {
                var target = qnaList[qIdx].a[idx].type;
                for (let i = 0; i < target.length; i++) {
                    select[target[i]] += 1;
                }

                for (let i = 0; i < children.length; i++) {
                    children[i].style.display = 'none';
                }
                goNext(++qIdx, questions);
            }, 450);
        }, false);
    }

    function goNext(qIdx, questions) {
        if (qIdx === endPoint) {
            goResult();
            return;
        }

        var q = document.querySelector('.qBox');
        q.innerHTML = questions[qIdx].q;
        for (let i in questions[qIdx].a) {
            addAnswer(questions[qIdx].a[i].answer, i);
        }
        var status = document.querySelector('.statusBar');
        status.style.width = (100 / endPoint) * (qIdx + 1) + '%';
    }

    function moveHome() {
        location.href = "../mbti/mbti_main.html";
    }
});