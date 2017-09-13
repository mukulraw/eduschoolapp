package com.eduschool.eduschoolapp.WishingCards;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.BirthStuListPOJO.BirthStuList;
import com.eduschool.eduschoolapp.BirthStuListPOJO.BirthStuListBean;
import com.eduschool.eduschoolapp.ClassWork.AdapterCwParent;
import com.eduschool.eduschoolapp.ClassWrkParentPOJO.ClasssubjectListBean;
import com.eduschool.eduschoolapp.ClassWrkParentPOJO.SubjectList;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SendBirthPOJO.SendBirthBean;
import com.eduschool.eduschoolapp.User;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 6/30/2017.
 */

public class WishingCardsFrgmnt extends Fragment {
    Button send;
    Toolbar toolbar;
    String value, mCurrentPhotoPath;

    ImageView img1, img2, img3, img4, img5;
    RadioButton radio1, radio2, radio3, radio4, radio5;
    RadioGroup group;

    ProgressBar progress;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    List<BirthStuList> list;
    Adapter adapter;
    Bitmap bitmap;
    List<AdapterBean> s;
    int index;
    File file;

    List<String> studentId;

    public WishingCardsFrgmnt() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.wishing_cards_frgmnt, container, false);
        toolbar = (Toolbar) ((ParentHome) getContext()).findViewById(R.id.toolbar);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getActivity(), 1);
        img1 = (ImageView) view.findViewById(R.id.img1);
        img2 = (ImageView) view.findViewById(R.id.img2);
        img3 = (ImageView) view.findViewById(R.id.img3);
        img4 = (ImageView) view.findViewById(R.id.img4);
        img5 = (ImageView) view.findViewById(R.id.img5);
        radio1 = (RadioButton) view.findViewById(R.id.radio1);
        radio2 = (RadioButton) view.findViewById(R.id.radio2);
        radio3 = (RadioButton) view.findViewById(R.id.radio3);
        radio4 = (RadioButton) view.findViewById(R.id.radio4);
        radio5 = (RadioButton) view.findViewById(R.id.radio5);


        list = new ArrayList<>();
        s = new ArrayList<>();
        studentId = new ArrayList<>();

        adapter = new Adapter(getActivity(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        send = (Button) view.findViewById(R.id.send);


        group = (RadioGroup) view.findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                index = radioGroup.indexOfChild(radioButton);


            }

        });

        User b = (User) getActivity().getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<BirthStuListBean> call = cr.stu_bithday(b.school_id);


        call.enqueue(new Callback<BirthStuListBean>() {
            @Override
            public void onResponse(Call<BirthStuListBean> call, Response<BirthStuListBean> response) {


                adapter.setGridData(response.body().getBirthStuList(), s);
                adapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<BirthStuListBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                JSONArray jsonArray = new JSONArray();

                List<AdapterBean> list1 = adapter.getCheckList();

                Log.d("sizezee", String.valueOf(list1.size()));

                for (int i = 0; i < list1.size(); i++) {
                    JSONObject object = new JSONObject();

                    try {

                        object.put("Id", list1.get(i).getId());
                        jsonArray.put(object);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                final JSONObject jsonObject = new JSONObject();

                try {
                    jsonObject.put("stu_id", jsonArray);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("dsds", jsonObject.toString());


                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(false);
                dialog.setMessage("Are you sure you want to send card ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                        MultipartBody.Part body = MultipartBody.Part.createFormData("birth_card", file.getName(), reqFile);

                        User b = (User) getActivity().getApplicationContext();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        final AllAPIs cr = retrofit.create(AllAPIs.class);
                        progress.setVisibility(View.VISIBLE);

                        Call<SendBirthBean> call = cr.send_card(b.school_id, "Parent", b.user_id, "Parent", body, jsonObject.toString());


                        call.enqueue(new Callback<SendBirthBean>() {
                            @Override
                            public void onResponse(Call<SendBirthBean> call, Response<SendBirthBean> response) {


                                if (response.body().getStatus().equals("1")) {
                                    Toast.makeText(getActivity(), "Birthday Card Send Successfully.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "Birthday Card did not send Successfully.", Toast.LENGTH_SHORT).show();

                                }

                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<SendBirthBean> call, Throwable throwable) {
                                Log.d("imagg", String.valueOf(throwable));
                                progress.setVisibility(View.GONE);

                            }
                        });


                        dialog.dismiss();

                    }
                })
                        .setNegativeButton("No ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Action for "Cancel".
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();

            }
        });


        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Send Birthday Cards");
        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }
}

