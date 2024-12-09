package ru.zulvit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import ru.zulvit.databinding.FragmentSignInBinding;
import ru.zulvit.models.User;

public class SignInFragment extends Fragment {
    private FragmentSignInBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);

        User user = null;
        if (getArguments() != null) {
            try {
                user = SignInFragmentArgs.fromBundle(getArguments()).getUser();
            } catch (IllegalArgumentException e) {
            }
        }

        User finalUser = user;
        binding.buttonSignIn.setOnClickListener(v -> {
            String email = binding.editTextEmail.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();

            if (finalUser != null && email.equals(finalUser.getEmail()) && password.equals(finalUser.getPassword())) {
                Toast.makeText(getContext(), "Вход успешен", Toast.LENGTH_SHORT).show();
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_signInFragment_to_homeFragment);
            } else {
                Toast.makeText(getContext(), "Неправильные учетные данные", Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonToSignUp.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_signInFragment_to_signUpFragment);
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
