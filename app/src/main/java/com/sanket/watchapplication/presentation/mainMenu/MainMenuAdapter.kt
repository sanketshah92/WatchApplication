package com.sanket.watchapplication.presentation.mainMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sanket.watchapplication.R


class MainMenuAdapter(
    private val menuItems: List<String>,
    private val onMenuItemClickListener: OnMenuItemClickListener
) :
    RecyclerView.Adapter<MainMenuAdapter.ViewHolder>() {

    class ViewHolder(
        itemView: View,
        private val onMenuItemClickListener: OnMenuItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {
        fun bind(menuItem: String) {
            val item = itemView.findViewById<TextView>(R.id.txtMainMenuItem)
            item.text = menuItem
            item.setOnClickListener {
                onMenuItemClickListener.onMenuItemSelected(menuItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_main_menu, parent, false)
        return ViewHolder(view,onMenuItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuItems[position])
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    interface OnMenuItemClickListener {
        fun onMenuItemSelected(selectedItem: String)
    }
}