package com.example.duan1_spotify_clone.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.duan1_spotify_clone.AdapterHome.NgheSiAdapterHome;
import com.example.duan1_spotify_clone.DBHelper.DBPlayList;
import com.example.duan1_spotify_clone.DTO.HomeItem;
import com.example.duan1_spotify_clone.DTO.Kenh;
import com.example.duan1_spotify_clone.DTO.Playlist;
import com.example.duan1_spotify_clone.Fragment.GET_API_JSON.JsonParser_Chanel_Home;
import com.example.duan1_spotify_clone.R;
import com.example.duan1_spotify_clone.intefaces.KenhSend;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class FragmentKenh extends Fragment {
    private NgheSiAdapterHome NSAdapter;
    private RecyclerView recyclerViewNS;
    TextView tvGioiThieu,tv_dsBai,nameKenh;
    ListView lv;
    ImageView view,imgGthieu;
    Kenh kenh;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kenh, container, false);
        recyclerViewNS = v.findViewById(R.id.recycleNS);
        LinearLayoutManager managerNS = new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
        recyclerViewNS.setLayoutManager(managerNS);
        view = v.findViewById(R.id.imgKenh);
        imgGthieu=v.findViewById(R.id.imgGthieu);
        nameKenh =v.findViewById(R.id.nameKenh);
        kenh = new Kenh();
//        assert getArguments() != null;
        kenh = (Kenh) getArguments().getSerializable("Kenh");
        getDataKenh(kenh);
//        Log.d("hihihi",getArguments().getString("data"));
        try {
            init3();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        tvGioiThieu = v.findViewById(R.id.tv_gioiThieu);
        tvGioiThieu.setText("Nguyễn Thanh Tùng (sinh ngày 5 tháng 7 năm 1994), thường được biết đến với nghệ danh Sơn Tùng M-TP, là một nam ca sĩ kiêm sáng tác nhạc, rapper và diễn viên người Việt Nam.\n" +
                "\n" +
                "Sinh ra ở thành phố Thái Bình, thời thơ ấu, Sơn Tùng thường đi hát ở Cung văn hoá thiếu nhi tỉnh Thái Bình và học chơi đàn organ. Sau đó, Tùng cùng với các bạn trong lớp thành lập nên một nhóm nhạc, lấy tên là Over Band, bắt đầu sáng tác và đăng tải các bài hát của mình lên một trang web chuyên về lĩnh vực âm nhạc có tên là LadyKillah.");
        //
        return v;
    }
    public void init3() throws UnknownHostException {
        JsonParser_Chanel_Home jsonParser = new JsonParser_Chanel_Home(getActivity(),recyclerViewNS);
        jsonParser.execute("http://192.168.0.104:3000/kenhs");
    }
    public void getDataKenh(Kenh kenh){

        tvGioiThieu.setText(kenh.getThongtin_gioiThieu());
        Glide.with(getContext()).load(kenh.getImg_kenh()).placeholder(R.drawable.hiphop).into(view);
        Glide.with(getContext()).load(kenh.getImg_gioiThieu()).placeholder(R.drawable.hiphop).into(imgGthieu);
        tvGioiThieu.setText(kenh.getThongtin_gioiThieu());
        nameKenh.setText(kenh.getTen_kenh());


    }
}