package ru.zulvit.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GOTCharacter(
    val id: Int,
    val name: String?,
    val culture: String?,
    val born: String?,
    val titles: List<String>,
    val aliases: List<String>,
    @SerialName("playedBy") val playedBy: List<String>
)
