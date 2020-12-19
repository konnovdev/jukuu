package net.chineseguide.jukuu.ui.util

import androidx.recyclerview.widget.RecyclerView

class VerticalScrollListener(
    private val onScrolled: (() -> Unit)? = null
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if(dy > 0) {
            onScrolled?.invoke()
        }
    }
}