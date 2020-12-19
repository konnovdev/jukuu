package net.chineseguide.jukuu.ui.util

import android.os.Bundle

fun bundleOf(block: Bundle.() -> Unit) = Bundle().apply(block)