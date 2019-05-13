package com.kontrakanelite.healthivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kontrakanelite.healthivity.adapter.KategoriAdapter;
import com.kontrakanelite.healthivity.adapter.KomunitasAdapter;
import com.kontrakanelite.healthivity.model.KategoriModel;
import com.kontrakanelite.healthivity.model.Komunitas;
import com.kontrakanelite.healthivity.model.Member;
import com.kontrakanelite.healthivity.model.PopularModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rvKategori;
    private ImageView ivSetting;
    List<KategoriModel> kategoriModels = new ArrayList<>();
    List<PopularModel> popularModels = new ArrayList<>();
    List<Komunitas> komunitas = new ArrayList<>();
    List<Member> member = new ArrayList<>();
    int jumlahJoin, jumlahPending;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    DatabaseReference komunitasRef = FirebaseDatabase.getInstance().getReference("komunitas");
    DatabaseReference memberRef = FirebaseDatabase.getInstance().getReference("member");
    // Create a storage reference from our app
    StorageReference storageRef = storage.getReference();
    String name, idU; int age;
    private SearchView searchView;
    TextView nama, usia, jumlahKom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        name = intent.getStringExtra("nama");
        age = intent.getIntExtra("usia",0);
        idU = intent.getStringExtra("idUser");
        nama = findViewById(R.id.tvNameHome);
        usia = findViewById(R.id.ageText);
        usia.setText(age+" years old");
        nama.setText(name);
        getAllKomunitas();
        rvKategori = findViewById(R.id.rvKategoriHome);
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
        kategoriModels.add(new KategoriModel("Football", R.drawable.kat_bicycling));
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
        /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        RecyclerView recyclerView1 = (RecyclerView) findViewById(R.id.rvListPopular);
        PopularAdapter popularAdapter = new PopularAdapter(getApplicationContext(), (ArrayList<PopularModel>) popularModels);
        recyclerView1.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(popularAdapter);*/
    }

    private void getMembership(){
        memberRef.addListenerForSingleValueEvent(new ValueEventListener(){
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot){
            member.clear();

            for (DataSnapshot memberSnapshot : dataSnapshot.getChildren()){
                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {};
                List mem = memberSnapshot.getValue(t);
                if(memberSnapshot.getKey().equals(idU)){

                }
            }
        }
        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    }

    private void getAllKomunitas(){
        komunitasRef.addListenerForSingleValueEvent(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                komunitas.clear();
                for (DataSnapshot komunSnapshot : dataSnapshot.getChildren()){
                    Komunitas komun = komunSnapshot.getValue(Komunitas.class);
                        komunitas.add(komun);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
