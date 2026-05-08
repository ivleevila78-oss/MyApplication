package com.example.myapplication // Твой пакет!

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameInput = findViewById<EditText>(R.id.regUsername)
        val passwordInput = findViewById<EditText>(R.id.regPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (GameRepository.usersDatabase.containsKey(username)) {
                Toast.makeText(this, "Этот логин уже занят!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Сохраняем пользователя в базу
            GameRepository.usersDatabase[username] = password
            Toast.makeText(this, "Аккаунт создан! Теперь войдите.", Toast.LENGTH_LONG).show()

            finish() // Закрываем экран регистрации и возвращаемся ко входу
        }
    }
}