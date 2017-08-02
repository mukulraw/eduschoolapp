package com.eduschool.eduschoolapp.HomeWork;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.AssignHomeWrkPOJO.AssignHWbean;
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
import com.eduschool.eduschoolapp.Survey.Take_Survey;
import com.eduschool.eduschoolapp.User;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.eduschool.eduschoolapp.R.id.view;

/**
 * Created by user on 5/20/2017.
 */

public class FrgmntOne extends Fragment {
    Toolbar toolbar;
    Spinner className, sectionName, subjectName, chapter;
    AlertDialog.Builder alertDialog;
    View convertView;
    TextView date, due_date, upload;
    public String a;
    private String mCurrentPhotoPath;
    ProgressBar progress;
    String cId, sId, ssId;
    Button submit;
    EditText note;
    String sNote, sChapter;

    List<ClassList> list;
    public List<String> classlist, sectionlist, subjectlist, chapterlist, studentlist;
    public List<String> classId, sectionId, subjectId, chapterId, studentId;
    public List<SectionList> listSection;
    public List<SubjectList> listSubject;
    public List<StudentList> listStudent;
    public List<ChapterList> listChapter;

    private final int PICK_IMAGE_REQUEST = 2;

    boolean isFirst;


    public FrgmntOne() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.teacher_hw_frgmnt1, container, false);
        toolbar = (Toolbar) ((TeacherHome) getContext()).findViewById(R.id.tool_bar);


        className = (Spinner) v.findViewById(R.id.className);
        date = (TextView) v.findViewById(R.id.date);
        upload = (TextView) v.findViewById(R.id.upload);
        due_date = (TextView) v.findViewById(R.id.due_date);
        sectionName = (Spinner) v.findViewById(R.id.sectonName);
        subjectName = (Spinner) v.findViewById(R.id.subjectName);
        chapter = (Spinner) v.findViewById(R.id.chapter);
        submit = (Button) v.findViewById(R.id.submit);
        note = (EditText) v.findViewById(R.id.note);


        alertDialog = new AlertDialog.Builder(this.getActivity());
        convertView = (View) inflater.inflate(R.layout.custom, null);
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


        chapterlist = new ArrayList<>();
        listChapter = new ArrayList<>();
        chapterId = new ArrayList<>();

        studentlist = new ArrayList<>();
        listStudent = new ArrayList<>();
        studentId = new ArrayList<>();

        ListView lv = (ListView) convertView.findViewById(R.id.listView1);
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, classlist);
        lv.setAdapter(adapter1);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, PICK_IMAGE_REQUEST);

            }
        });

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
                subjectName.setAdapter(adp2);


                ArrayAdapter<String> adp3 = new ArrayAdapter<String>(getContext(),
                        android.R.layout.simple_list_item_1, chapterlist);

                adp3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                chapter.setAdapter(adp3);

                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);

            }
        });


        className.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {


                Call<SectionListbean> call2 = cr.sectionList(b.school_id, classId.get(i));
                //a = classId.get(i - 1);

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
                        a = sectionId.get(0);
                        cId = classId.get(i);


                        ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, sectionlist);

                        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        sectionName.setAdapter(adp);

                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<SectionListbean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }

                });


                Call<SubjectListBean> call1 = cr.subjectList(b.school_id, classId.get(i));

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

                        ssId = subjectId.get(i);

                        ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, subjectlist);

                        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        subjectName.setAdapter(adp);
                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<SubjectListBean> call, Throwable throwable) {
                        progress.setVisibility(View.GONE);

                    }
                });

//                    Log.d("subjectId", String.valueOf(subjectId.get(0)));
                Call<ChapterListbean> call = cr.chapterList(b.school_id, "8", "7");


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

                        sChapter = chapterId.get(i);

                        ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                android.R.layout.simple_list_item_1, chapterlist);

                        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        chapter.setAdapter(adp);
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


            /*@Override
            public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {

                Call<SubjectListBean> call1 = cr.subjectList(b.school_id, classId.get(i));

                progress.setVisibility(View.VISIBLE);

                call1.enqueue(new Callback<SubjectListBean>() {

                    @Override
                    public void onResponse(Call<SubjectListBean> call, Response<SubjectListBean> response) {

                        for (int i = 0; i < response.body().getSubjectList().size(); i++) {


                        }

                        progress.setVisibility(View.GONE);

                        ssId = subjectId.get(i);
                        Log.d("subject", ssId);

                        Call<ChapterListbean> call1 = cr.chapterList(b.school_id, cId, ssId);


                        progress.setVisibility(View.VISIBLE);


                        call1.enqueue(new Callback<ChapterListbean>() {

                            @Override
                            public void onResponse(Call<ChapterListbean> call1, Response<ChapterListbean> response) {


                                listChapter = response.body().getChapterList();
                                chapterlist.clear();
                                chapterlist.clear();
                                for (int i = 0; i < response.body().getChapterList().size(); i++) {

                                    chapterlist.add(response.body().getChapterList().get(i).getChapterName());

                                    chapterId.add(response.body().getChapterList().get(i).getChapterName());


                                }


                                ArrayAdapter<String> adp = new ArrayAdapter<String>(getContext(),
                                        android.R.layout.simple_list_item_1, chapterlist);

                                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                chapter.setAdapter(adp);
                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<ChapterListbean> call, Throwable throwable) {
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
*/
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

        due_date.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                android.app.DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getFragmentManager(), "df");


            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setCancelable(false);
                dialog.setMessage("Are you sure you want to add Home Work ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {


                        sNote = note.getText().toString().trim();
                        Log.d("note", sNote);

                        File file = new File(mCurrentPhotoPath);
                        final User b = (User) getActivity().getApplicationContext();

                        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

                        MultipartBody.Part body = MultipartBody.Part.createFormData("attach", file.getName(), reqFile);


                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        final AllAPIs cr = retrofit.create(AllAPIs.class);


                        Call<AssignHWbean> call3 = cr.assign_hw(b.school_id, cId, sId, ssId, sChapter, sNote, due_date.getText().toString(), studentId, body, b.user_id);

                        progress.setVisibility(View.VISIBLE);

                        Log.d("outputtt", String.valueOf(sNote));
                        call3.enqueue(new Callback<AssignHWbean>() {

                            @Override
                            public void onResponse(Call<AssignHWbean> call3, Response<AssignHWbean> response) {


                             /*  if (response.body().getStatus().equals("1")){
                                   Toast.makeText(getActivity(), "Home Work Added successfully", Toast.LENGTH_SHORT).show();
                               }
                               else {
                                   Toast.makeText(getActivity(), "Some Error occurred. Try Again!", Toast.LENGTH_SHORT).show();
                               }*/

                                Log.d("yooooo", response.body().getStatus());


                            }

                            @Override
                            public void onFailure(Call<AssignHWbean> call3, Throwable throwable) {
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


        return v;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && null != data) {
            Uri selectedImg = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImg,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //  String picturePath = cursor.getString(columnIndex);
            mCurrentPhotoPath = cursor.getString(columnIndex);


            cursor.close();
        }
    }


    /*public void updatepic() {

        File file = new File(mCurrentPhotoPath);
        final User b = (User) getActivity().getApplicationContext();

        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("attach", file.getName(), reqFile);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.baseURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllAPIs cr = retrofit.create(AllAPIs.class);


        Call<AssignHWbean> call = cr.update_doctorprofile_pic(b.userId, body, "doctor");

        call.enqueue(new Callback<AssignHWbean>() {
            @Override
            public void onResponse(Call<AssignHWbean> call, Response<AssignHWbean> response) {


                progress.setVisibility(View.GONE);
            }


            @Override
            public void onFailure(Call<AssignHWbean> call, Throwable throwable) {

                progress.setVisibility(View.GONE);
            }
        });
    }*/


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


    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends android.app.DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int day) {
           /* String years = "" + year;
            String months = "" + (monthOfYear + 1);
            String days = "" + day;
            if (monthOfYear >= 0 && monthOfYear < 9) {
                months = "0" + (monthOfYear + 1);
            }
            if (day > 0 && day < 10) {
                days = "0" + day;

            }
            due_date.setText(days + "/" + months + "/" + years);*/

            CharSequence strDate = null;
            Time chosenDate = new Time();
            chosenDate.set(day, monthOfYear, year);
            long dtDob = chosenDate.toMillis(true);
            strDate = DateFormat.format("dd-MMMM-yyyy", dtDob);
            due_date.setText(strDate);
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

