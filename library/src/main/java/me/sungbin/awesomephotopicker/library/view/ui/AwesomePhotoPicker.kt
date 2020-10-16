package me.sungbin.awesomephotopicker.library.view.ui


/**
 * Created by SungBin on 2020-10-16.
 */

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_content.*
import me.sungbin.awesomephotopicker.library.R
import me.sungbin.awesomephotopicker.library.adapter.PhotoAdapter
import me.sungbin.awesomephotopicker.library.model.Tile
import me.sungbin.awesomephotopicker.library.model.TileType
import me.sungbin.awesomephotopicker.library.util.GridSpacingItemDecoration
import me.sungbin.awesomephotopicker.library.util.PhotoUtil


class AwesomePhotoPicker : BottomSheetDialogFragment() {

    private val bottomSheetBehaviorCallback: BottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
            Log.w("BBB", "BBBB")

            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismissAllowingStateLoss()
            }
        }

        override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) = Unit
    }


    /* @SuppressLint("RestrictedApi")
     override fun setupDialog(dialog: Dialog, style: Int) {
         super.setupDialog(dialog, style)
         val contentView = View.inflate(context, R.layout.layout_content, null)
         dialog.setContentView(contentView)
         val layoutParams =
             (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
         val behavior = layoutParams.behavior
         if (behavior != null && behavior is BottomSheetBehavior<*>) {
             (behavior as BottomSheetBehavior).addBottomSheetCallback(bottomSheetBehaviorCallback)
         }
     }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.layout_content, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val maxHeight = getPopupHeight(.85f)
        cl_container.maxHeight = maxHeight + 1

        dialog?.setOnShowListener { dialog ->

            val d = dialog as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)!!
            val behavior = BottomSheetBehavior.from(bottomSheet)

            initLayoutHeight()

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onSlide(p0: View, p1: Float) {}

                override fun onStateChanged(p0: View, p1: Int) {
                    when (p1) {
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            dismiss()
                        }
                        else -> Unit
                    }
                }

            })
        }

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

    private fun getPopupHeight(percent: Float): Int {
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        return (displayMetrics.heightPixels * percent).toInt()
    }

    private fun initLayoutHeight() {
        val d = dialog as BottomSheetDialog
        val bottomSheet =
            d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)!!

        val behavior = BottomSheetBehavior.from(bottomSheet)

        behavior.apply {
            skipCollapsed = true
            state = BottomSheetBehavior.STATE_EXPANDED
        }

        val params = bottomSheet.layoutParams as CoordinatorLayout.LayoutParams
        val maxHeight = getPopupHeight(.85f)

        if (maxHeight < bottomSheet.height) {
            params.height = maxHeight
            bottomSheet.layoutParams = params
            behavior.peekHeight = maxHeight
        }
    }
}