package net.chineseguide.jukuu.ui.util.extensions

import androidx.appcompat.widget.SearchView

fun SearchView.setOnQuerySubmittedListener(onQuerySubmitted: (String) -> Unit) {

    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(s: String): Boolean {
            return true
        }

        override fun onQueryTextSubmit(s: String): Boolean {
            onQuerySubmitted(s)
            return true
        }
    })
}