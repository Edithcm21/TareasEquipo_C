package com.example.lorempicsum_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private final Context context;
    private final List<Client> lista;


    Adapter (Context context, List<Client> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (context).inflate (R.layout.recyclerview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull MyViewHolder holder, int position) {
        holder.setData (lista.get(position).getAuthor(),lista.get(position).getUrl(),lista.get(position).getDownload_url());
    }

    @Override
    public int getItemCount() {
        return lista.size ();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        final ImageView Download_Url;
        final TextView Author;
        final TextView Url;

        MyViewHolder(@NonNull View itemView) {
            super (itemView);
            Download_Url = itemView.findViewById (R.id.download_Url);
            Author = itemView.findViewById (R.id.author);
            Url = itemView.findViewById (R.id.url);
        }


        void setData (String Autor,String Link, String URL) {
            Author.setText (Autor);
            Url.setText (Link);
            if (URL.startsWith ("http://")) {
                URL = URL.replaceFirst ("http://", "https://");
            }
            Picasso.get ()
                    .load (URL)
                    .resize (250, 250)
                    .centerCrop ()
                    .placeholder (R.drawable.placeholder)
                    .into (Download_Url);
        }
    }
}
