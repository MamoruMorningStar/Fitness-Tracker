package com.example.fitnessapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.R
import com.example.fitnessapp.helpers.SessionManager

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginField = findViewById<EditText>(R.id.login_edittext)
        val passwordField = findViewById<EditText>(R.id.password_edittext)
        val loginButton = findViewById<Button>(R.id.login_button)
        val backArrow = findViewById<ImageView>(R.id.back_arrow)

        sessionManager = SessionManager(this)

        backArrow.setOnClickListener {
            navigateToMainActivity()
        }

        loginButton.setOnClickListener {
            val login = loginField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Для lab5 - простое логирование без проверки в БД
            sessionManager.createLoginSession(login)
            navigateToEmptystateActivity()
        }
    }

    private fun navigateToEmptystateActivity() {
        val intent = Intent(this, EmptystateActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
