package com.eduschool.eduschoolapp.Communication;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.TextUtils;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.eduschool.eduschoolapp.AllAPIs;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.R;
import com.eduschool.eduschoolapp.RaiseRequest.RaiseRequestActivity;
import com.eduschool.eduschoolapp.User;
import com.eduschool.eduschoolapp.composeBean;
import com.eduschool.eduschoolapp.sectionPOJO.sectionBean;
import com.eduschool.eduschoolapp.studentPOJO.studentBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.apptik.widget.multiselectspinner.BaseMultiSelectSpinner;
import io.apptik.widget.multiselectspinner.MultiSelectSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ComposeMessage extends AppCompatActivity {
    Toolbar toolbar;

    TextView sendTo;
    Spinner subject;
    EditText desc;
    CheckBox check;
    Button attach;
    RecyclerView grid;
    GridLayoutManager manager;
    AttachAdapter adapter;
    List<String> list;
    private final int PICK_IMAGE_REQUEST = 2;
    String type;
    JSONObject classObject , sectionObject;
    String sectionNames;
    TextView start , end , time;
    ProgressBar progress;

    Button send , discard;

    List<String> checkedClasses = new ArrayList<>();
    List<String> checkedSections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_message);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitle("Compose Message");
        toolbar.setTitleTextColor(0xFFFFFFFF);
        toolbar.setNavigationIcon(R.drawable.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list = new ArrayList<>();
        sectionObject = new JSONObject();
        classObject = new JSONObject();

        checkedSections = new ArrayList<>();

        sendTo = (TextView)findViewById(R.id.send_to);
        subject = (Spinner)findViewById(R.id.subject);
        desc = (EditText) findViewById(R.id.desc);
        check = (CheckBox)findViewById(R.id.check);
        attach = (Button)findViewById(R.id.attach);
        grid = (RecyclerView)findViewById(R.id.grid);
        send = (Button)findViewById(R.id.send);
        discard = (Button)findViewById(R.id.discard);
        manager = new GridLayoutManager(this , 1);
        adapter = new AttachAdapter(this , list);

        progress = (ProgressBar)findViewById(R.id.progress);

        start = (TextView)findViewById(R.id.start);
        end = (TextView)findViewById(R.id.end);
        time = (TextView)findViewById(R.id.time);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);


        discard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                Toast.makeText(ComposeMessage.this , "Message Discarded" , Toast.LENGTH_SHORT).show();

            }
        });


        final List<String> dd = new ArrayList<>();

        dd.add("PTM");
        dd.add("Collect Report Card");

        ArrayAdapter<String> adapter = new ArrayAdapter <String>(ComposeMessage.this, android.R.layout.simple_list_item_1, dd);

        subject.setAdapter(adapter);

        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                type = dd.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datepicker");
            }
        });

        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment2();
                newFragment.show(getFragmentManager(), "datepicker");
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timepicker");
            }
        });


        sendTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(ComposeMessage.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.compose_popup);

                dialog.show();

                final MultiSelectSpinner cls = (MultiSelectSpinner)dialog.findViewById(R.id.classs);
                final MultiSelectSpinner sec = (MultiSelectSpinner)dialog.findViewById(R.id.section);
                final ProgressBar progress = (ProgressBar)dialog.findViewById(R.id.progress);

                Button submit = (Button)dialog.findViewById(R.id.submit);



                final List<String> clasNames = new ArrayList<>();
                final List<String> clasIds = new ArrayList<>();

                final List<String> secNames = new ArrayList<>();
                final List<String> secIds = new ArrayList<>();

                final List<String> stuNames = new ArrayList<>();
                final List<String> stuIds = new ArrayList<>();

                final User b = (User) getApplicationContext();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.baseURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllAPIs cr = retrofit.create(AllAPIs.class);

                final Call<ClassListbean> call = cr.classList(b.school_id);
                progress.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<ClassListbean>() {
                    @Override
                    public void onResponse(Call<ClassListbean> call, Response<ClassListbean> response) {


                        for (int i = 0; i < response.body().getClassList().size(); i++)
                        {

                            clasNames.add(response.body().getClassList().get(i).getClassName());
                            clasIds.add(response.body().getClassList().get(i).getClassId());

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter <String>(ComposeMessage.this, android.R.layout.simple_list_item_multiple_choice, clasNames);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                        cls.setListAdapter(adapter);


                        progress.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<ClassListbean> call, Throwable throwable) {

                        progress.setVisibility(View.GONE);

                    }
                });


                cls.setListener(new BaseMultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] booleans) {

                        List<String> checkedClasses = new ArrayList<>();

                        for (int i = 0 ; i < booleans.length ; i++)
                        {
                            if (booleans[i])
                            {
                                checkedClasses.add(clasIds.get(i));
                            }

                        }



                        final User b = (User) getApplicationContext();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);

                        progress.setVisibility(View.VISIBLE);

                        Log.d("checked" , TextUtils.join(",", checkedClasses));

                        JSONArray array = new JSONArray();

                        for (int i = 0 ; i < checkedClasses.size() ; i++)
                        {

                            try {
                                JSONObject object = new JSONObject();
                                object.put("Id" , checkedClasses.get(i));
                                array.put(object);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            classObject = new JSONObject();
                            classObject.put("class_id" , array);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Call<sectionBean> call1 = cr.getSections(b.school_id , "," + TextUtils.join(",", checkedClasses));

                        call1.enqueue(new Callback<sectionBean>() {
                            @Override
                            public void onResponse(Call<sectionBean> call, Response<sectionBean> response) {

                                Log.d("response" , "entered");

                                for (int i = 0 ; i < response.body().getSectionList().size() ; i++)
                                {
                                    secNames.add(response.body().getSectionList().get(i).getForClassname() + " " + response.body().getSectionList().get(i).getSectionName());
                                    secIds.add(response.body().getSectionList().get(i).getSectionId());
                                }

                                ArrayAdapter<String> adapter = new ArrayAdapter <String>(ComposeMessage.this, android.R.layout.simple_list_item_multiple_choice, secNames);

                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                                sec.setListAdapter(adapter);

                                progress.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<sectionBean> call, Throwable t) {
                                progress.setVisibility(View.GONE);
                                Log.d("response" , t.toString());
                            }
                        });




                    }
                });


                sec.setListener(new BaseMultiSelectSpinner.MultiSpinnerListener() {
                    @Override
                    public void onItemsSelected(boolean[] booleans) {


                        checkedSections.clear();
                        List<String> checkedSecIds = new ArrayList<>();

                        for (int i = 0 ; i < booleans.length ; i++)
                        {
                            if (booleans[i])
                            {
                                checkedSections.add(secIds.get(i));
                                checkedSecIds.add(secNames.get(i));
                            }

                        }

                        JSONArray array = new JSONArray();

                        for (int i = 0 ; i < checkedSections.size() ; i++)
                        {

                            try {
                                JSONObject object = new JSONObject();
                                object.put("Id" , checkedSections.get(i));
                                array.put(object);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        try {
                            sectionObject = new JSONObject();
                            sectionObject.put("section_id" , array);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Log.d("checked" , TextUtils.join(",", checkedSections));


                        sectionNames = TextUtils.join(",", checkedSecIds);

                        final User b = (User) getApplicationContext();
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.baseURL)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllAPIs cr = retrofit.create(AllAPIs.class);

                        progress.setVisibility(View.VISIBLE);

                        Call<studentBean> call2 = cr.getStudents(b.school_id , "," + TextUtils.join(",", checkedSections));

                        call2.enqueue(new Callback<studentBean>() {
                            @Override
                            public void onResponse(Call<studentBean> call, Response<studentBean> response) {


                                for (int i = 0 ; i < response.body().getStudentList().size() ; i++)
                                {
                                    stuNames.add(response.body().getStudentList().get(i).getStuName());
                                    stuIds.add(response.body().getStudentList().get(i).getStudentId());
                                }




                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<studentBean> call, Throwable t) {
                                progress.setVisibility(View.GONE);
                            }
                        });



                    }
                });




                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sendTo.setText(sectionNames);
                        dialog.dismiss();

                    }
                });

            }
        });



        attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);

            }
        });





        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String s = sectionObject.toString();
                final String sd = start.getText().toString();
                final String ed = end.getText().toString();
                final String t = time.getText().toString();
                final String de = desc.getText().toString();

                if (checkedSections.size() > 0)
                {

                    if (sd.length() > 0)
                    {

                        if (ed.length()> 0)
                        {

                            if (t.length() > 0 )
                            {

                                if (de.length() > 0)
                                {
                                    final Dialog dialog = new Dialog(ComposeMessage.this);
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.setCancelable(true);
                                    dialog.setContentView(R.layout.confirm_send_dialog);
                                    dialog.show();

                                    TextView c = (TextView)dialog.findViewById(R.id.confirm);
                                    final TextView d = (TextView)dialog.findViewById(R.id.discard);
                                    final ProgressBar progressBar = (ProgressBar)dialog.findViewById(R.id.progress);

                                    d.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            finish();
                                            Toast.makeText(ComposeMessage.this , "Message Discarded" , Toast.LENGTH_SHORT).show();

                                        }
                                    });

                                    c.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            progressBar.setVisibility(View.VISIBLE);

                                            User u = (User) getApplicationContext();


                                            Retrofit retrofit = new Retrofit.Builder()
                                                    .baseUrl(u.baseURL)
                                                    .addConverterFactory(ScalarsConverterFactory.create())
                                                    .addConverterFactory(GsonConverterFactory.create())
                                                    .build();

                                            AllAPIs cr = retrofit.create(AllAPIs.class);

                                            Log.d("sd" , sd);
                                            Log.d("ed" , ed);


                                            Call<composeBean> call = cr.compose(
                                                    u.school_id,
                                                    sd,
                                                    ed,
                                                    t,
                                                    "",
                                                    "",
                                                    type,
                                                    de,
                                                    u.user_id,
                                                    "Teacher",
                                                    "Parent",
                                                    "," + TextUtils.join("," ,checkedClasses),
                                                    "," + TextUtils.join(",", checkedSections)
                                            );

                                            call.enqueue(new Callback<composeBean>() {
                                                @Override
                                                public void onResponse(Call<composeBean> call, Response<composeBean> response) {

                                                    //Toast.makeText(ComposeMessage.this , "Message sent successfully" , Toast.LENGTH_SHORT).show();

                                                    Dialog d1 = new Dialog(ComposeMessage.this);
                                                    d1.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                                    d1.setCancelable(true);
                                                    d1.setContentView(R.layout.compose_success_popup);
                                                    d1.show();



                                                    d1.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                                        @Override
                                                        public void onDismiss(DialogInterface dialog) {
                                                            finish();
                                                        }
                                                    });


                                                    dialog.dismiss();
                                                    progressBar.setVisibility(View.GONE);

                                                }

                                                @Override
                                                public void onFailure(Call<composeBean> call, Throwable t) {
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            });

                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(ComposeMessage.this , "Please Enter a Description" , Toast.LENGTH_SHORT).show();

                                }









                            }
                            else
                            {
                                Toast.makeText(ComposeMessage.this , "Please Choose a Time" , Toast.LENGTH_SHORT).show();
                            }


                        }
                        else
                        {
                            Toast.makeText(ComposeMessage.this , "Please Choose an End Date" , Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(ComposeMessage.this , "Please Choose a Start Date" , Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(ComposeMessage.this , "Please select Classes and Sections to send" , Toast.LENGTH_SHORT).show();
                }



















            }
        });







    }

    @SuppressLint("ValidFragment")
    public class DatePickerFragment2 extends DialogFragment implements DatePickerDialog.OnDateSetListener {
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
            strDate = DateFormat.format("dd-MMM-yyyy", dtDob);
            end.setText(strDate);

            //end.setText(days + "/" + months + "/" + years);
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //use the current date as the default date in the picker
            Calendar c = Calendar.getInstance();
            @SuppressLint("WrongConstant") int year = c.get(Calendar.YEAR);
            @SuppressLint("WrongConstant") int month = c.get(Calendar.MONTH);
            @SuppressLint("WrongConstant") int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = null;
            datePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);

            return datePickerDialog;
        }

    }




    @SuppressLint("ValidFragment")
    public class TimePickerFragment extends DialogFragment implements TimePicker.OnTimeChangedListener, TimePickerDialog.OnTimeSetListener {


        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            //use the current date as the default date in the picker
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            TimePickerDialog datePickerDialog = null;
            datePickerDialog = new TimePickerDialog(ComposeMessage.this, this, year, month, false);

            return datePickerDialog;
        }

        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
            time.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));
        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            time.setText(String.valueOf(hourOfDay) + ":" + String.valueOf(minute));

        }
    }




    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
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
            strDate = DateFormat.format("dd-MMM-yyyy", dtDob);
            start.setText(strDate);

            //start.setText(days + "/" + months + "/" + years);


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



    public class AttachAdapter extends RecyclerView.Adapter<AttachAdapter.ViewHolder>
    {

        List<String> list = new ArrayList<>();
        Context context;

        public AttachAdapter(Context context , List<String> list)
        {
            this.context = context;
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.attach_list_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            String item = list.get(position);

            holder.name.setText(item);

            holder.cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    removeItem(position);

                }
            });

        }

        public List<String> getFiles()
        {
            return this.list;
        }

        public void addItem(String item)
        {
            list.add(item);
            notifyItemInserted(list.size()-1);
        }

        public void removeItem(int position)
        {
            list.remove(position);
            notifyItemRemoved(position);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView name;
            ImageButton cross;

            public ViewHolder(View itemView) {
                super(itemView);

                name = (TextView)itemView.findViewById(R.id.name);
                cross = (ImageButton)itemView.findViewById(R.id.remove);

            }
        }

    }



    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {


        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {


            Uri selectedImageUri = data.getData();


            String mCurrentPhotoPath = getPath(getApplicationContext(), selectedImageUri);

            //list.add(mCurrentPhotoPath);
            adapter.addItem(mCurrentPhotoPath);


        }





    }

    private static String getPath(final Context context, final Uri uri) {
        final boolean isKitKatOrAbove = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isKitKatOrAbove && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/edu_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[]{
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}
