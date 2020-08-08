package net.chineseguide.jukuu

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun <T> executeCoroutine(function: () -> T): CoroutineResult<T> {
    var result: T? = null
    var exception: Throwable? = null

    CoroutineScope(Dispatchers.IO).launch {
        runCatching { function }
            .onSuccess {
                @Suppress("UNCHECKED_CAST")
                result = it as T
            }
            .onFailure { throwable ->
                exception = throwable
            }
    }
    return CoroutineResult(result, exception)
}

class CoroutineResult<T>(private val result: T?, private val exception: Throwable?) {

    private var observeOnMain = false

    fun observeOnMain(): CoroutineResult<T> {
        observeOnMain = true
        return this
    }

    fun onSuccess(function: (result: T) -> Unit): CoroutineResult<T> {
        if (result == null) {
            return this
        }

        if (observeOnMain) {
            CoroutineScope(Dispatchers.Main).launch { function(result) }
        } else {
            function(result)
        }
        return this
    }

    fun onError(function: (error: Throwable) -> Unit): CoroutineResult<T> {
        if (exception == null) {
            return this
        }

        if (observeOnMain) {
            CoroutineScope(Dispatchers.Main).launch { function(exception) }
        } else {
            function(exception)
        }

        return this
    }
}