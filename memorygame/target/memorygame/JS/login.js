function login(username, password, type) {
    const apiUrl = `http://localhost:8383/memorygame/${type}`;

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

    fetch(apiUrl, options)
    .then(response => {
        const successRedirect = response.headers.get('Success-Redirect');
        if (successRedirect) {
            window.location.href = successRedirect;
        }

        return response.text();
    })
    .then(message => {
        console.log('Login result:', message);
        if (!message.includes('Login Successful')) {
            alert('Login failed. Please check your credentials.');
        }
    })
    .catch(error => {
        console.error('Login error:', error.message);
    })
    .finally(() => {
        setTimeout(() => {
            console.log('Procede login');
        }, 30000);
    });
}