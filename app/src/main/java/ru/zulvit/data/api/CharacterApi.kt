package ru.zulvit.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.zulvit.data.models.GOTCharacter

interface CharacterApi {
    @GET("characters")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 50
    ): List<GOTCharacter>
}
