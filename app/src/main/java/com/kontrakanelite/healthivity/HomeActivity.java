package com.kontrakanelite.healthivity;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.kontrakanelite.healthivity.adapter.KategoriAdapter;
import com.kontrakanelite.healthivity.adapter.PopularAdapter;
import com.kontrakanelite.healthivity.model.KategoriModel;
import com.kontrakanelite.healthivity.model.PopularModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rvKategori;
    private ImageView ivSetting;
    List<KategoriModel> kategoriModels = new ArrayList<>();
    List<PopularModel> popularModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rvKategori = (RecyclerView) findViewById(R.id.rvKategoriHome);


        ivSetting = (ImageView) findViewById(R.id.ivSettingHome);
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        kategoriModels.add(new KategoriModel("Swim", R.drawable.kat_swim));
        kategoriModels.add(new KategoriModel("Gym", R.drawable.kat_gym));
        kategoriModels.add(new KategoriModel("Yoga", R.drawable.kat_yoga));
        kategoriModels.add(new KategoriModel("Dance", R.drawable.kat_dance));
        kategoriModels.add(new KategoriModel("Bicycling", R.drawable.kat_bicycling));
        kategoriModels.add(new KategoriModel("Martial Arts", R.drawable.kat_material_arts1));

        popularModels.add(new PopularModel("Karate Malang", "80 members"));
        popularModels.add(new PopularModel("Karate Malang", "80 members"));
        popularModels.add(new PopularModel("Karate Malang", "80 members"));
        popularModels.add(new PopularModel("Karate Malang", "80 members"));
        popularModels.add(new PopularModel("Karate Malang", "80 members"));
        popularModels.add(new PopularModel("Karate Malang", "80 members"));


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvKategoriHome);
        KategoriAdapter kategoriAdapter = new KategoriAdapter(getApplicationContext(), (ArrayList<KategoriModel>) kategoriModels);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(kategoriAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.rvListPopular);
        PopularAdapter popularAdapter = new PopularAdapter(getApplicationContext(), (ArrayList<PopularModel>) popularModels);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(popularAdapter);
    }
}
