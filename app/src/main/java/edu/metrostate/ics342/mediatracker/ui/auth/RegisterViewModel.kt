package edu.metrostate.ics342.mediatracker.ui.auth

import androidx.compose.runtime.annotation.FrequentlyChangingValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.metrostate.ics342.mediatracker.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel () {

    sealed class RegisterUiState {

        object Idle     : RegisterUiState()

        object Loading  : RegisterUiState()

        object Success  : RegisterUiState()

        data class Error(val msgResId: Int) : RegisterUiState()
    }
    private val _displayName = MutableStateFlow("")

    val displayName: StateFlow<String> = _displayName.asStateFlow()

    private val _username = MutableStateFlow("")

    val username: StateFlow<String> = _username.asStateFlow()

    private val _email = MutableStateFlow("")

    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")

    val password: StateFlow<String> = _password.asStateFlow()

    private val _confirmPassword = MutableStateFlow("")

    val confirmPassword: StateFlow<String> = _confirmPassword.asStateFlow()

    private val _registerState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)

    val registerState: StateFlow<RegisterUiState> = _registerState.asStateFlow()

    fun onDisplayNameChange(value: String)      { _displayName.value = value }
    fun onUsernameChange(value: String)         { _username.value = value }
    fun onEmailChange(value: String)            { _email.value = value }
    fun onPasswordChange(value: String)         { _password.value = value }
    fun onConfirmPasswordChange(value: String)  { _confirmPassword.value = value }

    fun onRegisterClick() {
        viewModelScope.launch {
            _registerState.value = RegisterUiState.Loading
            delay(800)

            val hasEmptyFields = _displayName.value.isBlank() ||
                    _username.value.isBlank() ||
                    _email.value.isBlank() ||
                    _password.value.isBlank() ||
                    _confirmPassword.value.isBlank()

            if (hasEmptyFields) {
                _registerState.value = RegisterUiState.Error(R.string.error_empty_credentials)
                return@launch
            }

            if (_password.value != _confirmPassword.value) {
                _registerState.value = RegisterUiState.Error(R.string.error_password_match)
                return@launch
            }

            _registerState.value = RegisterUiState.Success
        }
    }

    fun resetRegisterState() {
        _registerState.value = RegisterUiState.Idle
    }
}