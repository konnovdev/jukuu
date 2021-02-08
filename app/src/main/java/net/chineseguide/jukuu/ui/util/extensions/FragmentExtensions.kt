package net.chineseguide.jukuu.ui.util.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.showLongToast(@StringRes text: Int) {
    Toast.makeText(requireActivity(), text, Toast.LENGTH_LONG).show()
}