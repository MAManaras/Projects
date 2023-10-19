<!-- Credit to MarkHjorth https://github.com/MarkHjorth/nedry >:) -->
<style>
    .nedry {
        position: relative;
        top: 0;
        left: 0;
    }

    .body {
        position: relative;
        top: 0;
        left: 0;
    }

    .head {
        position: absolute;
        top: 35px;
        left: 140px;
    }

    .hand {
        position: absolute;
        top: 155px;
        left: 265px;
        animation: handFrames linear 1s;
        animation-iteration-count: infinite;
        transform-origin: 50% 100%;
        -webkit-animation: handFrames linear 0.7s;
        -webkit-animation-iteration-count: infinite;
        -webkit-transform-origin: 50% 100%;
    }

    @keyframes handFrames {
        0% {
            transform: rotate(-10deg);
        }
        50% {
            transform: rotate(14deg);
        }
        100% {
            transform: rotate(-10deg);
        }
    }
</style>
<div class="nedry">
    <img class="body" src="resources/body.png">
    <img class="head" src="resources/head.png">
    <img class="hand" src="resources/hand.png">
</div>
<script>
    ahahah = new Audio("resources/ahahah.mp3");
    ahahah.addEventListener('ended', function () {
        this.play();
    }, false);
    ahahah.play();
</script>