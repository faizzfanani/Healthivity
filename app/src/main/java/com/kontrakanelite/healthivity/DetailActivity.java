package com.kontrakanelite.healthivity;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kontrakanelite.healthivity.fragment.BottomSheetJoin;

public class DetailActivity extends AppCompatActivity {
    LinearLayout layoutBottomJoin;
    Button btnJoin; TextView namaKomunitas, jadwalKomunitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        btnJoin = (Button) findViewById(R.id.btnJoinDetail);
        layoutBottomJoin = findViewById(R.id.bottom_sheet_join);
        namaKomunitas = findViewById(R.id.tvNamaDetail);
        jadwalKomunitas = findViewById(R.id.tvDeskripsi);
        namaKomunitas.setText(intent.getStringExtra("nama"));
        float latitude = intent.getFloatExtra("latitude",0);
        float longitude = intent.getFloatExtra("longitude",0);
        //latitude sama longitude ini diubah jadi address biasa, sama dikasih gmapsnya kalo bisa
        jadwalKomunitas.setText("Jadwal kumpul: \n"+intent.getStringExtra("jadwal")+"\n\nBertempat di: \nlatitude "+latitude+"\nlongitude "+longitude);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetJoin bottomSheetJoin = new BottomSheetJoin();
                bottomSheetJoin.show(getSupportFragmentManager(),"joinBottomSheet");
            }
        });
    }
}
