package ru.zulvit.data.repository

import android.util.Log
import ru.zulvit.data.models.GOTCharacter
import retrofit2.HttpException
import java.io.IOException

class CharacterRepository {

    private val api = RetrofitClient.characterApi

    suspend fun getCharacters(page: Int): List<GOTCharacter> {
        return try {
            val response = api.getCharacters(page)
            Log.d("API Response", response.toString())

            if (response.isEmpty()) {
                Log.e("CharacterRepository", "Received empty response")
            }
            response
        } catch (e: HttpException) {
            Log.e("CharacterRepository", "HTTP error: ${e.message}")
            emptyList()
        } catch (e: IOException) {
            Log.e("CharacterRepository", "Network error: ${e.message}")
            emptyList()
        } catch (e: Exception) {
            Log.e("CharacterRepository", "Unexpected error: ${e.message}")
            emptyList()
        }
    }
}