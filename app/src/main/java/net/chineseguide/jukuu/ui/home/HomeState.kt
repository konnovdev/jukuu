package net.chineseguide.jukuu.ui.home

import net.chineseguide.jukuu.domain.entity.Result

sealed class HomeState {

    object EmptyTaskList : HomeState()

    class TaskList(val resultList: List<Result>) : HomeState()
}
