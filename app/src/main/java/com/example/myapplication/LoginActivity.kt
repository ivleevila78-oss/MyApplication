package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameInput = findViewById<EditText>(R.id.loginUsername)
        val passwordInput = findViewById<EditText>(R.id.loginPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvGoToRegister = findViewById<TextView>(R.id.tvGoToRegister)

        // Переход на экран регистрации
        tvGoToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Логика входа
        btnLogin.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Проверяем, есть ли такой юзер в нашей "базе"
            val savedPassword = GameRepository.usersDatabase[username]

            if (savedPassword != null && savedPassword == password) {
                // Успех! Запоминаем имя и открываем главное приложение
                GameRepository.currentUser = username
                Toast.makeText(this, "Добро пожаловать, $username!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Закрываем экран входа, чтобы нельзя было вернуться кнопкой "Назад"
            } else {
                Toast.makeText(this, "Неверный логин или пароль!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}