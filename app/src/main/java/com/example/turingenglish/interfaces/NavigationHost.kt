package com.example.turingenglish.interfaces

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


/**
 * A host (typically an `Activity`} that can display fragments and knows how to respond to
 * navigation events.
 */
interface NavigationHost {
    /**
     * Trigger a navigation to the specified fragment, optionally adding a transaction to the back
     * stack to make this navigation reversible.
     */
    fun navigateTo(fragment: Fragment, addToBackstack: Boolean)
}
