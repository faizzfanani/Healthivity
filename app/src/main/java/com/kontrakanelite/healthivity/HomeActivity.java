package com.kontrakanelite.healthivity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rvKategori;
    private ImageView ivSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rvKategori = (RecyclerView) findViewById(R.id.rvKategoriHome);
        rvKategori.setLayoutManager(new GridLayoutManager(this,3));

        ivSetting = (ImageView) findViewById(R.id.ivSettingHome);
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
