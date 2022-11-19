package com.example.duan1_spotify_clone.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duan1_spotify_clone.AdapterHome.NgheSiAdapterHome;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DTO.HomeItem;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentKenh extends Fragment {
    private NgheSiAdapterHome NSAdapter;
    private RecyclerView recyclerViewNS;
    TextView tvGioiThieu,tv_dsBai;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kenh, container, false);
        recyclerViewNS = v.findViewById(R.id.recycleNS);
        NSAdapter = new NgheSiAdapterHome(getListHome());
        LinearLayoutManager managerNS = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerViewNS.setLayoutManager(managerNS);
        recyclerViewNS.setAdapter(NSAdapter);
        tvGioiThieu = v.findViewById(R.id.tv_gioiThieu);
        tvGioiThieu.setText("Nguyễn Thanh Tùng (sinh ngày 5 tháng 7 năm 1994), thường được biết đến với nghệ danh Sơn Tùng M-TP, là một nam ca sĩ kiêm sáng tác nhạc, rapper và diễn viên người Việt Nam.\n" +
                "\n" +
                "Sinh ra ở thành phố Thái Bình, thời thơ ấu, Sơn Tùng thường đi hát ở Cung văn hoá thiếu nhi tỉnh Thái Bình và học chơi đàn organ. Sau đó, Tùng cùng với các bạn trong lớp thành lập nên một nhóm nhạc, lấy tên là Over Band, bắt đầu sáng tác và đăng tải các bài hát của mình lên một trang web chuyên về lĩnh vực âm nhạc có tên là LadyKillah.");
        //
        tv_dsBai = v.findViewById(R.id.tv_dsBai);
        DBPlayList db = new DBPlayList(getContext());
        ArrayList<Playlist> list = db.getAll();
        String name = "";
        for(int i=0;i<list.size();i++){
            name+=list.get(i).getTenList()+" • ";
        }
        name+="và hơn thế nữa";
        tv_dsBai.setText(name);
        return v;
    }
    private List<HomeItem> getListHome(){
        List<HomeItem> list1 = new ArrayList<>();

        list1.add(new HomeItem("Bad Bunny",R.drawable.v_pop));
        list1.add(new HomeItem("Sơn Tùng M-TP",R.drawable.hiphop));
        list1.add(new HomeItem("J97",R.drawable.k_pop));
        list1.add(new HomeItem("Amee",R.drawable.pop));
        return list1;

    }
}