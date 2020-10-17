package net.chineseguide.jukuu.ui

import android.os.Bundle

fun bundleOf(block: Bundle.() -> Unit) = Bundle().apply(block)