package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //런타임 권한 요청
        //RuntimePermission.askPermission(this);

        //Toolbar를 앱바로 설정
        setSupportActionBar(binding.toolbar);

        //탐색 그래프를 이용해서 앱바 구성(제목 및 백버튼) 설정
        //  1) Toolbar의 제목은 대상의 label 속성을 이용해서 표시됨
        //  2) 백버튼(<-)이 표시되며, 클릭시 이전 대상으로 이동
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host);
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        //대상 변경 이벤트 처리
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                //이동한 대상에 따른 처리
                if (navDestination.getId() == R.id.dest_list) {
                    Log.i(TAG, "List 대상으로 변경");
                } else if(navDestination.getId() == R.id.dest_login) {
                    Log.i(TAG, "Login 대상으로 변경");
                }
            }
        });
    }
}