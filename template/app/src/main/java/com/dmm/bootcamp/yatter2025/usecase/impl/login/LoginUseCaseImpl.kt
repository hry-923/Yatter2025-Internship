package com.dmm.bootcamp.yatter2025.usecase.impl.login

import com.dmm.bootcamp.yatter2025.domain.model.Password
import com.dmm.bootcamp.yatter2025.domain.model.Username
import com.dmm.bootcamp.yatter2025.domain.service.LoginService
import com.dmm.bootcamp.yatter2025.infra.pref.LoginUserPreferences
import com.dmm.bootcamp.yatter2025.usecase.login.LoginUseCase
import com.dmm.bootcamp.yatter2025.usecase.login.LoginUseCaseResult

internal class LoginUseCaseImpl(
  private val loginService: LoginService,
  private val loginUserPreferences: LoginUserPreferences,
) : LoginUseCase {
  override suspend fun execute(
    username: Username,
    password: Password,
  ): LoginUseCaseResult {
    try {
      if (username.value.isBlank()) return LoginUseCaseResult.Failure.EmptyUsername
      if (password.value.isBlank()) return LoginUseCaseResult.Failure.EmptyPassword

      if (!password.validate()) return LoginUseCaseResult.Failure.InvalidPassword
      loginService.execute(username, password)
      loginUserPreferences.putUsername(username.value)
      return LoginUseCaseResult.Success
    } catch (e: Exception) {
      return LoginUseCaseResult.Failure.OtherError(e)
    }
  }
}
