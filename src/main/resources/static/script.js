
/*console.log("JavaScript is working!");*/



document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("login-form");
    if (loginForm) {
        loginForm.addEventListener("submit", function (e) {
            e.preventDefault();
            // your login logic here
        });
    }
});

