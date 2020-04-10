package net.chineseguide.jukuu.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeSafe(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, Observer { it?.let(observer::invoke) })
}