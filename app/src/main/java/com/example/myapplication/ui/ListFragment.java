package com.example.myapplication.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentListBinding;
import com.example.myapplication.dto.Board;
import com.example.myapplication.datastore.AppKeyValueStore;
import com.example.myapplication.service.BoardService;
import com.example.myapplication.service.RetrofitSingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFragment extends Fragment {
    private static final String TAG = "ListFragment";
    private FragmentListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView() 실행");
        binding = FragmentListBinding.inflate(inflater, container, false);
        //메뉴 초기화
        initMenu();
        //목록 초기화
        initRecyclerView();
        return binding.getRoot();
    }

    private void initMenu() {
        MenuProvider menuProvider = new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.list_fragment, menu);
                if(AppKeyValueStore.getValue(getContext(),"mid") == null) {
                    Log.i(TAG, "로그인 정보 없음");
                    //로그인 정보가 없을 때
                    menu.findItem(R.id.item_login).setVisible(true);
                    menu.findItem(R.id.item_logout).setVisible(false);
                    menu.findItem(R.id.item_write).setVisible(false);
                } else {
                    Log.i(TAG, "로그인 정보 있음");
                    //로그인 정보가 있을 때
                    menu.findItem(R.id.item_login).setVisible(false);
                    menu.findItem(R.id.item_logout).setVisible(true);
                    menu.findItem(R.id.item_write).setVisible(true);
                }
                menu.findItem(R.id.item_settings).setVisible(true);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                NavController navController = NavHostFragment.findNavController(ListFragment.this);
                if(menuItem.getItemId() == R.id.item_login) {
                    //현재 대상이 무엇이던 상관없이 dest_login으로 이동
                    navController.navigate(R.id.dest_login);
                    return true;
                } else if (menuItem.getItemId() == R.id.item_logout) {
                    Log.i(TAG, "itemLogout 클릭됨");

                    //로그인 정보 삭제
                    AppKeyValueStore.remove(getContext(), "mid");
                    AppKeyValueStore.remove(getContext(), "apiAccessKey");

                    //현재 메뉴를 무효화하고 onCreateMenu() 재호출
                    getActivity().invalidateMenu();

                    return true;
                } else if(menuItem.getItemId() == R.id.item_settings) {
                    //현재 대상에서 출발하는 선을 이용해서 이동
                    navController.navigate(R.id.action_dest_list_to_dest_settings);
                    return true;
                } else if(menuItem.getItemId() == R.id.item_write) {
                    //현재 대상에서 출발하는 선을 이용해서 이동
                    navController.navigate(R.id.action_dest_list_to_dest_write);
                }
                return false;
            }
        };

        getActivity().addMenuProvider(menuProvider, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }

    private void initRecyclerView() {
        //레이아웃 관리자 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        BoardAdapter adapter = new BoardAdapter();

        //목록 요청해서 가져오기
        BoardService boardService = RetrofitSingleton.getBoardService();
        Call<List<Board>> call = boardService.getBoardList();
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                List<Board> boardList = response.body();
                //어댑터 설정
                adapter.setList(boardList);
                binding.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
            }
        });

        //아이템 클릭 이벤트 처리
        adapter.setOnItemClickListener(new BoardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                TextView btitle = (TextView) itemView.findViewById(R.id.btitle);
                Log.i(TAG, btitle.getText().toString());
                Board board = adapter.getItem(position);
                Log.i(TAG, board.toString());

                NavController navController = NavHostFragment.findNavController(ListFragment.this);
                Bundle bundle = new Bundle();
                bundle.putSerializable("board", board);
                navController.navigate(R.id.action_dest_list_to_dest_detail, bundle);
            }
        });
    }
}