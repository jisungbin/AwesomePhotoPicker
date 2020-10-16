package me.sungbin.awesomephotopicker.library.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import me.sungbin.awesomephotopicker.library.R
import me.sungbin.awesomephotopicker.library.databinding.LayoutTileBinding
import me.sungbin.awesomephotopicker.library.model.Tile
import me.sungbin.awesomephotopicker.library.model.TileType


/**
 * Created by SungBin on 2020-07-20.
 */

class PhotoAdapter(
    private val items: List<Tile>,
    private val activity: Activity
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    class ViewHolder(private val binding: LayoutTileBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViewHolder(tile: Tile) {
            with(binding) {
                when (tile.type) {
                    TileType.PHOTO -> {
                        ivPhoto.setImageURI(tile.uri)
                    }
                    TileType.CAMERA -> {
                    }
                    TileType.GALLERY -> {
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(activity),
                R.layout.layout_tile, viewGroup, false
            )
        )

    override fun onBindViewHolder(@NonNull viewholder: ViewHolder, position: Int) {
        viewholder.bindViewHolder(items[position])
    }

    override fun getItemCount() = items.size
    override fun getItemId(position: Int) = position.toLong()
    override fun getItemViewType(position: Int) = position
}