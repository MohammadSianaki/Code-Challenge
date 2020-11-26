package com.github.mohammadsianaki.core.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.mohammadsianaki.core.navigation.NavigationCommand

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

inline fun <reified T : NavigationCommand> LiveData<T>.observerNavigationCommands(
    fragment: Fragment
) {
    observe(fragment.viewLifecycleOwner, Observer { command ->
        when (command) {
            is NavigationCommand.ForwardTo -> fragment.findNavController()
                .navigate(command.destinationId)
            is NavigationCommand.BackTo -> fragment.findNavController()
                .navigate(command.destinationId)
            is NavigationCommand.DeepLinkTo -> fragment.findNavController()
                .navigate(command.deepLink.toUri())
            is NavigationCommand.Up -> fragment.findNavController().navigateUp()
            is NavigationCommand.DirectTo -> fragment.findNavController()
                .navigate(command.navDirections)
        }
    })
}
