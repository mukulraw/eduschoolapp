package com.eduschool.eduschoolapp.ClassWork;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.AssingCwPOJO.AssignClsWrkBean;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterList;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterListbean;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassList;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.Home.TeacherHome;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionList;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentList;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectList;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by user on 5/25/2017.
 */

public class Teacherclswrk extends Fragment {
    Toolbar toolbar;
    TabLayout tabLayout;
    static ViewPager viewPager;

    static PagerAdapter adapter;

    public Teacherclswrk(){

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_class_work, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);


        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setText("RECEIVED"));
        tabLayout.addTab(tabLayout.newTab().setText("SENT"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


    public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;
        private String[] tabTitles = new String[]{"ADD", "VIEW"};


        public PagerAdapter(FragmentManager fm, int NumOfTabs) {
            super(fm);
            this.mNumOfTabs = NumOfTabs;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    OneFragment tab1 = new OneFragment();
                    return tab1;
                case 1:
                    TwoFragment tab2 = new TwoFragment();
                    return tab2;

                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return mNumOfTabs;
        }
    }



    @Override
    public void onResume() {

        super.onResume();
        toolbar.setTitle("Class Work");

        User u = (User) getContext().getApplicationContext();

        u.back = true;
    }


    public static class OneFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

        Spinner className, sectionName, status, subject, chapter;
        AlertDialog.Builder alertDialog;
        TextView date;
        EditText note;
        View convertView;
        Button add;
        String cId, sId, ssId, sChapter, sNote;
        ProgressBar progress;

        String subName , chapName;

        public List<String> classlist, sectionlist, subjectlist, chapterlist, studentlist, statuslist;
        public List<String> classId, sectionId, subjectId, chapterId, studentId;
        public List<SectionList> listSection;
        public List<SubjectList> listSubject;
        public List<StudentList> listStudent;
        public List<ChapterList> listChapter;
        List<ClassList> list;


        public OneFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            View v = inflater.inflate(R.layout.fragment_one, container, false);


            className = (Spinner) v.findViewById(R.id.className);
            date = (TextView) v.findViewById(R.id.date);
            sectionName = (Spinner) v.findViewById(R.id.sectonName);
            status = (Spinner) v.findViewById(R.id.status);
            add = (Button) v.findViewById(R.id.add);
            note = (EditText) v.findViewById(R.id.note);
            subject = (Spinner) v.findViewById(R.id.subjectName);
            chapter = (Spinner) v.findViewById(R.id.chapter);
            progress = (ProgressBar) v.findViewById(R.id.progress);

            list = new ArrayList<>();
            classlist = new ArrayList<>();
            classId = new ArrayList<>();

            listSection = new ArrayList<>();
            sectionlist = new ArrayList<>();
            sectionId = new ArrayList<>();

            subjectlist = new ArrayList<>();
            listSubject = new ArrayList<>();
            subjectId = new ArrayList<>();

            statuslist = new ArrayList<>();


            chapterlist = new ArrayList<>();
            listChapter = new ArrayList<>();
            chapterId = new ArrayList<>();

            studentlist = new ArrayList<>();
            listStudent = new ArrayList<>();
            studentId = new ArrayList<>();


            alertDialog = new AlertDialog.Builder(this.getActivity());
            convertView = (View) inflater.inflate(R.layout.custom, null);


            final User b = (User) getActivity().getApplicationContext();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.baseURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            final AllAPIs cr = retrofit.create(AllAPIs.class);

            Call<ClassListbean> call = cr.classList(b.school_id);
            progress.setVisibility(View.VISIBLE);

            call.enqueue(new Callback<ClassListbean>() {
                @Override
                public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {


                    list = response.body().getClassList();

                    classlist.clear();
                    classId.clear();


                    for (int i = 0; i < list.size(); i++) {

                        if (list.get(i).getClassName() != null && list.get(i).getClassId() != null) {

                            classlist.add(list.get(i).getClassName());
                            classId.add(list.get(i).getClassId());
                        }

                    }

                    ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1, classlist);
                    adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    className.setAdapter(adp1);


                    ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1, sectionlist);

                    adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sectionName.setAdapter(adp);


                    ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1, subjectlist);

                    adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    subject.setAdapter(adp2);


                    ArrayAdapter<String> adp3 = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1, chapterlist);

                    adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    chapter.setAdapter(adp3);


                    ArrayAdapter<String> adp4 = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1, statuslist);

                    statuslist.add("Reading");
                    statuslist.add("Home Work Assigned");
                    statuslist.add("Doubt Clearing");
                    statuslist.add("Completed");
                    adp4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    status.setAdapter(adp4);
                    progress.setVisibility(View.GONE);


                }

                @Override
                public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                    progress.setVisibility(View.GONE);

                }
            });


            className.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    cId = classId.get(i);
                    Call<SectionListbean> call2 = cr.sectionList(b.school_id, classId.get(i));

                    //Toast.makeText(getActivity(), a, Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.VISIBLE);


                    call2.enqueue(new Callback<SectionListbean>() {

                        @Override
                        public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                            listSection = response.body().getSectionList();

                            sectionId.clear();
                            sectionlist.clear();

                            for (int i = 0; i < response.body().getSectionList().size(); i++) {

                                sectionlist.add(response.body().getSectionList().get(i).getSectionName());

                                sectionId.add(response.body().getSectionList().get(i).getSectionId());
                            }


                            try {
                                ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                        android.R.layout.simple_list_item_1, sectionlist);

                                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                adp.notifyDataSetChanged();
                                sectionName.setAdapter(adp);
                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }



                            progress.setVisibility(View.GONE);


                        }

                        @Override
                        public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                            progress.setVisibility(View.GONE);

                        }

                    });


//                    Log.d("subjectId", String.valueOf(subjectId.get(0)));
                    Call<ChapterListbean> call = cr.chapterList(b.school_id, cId, ssId);


                    progress.setVisibility(View.VISIBLE);


                    call.enqueue(new Callback<ChapterListbean>() {

                        @Override
                        public void onResponse(Call<ChapterListbean> call, Response<ChapterListbean> response) {


                            listChapter = response.body().getChapterList();
                            chapterlist.clear();
                            chapterId.clear();
                            for (int i = 0; i < response.body().getChapterList().size(); i++) {

                                chapterlist.add(response.body().getChapterList().get(i).getChapterName());

                                chapterId.add(response.body().getChapterList().get(i).getChapterId());

                            }


                            ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getContext(),
                                    android.R.layout.simple_list_item_1, chapterlist);

                            adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            adp2.notifyDataSetChanged();
                            chapter.setAdapter(adp2);

                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<ChapterListbean> call, Throwable throwable) {
                            progress.setVisibility(View.GONE);

                        }
                    });


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    ssId = subjectId.get(i);
                    subName = subjectlist.get(i);

                    Call<SubjectListBean> call1 = cr.subjectList(b.school_id, cId,sId);

                    progress.setVisibility(View.VISIBLE);

                    call1.enqueue(new Callback<SubjectListBean>() {

                        @Override
                        public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {


                            for (int i = 0; i < response.body().getSubjectList().size(); i++) {

                            }


                            Call<ChapterListbean> call2 = cr.chapterList(b.school_id, cId, ssId);


                            progress.setVisibility(View.VISIBLE);


                            call2.enqueue(new Callback<ChapterListbean>() {

                                @Override
                                public void onResponse(Call<ChapterListbean> call2, Response<ChapterListbean> response) {


                                    listChapter = response.body().getChapterList();
                                    chapterlist.clear();
                                    chapterId.clear();
                                    for (int i = 0; i < response.body().getChapterList().size(); i++) {

                                        chapterlist.add(response.body().getChapterList().get(i).getChapterName());

                                        chapterId.add(response.body().getChapterList().get(i).getChapterId());

                                    }

                                    try {
                                        ArrayAdapter<String> adp2 = new ArrayAdapter<String>(getContext(),
                                                android.R.layout.simple_list_item_1, chapterlist);

                                        adp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                        adp2.notifyDataSetChanged();
                                        chapter.setAdapter(adp2);
                                    }catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }


                                    progress.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<ChapterListbean> call2, Throwable throwable) {
                                    progress.setVisibility(View.GONE);

                                }
                            });


                        }

                        @Override
                        public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                            progress.setVisibility(View.GONE);

                        }
                    });


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            chapter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                    Call<ChapterListbean> call = cr.chapterList(b.school_id, cId, ssId);

                    progress.setVisibility(View.VISIBLE);

                    call.enqueue(new Callback<ChapterListbean>() {

                        @Override
                        public void onResponse(Call<ChapterListbean> call, Response<ChapterListbean> response) {

                            for (int i = 0; i < response.body().getChapterList().size(); i++) {


                            }

                            sChapter = chapterId.get(i);
                            chapName = chapterlist.get(i);
                            progress.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<ChapterListbean> call, Throwable throwable) {
                            progress.setVisibility(View.GONE);

                        }
                    });
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            sectionName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.baseURL)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    final AllAPIs cr = retrofit.create(AllAPIs.class);

                    Call<SectionListbean> call2 = cr.sectionList(b.school_id, classId.get(i));

                    progress.setVisibility(View.VISIBLE);


                    call2.enqueue(new Callback<SectionListbean>() {

                        @Override
                        public void onResponse(Call<SectionListbean> call, Response<SectionListbean> response) {


                            for (int i = 0; i < response.body().getSectionList().size(); i++) {


                            }
                            Log.d("section", String.valueOf(sectionId.get(i)));
                            sId = sectionId.get(i);


                            Call<StudentListbean> call3 = cr.student_list(b.school_id, cId, sId);

                            progress.setVisibility(View.VISIBLE);


                            call3.enqueue(new Callback<StudentListbean>() {

                                @Override
                                public void onResponse(Call<StudentListbean> call3, Response<StudentListbean> response) {


                                    listStudent = response.body().getStudentList();
                                    studentlist.clear();
                                    studentId.clear();
                                    for (int i = 0; i < response.body().getStudentList().size(); i++) {

                                        studentlist.add(response.body().getStudentList().get(i).getStudentName());

                                        studentId.add(response.body().getStudentList().get(i).getStudentId());

                                    }

                                    progress.setVisibility(View.GONE);

                                    Log.d("name", String.valueOf(studentlist.get(0)));

                                }

                                @Override
                                public void onFailure(Call<StudentListbean> call, Throwable throwable) {
                                    progress.setVisibility(View.GONE);

                                }
                            });


                        }

                        @Override
                        public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                            progress.setVisibility(View.GONE);

                        }
                    });


                    Call<SubjectListBean> call1 = cr.subjectList(b.school_id, classId.get(i),sectionId.get(i));

                    progress.setVisibility(View.VISIBLE);

                    call1.enqueue(new Callback<SubjectListBean>() {

                        @Override
                        public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {

                            listSubject = response.body().getSubjectList();
                            subjectlist.clear();
                            subjectId.clear();


                            for (int i = 0; i < response.body().getSubjectList().size(); i++) {

                                subjectlist.add(response.body().getSubjectList().get(i).getSubjectName());
                                subjectId.add(response.body().getSubjectList().get(i).getSubjectId());
                            }

                            try {

                                ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                                        android.R.layout.simple_list_item_1, subjectlist);

                                adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                subject.setAdapter(adp1);
                                adp1.notifyDataSetChanged();

                            }catch (Exception e)
                            {
                                e.printStackTrace();
                            }



                            progress.setVisibility(View.GONE);


                        }

                        @Override
                        public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                            progress.setVisibility(View.GONE);

                        }
                    });

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            date.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {

                    android.app.DialogFragment newFragment = new DatePickerFragment2();
                    newFragment.show(getActivity().getFragmentManager(), "df");


                }
            });


            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setCancelable(false);
                    dialog.setMessage("Are you sure you want to add Class Work ?");
                    dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {

                            sNote = note.getText().toString().trim();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.baseURL)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            final AllAPIs cr = retrofit.create(AllAPIs.class);
                            String text = status.getSelectedItem().toString();

                            Call<AssignClsWrkBean> call3 = cr.assign_cw(b.school_id, cId, sId, ssId, sChapter, sNote, text, TextUtils.join(",", studentId), "image", b.user_id , subName , chapName);

                            progress.setVisibility(View.VISIBLE);

                            Log.d("outputtt", String.valueOf(sId));
                            call3.enqueue(new Callback<AssignClsWrkBean>() {

                                @Override
                                public void onResponse(Call<AssignClsWrkBean> call3, Response<AssignClsWrkBean> response) {


                                    if (response.body().getStatus().equals("1")) {
                                        Toast.makeText(getContext(), "Class Work Added Successfully.", Toast.LENGTH_LONG).show();
                                        note.setText("");

                                        viewPager.setAdapter(adapter);

                                        viewPager.setCurrentItem(1);


                                    } else {
                                        Toast.makeText(getContext(), "Class work did not added Successfully!", Toast.LENGTH_LONG).show();
                                    }
                                    progress.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<AssignClsWrkBean> call3, Throwable throwable) {
                                    Log.d("yooooo", "sds");
                                    progress.setVisibility(View.GONE);

                                }
                            });


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


            return v;


        }

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        }


        @SuppressLint("ValidFragment")
        public static class DatePickerFragment extends DialogFragment
                implements DatePickerDialog.OnDateSetListener {


            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
//// Use the current date as the default date in the picker
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

//// Create a new instance of DatePickerDialog and return it
                return new DatePickerDialog(getActivity(), this, year, month, day);
            }

            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, day);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = sdf.format(c.getTime());


            }
        }

        @SuppressLint("ValidFragment")
        public class DatePickerFragment2 extends android.app.DialogFragment implements DatePickerDialog.OnDateSetListener {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int day) {
                String years = "" + year;
                String months = "" + (monthOfYear + 1);
                String days = "" + day;

                if (monthOfYear >= 0 && monthOfYear < 9) {
                    months = "0" + (monthOfYear + 1);
                }
                if (day > 0 && day < 10) {
                    days = "0" + day;

                }

                CharSequence strDate = null;
                Time chosenDate = new Time();
                chosenDate.set(day, monthOfYear, year);
                long dtDob = chosenDate.toMillis(true);
                strDate = DateFormat.format("dd-MMMM-yyyy", dtDob);
                date.setText(strDate);


            }

            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                //use the current date as the default date in the picker
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = null;
                datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

                return datePickerDialog;
            }


        }

    }


}