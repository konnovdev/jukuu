package net.chineseguide.jukuu.di.module

import net.chineseguide.jukuu.data.ResultRepositoryImpl
import net.chineseguide.jukuu.domain.repository.ResultRepository
import toothpick.config.Module

class DataModule : Module() {

    init {
        bind(ResultRepository::class.java)
            .to(ResultRepositoryImpl::class.java)
            .singleton()
    }
}