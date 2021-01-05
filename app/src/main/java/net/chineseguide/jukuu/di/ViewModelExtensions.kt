package net.chineseguide.jukuu.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import toothpick.Scope
import toothpick.Toothpick

@Deprecated("No need for hilt")
inline fun <reified T : ViewModel> ViewModelStoreOwner.viewModel(): Lazy<T> = lazy {
    ViewModelProvider(this, ViewModelFactory(this))
        .get(T::class.java)
}

@Deprecated("No need for hilt")
class ViewModelFactory(
    viewModelStoreOwner: ViewModelStoreOwner
) : ViewModelProvider.Factory {
    private val scope: Scope =
        if (viewModelStoreOwner is Fragment) {
            Toothpick.openScopes(Scopes.ACTIVITY, Scopes.FRAGMENT)
        } else {
            Toothpick.openScopes(Scopes.APP, Scopes.ACTIVITY)
        }

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        scope.getInstance(modelClass) as T
}