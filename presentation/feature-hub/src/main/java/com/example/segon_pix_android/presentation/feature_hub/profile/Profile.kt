package com.example.segon_pix_android.presentation.feature_hub.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.segon_pix_android.component.button.RectangleButton
import com.example.segon_pix_android.component.card.ProfileCard
import com.example.segon_pix_android.component.divider.LabelDivider
import com.example.segon_pix_android.domain.model.User

private const val SPACE = 16
private const val VERTICAL_PADDING = 32
private const val HORIZONTAL_PADDING = 16

@Composable
fun Profile(
    modifier: Modifier = Modifier,
    coreNavController: NavHostController,
) {
    val profileViewModel: ProfileViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        profileViewModel.onIntent(ProfileIntent.Init)
    }

    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(vertical = VERTICAL_PADDING.dp, horizontal = HORIZONTAL_PADDING.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ProfileCard(user = User())
        Spacer(modifier = Modifier.padding(SPACE.dp))
        Setting(coreNavController, profileViewModel::onIntent)
    }
}

@Composable
private fun Setting(
    coreNavController: NavHostController,
    onProfileIntent: (ProfileIntent) -> Unit,
) {
    val buttonModifier = Modifier.fillMaxWidth()

    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LabelDivider(label = "Setting")
        RectangleButton(
            modifier = buttonModifier,
            text = "Sign out",
            onClick = { onProfileIntent(ProfileIntent.SignOut(coreNavController)) },
            enabled = true,
        )
        RectangleButton(
            modifier = buttonModifier,
            text = "Delete account",
            onClick = { onProfileIntent(ProfileIntent.DeleteAccount(coreNavController)) },
            enabled = true,
        )
        RectangleButton(
            modifier = buttonModifier,
            text = "Update Profile",
            onClick = { onProfileIntent(ProfileIntent.UpdateProfile(User())) },
            enabled = true,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ProfilePreview() {
    val nav = rememberNavController()
    Profile(coreNavController = nav)
}
