package com.example.simple_smart_city.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.simple_smart_city.API;
import com.example.simple_smart_city.Const;
import com.example.simple_smart_city.R;
import com.example.simple_smart_city.bean_p.BannerBean;
import com.example.simple_smart_city.bean_p.ServerListBean;
import com.example.simple_smart_city.databinding.ActivityLoginBinding;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import java.io.IOException;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        API req = Const.getReq();
        binding.serverList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        new Thread(() -> {
            try {
                List<ServerListBean.RowsDTO> serverListBean = req.serverList().execute().body().getRows();
                runOnUiThread(() -> binding.serverList.setAdapter(new RecyclerView.Adapter<MyHolder>() {
                    @NonNull
                    @Override
                    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        return new MyHolder(LayoutInflater.from(LoginActivity.this).inflate(R.layout.server_list, parent, false));
                    }

                    @Override
                    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
                        holder.text.setText(serverListBean.get(position).getServiceName());
                        Glide.with(LoginActivity.this).load(Const.getIP() + serverListBean.get(position).getImgUrl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(holder.img);
                    }

                    @Override
                    public int getItemCount() {
                        return serverListBean.size();
                    }
                }));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // banner
        new Thread(() -> {
            List<BannerBean.RowsDTO> rows = null;
            try {
                rows = req.banner().execute().body().getRows();
            } catch (IOException e) {
                e.printStackTrace();
            }
            final List<BannerBean.RowsDTO> finalRows = rows;
            runOnUiThread(() -> {
                binding.banner.setAdapter(new BannerImageAdapter<BannerBean.RowsDTO>(finalRows) {
                    @Override
                    public void onBindView(BannerImageHolder holder, BannerBean.RowsDTO data, int position, int size) {
                        Glide.with(holder.itemView)
                                .load(Const.getIP() + data.getAdvImg())
                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                .into(holder.imageView);
                    }
                }).addBannerLifecycleObserver(this);
            });
        }).start();

    }

    private static class MyHolder extends RecyclerView.ViewHolder{
        TextView text;
        ImageView img;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            img = itemView.findViewById(R.id.img);
        }
    }
}