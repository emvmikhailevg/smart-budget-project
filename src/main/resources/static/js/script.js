const loginForm = document.querySelector('.login-form')
const regForm = document.querySelector('.reg-form')

const regBtn = document.querySelector('.login-form__reg__btn')
const loginBtn = document.querySelector('.reg-form__login-btn')

regBtn.addEventListener('click', () => {
    regForm.classList.remove('hidden')
    loginForm.classList.add('hidden')
})

loginBtn.addEventListener('click', () => {
    loginForm.classList.remove('hidden')
    regForm.classList.add('hidden')
})

const noAccReg = document.querySelector('.no-acc__register')
const noAccLogin = document.querySelector('.no-acc-login')

noAccReg.addEventListener('click', () => {
    regForm.classList.remove('hidden')
    loginForm.classList.add('hidden')
})

noAccLogin.addEventListener('click', () => {
    loginForm.classList.remove('hidden')
    regForm.classList.add('hidden')
})