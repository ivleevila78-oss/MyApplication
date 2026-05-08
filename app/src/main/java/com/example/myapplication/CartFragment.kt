package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.cartRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = GameAdapter(GameRepository.cartList)
        recyclerView.adapter = adapter

        val totalText = view.findViewById<TextView>(R.id.totalPriceText)
        val buyButton = view.findViewById<Button>(R.id.buyButton)

        // Функция подсчета суммы
        fun calculateTotal(): Int {
            var sum = 0
            for (game in GameRepository.cartList) {
                val priceNumber = game.price.replace("[^0-9]".toRegex(), "")
                if (priceNumber.isNotEmpty()) {
                    sum += priceNumber.toInt()
                }
            }
            totalText.text = "Итого: $sum ₽"
            return sum // Теперь функция возвращает цифру суммы!
        }

        calculateTotal()

        // ЛОГИКА ПОКУПКИ
        buyButton.setOnClickListener {
            if (GameRepository.cartList.isEmpty()) {
                Toast.makeText(context, "Корзина пуста!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Узнаем итоговую сумму корзины
            val totalSum = calculateTotal()

            // ПРОВЕРКА БАЛАНСА
            if (GameRepository.userBalance >= totalSum) {
                // 1. Списываем деньги
                GameRepository.userBalance -= totalSum

                // 2. Переносим игры в библиотеку
                for (game in GameRepository.cartList) {
                    val boughtGame = game.copy(price = "В библиотеке")
                    GameRepository.libraryList.add(boughtGame)
                }

                // 3. Очищаем корзину и обновляем экран
                GameRepository.cartList.clear()
                adapter.notifyDataSetChanged()
                calculateTotal()

                Toast.makeText(context, "Успешно! Остаток: ${GameRepository.userBalance} ₽", Toast.LENGTH_LONG).show()
            } else {
                // ЕСЛИ ДЕНЕГ НЕ ХВАТАЕТ
                Toast.makeText(context, "Недостаточно средств! Пополните баланс в профиле.", Toast.LENGTH_LONG).show()
            }
        }

        return view
    }
}