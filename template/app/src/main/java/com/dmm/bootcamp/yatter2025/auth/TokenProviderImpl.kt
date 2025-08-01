package com.dmm.bootcamp.yatter2025.auth

import android.accounts.AuthenticatorException
import com.dmm.bootcamp.yatter2025.infra.pref.TokenPreferences

class TokenProviderImpl(private val tokenPreferences: TokenPreferences) : TokenProvider {
  override suspend fun provide(): String {
    return tokenPreferences.getAccessToken()?.let { "username $it" } ?: throw AuthenticatorException()
  }
}
