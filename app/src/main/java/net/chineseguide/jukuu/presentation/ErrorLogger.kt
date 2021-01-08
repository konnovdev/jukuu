package net.chineseguide.jukuu.presentation

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject

interface ErrorLogger {

    fun log(error: Throwable)
}

class ErrorLoggerImpl @Inject constructor() : ErrorLogger {

    private val firebaseCrashlytics = FirebaseCrashlytics.getInstance()

    override fun log(error: Throwable) {
        firebaseCrashlytics.recordException(error)
    }
}

class ErrorLoggerMockImpl @Inject constructor() : ErrorLogger {

    private val tag = "LoggerMock"

    override fun log(error: Throwable) {
        Log.e(tag, "Recorded error: ", error)
    }
}