document.addEventListener('DOMContentLoaded', function () {
    const stopwatch = document.getElementById('stopwatch');
    const cardContainer = document.getElementById('cardContainer');
    const scoreElement = document.getElementById('score');

    let seconds = 0;
    let minutes = 0;
    let hours = 0;
    let interval;
    let score = 0;
    let flippedCards = [];

    function startStopwatch() {
        interval = setInterval(function () {
            seconds++;
            if (seconds === 60) {
                seconds = 0;
                minutes++;
                if (minutes === 60) {
                    minutes = 0;
                    hours++;
                }
            }

            const formattedTime = `${hours.toString().padStart(2, '0')}:${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
            stopwatch.textContent = formattedTime;
        }, 1000);
    }

    function flipCard(card) {
        if (!card.classList.contains('flipped') && flippedCards.length < 2) {
            card.classList.add('flipped');
            flippedCards.push(card);

            if (flippedCards.length === 2) {
                setTimeout(checkMatch, 1000);
            }
        }
    }

    function checkMatch() {
        const [card1, card2] = flippedCards;
        const card1Data = card1.getAttribute('data-card');
        const card2Data = card2.getAttribute('data-card');

        if (card1Data === card2Data) {
            // Matched, keep cards flipped
            score++;
            scoreElement.textContent = 'Score: ' + score;
        } else {
            // Not matched, flip cards back after a delay
            setTimeout(() => {
                card1.classList.remove('flipped');
                card2.classList.remove('flipped');
            }, 1000);
        }

        flippedCards = [];

    }

    function enableCardClicks() {
        const cards = document.querySelectorAll('.card');

        cards.forEach(card => {
            card.addEventListener('click', function () {
                flipCard(this);
            });
        });
    }

    startStopwatch();

    enableCardClicks();
});
