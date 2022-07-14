const route = (event) => {
    event = event || window.event;
    event.preventDefault();
    window.history.pushState({}, "", event.target.href);
    handleLocation();
};

const routes = {
    404: "/views/404.html",
    "/": "./views/index.html",
    "/login": "/views/login.html",
    "/register": "/views/register.html",
};

const handleLocation = async () => {
    const path = window.location.pathname;
    const route = routes[path] || routes[404];
    const html = await fetch(route).then((data) => data.text());

    let login_nav = document.getElementById("login_nav");
    if (path === "/login") {
        login_nav.classList.add("hidden")
    } else if (path === "/") {
        login_nav.classList.remove("hidden")
    }

    document.getElementById("main-page").innerHTML = html;
};

window.onpopstate = handleLocation;
window.route = route;

handleLocation();