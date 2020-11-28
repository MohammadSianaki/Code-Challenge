package com.github.mohammadsianaki.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.github.mohammadsianaki.core.navigation.NavigationCommand

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

inline fun <reified T : NavigationCommand> Fragment.observerNavigationCommands(liveData: LiveData<T>) {
    observeInFragment(liveData) { command ->
        when (command) {
            is NavigationCommand.ForwardTo -> findNavController()
                .navigate(command.destinationId)
            is NavigationCommand.BackTo -> findNavController()
                .navigate(command.destinationId)
            is NavigationCommand.DeepLinkTo -> findNavController()
                .navigate(command.deepLink.toUri())
            is NavigationCommand.Up -> findNavController().navigateUp()
            is NavigationCommand.DirectTo -> findNavController()
                .navigate(command.navDirections)
        }
    }
}
