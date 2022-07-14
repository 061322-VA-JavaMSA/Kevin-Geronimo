async function loginFormHandler(event) {
    event.preventDefault();

    let apiUrl = 'http://localhost:8080/ers';

    let response = await fetch(`${apiUrl}/auth`, {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(new FormData(event.target))
    });

    if (response.status == 200) {
        let data = await response.json();
        sessionStorage.setItem('principal', JSON.stringify(data));
        // redirect to the homepage on success
        window.history.pushState({}, "", "/home");
        handleLocation()
    } else {
        console.log('Unable to login.')
    }
};