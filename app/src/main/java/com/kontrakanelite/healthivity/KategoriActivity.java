package com.kontrakanelite.healthivity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.kontrakanelite.healthivity.adapter.KomunitasAdapter;
import com.kontrakanelite.healthivity.adapter.PopularAdapter;
import com.kontrakanelite.healthivity.model.PopularModel;

import java.util.ArrayList;
import java.util.List;

public class KategoriActivity extends AppCompatActivity {

    List<Komunitas> komunitas = new ArrayList<>();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    DatabaseReference databaseRef;
    String kategori;
    TextView nama, usia, jumlahKom;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        Intent intent = getIntent();
        kategori = intent.getStringExtra("Kategori").toLowerCase();
        databaseRef = FirebaseDatabase.getInstance().getReference("komunitas");
        getKomunitasByKategori();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.rvListKategori);
        final KomunitasAdapter komunitasAdapter = new KomunitasAdapter(getApplicationContext(), (ArrayList<Komunitas>) komunitas);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(komunitasAdapter);
        jumlahKom = findViewById(R.id.tvJumlahKomunitas);
        jumlahKom.setText(komunitas.size()+" ");


        searchView = findViewById(R.id.searchVw);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search community");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                komunitasAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                komunitasAdapter.getFilter().filter(query);
                return false;
            }
        });
    }



    private void getKomunitasByKategori(){
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                komunitas.clear();
                for (DataSnapshot komunSnapshot : dataSnapshot.getChildren()){
                    Komunitas komun = komunSnapshot.getValue(Komunitas.class);
                    if(komun.getKategori().equals(kategori)){
                        komunitas.add(komun);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
