function login(username, password) {
    const apiUrl = 'http://localhost:8383/memorygame/api';

    const data = {
        username: username,
        password: password
    };

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams(data),
    };

    // Perform the login request
    fetch(apiUrl, options)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(html => {
            console.log('Login successful:', html);

            document.body.innerHTML = html;
        })
        .catch(error => {
            console.error('Login error:', error.message);
        });
}