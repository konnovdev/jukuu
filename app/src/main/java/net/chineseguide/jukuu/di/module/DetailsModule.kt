package net.chineseguide.jukuu.di.module

import android.os.Bundle
import net.chineseguide.jukuu.ui.detail.DetailsFragmentArgs
import toothpick.config.Module

class DetailsModule(args: Bundle) : Module() {
    init {
        val resultId = DetailsFragmentArgs.fromBundle(args).resultId
        bind(String::class.java).toInstance(resultId)
    }
}