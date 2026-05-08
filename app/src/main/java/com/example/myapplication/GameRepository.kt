package com.example.myapplication // НЕ ЗАБУДЬ СВОЙ ПАКЕТ!

object GameRepository {
    val cartList = mutableListOf<Game>()
    val favoritesList = mutableListOf<Game>()
    val libraryList = mutableListOf<Game>()
    var userBalance = 2500

    // --- НОВОЕ ДЛЯ АВТОРИЗАЦИИ ---
    // База пользователей (Логин -> Пароль)
    val usersDatabase = mutableMapOf<String, String>()

    // Имя того, кто сейчас вошел в систему
    var currentUser = "Гость"
}