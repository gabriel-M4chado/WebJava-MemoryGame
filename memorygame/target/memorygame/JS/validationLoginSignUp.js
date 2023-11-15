const email = document.getElementById('typeEmailX');
const passWd = document.getElementById('typePasswordX');
const btnLogin = document.getElementById('btnLogin');
const btnSignUP = document.getElementById('btnSignUP');
const containerTypeUserX = document.getElementById('containerTypeUserX');

btnSignUP.classList.add('d-none');
containerTypeUserX.classList.add('d-none');

btnLogin.addEventListener('click', () => {
    removeValidationLogin();
    addValidation(false);
    if (verifyInputsForm(false)) {
        login(email.value, passWd.value);
    }
});

const btnSignUPText = document.getElementById('btnSignUPText');
btnSignUPText.addEventListener('click', () => {
    const titleLoginScreen = document.getElementById('titleLoginScreen');

    removeValidationLogin();

    btnLogin.style.display = 'none';
    btnSignUP.classList.remove('d-none');
    containerTypeUserX.classList.remove('d-none');
    titleLoginScreen.textContent = 'sing up';
});

btnSignUP.addEventListener('click', () => {
    addValidation(true);
    document.querySelector('.mb-0').classList.add('d-none');
    document.querySelector('#textDataLogin').textContent = 'Enter your login details';
});


function removeValidationLogin() {
    email.classList.remove('is-invalid');
    passWd.classList.remove('is-invalid');
    email.classList.remove('is-valid');
    passWd.classList.remove('is-valid');
}

function addValidation(signUp) {
    if (signUp) {
        const typeUserX = document.getElementById('typeUserX');
        (typeUserX.value === '') ? typeUserX.classList.add('is-invalid') : typeUserX.classList.add('is-valid');
    }

    (email.value === '') ? email.classList.add('is-invalid') : email.classList.add('is-valid');

    (passWd.value === '') ? passWd.classList.add('is-invalid') : passWd.classList.add('is-valid');
}

function verifyInputsForm(isSignUp) {
    let allInputs = false;

    if (isSignUp) {
        const typeUserX = document.getElementById('typeUserX');
        allInputs = typeUserX.classList.contains('is-valid');
    }

    allInputs = (email.classList.contains('is-valid') && passWd.classList.contains('is-valid'));

    return allInputs;
}
