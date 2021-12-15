package com.example.simple_smart_city;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.simple_smart_city.databinding.ActivityMainBinding;
import com.example.simple_smart_city.view.HomeFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content, homeFragment).commitAllowingStateLoss();
    }
}