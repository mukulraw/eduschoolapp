package com.eduschool.eduschoolapp;

import com.eduschool.eduschoolapp.AssignHomeWrkPOJO.AssignHWbean;
import com.eduschool.eduschoolapp.ChangePssPOJO.PasswrdChngebean;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterListbean;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassWrkListbean;
import com.eduschool.eduschoolapp.HomewrkPOJO.HomewrkBean;
import com.eduschool.eduschoolapp.HwListSearchPOJO.Homeworksrchbean;
import com.eduschool.eduschoolapp.LoginPOJO.Loginbean;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomewrkListbean;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by user on 7/14/2017.
 */

public interface AllAPIs {

    @Multipart
    @POST("TBX/eduschool/eduschool_app/teacher_login.php")
    Call<Loginbean> login(@Part("user") String user, @Part("pass") String pass);

    @Multipart
    @POST("TBX/eduschool/eduschool_app/change_password.php")
    Call<PasswrdChngebean> chngrPass(@Part("school_id") String school_id, @Part("type") String type, @Part("newpass") String newpass, @Part("oldpass") String oldpass, @Part("userid") String userid);


    @Multipart
    @POST("TBX/eduschool/eduschool_app/class_list.php")
    Call<ClassListbean> classList(@Part("school_id") String school_id);

    @Multipart
    @POST("TBX/eduschool/eduschool_app/section_list.php")
    Call<SectionListbean> sectionList(@Part("school_id") String school_id, @Part("class_id") String class_id );

    @Multipart
    @POST("TBX/eduschool/eduschool_app/subject_list.php")
    Call<SubjectListBean> subjectList(@Part("school_id") String school_id, @Part("class_id") String class_id );

    @Multipart
    @POST("TBX/eduschool/eduschool_app/chapter_list.php")
    Call<ChapterListbean> chapterList(@Part("school_id") String school_id, @Part("class_id") String class_id, @Part("subject_id") String subject_id );


    @Multipart
    @POST("TBX/eduschool/eduschool_app/view_homeworklist.php")
    Call<HomewrkListbean> homwwrk_list(@Part("school_id") String school_id, @Part("teacher_id") String teacher_id );

    @Multipart
    @POST("dental_avenue/app-api/update_doctorprofile_pic.php")
    Call<AssignHWbean> assign_hw(@Part("school_id") String school_id, @Part("class") String classid, @Part("section") String section, @Part("subject") String subject, @Part("chapter") String chapter, @Part("notes") String notes, @Part("duedate") String duedate, @Part("student") List<String> student, @Part MultipartBody.Part file, @Part("teacher_id") String teacher_id);

    @Multipart
    @POST("TBX/eduschool/eduschool_app/student_list.php")
    Call<StudentListbean> student_list(@Part("school_id") String school_id, @Part("class_id") String class_id, @Part("section_id") String section_id );

    @Multipart
    @POST("TBX/eduschool/eduschool_app/homework_searchlist.php")
    Call<HomewrkListbean> homwwrk_list(@Part("school_id") String school_id, @Part("teacher_id") String teacher_id, @Part("class") String className, @Part("section") String section, @Part("subject") String subject );


    @Multipart
    @POST("TBX/eduschool/eduschool_app/classworklist.php")
    Call<ClassWrkListbean> classwrk_list(@Part("school_id") String school_id, @Part("teacher_id") String teacher_id );


    @Multipart
    @POST("TBX/eduschool/eduschool_app/homeworkbyid.php")
    Call<HomewrkBean> homewrk(@Part("school_id") String school_id, @Part("homework_id") String homework_id );
}
