package com.example.myapplication.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.dto.LoginResult;
import com.example.myapplication.service.MemberService;
import com.example.myapplication.service.RetrofitSingleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";
    private FragmentLoginBinding binding;

    //뷰 생성
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        //버튼 초기화
        initBtnLogin();
        initBtnCancel();
        return binding.getRoot();
    }

    private void initBtnLogin() {
        binding.btnLogin.setOnClickListener(v -> {
            String mid = binding.mid.getText().toString();
            String mpassword = binding.mpassword.getText().toString();

            MemberService memberService = RetrofitSingleton.getMemberService();
            Call<LoginResult> call = memberService.login(mid, mpassword);
            call.enqueue(new Callback<LoginResult>() {
                @Override
                public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                    LoginResult loginResult = response.body();
                    Log.i(TAG, loginResult.toString());

                    //로그인 성공되었을 때
                    if(loginResult.getResult().equals("success")) {
                        //로그인 정보 저장
                        AppKeyValueStore.put(getContext(), "mid", loginResult.getMid());
                        AppKeyValueStore.put(getContext(), "apiAccessKey", loginResult.getApiAccessKey());

                        //이전 대상으로 이동
                        NavController navController = NavHostFragment.findNavController(LoginFragment.this);
                        navController.popBackStack();
                    }
                    //로그인 실패되었을 때
                    else {
                        //다이얼로그 띄움
                        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                                .setTitle("로그인 실패")
                                .setMessage(loginResult.getResult())
                                .setPositiveButton("확인", null)
                                .create();
                        alertDialog.show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResult> call, Throwable t) {
                    Log.i(TAG, t.toString());
                }
            });
        });
    }

    private void initBtnCancel() {
        binding.btnCancel.setOnClickListener(v -> {
            //현재 대상에 정의되어 있는 선을 이용해서 이동
            NavController navController = NavHostFragment.findNavController(this);
            navController.popBackStack();
        });
    }
}