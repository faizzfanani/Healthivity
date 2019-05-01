package com.kontrakanelite.healthivity;

import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.kontrakanelite.healthivity.fragment.BottomSheetJoin;

public class DetailActivity extends AppCompatActivity {
    LinearLayout layoutBottomJoin;
    Button btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btnJoin = (Button) findViewById(R.id.btnJoinDetail);
        layoutBottomJoin = findViewById(R.id.bottom_sheet_join);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetJoin bottomSheetJoin = new BottomSheetJoin();
                bottomSheetJoin.show(getSupportFragmentManager(),"joinBottomSheet");
            }
        });
    }
}
