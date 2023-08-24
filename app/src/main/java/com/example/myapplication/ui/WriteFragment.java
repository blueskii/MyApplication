package com.example.myapplication.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentWriteBinding;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.dto.WriteResult;
import com.example.myapplication.service.BoardService;
import com.example.myapplication.service.RetrofitSingleton;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteFragment extends Fragment {
    private static final String TAG = "WriteFragment";
    private FragmentWriteBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentWriteBinding.inflate(inflater, container, false);
        //버튼 초기화
        initBtnImageSelect();
        initBtnWrite();
        return binding.getRoot();
    }

    private void initBtnImageSelect() {
        ActivityResultLauncher<PickVisualMediaRequest> activityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.PickVisualMedia(),
                        uri -> {
                            if (uri != null) {
                                binding.battach.setImageURI(uri);
                                binding.battach.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            }
                        }
                );

        binding.btnImageSelect.setOnClickListener(v -> {
            PickVisualMediaRequest request = new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build();
            activityResultLauncher.launch(request);
        });
    }

    private void initBtnWrite() {
        binding.btnWrite.setOnClickListener(v -> {
            MultipartBody.Part btitlePart = MultipartBody.Part.createFormData("btitle", binding.btitle.getText().toString());
            MultipartBody.Part bcontentPart = MultipartBody.Part.createFormData("bcontent", binding.bcontent.getText().toString());
            MultipartBody.Part midPart = MultipartBody.Part.createFormData("mid", AppKeyValueStore.getValue(getContext(), "mid"));
            MultipartBody.Part apiAccessKeyPart = MultipartBody.Part.createFormData("apiAccessKey", AppKeyValueStore.getValue(getContext(), "apiAccessKey"));

            Bitmap bitmap = ((BitmapDrawable) binding.battach.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), bytes);
            //filename이 null이 되면 서버쪽에서 MultipartFile 객체로 생성할 때 문제가 됨
            MultipartBody.Part battachPart = MultipartBody.Part.createFormData("battach", "", requestBody);

            List<MultipartBody.Part> partList = new ArrayList<>();
            partList.add(btitlePart);
            partList.add(bcontentPart);
            partList.add(midPart);
            partList.add(apiAccessKeyPart);
            partList.add(battachPart);

            BoardService boardService = RetrofitSingleton.getBoardService();
            Call<WriteResult> call = boardService.writeBoard(partList);
            call.enqueue(new Callback<WriteResult>() {
                @Override
                public void onResponse(Call<WriteResult> call, Response<WriteResult> response) {
                    WriteResult writeResult = response.body();
                    Log.i(TAG, writeResult.toString());
                    //이전 대상으로 이동
                    NavController navController = NavHostFragment.findNavController(WriteFragment.this);
                    navController.popBackStack();
                }

                @Override
                public void onFailure(Call<WriteResult> call, Throwable t) {

                }
            });
        });
    }
}