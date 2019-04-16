package com.neosoft.neostoreapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.neosoft.neostoreapp.model.request.LoginRequest
import com.neosoft.neostoreapp.model.response.LoginResponse
import com.neosoft.neostoreapp.utils.isNull
import com.neosoft.neostoreapp.view.activity.DashBoardActivity
import com.neosoft.neostoreapp.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var loginViewModel: LoginViewModel? = null
    //    var loginViewModel: RegisterViewModel2? = null
    var email: String? = null
    var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this@LoginActivity).get(LoginViewModel::class.java)
//        loginViewModel = ViewModelProviders.of(this@LoginActivity).get(RegisterViewModel2::class.java)

        ib_add.setOnClickListener {
            val registerIntent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(registerIntent)
        }

        btn_login.setOnClickListener {
            email = edt_login_email.text.toString()
            password = edt_login_password.text.toString()
            validateEmailAndPassword()
        }

        loginViewModel?.getLoginResponse()?.observe(
            this@LoginActivity,
            Observer<LoginResponse> { response ->
                response.let {
                    val intent = Intent(this, DashBoardActivity::class.java)
                    startActivity(intent)
                    Log.d("BaseResponse", response.toString())
                    Toast.makeText(this@LoginActivity, response?.userMessage, Toast.LENGTH_SHORT).show()
//                openDashboard()
                }
            })

//        loginViewModel?.getRegResponse2()?.observe(
//            this@LoginActivity,
//            Observer<LoginResponse> {
//                    response -> response.let {
//                val intent = Intent(this,DashBoardActivity::class.java)
//                startActivity(intent)
//                Log.d("BaseResponse", response.toString())
//                Toast.makeText(this@LoginActivity, response?.userMessage, Toast.LENGTH_SHORT).show()
////                openDashboard()
//            }
//            })

        edt_login_email.setText("test17@test.com")
        edt_login_password.setText("pass123")
    }

    private fun validateEmailAndPassword() {

        if (TextUtils.isEmpty(email)) {
            edt_login_email.isNull()
        } else if (TextUtils.isEmpty(password)) {
            edt_login_password.isNull()
        } else {
            val loginRequest = LoginRequest(email!!, password!!)
            loginViewModel?.loginUser(loginRequest)
            /*!!.observe(
                    this,
                    Observer<LoginResponse> {
                            response -> response.let {
                        val intent = Intent(this,DashBoardActivity::class.java)
                        startActivity(intent)
                        Log.d("BaseResponse", response.toString())
                        Toast.makeText(this@LoginActivity, response?.userMessage, Toast.LENGTH_SHORT).show()
//                openDashboard()
                    }
                    })*/


        }
    }
}
