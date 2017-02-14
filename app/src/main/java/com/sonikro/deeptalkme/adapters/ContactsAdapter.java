package com.sonikro.deeptalkme.adapters;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.sonikro.deeptalkme.R;
import com.sonikro.deeptalkme.activity.layout.UserImageBuilder;
import com.sonikro.deeptalkme.framework.dialog.ViewDialog;
import com.sonikro.deeptalkme.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jonathan Nagayoshi on 19/07/2016.
 */
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>{

    private List<User> mFriends;
    private View mSelectedView;

    public ContactsAdapter(User[] friends)
    {
        mFriends = Arrays.asList(friends);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                viewType ,parent,false
        );

        return new ContactViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.contact_card;
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        User currentUser = mFriends.get(position);
        holder.txtName.setText(currentUser.getName());
        holder.imgView.setImageDrawable(UserImageBuilder.buildUserDrawable(currentUser));
        holder.txtEmail.setText(currentUser.getEmail());
    }

    @Override
    public int getItemCount() {
        return mFriends.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtName,txtEmail;
        public ImageView imgView;
        public ContactViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.contact_card_name);
            imgView = (ImageView) itemView.findViewById(R.id.contact_card_image);
            txtEmail = (TextView) itemView.findViewById(R.id.contact_card_email);

        }
    }
}
