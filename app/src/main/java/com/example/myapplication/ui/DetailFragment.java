package com.example.myapplication.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDetailBinding;
import com.example.myapplication.dto.Board;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.service.BoardService;
import com.example.myapplication.service.NetworkInfo;

public class DetailFragment extends Fragment {
    private static final String TAG = "DetailFragment";
    private FragmentDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);

        //메뉴 초기화
        initMenu();

        //이전 대상에서 전달한 데이터 얻기
        Bundle bundle = getArguments();
        Board board = (Board) bundle.getSerializable("board");
        Log.i(TAG, board.toString());

        BoardService.loadImage(container.getContext(), board.getBno(), binding.battach);
        binding.btitle.setText(board.getBtitle());
        binding.mid.setText(board.getMid());
        binding.bdate.setText(board.getBdate());
        binding.bcontent.setText(board.getBcontent());

        initWebView();

        return binding.getRoot();
    }

    private void initMenu() {
        MenuProvider menuProvider = new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.detail_fragment, menu);
                if(AppKeyValueStore.getValue(getContext(),"mid") == null) {
                    //로그인 정보가 없을 때
                    menu.findItem(R.id.item_update).setVisible(false);

                } else {
                    //로그인 정보가 있을 때
                    menu.findItem(R.id.item_update).setVisible(true);

                }
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                NavController navController = NavHostFragment.findNavController(DetailFragment.this);
                if(menuItem.getItemId() == R.id.item_update) {
                    //현재 대상에서 출발하는 선을 이용해서 이동
                    navController.navigate(R.id.action_dest_detail_to_dest_update);
                    return true;
                }
                return false;
            }
        };

        getActivity().addMenuProvider(menuProvider, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    private void initWebView() {
        binding.webView.loadUrl("https://www.nomadicmatt.com/travel-blogs/croatia-itinerary/");
    }
}