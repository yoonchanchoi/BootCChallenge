package com.example.bootcchallenge

object Constants {
    // key값
    const val NAME = "name"
    const val EMAIL = "email"
    const val PASSWORD = "password"
    const val CHECKPASSWORD = "checkPassword"

    // 유효성
    const val EMAILVALIDATION = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    const val PASS_VALIDATION  = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$"
}