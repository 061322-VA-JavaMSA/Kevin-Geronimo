async function editProfileFormHandler(event) {
    event.preventDefault();
    let apiUrl = 'http://localhost:8080/ers';

    let formData = new FormData(event.target);
    const data = {};
    formData.forEach((value, key) => (data[key] = value));

    // to avoid fetching auth endpoint again
    let principal = JSON.parse(sessionStorage.getItem("principal"));
    principal.username = data.username;
    sessionStorage.setItem("principal", JSON.stringify(principal))

    let response = await fetch(`${apiUrl}/users`, {
        method: 'PUT',
        credentials: 'include',
        body: new FormData(event.target)
    });

    if (response.status == 200) {
        console.log(response)
    } else {
        console.log(response)
    }

    event.target.reset();
};