package com.example.myapplication
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CatalogFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Я ДОБАВИЛ МНОГО ИГР, ЧТОБЫ ПОЯВИЛСЯ СКРОЛЛ ВНИЗ!
        val games = listOf(
            Game("Cyberpunk 2077", "1999 ₽", R.drawable.cyberpunk),
            Game("The Witcher 3", "999 ₽", R.drawable.witcher),
            Game("Counter-Strike 2", "Бесплатно", R.drawable.cs2),
            Game("Dota 2", "Бесплатно", R.drawable.dota),
            Game("GTA V", "1499 ₽", R.drawable.gta),
            Game("Red Dead Redemption 2", "2499 ₽", R.drawable.rdr),
            Game("Rust", "1100 ₽", R.drawable.rust),
            Game("DayZ", "1399 ₽", R.drawable.dayz),
            Game("Elden Ring", "3599 ₽", R.drawable.elden),
        )

        recyclerView.adapter = GameAdapter(games) { clickedGame ->
            showActionDialog(clickedGame)
        }

        return view
    }

    private fun showActionDialog(game: Game) {
        val options = arrayOf("В корзину", "В избранное", "В библиотеку")

        AlertDialog.Builder(requireContext())
            .setTitle("Что сделать с ${game.title}?")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> { // В КОРЗИНУ
                        val alreadyOwned = GameRepository.libraryList.any { it.title == game.title }
                        if (alreadyOwned) {
                            Toast.makeText(context, "Эта игра уже есть в вашей библиотеке!", Toast.LENGTH_SHORT).show()
                            return@setItems
                        }
                        val alreadyInCart = GameRepository.cartList.any { it.title == game.title }
                        if (alreadyInCart) {
                            Toast.makeText(context, "Игра уже добавлена в корзину!", Toast.LENGTH_SHORT).show()
                            return@setItems
                        }
                        GameRepository.cartList.add(game)
                        Toast.makeText(context, "Добавлено в корзину!", Toast.LENGTH_SHORT).show()
                    }

                    1 -> { // В ИЗБРАННОЕ
                        val alreadyInFav = GameRepository.favoritesList.any { it.title == game.title }
                        if (alreadyInFav) {
                            Toast.makeText(context, "Уже в избранном!", Toast.LENGTH_SHORT).show()
                            return@setItems
                        }
                        GameRepository.favoritesList.add(game)
                        Toast.makeText(context, "Добавлено в избранное!", Toast.LENGTH_SHORT).show()
                    }

                    2 -> { // В БИБЛИОТЕКУ
                        val alreadyOwned = GameRepository.libraryList.any { it.title == game.title }
                        if (alreadyOwned) {
                            Toast.makeText(context, "Эта игра уже есть в вашей библиотеке!", Toast.LENGTH_SHORT).show()
                            return@setItems
                        }
                        val boughtGame = game.copy(price = "В библиотеке")
                        GameRepository.libraryList.add(boughtGame)
                        Toast.makeText(context, "Добавлено в библиотеку!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            .show()
    }
}