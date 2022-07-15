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
    "/home": "/views/home.html",
    "/pending": "/views/pending.html"
};

const updateNav = (path) => {
    let login_nav = document.getElementById("login_nav");
    let home_nav = document.getElementById("home_nav");
    let logout_nav = document.getElementById("logout_nav");
    
    if (path === "/" && sessionStorage.getItem("principal")) {
        home_nav.classList.remove("hidden")
        login_nav.classList.add("hidden")
        logout_nav.classList.remove("hidden")
    } else if (path === "/") {
        login_nav.classList.remove("hidden")
        home_nav.classList.add("hidden")
        logout_nav.classList.add("hidden")
    } else if (path === "/login") {
        login_nav.classList.add("hidden")
        home_nav.classList.add("hidden")
        logout_nav.classList.add("hidden")
    } else {
        logout_nav.classList.remove("hidden")
        login_nav.classList.add("hidden")
        home_nav.classList.add("hidden")
    }
}

const handleLocation = async () => {
    const path = window.location.pathname;
    const route = routes[path] || routes[404];
    const html = await fetch(route).then((data) => data.text());
    updateNav(path);

    if (path === "/pending") {
        document.getElementById("inner-main-page").innerHTML = html;
    } else {
        document.getElementById("main-page").innerHTML = html;
    }

};

window.onpopstate = handleLocation;
window.route = route;

handleLocation();