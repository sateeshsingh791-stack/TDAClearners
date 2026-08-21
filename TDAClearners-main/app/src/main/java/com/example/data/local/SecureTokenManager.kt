package com.example.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * Secure Token Storage Manager for Android using EncryptedSharedPreferences backed by Android Keystore.
 * CRITICAL SECURITY REQUIREMENT: Tokens are NEVER saved in plain SharedPreferences.
 */
object SecureTokenManager {
    private const val PREF_FILENAME = "secure_user_credentials"
    private const val KEY_JWT_TOKEN = "jwt_access_token"
    private const val KEY_USER_EMAIL = "user_email"

    private fun getEncryptedPrefs(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            PREF_FILENAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SKEY_KEY_GEN,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun saveAuthToken(context: Context, token: String, email: String? = null) {
        getEncryptedPrefs(context).edit().apply {
            putString(KEY_JWT_TOKEN, token)
            email?.let { putString(KEY_USER_EMAIL, it) }
            apply()
        }
    }

    fun getAuthToken(context: Context): String? {
        return getEncryptedPrefs(context).getString(KEY_JWT_TOKEN, null)
    }

    fun clearAuthToken(context: Context) {
        getEncryptedPrefs(context).edit().apply {
            remove(KEY_JWT_TOKEN)
            remove(KEY_USER_EMAIL)
            apply()
        }
    }
}
