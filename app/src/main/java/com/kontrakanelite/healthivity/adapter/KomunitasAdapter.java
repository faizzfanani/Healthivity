package com.kontrakanelite.healthivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kontrakanelite.healthivity.DetailActivity;
import com.kontrakanelite.healthivity.KategoriActivity;
import com.kontrakanelite.healthivity.Komunitas;
import com.kontrakanelite.healthivity.R;
import com.kontrakanelite.healthivity.model.PopularModel;

import java.util.ArrayList;

public class KomunitasAdapter extends RecyclerView.Adapter<KomunitasAdapter.ViewHolder> {
    Context context;
    private ArrayList<Komunitas> komunitasModels ;

    public KomunitasAdapter(Context context, ArrayList<Komunitas> komunitasModels) {
        this.context = context;
        this.komunitasModels = komunitasModels;
    }

    @NonNull
    @Override
    public KomunitasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.list_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KomunitasAdapter.ViewHolder holder, final int position) {
        holder.tvNamaPopular.setText(komunitasModels.get(position).getNamaKomunitas());
        //holder.tvJumlahMember.setText(komunitasModels.get(position).getJumlahMember());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", komunitasModels.get(position).getIdKomunitas());
                intent.putExtra("nama", komunitasModels.get(position).getNamaKomunitas());
                intent.putExtra("jadwal", komunitasModels.get(position).getJadwalKomunitas());
                intent.putExtra("latitude", komunitasModels.get(position).getLatitude());
                intent.putExtra("longitude", komunitasModels.get(position).getLongitude());
                context.startActivity(intent );
            }
        });
    }

    @Override
    public int getItemCount() {
        return komunitasModels.size();
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
