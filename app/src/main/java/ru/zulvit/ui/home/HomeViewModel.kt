package ru.zulvit.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.zulvit.data.repository.CharacterRepository
import ru.zulvit.data.models.GOTCharacter
import ru.zulvit.data.repository.Resource

class HomeViewModel(private val characterRepository: CharacterRepository) : ViewModel() {

    private val _characters = MutableLiveData<Resource<List<GOTCharacter>>>()
    val characters: LiveData<Resource<List<GOTCharacter>>> get() = _characters

    fun getCharacters(page: Int) {
        _characters.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val result = characterRepository.getCharacters(page)
                if (result.isNotEmpty()) {
                    _characters.value = Resource.Success(result)
                } else {
                    _characters.value = Resource.Error("No characters found.")
                }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error loading characters: ${e.message}", e)
                _characters.value = Resource.Error("Error loading characters: ${e.message}")
            }
        }
    }
}