package com.eduschool.eduschoolapp.WishingCards;

import android.app.Dialog;
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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.eduschool.eduschoolapp.Communication.SendBirthdayCard;
import com.eduschool.eduschoolapp.Home.ParentHome;
import com.eduschool.eduschoolapp.R;

import com.eduschool.eduschoolapp.RaiseRequest.RaiseRequestActivity;
import com.eduschool.eduschoolapp.SendBirthPOJO.SendBirthBean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.birthPOJO.BirthList;
import com.eduschool.eduschoolapp.birthPOJO.birthBean;
import com.makeramen.roundedimageview.RoundedImageView;


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
import java.util.Objects;

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

    RoundedImageView img1, img2, img3, img4, img5;
    RadioButton radio1, radio2, radio3, radio4, radio5;
    RadioGroup group;

    ProgressBar progress;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    List<BirthList> list;
    Adapter adapter;
    Bitmap bitmap;
    List<AdapterBean> s;
    int index = -1;
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
        img1 = (RoundedImageView) view.findViewById(R.id.img1);
        img2 = (RoundedImageView) view.findViewById(R.id.img2);
        img3 = (RoundedImageView) view.findViewById(R.id.img3);
        img4 = (RoundedImageView) view.findViewById(R.id.img4);
        img5 = (RoundedImageView) view.findViewById(R.id.img5);
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

        final User b = (User) getActivity().getApplicationContext();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AllAPIs cr = retrofit.create(AllAPIs.class);
        progress.setVisibility(View.VISIBLE);

        Call<birthBean> call = cr.stu_bithday(b.school_id);


        call.enqueue(new Callback<birthBean>() {
            @Override
            public void onResponse(Call<birthBean> call, Response<birthBean> response) {

                s.clear();

                if (response.body().getBirthList().size() > 0) {
                    for (int i = 0; i < response.body().getBirthList().size(); i++) {

                        if (!Objects.equals(response.body().getBirthList().get(i).getId(), b.user_id)) {
                            list.add(response.body().getBirthList().get(i));
                        }

                    }

                    adapter.setGridData(list, s);
                    adapter.notifyDataSetChanged();
                }


                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<birthBean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (index > -1)
                {
                    JSONArray jsonArray = new JSONArray();

                    List<AdapterBean> list1 = adapter.getCheckList();

                    final List<String> ll = new ArrayList<>();

                    Log.d("sizezee", String.valueOf(list1.size()));

                    for (int i = 0; i < list1.size(); i++) {
                        JSONObject object = new JSONObject();

                        //object.put("Id", list1.get(i).getId());

                        if (Objects.equals(list1.get(i).getStatus(), "1"))
                        {
                            ll.add(list1.get(i).getId());
                        }


                        //jsonArray.put(object);

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

                            try {


                                User b = (User) getActivity().getApplicationContext();
                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.baseURL)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                final AllAPIs cr = retrofit.create(AllAPIs.class);



                                String ca = "";

                                if (index == 0) {
                                    ca = "card1.jpg";
                                } else if (index == 1) {
                                    ca = "card2.jpg";
                                } else if (index == 2) {
                                    ca = "card4.jpg";
                                } else if (index == 3) {
                                    ca = "card5.jpg";
                                } else if (index == 4) {
                                    ca = "card6.jpg";
                                }


                                if (ll.size() > 0) {

                                    progress.setVisibility(View.VISIBLE);

                                    Call<SendBirthBean> call = cr.send_card(b.school_id, "Parent", b.user_id, "Parent", ca, TextUtils.join(",", ll));


                                    call.enqueue(new Callback<SendBirthBean>() {
                                        @Override
                                        public void onResponse(Call<SendBirthBean> call, Response<SendBirthBean> response) {


                                            if (response.body().getStatus().equals("1")) {
                                                Dialog dialog = new Dialog(getActivity());
                                                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                dialog.setContentView(R.layout.birthday_success_popup);
                                                dialog.setCancelable(true);
                                                dialog.show();

                                                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                    @Override
                                                    public void onCancel(DialogInterface dialogInterface) {
                                                        //getActivity().finish();
                                                        //getFragmentManager().popBackStack();

                                                        group.clearCheck();
                                                        adapter.setGridData(list, s);
                                                        adapter.notifyDataSetChanged();
                                                    }
                                                });


                                            } else {
                                                Toast.makeText(getActivity(), "Error sending Cards, please try again", Toast.LENGTH_SHORT).show();

                                            }

                                            progress.setVisibility(View.GONE);

                                        }

                                        @Override
                                        public void onFailure(Call<SendBirthBean> call, Throwable throwable) {
                                            Log.d("imagg", String.valueOf(throwable));
                                            progress.setVisibility(View.GONE);

                                        }
                                    });
                                } else {
                                    Toast.makeText(getContext(), "Please select a student", Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception e) {
                                e.printStackTrace();
                            }


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
                else
                {
                    Toast.makeText(getContext() , "Please select a Card" , Toast.LENGTH_SHORT).show();
                }




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

