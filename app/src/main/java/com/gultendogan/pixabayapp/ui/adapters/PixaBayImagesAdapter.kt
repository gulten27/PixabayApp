package com.gultendogan.pixabayapp.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gultendogan.pixabayapp.R
import com.gultendogan.pixabayapp.common.base.ui.BaseRvAdapter
import com.gultendogan.pixabayapp.utils.ext.inflateRvItem
import com.gultendogan.pixabayapp.databinding.ItemImageMainBinding
import com.gultendogan.pixabayapp.data.entity.Hit

class PixaBayImagesAdapter(
    val clickListener: (Hit) -> Unit
) : BaseRvAdapter<Hit, PixaBayImagesAdapter.PixaBayViewHolder>() {

    var onFavoriteItemClick: ((Hit) -> Unit)? = null

    inner class PixaBayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemImageMainBinding.bind(itemView)
        fun bindView(hit: Hit) {
            binding.card.setOnClickListener {
                clickListener.invoke(hit)
            }
            binding.image.load(hit.webformatURL)
            binding.like.text = hit.likes.toString()
            binding.comments.text = "(" + hit.comments.toString() + " comments)"
            binding.views.text = hit.views.toString() + " (views)"

            binding.favButton.setOnClickListener {
                onFavoriteItemClick?.invoke(hit)


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaBayViewHolder {
        return PixaBayViewHolder(parent.inflateRvItem(R.layout.item_image_main))
    }

    override fun onBindViewHolder(holder: PixaBayViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }
}