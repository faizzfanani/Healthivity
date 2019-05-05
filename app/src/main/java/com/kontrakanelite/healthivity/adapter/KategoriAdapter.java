package com.kontrakanelite.healthivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kontrakanelite.healthivity.KategoriActivity;
import com.kontrakanelite.healthivity.R;
import com.kontrakanelite.healthivity.model.KategoriModel;

import java.util.ArrayList;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder> {

    private ArrayList<KategoriModel> kategoriModels;
    Context context;


    public KategoriAdapter(Context context, ArrayList<KategoriModel> kategoriModels) {
        this.kategoriModels = kategoriModels;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.list_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvKategori.setText(kategoriModels.get(position).getNamaKategori());
        Glide.with(context)
                .load(kategoriModels.get(position).getGambarKategori())
                .into(holder.ivKategori);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KategoriActivity.class);
                intent.putExtra("Kategori", kategoriModels.get(position).getNamaKategori());
                context.startActivity(intent );
            }
        });

    }

    @Override
    public int getItemCount() {
        return kategoriModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivKategori;
        TextView tvKategori;
        public ViewHolder(View itemView) {
            super(itemView);
            ivKategori =(ImageView) itemView.findViewById(R.id.ivKategoriHome);
            tvKategori = (TextView) itemView.findViewById(R.id.tvKategoriHome);

        }
    }
}
