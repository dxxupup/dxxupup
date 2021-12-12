package com.example.simple_smart_city.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.simple_smart_city.Const;
import com.example.simple_smart_city.bean_p.BannerBean;
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
        new Thread(() -> {
            List<BannerBean.RowsDTO> rows = null;
            try {
                rows = Const.getReq().banner().execute().body().getRows();
            } catch (IOException e) {
                e.printStackTrace();
            }
            final List<BannerBean.RowsDTO> finalRows = rows;
            this.runOnUiThread(() -> {
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
}