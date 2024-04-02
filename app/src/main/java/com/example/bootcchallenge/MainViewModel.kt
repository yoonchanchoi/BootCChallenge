package com.example.bootcchallenge

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class MainViewModel : ViewModel() {
    private val _name = MutableLiveData<Boolean>()
    val name: LiveData<Boolean> = _name

    private val _email = MutableLiveData<Boolean>()
    val email: LiveData<Boolean> = _email

    private val _pw = MutableLiveData<Boolean>()
    val pw: LiveData<Boolean> = _pw

    private val _chPw = MutableLiveData<Boolean>()
    val chPw: LiveData<Boolean> = _chPw

    private val _event = MutableLiveData<Boolean>()
    val event: LiveData<Boolean> = _event

    private var _pwStr = ""
    private var _chPwStr = ""


    fun validation(str: String, flag: String) {

        when (flag) {
            Constants.NAME -> {
                if (str.isNullOrEmpty()) {
                    _name.value = false
                } else {
                    _name.value = true
                }
            }

            Constants.EMAIL -> {
                if (str.isNullOrEmpty()) {
                    _email.value = false
                } else {
                    var email = str.trim()
                    if (Pattern.matches(Constants.EMAILVALIDATION, email)) {
                        _email.value = true
                    } else {
                        _email.value = false
                    }
                }
            }

            Constants.PASSWORD -> {
                _pwStr = str
                if (str.isNullOrEmpty()) {
                    _pw.value = false
                } else {
                    if (_chPwStr == str) {
                        _chPw.value = true
                    } else {
                        _chPw.value = false
                    }
                    val pass = str.trim()
                    if (Pattern.matches(Constants.PASS_VALIDATION, pass)) {
                        Log.e("cyc", "str-->${str}")
                        _pw.value = true

                    } else {
                        _pw.value = false
                    }
                }
            }

            Constants.CHECKPASSWORD -> {
                _chPwStr = str
                if (_pwStr == str) {
                    _chPw.value = true
                } else {
                    _chPw.value = false
                }
            }
        }
    }


    fun enableEvent() {
        if (_name.value == true && _email.value == true && _pw.value == true && _chPw.value == true) {
            _event.value = true
        } else {
            _event.value = false
        }
    }
}