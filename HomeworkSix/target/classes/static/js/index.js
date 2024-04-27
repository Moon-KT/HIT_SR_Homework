const login = document.getElementById("login");

login.addEventListener("submit", (e) => {
    e.preventDefault();

    const inputs = document.querySelectorAll("input");
    const err = document.getElementById("err");

    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].value === '') {
            err.textContent = 'Please leave no field blank!';
            return;
        }
    }

    err.textContent = '';
    login.submit();
})