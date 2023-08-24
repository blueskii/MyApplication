package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.dto.Board;

import java.util.ArrayList;
import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardViewHolder>  {
    //데이터 컬렉션 선언
    private List<Board> list = new ArrayList<>();

    //ViewHolder 생성
    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.board_item, parent, false);
        BoardViewHolder boardViewHolder = new BoardViewHolder(itemView, onItemClickListener);
        return boardViewHolder;
    }

    //ViewHolder에 데이터 셋팅
    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        holder.setData(list.get(position));
    }

    //전체 아이템 수
    @Override
    public int getItemCount() {
        return list.size();
    }

    //데이터 컬렉션 셋팅
    public void setList(List<Board> list) {
        this.list = list;
    }

    public Board getItem(int position) {
        return list.get(position);
    }

    //아이템 클릭 리스너 선언 ---------------------------------------------------------
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
