package com.example.assingment_github_api.Adapter;


import android.content.Intent;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.assingment_github_api.Model.Repository;
import com.example.assingment_github_api.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {

    public ArrayList<Repository> repositories;


    public RepositoryAdapter(ArrayList<Repository> repositories) {
        this.repositories = repositories;
    }

    public RepositoryAdapter() {

    }

    public void setRepositories(ArrayList<Repository> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repository repository = repositories.get(position);
        holder.repositoryNameTextView.setText(repository.getName());
        holder.repositoryDescriptionTextView.setText(repository.getDescription());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                String sb = repository.getName() +
                        "\n" +
                        repository.getHtml_url() +
                        "\n\n";

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, sb);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                v.getContext().startActivity(shareIntent);

                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(repository.getHtml_url()));
                view.getContext().startActivity(browserIntent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return repositories != null ? repositories.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView repositoryNameTextView, repositoryDescriptionTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            repositoryNameTextView = itemView.findViewById(R.id.text_name);
            repositoryDescriptionTextView = itemView.findViewById(R.id.text_description);
        }
    }
}
