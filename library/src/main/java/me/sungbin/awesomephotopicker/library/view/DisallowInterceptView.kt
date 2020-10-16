package me.sungbin.awesomephotopicker.library.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

// https://stackoverflow.com/a/61890073/14299073
// Fix `Scroll not working for multiple RecyclerView in BottomSheetDialogFragment`
class DisallowInterceptView : LinearLayout {
    constructor(context: Context?) : super(context) {
        requestDisallowInterceptTouchEvent(true)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        requestDisallowInterceptTouchEvent(true)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        requestDisallowInterceptTouchEvent(true)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        return super.dispatchTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> requestDisallowInterceptTouchEvent(true)
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> requestDisallowInterceptTouchEvent(
                false
            )
        }
        return super.onTouchEvent(event)
    }
}