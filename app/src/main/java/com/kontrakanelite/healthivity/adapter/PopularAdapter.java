package com.kontrakanelite.healthivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kontrakanelite.healthivity.DetailActivity;
import com.kontrakanelite.healthivity.KategoriActivity;
import com.kontrakanelite.healthivity.Komunitas;
import com.kontrakanelite.healthivity.R;
import com.kontrakanelite.healthivity.model.PopularModel;

import java.util.ArrayList;
import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    Context context;
    private ArrayList<PopularModel> popularModels ;

    List<Komunitas> komunitas = new ArrayList<>();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    DatabaseReference databaseRef;
    // Create a storage reference from our app
    StorageReference storageRef = storage.getReference();
    public PopularAdapter(Context context, ArrayList<PopularModel> popularModels) {
        this.context = context;
        this.popularModels = popularModels;
    }

    @NonNull
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.list_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, final int position) {
        holder.tvNamaPopular.setText(popularModels.get(position).getNamaPopular());
        holder.tvJumlahMember.setText(popularModels.get(position).getJumlahMember());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Nama", popularModels.get(position).getNamaPopular());
                context.startActivity(intent );
            }
        });
    }

    private void getAllKomunitas(){
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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

    @Override
    public int getItemCount() {
        return popularModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaPopular;
        TextView tvJumlahMember;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNamaPopular = (TextView) itemView.findViewById(R.id.tvNamaPopular);
            tvJumlahMember = (TextView) itemView.findViewById(R.id.tvJumlahMemberPopular);
        }
    }
}
