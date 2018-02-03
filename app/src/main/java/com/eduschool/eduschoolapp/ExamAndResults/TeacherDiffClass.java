package com.eduschool.eduschoolapp.ExamAndResults;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.BasicCellViewGroup;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.classResultPOJO.classResultBean;
import com.eduschool.eduschoolapp.ownResultPOJO.ownResultBean;
import com.inqbarna.tablefixheaders.TableFixHeaders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import miguelbcr.ui.tableFixHeadesWrapper.TableFixHeaderAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by TBX on 12/15/2017.
 */

public class TeacherDiffClass extends Fragment {
    Toolbar toolbar;
    TextView tvresult;

    ProgressBar progress;

    String cname, sname;

    private TableFixHeaders tablefixheaders;

    String cls, sec;

    ImageButton edit;

    TextView day, month, classsection;

    TextView total, pass, fail;

    String id;

    private BottomSheetBehavior mBottomSheetBehavior1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.teacher_diff_class, container, false);

        id = getArguments().getString("id");
        cls = getArguments().getString("cls");
        sec = getArguments().getString("sec");
        cname = getArguments().getString("cname");
        sname = getArguments().getString("sname");


        edit = (ImageButton) view.findViewById(R.id.edit);
        day = (TextView) view.findViewById(R.id.day);
        month = (TextView) view.findViewById(R.id.month);
        classsection = (TextView) view.findViewById(R.id.classSection);

        classsection.setText(cname + " " + sname);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
        String formattedDate = df.format(c.getTime());

        String[] d1 = formattedDate.split("-");

        day.setText(d1[0]);
        month.setText(d1[1] + " " + d1[2]);


        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);

        total = (TextView) view.findViewById(R.id.total);
        pass = (TextView) view.findViewById(R.id.pass);
        fail = (TextView) view.findViewById(R.id.fail);

        progress = (ProgressBar) view.findViewById(R.id.progress);

        final View bottomSheet1 = view.findViewById(R.id.bottom_sheet1);
        mBottomSheetBehavior1 = BottomSheetBehavior.from(bottomSheet1);
        //mBottomSheetBehavior1.setHideable(true);
        mBottomSheetBehavior1.setPeekHeight(100);
        mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_HIDDEN);

        tvresult = (TextView) view.findViewById(R.id.tvresult);
        tvresult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBottomSheetBehavior1.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);

                } else {
                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);

                }
            }
        });


        tablefixheaders = (TableFixHeaders) view.findViewById(R.id.tablefixheaders);


        progress.setVisibility(View.VISIBLE);


        User u = (User) getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(u.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);

        Call<ownResultBean> call = cr.getOwnClassResult(u.school_id, cls, sec, id);


        call.enqueue(new Callback<ownResultBean>() {
            @Override
            public void onResponse(Call<ownResultBean> call, Response<ownResultBean> response) {

                try {


                    if (response.body().getResultList().size() > 0) {
                        BasicTableFixHeaderAdapter adapter = new BasicTableFixHeaderAdapter(getContext(), response.body().getResultList().get(0).getStudentList().size());
                        List<List<String>> body = getBody();

                        adapter.setFirstHeader("ROLL NO.,STUDENT NAME");

                        List<String> head = new ArrayList<>();

                        for (int i = 0; i < response.body().getResultList().get(0).getSubjectType().size(); i++) {

                            String ex = response.body().getResultList().get(0).getSubjectType().get(i).getExamDate() + "," + response.body().getResultList().get(0).getSubjectType().get(i).getSubjectName();
                            //String ex = Html.fromHtml("<b>" + response.body().getResultList().get(0).getSubjectType().get(i).getExamDate() + "</b>") + System.getProperty("line.separator") + System.getProperty("line.separator") + response.body().getResultList().get(0).getSubjectType().get(i).getSubjectName();

                            head.add(ex);

                        }

                        adapter.setHeader(head);

                        List<List<String>> firHead = new ArrayList<>();

                        for (int l = 0; l < response.body().getResultList().get(0).getStudentList().size(); l++) {
                            List<String> sub = new ArrayList<>();
                            for (int j = 0; j < response.body().getResultList().get(0).getStudentList().size(); j++) {

                                sub.add(response.body().getResultList().get(0).getStudentList().get(l).getRollNo() + "," + response.body().getResultList().get(0).getStudentList().get(l).getStudentName());

                            }
                            firHead.add(sub);
                        }


                        adapter.setFirstBody(firHead);

                        List<List<String>> body1 = new ArrayList<>();

                        for (int l = 0; l < response.body().getResultList().get(0).getStudentList().size(); l++) {
                            List<String> sub = new ArrayList<>();
                            for (int j = 0; j < response.body().getResultList().get(0).getStudentList().get(l).getStuMarks().size(); j++) {

                                sub.add(response.body().getResultList().get(0).getStudentList().get(l).getStuMarks().get(j).getMarks());

                            }
                            body1.add(sub);
                        }


                        adapter.setBody(body1);
                        adapter.setSection(body1);

                        tablefixheaders.setAdapter(adapter);
                        tablefixheaders.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                        tablefixheaders.setVisibility(View.GONE);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<ownResultBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


        progress.setVisibility(View.VISIBLE);


        Call<classResultBean> call2 = cr.getResultData(u.school_id, cls, sec, id);


        call2.enqueue(new Callback<classResultBean>() {
            @Override
            public void onResponse(Call<classResultBean> call, Response<classResultBean> response) {

                try {

                    int pas = response.body().getClassResult().get(0).getTotalPassStudent();
                    int tot = response.body().getClassResult().get(0).getTotalStudent();
                    total.setText(String.valueOf(response.body().getClassResult().get(0).getTotalStudent()));
                    pass.setText(String.valueOf(response.body().getClassResult().get(0).getTotalPassStudent()));
                    fail.setText(String.valueOf(tot - pas));

                } catch (Exception e) {
                    e.printStackTrace();
                }

                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<classResultBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog dialog = new Dialog(getActivity());
                dialog.setCancelable(true);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.attendance_diff_class_dialog);

                Button submit = (Button) dialog.findViewById(R.id.submit);
                final ProgressBar bar = (ProgressBar) dialog.findViewById(R.id.progress);
                final Spinner cl = (Spinner) dialog.findViewById(R.id.className);
                final Spinner se = (Spinner) dialog.findViewById(R.id.sectionName);


                final List<String> clasNames = new ArrayList<>();
                final List<String> clasIds = new ArrayList<>();

                final List<String> secNames = new ArrayList<>();
                final List<String> secIds = new ArrayList<>();

                final List<String> stuNames = new ArrayList<>();
                final List<String> stuIds = new ArrayList<>();

                final String[] cid = new String[1];
                final String[] sid = new String[1];
                final String[] cname = new String[1];
                final String[] sname = new String[1];

                final User b = (User) getActivity().getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final AllAPIs cr = retrofit.create(AllAPIs.class);

                final Call<ClassListbean> call = cr.classList(b.school_id);
                bar.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<ClassListbean>() {
                    @Override
                    public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {

                        clasNames.clear();
                        clasIds.clear();

                        for (int i = 0; i < response.body().getClassList().size(); i++) {

                            clasNames.add(response.body().getClassList().get(i).getClassName());
                            clasIds.add(response.body().getClassList().get(i).getClassId());

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, clasNames);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        cl.setAdapter(adapter);


                        bar.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                        bar.setVisibility(View.GONE);

                    }
                });


                cl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        cid[0] = clasIds.get(position);
                        cname[0] = clasNames.get(position);


                        Call<SectionListbean> call2 = cr.sectionList(b.school_id, cid[0]);

                        bar.setVisibility(View.VISIBLE);


                        call2.enqueue(new Callback<SectionListbean>() {

                            @Override
                            public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                                //listSection = response.body().getSectionList();

                                secNames.clear();
                                secIds.clear();

                                for (int i = 0; i < response.body().getSectionList().size(); i++) {

                                    secNames.add(response.body().getSectionList().get(i).getSectionName());

                                    secIds.add(response.body().getSectionList().get(i).getSectionId());
                                }


                                ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                        android.R.layout.simple_list_item_1, secNames);

                                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                adp.notifyDataSetChanged();
                                se.setAdapter(adp);

                                bar.setVisibility(View.GONE);


                            }

                            @Override
                            public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                                bar.setVisibility(View.GONE);

                            }

                        });


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                se.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        sid[0] = secIds.get(position);
                        sname[0] = secNames.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();


                        classsection.setText(cname[0] + sname[0]);
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
                        String formattedDate = df.format(c.getTime());

                        String[] d1 = formattedDate.split("-");

                        day.setText(d1[0]);
                        month.setText(d1[1] + " " + d1[2]);


                        progress.setVisibility(View.VISIBLE);


                        User u = (User) getContext().getApplicationContext();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(u.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);

                        Call<ownResultBean> call = cr.getOwnClassResult(u.school_id, cid[0], sid[0], id);


                        call.enqueue(new Callback<ownResultBean>() {
                            @Override
                            public void onResponse(Call<ownResultBean> call, Response<ownResultBean> response) {

                                try {

                                    if (response.body().getResultList().size() > 0) {
                                        BasicTableFixHeaderAdapter adapter = new BasicTableFixHeaderAdapter(getContext(), response.body().getResultList().get(0).getStudentList().size());
                                        List<List<String>> body = getBody();

                                        adapter.setFirstHeader("ROLL NO.,STUDENT NAME");

                                        List<String> head = new ArrayList<>();

                                        for (int i = 0; i < response.body().getResultList().get(0).getSubjectType().size(); i++) {

                                            String ex = response.body().getResultList().get(0).getSubjectType().get(i).getExamDate() + "," + response.body().getResultList().get(0).getSubjectType().get(i).getSubjectName();
                                            //String ex = Html.fromHtml("<b>" + response.body().getResultList().get(0).getSubjectType().get(i).getExamDate() + "</b>") + System.getProperty("line.separator") + System.getProperty("line.separator") + response.body().getResultList().get(0).getSubjectType().get(i).getSubjectName();

                                            head.add(ex);

                                        }

                                        adapter.setHeader(head);

                                        List<List<String>> firHead = new ArrayList<>();

                                        for (int l = 0; l < response.body().getResultList().get(0).getStudentList().size(); l++) {
                                            List<String> sub = new ArrayList<>();
                                            for (int j = 0; j < response.body().getResultList().get(0).getStudentList().size(); j++) {

                                                sub.add(response.body().getResultList().get(0).getStudentList().get(l).getRollNo() + "," + response.body().getResultList().get(0).getStudentList().get(l).getStudentName());

                                            }
                                            firHead.add(sub);
                                        }


                                        adapter.setFirstBody(firHead);

                                        List<List<String>> body1 = new ArrayList<>();

                                        for (int l = 0; l < response.body().getResultList().get(0).getStudentList().size(); l++) {
                                            List<String> sub = new ArrayList<>();
                                            for (int j = 0; j < response.body().getResultList().get(0).getStudentList().get(l).getStuMarks().size(); j++) {

                                                sub.add(response.body().getResultList().get(0).getStudentList().get(l).getStuMarks().get(j).getMarks());

                                            }
                                            body1.add(sub);
                                        }


                                        adapter.setBody(body1);
                                        adapter.setSection(body1);

                                        tablefixheaders.setAdapter(adapter);
                                    } else {
                                        Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                                    }


                                } catch (Exception e) {
                                    tablefixheaders.setAdapter(new BasicTableFixHeaderAdapter(getContext(), 0));
                                    e.printStackTrace();
                                }


                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<ownResultBean> call, Throwable t) {
                                progress.setVisibility(View.GONE);
                            }
                        });


                        progress.setVisibility(View.VISIBLE);


                        Call<classResultBean> call2 = cr.getResultData(u.school_id, cid[0], sid[0], id);


                        call2.enqueue(new Callback<classResultBean>() {
                            @Override
                            public void onResponse(Call<classResultBean> call, Response<classResultBean> response) {

                                try {
                                    total.setText(String.valueOf(response.body().getClassResult().get(0).getTotalStudent()));
                                    pass.setText(String.valueOf(response.body().getClassResult().get(0).getTotalPassStudent()));
                                    fail.setText(String.valueOf(response.body().getClassResult().get(0).getTotalFailStudent()));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                progress.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<classResultBean> call, Throwable t) {
                                progress.setVisibility(View.GONE);
                            }
                        });


                    }
                });

                dialog.show();

            }
        });


        return view;

    }


    private List<String> getHeader() {
        List<String> header = new ArrayList<>();

        for (int i = 0; i < 20; i++)
            header.add("H " + (i + 1));

        return header;
    }

    private List<List<String>> getBody() {
        List<List<String>> rows = new ArrayList<>();

        for (int row = 0; row < 3; row++) {
            List<String> cols = new ArrayList<>();

            for (int col = 0; col < 3; col++)
                cols.add("Col " + col + row);

            rows.add(cols);
        }

        return rows;
    }


    public class BasicTableFixHeaderAdapter extends TableFixHeaderAdapter<String, BasicCellViewGroup,
            String, BasicCellViewGroup,
            List<String>,
            BasicCellViewGroup,
            BasicCellViewGroup,
            BasicCellViewGroup> {

        int count = 0;

        private Context context;

        public BasicTableFixHeaderAdapter(Context context, int count) {
            super(context);
            this.context = context;
            this.count = count;
        }

        @Override
        protected BasicCellViewGroup inflateFirstHeader() {
            return new BasicCellViewGroup(context);
        }

        @Override
        protected BasicCellViewGroup inflateHeader() {
            return new BasicCellViewGroup(context);
        }

        @Override
        protected BasicCellViewGroup inflateFirstBody() {
            return new BasicCellViewGroup(context);
        }

        @Override
        protected BasicCellViewGroup inflateBody() {
            return new BasicCellViewGroup(context);
        }

        @Override
        protected BasicCellViewGroup inflateSection() {
            return new BasicCellViewGroup(context);
        }

        @Override
        protected List<Integer> getHeaderWidths() {
            List<Integer> headerWidths = new ArrayList<>();

            // First header
            headerWidths.add((int) context.getResources().getDimension(R.dimen._150dp));

            for (int i = 0; i <= count; i++)
                headerWidths.add((int) context.getResources().getDimension(R.dimen._100dp));

            return headerWidths;
        }

        @Override
        protected int getHeaderHeight() {
            return (int) context.getResources().getDimension(R.dimen._80dp);
        }

        @Override
        protected int getSectionHeight() {
            return (int) context.getResources().getDimension(R.dimen._40dp);
        }

        @Override
        protected int getBodyHeight() {
            return (int) context.getResources().getDimension(R.dimen._40dp);
        }

        @Override
        protected boolean isSection(List<List<String>> items, int row) {
            return false;
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        toolbar.setTitle("Result");
        User u = (User) getContext().getApplicationContext();

        u.back = false;
    }

}
