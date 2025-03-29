package com.example.segon_pix_android.ui.component.bar.bottom_bar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class BottomNavigationBarAction(
    val onUpdateIndex: (newIndex: Int) -> Unit,
)

class BottomNavigationBarViewModel : ViewModel() {
    var index by mutableIntStateOf(0)
        private set

    fun onGetBottomNavigationBarAction(): BottomNavigationBarAction =
        BottomNavigationBarAction(
            onUpdateIndex = this::onUpdateIndex,
        )

    private fun onUpdateIndex(newIndex: Int) {
        index = newIndex
    }
}
