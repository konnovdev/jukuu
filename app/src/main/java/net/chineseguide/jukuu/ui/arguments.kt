package net.chineseguide.jukuu.ui

import android.os.Bundle

private const val TASK_ID_ARG = "task_id_arg"

var Bundle?.taskId: String
    get() = this?.getString(TASK_ID_ARG)!!
    set(value) = this!!.putString(TASK_ID_ARG, value)