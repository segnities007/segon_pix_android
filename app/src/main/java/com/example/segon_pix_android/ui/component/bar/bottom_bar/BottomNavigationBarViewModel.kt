package com.example.segon_pix_android.ui.component.bar.bottom_bar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class BottomNavigationBarAction(
    val onUpdateIndex: (newIndex: Int) -> Unit,
)

class BottomNavigationBarViewModel: ViewModel(){

    var index by mutableIntStateOf(0)
        private set

    fun onGetBottomNavigationBarAction(): BottomNavigationBarAction{
        return BottomNavigationBarAction(
            onUpdateIndex = this::onUpdateIndex,
        )
    }

    private fun onUpdateIndex(newIndex: Int){
        index = newIndex
    }

}

