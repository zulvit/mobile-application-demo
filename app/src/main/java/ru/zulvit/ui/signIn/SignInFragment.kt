package ru.zulvit.ui.signIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import ru.zulvit.R
import ru.zulvit.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.buttonSignIn.setOnClickListener {
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()

            // Здесь можно добавить проверку на корректность email и password
            // Для примера просто проверим, что они не пустые
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Предположим, что у нас есть логика авторизации (например, через API)
                // Здесь просто показываем успешный вход
                Toast.makeText(context, "Вход успешен", Toast.LENGTH_SHORT).show()

                // Переход на HomeFragment
                val navController: NavController = Navigation.findNavController(it)
                navController.navigate(R.id.action_signInFragment_to_homeFragment)
            } else {
                Toast.makeText(context, "Неправильные учетные данные", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonToSignUp.setOnClickListener {
            val navController: NavController = Navigation.findNavController(it)
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)  // Навигация на SignUp
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
