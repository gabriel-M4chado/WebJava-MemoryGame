document.addEventListener('DOMContentLoaded', async function () {
    const game = {
        stopwatch: document.getElementById('stopwatch'),
        cardContainer: document.getElementById('cardContainer'),
        scoreElement: document.getElementById('score'),
        seconds: 0,
        minutes: 0,
        hours: 0,
        interval: null,
        score: 0,
        flippedCards: [],
    };

    startStopwatch(game);

    enableCardClicks(game);

    await setupGame(game);
});

function stopStopwatch(game) {
    clearInterval(game.interval);
}

function startStopwatch(game) {
    game.interval = setInterval(function () {
        game.seconds++;
        if (game.seconds === 60) {
            game.seconds = 0;
            game.minutes++;
            if (game.minutes === 60) {
                game.minutes = 0;
                game.hours++;
            }
        }

        const formattedTime = `${game.hours.toString().padStart(2, '0')}:${game.minutes.toString().padStart(2, '0')}:${game.seconds.toString().padStart(2, '0')}`;
        game.stopwatch.textContent = formattedTime;
    }, 1000);
}

function flipCard(game, card) {
    if (!card.classList.contains('flipped') && game.flippedCards.length < 2) {
        card.classList.add('flipped');
        game.flippedCards.push(card);

        if (game.flippedCards.length === 2) {
            setTimeout(() => checkMatch(game), 1000);
        }
    }
}

function checkMatch(game) {
    const [card1, card2] = game.flippedCards;
    const card1Data = card1.getAttribute('data-card');
    const card2Data = card2.getAttribute('data-card');

    if (card1Data === card2Data) {
        game.score++;
        game.scoreElement.textContent = 'Score: ' + game.score;
        finishGame(game);
    } else {
        setTimeout(() => {
            card1.classList.remove('flipped');
            card2.classList.remove('flipped');
        }, 1000);
    }

    game.flippedCards = [];
}

function enableCardClicks(game) {
    const cards = document.querySelectorAll('.card');

    cards.forEach(card => {
        card.addEventListener('click', function () {
            flipCard(game, this);
        });
    });
}

async function setupGame(game) {
    const character1 = await getRandomCharacter();
    const character2 = await getRandomCharacter();

    if (character1 && character2) {
        const cards = Array.from(game.cardContainer.querySelectorAll('.card'));

        /* Embaralhado os cards */
        shuffleArray(cards);

        cards[0].setAttribute('data-card', character1.id);
        cards[0].querySelector('.back').style.backgroundImage = `url(${character1.image})`;

        cards[1].setAttribute('data-card', character2.id);
        cards[1].querySelector('.back').style.backgroundImage = `url(${character2.image})`;

        cards[2].setAttribute('data-card', character1.id);
        cards[2].querySelector('.back').style.backgroundImage = `url(${character1.image})`;

        cards[3].setAttribute('data-card', character2.id);
        cards[3].querySelector('.back').style.backgroundImage = `url(${character2.image})`;
    } else {
        console.error('Erro ao atualizar os cards');
    }
}

function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

function finishGame(game) {
    if (game.score >= 2) {
        stopStopwatch(game);
        alert('Parabéns, você finalizou o jogo!');
    }
}


/* 
adicionar a validação no servidor caso tente acessar a pagina do game, sem estar logado.
*/