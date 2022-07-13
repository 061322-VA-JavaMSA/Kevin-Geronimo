async function loginFormHandler(event) {
    event.preventDefault();

    let apiUrl = 'http://localhost:8080/ers';

    let response = await fetch(`${apiUrl}/auth`,{
        method: 'POST',
        credentials: 'include',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(new FormData(event.target))
    });

    if(response.status == 200){
        let data = await response.json();

        /*
            persisting the User object sent back to session storage for use in other pages
            Session Storage only allows persistence of Strings so the JS Object is converted to a JSON string using JSON.stringify
         */
         sessionStorage.setItem('principal', JSON.stringify(data));
        // redirect to the homepage on success
        //window.location.href="./index.html";
        console.log(sessionStorage.getItem('principal'))
    } else{
        console.log('Unable to login.')
    }
};