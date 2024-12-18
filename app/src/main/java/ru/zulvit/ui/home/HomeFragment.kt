package ru.zulvit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ru.zulvit.data.repository.CharacterRepository
import ru.zulvit.data.repository.Resource
import ru.zulvit.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var characterAdapter: CharacterAdapter

    private var currentPage = 1  // Текущая страница

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Инициализация ViewModel с фабрикой
        val characterRepository = CharacterRepository()  // Получаем репозиторий
        homeViewModel = ViewModelProvider(this, HomeViewModelFactory(characterRepository))
            .get(HomeViewModel::class.java)

        // Настроим RecyclerView
        characterAdapter = CharacterAdapter { character ->
            Toast.makeText(requireContext(), "Selected: ${character.name}", Toast.LENGTH_SHORT)
                .show()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = characterAdapter

        // Наблюдаем за изменениями в данных
        homeViewModel.characters.observe(viewLifecycleOwner, { resource ->
            when (resource) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    resource.data?.let { characters ->
                        if (characters.isNotEmpty()) {
                            characterAdapter.submitList(characters)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "No characters found.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Error: ${resource.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        // Установим слушатели на кнопки
        binding.prevPageButton.setOnClickListener {
            if (currentPage > 1) {
                currentPage--
                loadPage()
            }
        }

        binding.nextPageButton.setOnClickListener {
            currentPage++
            loadPage()
        }

        // Загрузка первой страницы
        loadPage()

        return binding.root
    }

    private fun loadPage() {
        // Обновляем номер страницы
        binding.pageNumber.text = "Page: $currentPage"
        // Загружаем данные
        homeViewModel.getCharacters(page = currentPage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
