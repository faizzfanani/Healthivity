package com.kontrakanelite.healthivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
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
    private ArrayList<Komunitas> komunitasModelsFiltered;

    public KomunitasAdapter(Context context, ArrayList<Komunitas> komunitasModels) {
        this.context = context;
        this.komunitasModels = komunitasModels;
        this.komunitasModelsFiltered = komunitasModels;
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
        holder.tvNamaPopular.setText(komunitasModelsFiltered.get(position).getNamaKomunitas());
        //holder.tvJumlahMember.setText(komunitasModels.get(position).getJumlahMember());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", komunitasModelsFiltered.get(position).getIdKomunitas());
                intent.putExtra("nama", komunitasModelsFiltered.get(position).getNamaKomunitas());
                intent.putExtra("jadwal", komunitasModelsFiltered.get(position).getJadwalKomunitas());
                intent.putExtra("latitude", komunitasModelsFiltered.get(position).getLatitude());
                intent.putExtra("longitude", komunitasModelsFiltered.get(position).getLongitude());
                context.startActivity(intent );
            }
        });
    }

    @Override
    public int getItemCount() {
        return (komunitasModelsFiltered != null) ? komunitasModelsFiltered.size() : 0;
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    komunitasModelsFiltered = komunitasModels;
                } else {
                    ArrayList<Komunitas> filteredList = new ArrayList<>();
                    for (Komunitas row : komunitasModels) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getKategori().toLowerCase().contains(charString.toLowerCase()) || row.getNamaKomunitas().toLowerCase().contains(charString.toLowerCase()) || row.getJadwalKomunitas().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    komunitasModelsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = komunitasModelsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                komunitasModelsFiltered = (ArrayList<Komunitas>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
