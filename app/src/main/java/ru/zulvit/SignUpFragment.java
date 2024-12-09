package ru.zulvit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import ru.zulvit.databinding.FragmentSignUpBinding;
import ru.zulvit.models.User;

public class SignUpFragment extends Fragment {
    private FragmentSignUpBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);

        binding.buttonSignUp.setOnClickListener(v -> {
            String username = binding.editTextUsername.getText().toString().trim();
            String email = binding.editTextEmail.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();

            User user = new User(username, email, password);
            NavController navController = Navigation.findNavController(v);
            navController.navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment(user));
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
