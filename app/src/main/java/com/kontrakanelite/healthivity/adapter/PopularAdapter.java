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
import com.kontrakanelite.healthivity.R;
import com.kontrakanelite.healthivity.model.PopularModel;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    Context context;
    private ArrayList<PopularModel> popularModels ;

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
                intent.putExtra("Detail", popularModels.get(position).getNamaPopular());
                context.startActivity(intent );
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
