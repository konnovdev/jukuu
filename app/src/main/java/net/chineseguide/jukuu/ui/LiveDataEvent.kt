package net.chineseguide.jukuu.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class LiveDataEvent : LiveData<Unit>() {

    override fun observe(owner: LifecycleOwner, observer: Observer<in Unit>) {
        super.observe(owner, Observer {
            if (value != null) {
                observer.onChanged(value)
                value = null
            }
        })
    }

    fun send() {
        value = Unit
    }
}