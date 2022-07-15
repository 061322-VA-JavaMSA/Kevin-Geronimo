const populateProfile = async () => {
    await route()
    let principal = JSON.parse(sessionStorage.getItem("principal"));

    console.log(document.getElementById("user-role"));

    document.getElementById("user-role").textContent = principal.ersUserRole.role + " INFORMATION";
    document.getElementById("user-fullname").textContent = principal.firstName + " " + principal.lastName;
    document.getElementById("username").textContent = principal.username;
    document.getElementById("user-email").textContent = principal.email;
}