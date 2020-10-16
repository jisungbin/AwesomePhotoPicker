package me.sungbin.awesomephotopicker.library.view.ui


/**
 * Created by SungBin on 2020-10-16.
 */

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_content.*
import me.sungbin.awesomephotopicker.library.R
import me.sungbin.awesomephotopicker.library.adapter.PhotoAdapter
import me.sungbin.awesomephotopicker.library.model.Tile
import me.sungbin.awesomephotopicker.library.model.TileType
import me.sungbin.awesomephotopicker.library.util.GridSpacingItemDecoration
import me.sungbin.awesomephotopicker.library.util.PhotoUtil


class AwesomePhotoPicker : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.layout_content, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tiles = ArrayList<Tile>()
        PhotoUtil.getAllPath(requireContext()).map {
            tiles.add(Tile(it, TileType.PHOTO))
        }

        rv_gallery.apply {
            adapter = PhotoAdapter(tiles, requireActivity())
            val spacingInPixels = resources.getDimensionPixelSize(R.dimen.margin_half)
            addItemDecoration(GridSpacingItemDecoration(3, spacingInPixels, true, 0))
        }
    }
}