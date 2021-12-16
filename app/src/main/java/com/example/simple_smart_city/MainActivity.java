package com.example.simple_smart_city;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.simple_smart_city.databinding.ActivityMainBinding;
import com.example.simple_smart_city.view.HomeFragment;
import com.example.simple_smart_city.view.NewsFrag;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        HomeFragment homeFragment = new HomeFragment();
        NewsFrag newsFrag = new NewsFrag();
        getSupportFragmentManager().beginTransaction().add(R.id.content, homeFragment).commitAllowingStateLoss();
        binding.hb1.setOnClickListener(v -> getSupportFragmentManager().beginTransaction().replace(R.id.content, homeFragment).commitAllowingStateLoss());
        binding.hb4.setOnClickListener(v -> getSupportFragmentManager().beginTransaction().replace(R.id.content, newsFrag).commitAllowingStateLoss());
        binding.hb5.setOnClickListener(v -> getSupportFragmentManager().beginTransaction().replace(R.id.content, newsFrag).commitAllowingStateLoss());
    }
}