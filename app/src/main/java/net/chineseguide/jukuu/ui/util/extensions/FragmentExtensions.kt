package net.chineseguide.jukuu.ui.util.extensions

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.showLongToast(@StringRes text: Int) {
    Toast.makeText(requireActivity(), text, Toast.LENGTH_LONG).show()
}

fun Fragment.addBackPressedListener(enabledCallback: Boolean = true, action: OnBackPressedCallback.() -> Unit): OnBackPressedCallback {
    val callback: OnBackPressedCallback = object : OnBackPressedCallback(enabledCallback) {
        override fun handleOnBackPressed() {
            action.invoke(this)
        }
    }
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    return callback
}