package com.example.duan1_spotify_clone.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_spotify_clone.ActivityLogin.Screen_input_Login;
import com.example.duan1_spotify_clone.AdapterHome.Adapter.PlayListAdapter2;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class FragmentPramium extends Fragment {
    ListView listView;
    ArrayList<Playlist> list = new ArrayList<>();
    DBPlayList db;
    PlayListAdapter2 adapter;
    ImageView img;
    TextView tv;
    Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pramium, container, false);
        listView = v.findViewById(R.id.ListViewListIn4);
        db = new DBPlayList(getContext());
        capNhat();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openDialog(Gravity.CENTER,position);
                return true;
            }
        });
        btn = v.findViewById(R.id.btnLogout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(getActivity(), Screen_input_Login.class));
            }
        });
        img = v.findViewById(R.id.deleteName);
        tv = v.findViewById(R.id.tv_Name);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog2(Gravity.CENTER);

            }
        });
        return v;
    }
    private void openDialog2(int gravity){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialogdelete_name);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.background_errol));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
        TextInputEditText tv1 = dialog.findViewById(R.id.editName);
        Button btn = dialog.findViewById(R.id.btnEditName);
        TextView tv2 = dialog.findViewById(R.id.btnHuyEditName);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if(Gravity.CENTER==gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    tv.setText(tv1.getText());
                    Toast.makeText(getContext(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
            }
        });

    }
    private void openDialog(int gravity,int position){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_deletelist);
        dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.background_errol));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.show();
        TextView tv = dialog.findViewById(R.id.tvCloseDialogList);
        Button btn = dialog.findViewById(R.id.btnXoaList);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        if(Gravity.CENTER==gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( db.delete(list.get(position))>0){
                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                    capNhat();
                    dialog.dismiss();

                }else{
                    Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    void capNhat(){
        list = db.getAll();
        adapter = new PlayListAdapter2(getActivity(),list);
        listView.setAdapter(adapter);
    }
}