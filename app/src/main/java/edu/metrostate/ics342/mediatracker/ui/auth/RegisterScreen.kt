package edu.metrostate.ics342.mediatracker.ui.auth

import android.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.sensitiveContent
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.metrostate.ics342.mediatracker.R
import edu.metrostate.ics342.mediatracker.theme.OnPrimaryContainer
import edu.metrostate.ics342.mediatracker.theme.OnSurface
import edu.metrostate.ics342.mediatracker.theme.PrimaryContainer


@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: RegisterViewModel = viewModel()
) {

    val displayName     by viewModel.displayName.collectAsState()
    val username        by viewModel.username.collectAsState()
    val email           by viewModel.email.collectAsState()
    val password        by viewModel.password.collectAsState()
    val confirmPassword by viewModel.confirmPassword.collectAsState()
    val registerState   by viewModel.registerState.collectAsState()

    LaunchedEffect(registerState) {
        if registerState is AuthViewModel.AuthUiState.Success) {
            viewModel.resetRegisterState()
            onLoginSuccess()
        }
    }

    val isLoading = registerState is AuthViewModel.AuthUiState.Loading
    val errorMsg = (registerState as? AuthViewModel.AuthUiState.Error)?.msgResId?.let { stringResource(it) }

    /*
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(edu.metrostate.ics342.mediatracker.R.string.register_not_implemented),
            style = MaterialTheme.typography.bodyLarge
        )
    }
    */

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement   = Arrangement.Center,
        horizontalAlignment   = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(R.drawable.smart_display),
            contentDescription = "Application Icon",
            modifier = Modifier
                .size(width = 64.dp, height = 64.dp)
                .background(color = PrimaryContainer, shape = RoundedCornerShape(12.dp))
                .padding(12.dp),
            colorFilter = ColorFilter.tint(OnPrimaryContainer)
        )

        Text("Create Account")
        SecureTextField(
            state = TextFieldState(),
            modifier = Modifier,
            placeholder = {Text("Password")}
        )
        SecureTextField(
            state = TextFieldState(),
            modifier = Modifier,
            placeholder = {Text("Confirm Password")}
        )

        Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.app_name), style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary)

        Spacer(Modifier.height(8.dp))

        Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.app_tagline),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center)

        Spacer(Modifier.height(40.dp))

        // Display Name
        OutlinedTextField(
            value         = displayName,
            onValueChange = viewModel::onDisplayNameChange,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.display_name_label)) },
            singleLine    = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction    = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // Username
        OutlinedTextField(
            value         = username,
            onValueChange = viewModel::onUsernameChange,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.username_label)) },
            singleLine    = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction    = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // Email
        OutlinedTextField(
            value         = email,
            onValueChange = viewModel::onEmailChange,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.email_label)) },
            singleLine    = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction    = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // Password
        OutlinedTextField(
            value         = password,
            onValueChange = viewModel::onPasswordChange,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.password_label)) },
            singleLine    = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction    = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // Confirm Password
        OutlinedTextField(
            value         = confirmPassword,
            onValueChange = viewModel::onConfirmPasswordChange,
            label         = { Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.confirm_password_label)) },
            singleLine    = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction    = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(); viewModel.onRegisterClick() }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMsg != null) {
            Spacer(Modifier.height(8.dp))
            Text(errorMsg, color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall)
        }

        Spacer(Modifier.height(24.dp))

        // Sign Up Button
        Button(
            onClick  = { focusManager.clearFocus(); viewModel.onRegisterClick() },
            enabled  = !isLoading,
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            } else {
                Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.register_button))
            }
        }

        Spacer(Modifier.height(16.dp))

        // Navigate back to Login Screen
        TextButton(onClick = onNavigateToLogin) {
            Text(stringResource(edu.metrostate.ics342.mediatracker.R.string.login_prompt))
        }
    }
}

@Preview (showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen (
        onRegisterSuccess = {},
        onNavigateToLogin = {}
    )
}