const register = document.getElementById("register");

register.addEventListener("submit", (e) => {
    e.preventDefault();

    const inputs = document.querySelectorAll("input");
    const err = document.getElementById("err");

    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].value === '') {
            err.textContent = "Please leave no field empty!";
            return;
        }
    }

    const password = document.getElementById("password").value;
    const confirm = document.getElementById("confirm").value;

    if (password !== confirm) {
        err.textContent = "Passwords do not match!";
        return;
    }

    err.textContent = "";
    register.submit();
})