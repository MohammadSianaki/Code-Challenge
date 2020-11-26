package com.github.mohammadsianaki.core.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavArgs
import androidx.navigation.NavDirections

sealed class NavigationCommand {
    data class ForwardTo(@IdRes val destinationId: Int, val args: NavArgs? = null) :
        NavigationCommand()

    data class DirectTo(val navDirections: NavDirections) : NavigationCommand()
    data class BackTo(@IdRes val destinationId: Int) : NavigationCommand()
    data class DeepLinkTo(val deepLink: String) : NavigationCommand()
    object Up : NavigationCommand()
}
