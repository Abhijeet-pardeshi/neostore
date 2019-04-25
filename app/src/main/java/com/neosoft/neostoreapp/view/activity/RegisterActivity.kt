package com.neosoft.neostoreapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.RadioGroup
import android.widget.Toast
import com.neosoft.neostoreapp.model.request.RegisterRequest
import com.neosoft.neostoreapp.model.response.RegisterResponse
import com.neosoft.neostoreapp.utils.isNotChecked
import com.neosoft.neostoreapp.utils.isNotSelected
import com.neosoft.neostoreapp.utils.isNull
import com.neosoft.neostoreapp.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    var mRegisterViewModel: RegisterViewModel? = null
    var firstName: String? = null
    var lastName: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null
    var rbId: Int? = null
    var gender: String? = null
    var phoneNumber: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mRegisterViewModel = ViewModelProviders.of(this@RegisterActivity).get(RegisterViewModel::class.java)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_36dp)

        btn_register.setOnClickListener {

            initValues()
            validateForm()

            Toast.makeText(this@RegisterActivity, gender, Toast.LENGTH_SHORT).show()
//
//            mRegisterViewModel?.getRegResponse()?.observe(this, Observer<RegisterResponse> { t->
//
//                //registration status
//                    Toast.makeText(this@RegisterActivity,t?.userMessage,Toast.LENGTH_SHORT).show()
//            })


            mRegisterViewModel?.getRegResponse()?.observe(this, Observer<RegisterResponse> { response ->

                //registration status
                response.let {
                    Toast.makeText(this@RegisterActivity, response?.userMessage, Toast.LENGTH_SHORT).show()
                    val loginIntent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    finish()
                    startActivity(loginIntent)
                }
            })
        }
    }

    private fun initValues() {
        firstName = edt_reg_first_name.text.toString()
        lastName = edt_reg_last_name.text.toString()
        email = edt_reg_email.text.toString()
        password = edt_reg_password.text.toString()
        confirmPassword = edt_reg_con_password.text.toString()
        rbId = rg_gender.checkedRadioButtonId
        phoneNumber = edt_reg_phone.text.toString()
    }

    private fun getCheckedId() {
        if (rbId == R.id.rb_male) {
            gender = "M"
        } else if (rbId == R.id.rb_female) {
            gender = "F"
        }
    }

    private fun isEmailValid(): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edt_reg_email.error = "Invalid Email"
            edt_reg_email.requestFocus()
            return false
        }
        return true
    }

    private fun isPhoneNumberValid(): Boolean {
        if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            edt_reg_phone.error = "Invalid Phone Number"
            edt_reg_phone.requestFocus()
            return false
        }

        if (phoneNumber?.length!! < 10 || phoneNumber?.length!! > 12) {
            edt_reg_phone.error = "Invalid Phone Number"
            edt_reg_phone.requestFocus()
            return false
        }
        return true
    }

    private fun arePasswordsSame(): Boolean {
        if (password != confirmPassword) {
            edt_reg_con_password.error = "Confirm password should match with password."
            edt_reg_con_password.requestFocus()
            return false
        }
        return true
    }

    private fun validateForm() {
        //null check
        if (TextUtils.isEmpty(firstName)) {
            edt_reg_first_name.isNull()
        } else if (TextUtils.isEmpty(lastName)) {
            edt_reg_last_name.isNull()
        } else if (TextUtils.isEmpty(email)) {
            edt_reg_email.isNull()
        } else if (TextUtils.isEmpty(password)) {
            edt_reg_password.isNull()
        } else if (TextUtils.isEmpty(confirmPassword)) {
            edt_reg_con_password.isNull()
        } else if (rg_gender.checkedRadioButtonId == -1) {
            Log.d("RG", "Not selected")
            rg_gender.isNotSelected()
        } else if (TextUtils.isEmpty(phoneNumber)) {
            edt_reg_phone.isNull()
        } else if (!cb_terms.isChecked) {
            cb_terms.isNotChecked()
        } else {
            //email and phone number validation
            getCheckedId()

            if (isEmailValid() && arePasswordsSame() && isPhoneNumberValid()) {
                Log.d("Request", "$firstName $lastName $email $password $confirmPassword $gender $phoneNumber")
                val registerRequest = RegisterRequest(
                    firstName!!,
                    lastName!!,
                    email!!,
                    password!!,
                    confirmPassword!!,
                    gender.toString(),
                    phoneNumber!!
                )
                mRegisterViewModel?.registerUser(registerRequest)
            }
        }
    }
//
//    override fun onBackPressed() {
//        val loginIntent = Intent(this,LoginActivity::class.java)
//        finish()
//        startActivity(loginIntent)
//    }

}