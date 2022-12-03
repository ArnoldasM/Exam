package lt.makseckas.exam.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.Executor;

import lt.makseckas.exam.R;
import lt.makseckas.exam.databinding.FragmentLoginBinding;

public class FragmentLogin extends Fragment {

    private FragmentLoginBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Executor executor = ContextCompat.getMainExecutor(requireContext());
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Snackbar.make(binding.getRoot(), "Authentication error: " + errString, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                NavHostFragment.findNavController(FragmentLogin.this)
                        .navigate(R.id.FragmentAdminPanel);
                Snackbar.make(binding.getRoot(), "Authentication succeeded!", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Snackbar.make(binding.getRoot(), "Authentication failed", Snackbar.LENGTH_SHORT).show();
            }
        });
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle(getString(R.string.biometric_login))
                .setSubtitle(getString(R.string.log_in_using_your_biometric_credential))
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_WEAK | BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                .build();

        biometricPrompt.authenticate(promptInfo);

        Button buttonBack = binding.buttonBack;
        buttonBack.setOnClickListener(view1 ->
                NavHostFragment.findNavController(FragmentLogin.this)
                        .navigate(R.id.MainFragment)
        );

        Button buttonLogin = binding.buttonLogin;
        buttonLogin.setOnClickListener(view1 ->
                biometricPrompt.authenticate(promptInfo)
        );

    }

}