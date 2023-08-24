package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentUpdateBinding;

public class UpdateFragment extends Fragment {
    private FragmentUpdateBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentUpdateBinding.inflate(inflater, container, false);
        //버튼 초기화
        initBtnUpdate();
        return binding.getRoot();
    }

    private void initBtnUpdate() {
        binding.btnUpdate.setOnClickListener(v -> {
            //현재 대상에 정의되어 있는 선을 이용해서 이동
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_dest_update_to_dest_list);
        });
    }
}