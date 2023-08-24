package com.example.myapplication.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.dto.Board;
import com.example.myapplication.service.BoardService;
import com.example.myapplication.service.NetworkInfo;

public class BoardViewHolder extends RecyclerView.ViewHolder {
    private ImageView battach;
    private TextView btitle;
    private TextView mid;
    private TextView bdate;
    private TextView bcontent;

    public BoardViewHolder(@NonNull View itemView, BoardAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        //아이템 UI 얻기
        battach = (ImageView) itemView.findViewById(R.id.battach);
        btitle = (TextView) itemView.findViewById(R.id.btitle);
        mid = (TextView)itemView.findViewById(R.id.mid);
        bdate = (TextView)itemView.findViewById(R.id.bdate);
        bcontent = (TextView)itemView.findViewById(R.id.bcontent);

        //아이템 클릭 이벤트 처리
        itemView.setOnClickListener(v -> {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        });
    }

    //아이템 UI 데이터 세팅
    public void setData(Board board) {
        BoardService.loadImage(battach.getContext(), board.getBno(), battach);
        btitle.setText(board.getBtitle());
        mid.setText(board.getMid());
        bdate.setText(board.getBdate());
        bcontent.setText(board.getBcontent());
    }
}
