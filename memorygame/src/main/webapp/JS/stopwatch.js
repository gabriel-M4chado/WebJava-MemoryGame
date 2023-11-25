document.addEventListener('DOMContentLoaded', function () {
    const stopwatch = document.getElementById('stopwatch');
    let seconds = 0;
    let minutes = 0;
    let hours = 0;
    let interval;

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

    startStopwatch();
});
