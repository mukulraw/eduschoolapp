package com.eduschool.eduschoolapp;

import com.eduschool.eduschoolapp.ChangePssPOJO.PasswrdChngebean;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.LoginPOJO.Loginbean;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;

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
}
