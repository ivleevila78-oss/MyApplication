package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Находим элементы на экране
        val balanceText = view.findViewById<TextView>(R.id.balanceText)
        val topUpButton = view.findViewById<Button>(R.id.topUpButton)

        // --- ВОТ ТОТ САМЫЙ НОВЫЙ КОД ---
        val profileNameText = view.findViewById<TextView>(R.id.profileNameText)
        // Ставим имя из нашего хранилища!
        profileNameText.text = GameRepository.currentUser
        // -------------------------------

        // Функция обновления текста на экране
        fun updateBalanceUI() {
            balanceText.text = "${GameRepository.userBalance} ₽"
        }

        // Показываем актуальный баланс при заходе в профиль
        updateBalanceUI()

        // ЛОГИКА ПОПОЛНЕНИЯ
        topUpButton.setOnClickListener {
            GameRepository.userBalance += 1000 // Добавляем 1000 рублей
            updateBalanceUI() // Обновляем цифры на экране
            Toast.makeText(context, "Баланс пополнен на 1000 ₽!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}