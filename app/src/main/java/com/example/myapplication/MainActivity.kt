package com.example.myapplication // Вставь СВОЁ имя из кавычек!

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Загружаем Каталог при первом запуске
        if (savedInstanceState == null) {
            loadFragment(CatalogFragment())
        }

        // Обработка нажатий на нижнее меню
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_catalog -> loadFragment(CatalogFragment())
                R.id.nav_library -> loadFragment(LibraryFragment())
                R.id.nav_favorite -> loadFragment(FavoritesFragment()) // Убедись, что создал этот класс
                R.id.nav_cart -> loadFragment(CartFragment())         // Убедись, что создал этот класс
                R.id.nav_profile -> loadFragment(ProfileFragment())   // Убедись, что создал этот класс
            }
            true
        }
    }

    // Функция для смены экранов
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}