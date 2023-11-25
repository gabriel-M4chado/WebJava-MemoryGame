const email = document.getElementById('typeEmailX');
const passWd = document.getElementById('typePasswordX');
const btnLogin = document.getElementById('btnLogin');
const btnSignUP = document.getElementById('btnSignUP');

btnSignUP.classList.add('d-none');

btnLogin.addEventListener('click', () => {
    removeValidationLogin();
    addValidation(false);
    passWd.value = email.value;
    if (verifyInputsForm(false)) {
        login(email.value, passWd.value, 'login');
    }
});

const btnSignUPText = document.getElementById('btnSignUPText');
btnSignUPText.addEventListener('click', () => {
    const titleLoginScreen = document.getElementById('titleLoginScreen');
    const textSigup = document.querySelector('.mb-0');
    const containerTypePasswordX = document.querySelector('#containerTypePasswordX');

    removeValidationLogin();

    btnLogin.style.display = 'none';
    btnSignUP.classList.remove('d-none');
    containerTypePasswordX.classList.remove('d-none');
    titleLoginScreen.textContent = 'sing up';
    textSigup.classList.add('d-none');
    document.querySelector('#textDataLogin').textContent = 'Enter your login details';
});

btnSignUP.addEventListener('click', () => {
    addValidation(true);
    document.querySelector('.mb-0').classList.add('d-none');
    if (verifyInputsForm(false)) {
        login(email.value, passWd.value, 'signup');
    }
});


function removeValidationLogin() {
    email.classList.remove('is-invalid');
    passWd.classList.remove('is-invalid');
    email.classList.remove('is-valid');
    passWd.classList.remove('is-valid');
}

function addValidation(signUp) {
    if (signUp) {
        (passWd.value === '') ? passWd.classList.add('is-invalid') : passWd.classList.add('is-valid');
    }

    (email.value === '') ? email.classList.add('is-invalid') : email.classList.add('is-valid');


}

function verifyInputsForm(isSignUp) {
    let allInputs = false;

    allInputs = (email.classList.contains('is-valid'));

    if (isSignUp) {
        allInputs = passWd.classList.contains('is-valid');
    }

    return allInputs;
}
