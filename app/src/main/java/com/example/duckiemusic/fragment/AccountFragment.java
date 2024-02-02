package com.example.duckiemusic.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duckiemusic.R;
import com.example.duckiemusic.model.User;
import com.example.duckiemusic.ui.Utils;
import com.google.gson.Gson;

public class AccountFragment extends Fragment {
    TextView tvUserNameC;
    private final Gson gson=new Gson();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_account,container,false);
        tvUserNameC=view.findViewById(R.id.tvUserName);

        SharedPreferences share=getActivity().getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        String userPref=share.getString(Utils.KEY_USER,null);

        User user=gson.fromJson(userPref,User.class);
        if(user==null)
        {
            tvUserNameC.setText("Không có dữ liệu");
        }
        else
        {
            String info="Email:"+user.getUsername()+"\n";
            info+="Password:"+user.getPassword()+"\n";
            tvUserNameC.setText(info);
        }
        return view;
    }
}