package lt.makseckas.exam.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import lt.makseckas.exam.R;
import lt.makseckas.exam.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linearOne = binding.layoutOne;
        linearOne.setOnClickListener(view1 ->
                NavHostFragment.findNavController(MainFragment.this).navigate(R.id.action_MainFragment_to_FragmentOne)
        );
        LinearLayout linearTwo = binding.layoutTwo;
        linearTwo.setOnClickListener(view1 ->
                NavHostFragment.findNavController(MainFragment.this).navigate(R.id.action_MainFragment_to_FragmentTwo)
        );
    }

}