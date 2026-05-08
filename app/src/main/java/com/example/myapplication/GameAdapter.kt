package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Мы добавили onItemClick в скобки!
class GameAdapter(
    private val gameList: List<Game>,
    private val onItemClick: ((Game) -> Unit)? = null
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.gameImage)
        val title: TextView = itemView.findViewById(R.id.gameTitle)
        val price: TextView = itemView.findViewById(R.id.gamePrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = gameList[position]
        holder.title.text = game.title
        holder.price.text = game.price
        holder.image.setImageResource(game.imageResId)

        // ВОТ ТУТ МЫ ЛОВИМ НАЖАТИЕ НА КАРТОЧКУ
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(game)
        }
    }

    override fun getItemCount(): Int = gameList.size
}