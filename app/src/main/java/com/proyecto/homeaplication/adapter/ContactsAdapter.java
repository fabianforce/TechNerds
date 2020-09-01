package com.proyecto.homeaplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.homeaplication.R;
import com.proyecto.homeaplication.model.User;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsHolder> {

    List<User> userList;
    private onItemClickChatListener onItemClickChatListener;

    public ContactsAdapter(List<User> seriesList) {
        this.userList = seriesList;
    }

    @NonNull
    @Override
    public ContactsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsHolder contactHolder, final int position) {

        contactHolder.nameLabel.setText(userList.get(position).getName());
        contactHolder.emailLabel.setText(userList.get(position).getEmail());
        contactHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickChatListener.showChat(userList.get(position));
                Log.e("here","here");
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ContactsHolder extends RecyclerView.ViewHolder {
        TextView nameLabel, emailLabel;
        Button btnDetail;
        CardView cardView;
        View mview;

        public ContactsHolder(@NonNull final View itemView) {
            super(itemView);
            mview = itemView;
            nameLabel = mview.findViewById(R.id.name_label);
            emailLabel = mview.findViewById(R.id.email_label);
            cardView = mview.findViewById(R.id.contact_card);
        }


    }

    public interface onItemClickChatListener {
        void showChat(User user);
    }

    public void setOnItemClickListener(onItemClickChatListener onItemClickListener) {
        this.onItemClickChatListener = onItemClickListener;
    }
}
