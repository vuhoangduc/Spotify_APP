package com.example.duan1_spotify_clone.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1_spotify_clone.AdapterHome.Adapter.PlayListAdapter2;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.MainActivity2;
import com.example.duan1_spotify_clone.R;

import java.util.ArrayList;

public class FragmentLibraryAll extends Fragment {
    ListView listView;
    ArrayList<Playlist> list = new ArrayList<>();
    DBPlayList db;
    PlayListAdapter2 adapter;
    LinearLayout show_music_fv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_library_all, container, false);
        listView = v.findViewById(R.id.ListViewList);
        show_music_fv =v.findViewById(R.id.show_music_fv);
        show_music_fv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity2) getContext()).setCurrentPage(9);
            }
        });
        db = new DBPlayList(getContext());
        capNhat();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                openDialog(Gravity.CENTER,position);
                return true;
            }
        });

        return v;
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