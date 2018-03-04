package com.eduschool.eduschoolapp;

import com.eduschool.eduschoolapp.AddAlbumPOJO.AddAlbumBean;
import com.eduschool.eduschoolapp.AssignHomeWrkPOJO.AssignHWbean;
import com.eduschool.eduschoolapp.AssingCwPOJO.AssignClsWrkBean;
import com.eduschool.eduschoolapp.AttendanceSummaryPOJO.AttendanceSummaryBean;
import com.eduschool.eduschoolapp.AttendncDatePOJO.AttendanceListBean;
import com.eduschool.eduschoolapp.BirthStuListPOJO.BirthStuListBean;
import com.eduschool.eduschoolapp.ChangePssPOJO.PasswrdChngebean;
import com.eduschool.eduschoolapp.ChapterListPOJO.ChapterListbean;
import com.eduschool.eduschoolapp.ClassListPOJO.ClassListbean;
import com.eduschool.eduschoolapp.ClassWrkListPOJO.ClassWrkListbean;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClasssworkList;
import com.eduschool.eduschoolapp.ClassWrkParent1POJO.ClasssworkListBean;
import com.eduschool.eduschoolapp.ClassWrkParentPOJO.ClasssubjectListBean;
import com.eduschool.eduschoolapp.ClssWrkPOJO.ClasswrkBean;
import com.eduschool.eduschoolapp.ClswrkDetailsPOJO.ClasssworkDetailBean;
import com.eduschool.eduschoolapp.DeleteAlbumPOJO.DeleteAlbumBean;
import com.eduschool.eduschoolapp.Gallery.addImageBean;
import com.eduschool.eduschoolapp.GalleryAlbumPOJO.AlbumListBean;
import com.eduschool.eduschoolapp.GalleryParentPOJO.GalleryListBean;
import com.eduschool.eduschoolapp.HolidayPOJO.HolidayListBean;
import com.eduschool.eduschoolapp.HomeWrkDetailsPOJO.HomeWrkDetailsBean;
import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeWorkListBean;
import com.eduschool.eduschoolapp.HomeWrkParent1POJO.HomeworkList;
import com.eduschool.eduschoolapp.HomeWrkUpdPOJO.HomeworkUpdateBean;
import com.eduschool.eduschoolapp.HomewrkPOJO.HomewrkBean;
import com.eduschool.eduschoolapp.HomewrkParentPOJO.ParentSubjectListBean;
import com.eduschool.eduschoolapp.HwListSearchPOJO.Homeworksrchbean;
import com.eduschool.eduschoolapp.LibraryBookPOJO.BookListBean;
import com.eduschool.eduschoolapp.LoginPOJO.Loginbean;
import com.eduschool.eduschoolapp.MarkAttendancePOJO.MarkAttendanceBean;
import com.eduschool.eduschoolapp.ParentAttendancePOJO.paraneAttendanceBean;
import com.eduschool.eduschoolapp.SectionListPOJO.SectionListbean;
import com.eduschool.eduschoolapp.SendBirthPOJO.SendBirthBean;
import com.eduschool.eduschoolapp.StudentListPOJO.StudentListbean;
import com.eduschool.eduschoolapp.SubjectListPOJO.SubjectListBean;
import com.eduschool.eduschoolapp.SurveyListPOJO.SurveyListBean;
import com.eduschool.eduschoolapp.SurveyListParentPOJO.SurveyListBeanParent;
import com.eduschool.eduschoolapp.SurveyQusPOJO.SurveyQusBean;
import com.eduschool.eduschoolapp.SyllabusPOJO.SyllabusListBean;
import com.eduschool.eduschoolapp.TodaysAttendanceSummaryPOJO.todayBean;
import com.eduschool.eduschoolapp.UpdateAlbumPOJO.UpdateAlbumBean;
import com.eduschool.eduschoolapp.UpdateAttndncPOJO.UpdateAttendncBean;
import com.eduschool.eduschoolapp.UpdateCwPOJO.UpdateCwBean;
import com.eduschool.eduschoolapp.UpdateHwPOJO.UpdateHwBean;
import com.eduschool.eduschoolapp.UpdateSurveyPOJO.UpdateSurveyBean;
import com.eduschool.eduschoolapp.ViewHomeWrkPOJO.HomewrkListbean;
import com.eduschool.eduschoolapp.academicTeacherPOJO.academicTeacherBean;
import com.eduschool.eduschoolapp.allEventPOJO.allEventBean;
import com.eduschool.eduschoolapp.allNotificationPOJO.allNotificationBean;
import com.eduschool.eduschoolapp.birthPOJO.birthBean;
import com.eduschool.eduschoolapp.borrowedBookBean.borrowedBookBean;
import com.eduschool.eduschoolapp.classResultPOJO.classResultBean;
import com.eduschool.eduschoolapp.communicationTeacherPOJO.communicationTeacherBean;
import com.eduschool.eduschoolapp.examTypePOJO.examTypeBean;
import com.eduschool.eduschoolapp.imageListPOJO.imageListBean;
import com.eduschool.eduschoolapp.notificationsPOJO.notificationsBean;
import com.eduschool.eduschoolapp.onlinePOJO.onlineBean;
import com.eduschool.eduschoolapp.onlineTestQuesPOJO.onlineTestQuesBean;
import com.eduschool.eduschoolapp.ownResultPOJO.ownResultBean;
import com.eduschool.eduschoolapp.parentAttendanceSummaryPOJO.parentAttendanceSummaryBean;
import com.eduschool.eduschoolapp.parentExamPOJO.parentExamBean;
import com.eduschool.eduschoolapp.parentFeesPOJO.parentFeesBean;
import com.eduschool.eduschoolapp.parentHomePOJO.parentHomeBean;
import com.eduschool.eduschoolapp.parentTimePOJO.parentTimeBean;
import com.eduschool.eduschoolapp.recReqPOJO.recReqBean;
import com.eduschool.eduschoolapp.reservedBookPOJO.reservedBookBean;
import com.eduschool.eduschoolapp.sectionPOJO.sectionBean;
import com.eduschool.eduschoolapp.sentCommunicationTeacher.sentCommunicationTeacherBean;
import com.eduschool.eduschoolapp.sentReqPOJO.sentReqBean;
import com.eduschool.eduschoolapp.studentLeavePOJO.studentLeaveBean;
import com.eduschool.eduschoolapp.studentPOJO.studentBean;
import com.eduschool.eduschoolapp.studentResultPOJO.studentResultBean;
import com.eduschool.eduschoolapp.syllabusmanagementPOJO.syllabusManagementBean;
import com.eduschool.eduschoolapp.takeSurveyBean.surveyAnsBean;
import com.eduschool.eduschoolapp.takeTestPOJO.takeTestBean;
import com.eduschool.eduschoolapp.teacherHomePOJO.teacherHomeBean;
import com.eduschool.eduschoolapp.teacherProfilePOJO.teacherProfileBean;
import com.eduschool.eduschoolapp.teacherSurveyPOJO.teacherSurveryBean;
import com.eduschool.eduschoolapp.teacherTimeTablePOJO.teacherTimeTableBean;
import com.eduschool.eduschoolapp.viewEventPOJO.viewEventBean;


import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

import static com.google.android.gms.internal.zzxo.hh;

/**
 * Created by user on 7/14/2017.
 */

public interface AllAPIs {

    @Multipart
    @POST("eduschool_app/teacher_login.php")
    Call<Loginbean> login
            (@Part("user") String user,
             @Part("pass") String pass);

    @Multipart
    @POST("eduschool_app/change_password.php")
    Call<PasswrdChngebean> chngrPass
            (@Part("school_id") String school_id,
             @Part("type") String type,
             @Part("newpass") String newpass,
             @Part("oldpass") String oldpass,
             @Part("userid") String userid);


    @Multipart
    @POST("eduschool_app/class_list.php")
    Call<ClassListbean> classList
            (@Part("school_id") String school_id);

    @Multipart
    @POST("eduschool_app/getsection_array.php")
    Call<sectionBean> getSections
            (@Part("school_id") String school_id ,
             @Part("class") String clas);

    @Multipart
    @POST("eduschool_app/getstu_array.php")
    Call<studentBean> getStudents
            (@Part("school_id") String school_id ,
             @Part("sectioncheck") String sections);



    @Multipart
    @POST("eduschool_app/section_list.php")
    Call<SectionListbean> sectionList
            (@Part("school_id") String school_id,
             @Part("class_id") String class_id );

    @Multipart
    @POST("eduschool_app/check_holiday.php")
    Call<checkHolidayBean> checkHoliday
            (@Part("school_id") String school_id,
             @Part("date") String date);


    @Multipart
    @POST("eduschool_app/subject_list.php")
    Call<SubjectListBean> subjectList
            (@Part("school_id") String school_id,
             @Part("class_id") String class_id,
             @Part("section_id") String section_id );

    @Multipart
    @POST("eduschool_app/chapter_list.php")
    Call<ChapterListbean> chapterList
            (@Part("school_id") String school_id,
             @Part("class_id") String class_id,
             @Part("subject_id") String subject_id );


    @Multipart
    @POST("eduschool_app/view_homeworklist.php")
    Call<HomewrkListbean> homwwrk_list
            (@Part("school_id") String school_id,
             @Part("teacher_id") String teacher_id );

    @Multipart
    @POST("eduschool_app/assign_homework.php")
    Call<AssignHWbean> assign_hw(
            @Part("school_id") String school_id ,
            @Part("date") String date ,
            @Part("class") String classid,
            @Part("section") String section,
            @Part("subject") String subject,
            @Part("chapter") String chapter,
            @Part("notes") String notes,
            @Part("duedate") String duedate,
            @Part("student") String student,
            @Part MultipartBody.Part file,
            @Part("teacher_id") String teacher_id,
            @Part("subjName") String subName,
            @Part("chapName") String chapName
    );


    @Multipart
    @POST("eduschool_app/assign_classwork.php")
    Call<AssignClsWrkBean> assign_cw
            (@Part("school_id") String school_id,
             @Part("class") String classid,
             @Part("section") String section,
             @Part("subject") String subject, @Part("chapter") String chapter,
             @Part("notes") String notes,
             @Part("classwork_status") String classwork_status,
             @Part("stulist") String stulist,
             @Part("classattach") String classattach,
             @Part("teacher_id") String teacher_id,
             @Part("subjName") String subName,
             @Part("chapName") String chapName,
             @Part("date") String date
            );

    @Multipart
    @POST("eduschool_app/student_list.php")
    Call<StudentListbean> student_list
            (@Part("school_id") String school_id,
             @Part("class_id") String class_id,
             @Part("section_id") String section_id );

    @Multipart
    @POST("eduschool_app/student_list.php")
    Call<studentLeaveBean> student_leave_list
            (@Part("school_id") String school_id,
             @Part("class_id") String class_id,
             @Part("section_id") String section_id,
             @Part("date") String date
             );

    @Multipart
    @POST("eduschool_app/homework_searchlist.php")
    Call<HomewrkListbean> homwwrk_list
            (@Part("school_id") String school_id,
             @Part("teacher_id") String teacher_id,
             @Part("class") String className,
             @Part("section") String section,
             @Part("subject") String subject );

    @Multipart
    @POST("eduschool_app/classlistsearch.php")
    Call<ClassWrkListbean> classwrk_list
            (@Part("school_id") String school_id,
             @Part("teacher_id") String teacher_id,
             @Part("class") String className,
             @Part("section") String section,
             @Part("subject") String subject );


    @Multipart
    @POST("eduschool_app/classworklist.php")
    Call<ClassWrkListbean> classwrk_list
            (@Part("school_id") String school_id,
             @Part("teacher_id") String teacher_id );


    @Multipart
    @POST("eduschool_app/homeworkbyid.php")
    Call<HomewrkBean> homewrk
            (@Part("school_id") String school_id,
             @Part("homework_id") String homework_id );

    @Multipart
    @POST("eduschool_app/album_list.php")
    Call<AlbumListBean> album_list
            (@Part("school_id") String school_id );



    @Multipart
    @POST("eduschool_app/delete_homeworkattach.php")
    Call<deleteFileBean> deleteHomeWorkAttach
            (@Part("image_id") String imageId);



    @Multipart
    @POST("eduschool_app/searchby_album.php")
    Call<AlbumListBean> album_list
            (@Part("school_id") String school_id,
             @Part("date") String date );

    @Multipart
    @POST("eduschool_app/create_album.php")
    Call<AddAlbumBean> add_album
            (@Part("album_name") String album_name,
             @Part("school_id") String school_id,
             @Part("userid") String userid );


    @Multipart
    @POST("eduschool_app/update_album.php")
    Call<UpdateAlbumBean> update_album
            (@Part("school_id") String school_id,
             @Part("album_id") String album_id,
             @Part("album_name") String album_name );


    @Multipart
    @POST("eduschool_app/album_deleteid.php")
    Call<DeleteAlbumBean> delete_album
            (@Part("school_id") String school_id,
             @Part("album_id") String album_id );

    @Multipart
    @POST("eduschool_app/classworkbyid.php")
    Call<ClasswrkBean> clss_wrk
            (@Part("school_id") String school_id,
             @Part("classwork_id") String classwork_id );


    @Multipart
    @POST("eduschool_app/update_homework.php")
    Call<UpdateHwBean> update_hw(
            @Part("homeworkid") String homeworkid,
            @Part("school_id") String school_id,
            @Part("teacher_id") String teacher_id,
            @Part("class") String classId,
            @Part("section") String section,
            @Part("subject") String subject,
            @Part("chapter") String chapter,
            @Part("notes")String notes,
            @Part("duedate") String duedate,
            @Part("stulist")String stulist,
            @Part MultipartBody.Part f0
    );

    @Multipart
    @POST("eduschool_app/update_classwork.php")
    Call<UpdateCwBean> update_cw
            (@Part("classworkid") String classworkid,
             @Part("school_id") String school_id,
             @Part("teacher_id") String teacher_id,
             @Part("class") String classId,
             @Part("section") String section,
             @Part("subject") String subject,
             @Part("chapter") String chapter,
             @Part("additionlnote")String additionlnote,
             @Part("classwork_status")String classwork_status,
             @Part("stulist")String stulist,
             @Part("classattach") String classattach);



    @Multipart
    @POST("eduschool_app/view_survey_teacher.php")
    Call<teacherSurveryBean> survey_list
            (@Part("school_id") String school_id,
             @Part("type") String type,
             @Part("userid") String userid );


    @Multipart
    @POST("eduschool_app/survey_questionbyid.php")
    Call<SurveyQusBean> survey_qus
            (@Part("survey_id") String survey_id,
             @Part("question_id") String question_id );


    @Multipart
    @POST("eduschool_app/homework_subject_list.php")
    Call<ParentSubjectListBean> sub_list
            (@Part("school_id") String school_id,
             @Part("class_id") String class_id,
             @Part("section_id") String section_id
                    , @Part("userid") String userid );



    @Multipart
    @POST("eduschool_app/homeworklist_subjectid.php")
    Call<HomeWorkListBean> home_wrk
            (@Part("school_id") String school_id,
             @Part("class_id") String class_id,
             @Part("section_id") String section_id,
             @Part("userid") String userid,
             @Part("subject_id") String subject_id );

    @Multipart
    @POST("eduschool_app/parenthomework_search.php")
    Call<HomeWorkListBean> home_wrk2
            (@Part("school_id") String school_id,
             @Part("class_id") String class_id,
             @Part("section_id") String section_id,
             @Part("userid") String userid,
             @Part("subject_id") String subject_id,
             @Part("fromdate") String fromdate  ,
             @Part("todate") String todate);


    @Multipart
    @POST("eduschool_app/homeworkby_id.php")
    Call<HomeWrkDetailsBean> hw_details
            (@Part("school_id") String school_id,
             @Part("userid") String userid,
             @Part("homework_id") String homework_id );


    @Multipart
    @POST("eduschool_app/changehomework_status.php")
    Call<HomeworkUpdateBean> hw_update
            (@Part("userid") String userid,
             @Part("homework_id") String homework_id );

    @Multipart
    @POST("eduschool_app/classwork_subject_list.php")
    Call<ClasssubjectListBean> class_subject
            (@Part("school_id") String school_id,
             @Part("class_id") String class_id,
             @Part("section_id") String section_id,
             @Part("userid") String userid );


    @Multipart
    @POST("eduschool_app/classworkbysubjectid.php")
    Call<ClasssworkListBean> claass_wrk
            (@Part("school_id") String school_id,
             @Part("class_id") String class_id,
             @Part("section_id") String section_id,
             @Part("userid") String userid,
             @Part("subject_id") String subject_id );


    @Multipart
    @POST("eduschool_app/filterclassworkbydate.php")
    Call<ClasssworkListBean> claass_wrk2
            (@Part("school_id") String school_id,
             @Part("class_id") String class_id,
             @Part("section_id") String section_id,
             @Part("userid") String userid,
             @Part("subject_id") String subject_id,
             @Part("fromdate") String from ,
             @Part("todate") String to);



    @Multipart
    @POST("eduschool_app/image_listforparent.php")
    Call<GalleryListBean> gallery_img
            (@Part("school_id") String school_id,
             @Part("album_id") String album_id,
             @Part("stu_id") String stu_id );


    @Multipart
    @POST("eduschool_app/new_take_question_teacher.php")
    Call<UpdateSurveyBean> update_qus
            (@Part("school_id") String school_id,
             @Part("type") String type,
             @Part("userid") String userid,
             @Part("survey_id") String survey_id,
             @Part("question_id") String question_id,
             @Part("answer") String answer );


    @Multipart
    @POST("eduschool_app/classworkby_id.php")
    Call<ClasssworkDetailBean> clss_wrk
            (@Part("school_id") String school_id,
             @Part("userid") String userid,
             @Part("classwork_id") String classwork_id );


    @Multipart
    @POST("eduschool_app/parent_survey_list.php")
    Call<SurveyListBeanParent> survey_list1
            (@Part("school_id") String school_id,
             @Part("type") String type,
             @Part("userid") String userid,
             @Part("class") String classname,
             @Part("section") String section );



    @Multipart
    @POST("eduschool_app/mark_attendance.php")
    Call<MarkAttendanceBean> mark_attendance(
            @Part("school_id") String school_id,
            @Part("class") String classname,
            @Part("section") String section,
            @Part("date") String date,
            @Part("userid") String userid,
            @Part("class_teacher") String class_teacher,
            @Part("month") String month,
            @Part("year") String year,
            @Part("day") String day,
            @Part("stu_id") String stu_id,
            @Part("attend") String attend
    );


    @Multipart
    @POST("eduschool_app/get_attendancedata.php")
    Call<AttendanceListBean> attnce_list
            (@Part("school_id") String school_id,
             @Part("date") String date,
             @Part("class") String classname,
             @Part("section") String section);


    @Multipart
    @POST("eduschool_app/update_markattendace.php")
    Call<UpdateAttendncBean> update_attndnc
            (@Part("attendace_id") String attendace_id,
             @Part("stu_id") String stu_id,
             @Part("attend") String attend );


    @Multipart
    @POST("eduschool_app/total_attendance_summary.php")
    Call<AttendanceSummaryBean> attendnc_summry
            (@Part("school_id") String school_id,
             @Part("userid") String userid,
             @Part("date") String date );


    @Multipart
    @POST("eduschool_app/holiday_list.php")
    Call<HolidayListBean> holiday_list
            (@Part("school_id") String school_id);

    @Multipart
    @POST("eduschool_app/view_today_birth_stu.php")
    Call<birthBean> stu_bithday
            (@Part("school_id") String school_id);

    @Multipart
    @POST("eduschool_app/view_today_birth_techer.php")
    Call<birthBean> teacher_bithday
            (@Part("school_id") String school_id);

    @Multipart
    @POST("eduschool_app/get_syllabus_data_examwise.php")
    Call<SyllabusListBean> syllabus_mng
            (@Part("school_id")String school_id,
             @Part("subject_id") String subject_id,
             @Part("class") String classname,
             @Part("section") String section);

    @Multipart
    @POST("eduschool_app/send_birth_card.php")
    Call<SendBirthBean> send_card(
            @Part("school_id") String school_id,
            @Part("from_type") String from_type,
            @Part("from_userid") String from_userid,
            @Part("to_type") String to_type,
            @Part("birth_card") String card,
            @Part("stu_id") String stu_id);


    @Multipart
    @POST("eduschool_app/get_library_book.php")
    Call<BookListBean> book_list(@Part("school_id") String school_id);

    @Multipart
    @POST("eduschool_app/search_book.php")
    Call<BookListBean> search_book(@Part("school_id") String school_id , @Part("keyword") String keyword);

    @Multipart
    @POST("eduschool_app/book_detailbyid.php")
    Call<BookListBean> book_details(@Part("school_id") String school_id,@Part("book_no_id") String book_no_id,@Part("book_statu_id") String book_statu_id);

    @Multipart
    @POST("eduschool_app/bookreserv_detailbyid.php")
    Call<reservedBookBean> getReservedBook(@Part("school_id") String school_id, @Part("book_no_id") String book_no_id, @Part("book_statu_id") String book_statu_id);

    @Multipart
    @POST("eduschool_app/bookborrow_detailbyid.php")
    Call<borrowedBookBean> getBorrowedBook(@Part("school_id") String school_id, @Part("book_no_id") String book_no_id, @Part("book_statu_id") String book_statu_id);

    @Multipart
    @POST("eduschool_app/parenttoday_attendance.php")
    Call<paraneAttendanceBean> getAttendance(@Part("school_id") String school_id, @Part("class") String classId, @Part("section") String section , @Part("current_month") String month , @Part("current_year") String year , @Part("userid") String userId);

    @Multipart
    @POST("eduschool_app/parant_todayattendance.php")
    Call<todayBean> getTodaysAttendance(@Part("school_id") String school_id, @Part("class") String classId, @Part("section") String section , @Part("date") String date , @Part("userid") String userId);

    @GET
    @Streaming
    Call<ResponseBody> getFile(@Url String url);

    @Multipart
    @POST("eduschool_app/list_exam_type.php")
    Call<examTypeBean> getExamTeacher(@Part("school_id") String school_id);


    @Multipart
    @POST("eduschool_app/parentlist_exam_type.php")
    Call<examTypeBean> getExamList(@Part("school_id") String school_id, @Part("class") String classId, @Part("section") String section);

    @Multipart
    @POST("eduschool_app/teacher_survey_answer.php")
    Call<surveyAnsBean> getAnswer(@Part("type") String type, @Part("userid") String userId, @Part("survey_id") String surveyId, @Part("question_id") String questionId);

    @Multipart
    @POST("eduschool_app/get_online_test.php")
    Call<onlineBean> getTestList(@Part("school_id") String schoolId, @Part("userid") String userId, @Part("class") String class_id, @Part("section") String section);

    @Multipart
    @POST("eduschool_app/parent_receved_request.php")
    Call<recReqBean> getRec(@Part("school_id") String schoolId, @Part("userid") String userId, @Part("class") String class_id, @Part("section") String section , @Part("type") String type);

    @Multipart
    @POST("eduschool_app/sent_parent_request.php")
    Call<sentReqBean> getSent(@Part("school_id") String schoolId, @Part("userid") String userId, @Part("class_id") String class_id, @Part("section_id") String section , @Part("type") String type);

    @Multipart
    @POST("eduschool_app/student_time_table.php")
    Call<parentTimeBean> getParentTimeTable(@Part("school_id") String schoolId, @Part("class") String class_id, @Part("section") String section , @Part("date") String date);


    @Multipart
    @POST("eduschool_app/image_list.php")
    Call<imageListBean> getImages(@Part("school_id") String schoolId, @Part("album_id") String album_id);

    @Multipart
    @POST("eduschool_app/add_gallery.php")
    Call<addImageBean> addImage(
            @Part("school_id") String schoolId,
            @Part("album_id") String album_id,
            @Part("type") String type,
            @Part("userid") String userId,
            @Part MultipartBody.Part file,
            @Part("class") String cls,
            @Part("section") String sec,
            @Part("student") String stu
    );

    @Multipart
    @POST("eduschool_app/book_reserved.php")
    Call<reserveBookBean> reserveBook(
            @Part("school_id") String schoolId,
            @Part("type") String type,
            @Part("userid") String userId,
            @Part("book_id") String bookId,
            @Part("book_no_id") String bookNoId
    );

    @Multipart
    @POST("eduschool_app/teacher_time_table.php")
    Call<teacherTimeTableBean> getTeacherTimeTable(
            @Part("school_id") String schoolId,
            @Part("tdate") String toDate,
            @Part("fdate") String fromDate,
            @Part("teacher_id") String teacherId
    );


    @Multipart
    @POST("eduschool_app/sent_comunication_teachr.php")
    Call<sentCommunicationTeacherBean> getTeacherCommunication(
            @Part("school_id") String schoolId,
            @Part("userid") String userId,
            @Part("type") String type
    );


    @Multipart
    @POST("eduschool_app/delete_gallryimage.php")
    Call<addImageBean> deleteImage(@Part("image_id") String imageId);

    @Multipart
    @POST("eduschool_app/create_eventby_teacher.php")
    Call<composeBean> compose(
            @Part("school_id") String schoolId,
            @Part("start_date") String startDate,
            @Part("end_date") String endDate,
            @Part("time") String time,
            @Part("hr") String hr,
            @Part("min") String min,
            @Part("type_event") String type,
            @Part("additional_detail") String desc,
            @Part("from_id") String from,
            @Part("from_type") String ftype,
            @Part("to_type") String to,
            @Part("class_id") String classId,
            @Part("section_id") String sectionId,
            @Part("important") String h,
            @Part MultipartBody.Part file
    );

    @Multipart
    @POST("eduschool_app/teacher_profile.php")
    Call<teacherProfileBean> getTeacherProfile(
            @Part("school_id") String schoolId,
            @Part("teacher_id") String userId
    );




    @Multipart
    @POST("eduschool_app/get_onlinequestion_byid.php")
    Call<onlineTestQuesBean> getOnlineQues(
            @Part("onlinetest_id") String id
    );


    @Multipart
    @POST("eduschool_app/take_test.php")
    Call<takeTestBean> takeTest(
            @Part("onlinetest_id") String onlineTestId,
            @Part("userid") String userid,
            @Part("ques_id") String quesId,
            @Part("ans_id") String ansId
    );


    @Multipart
    @POST("eduschool_app/teacher_leave_request.php")
    Call<teacherRequestBean> requestLeave(
            @Part("school_id") String schoolId,
            @Part("teacher_id") String teacherId,
            @Part("from_date") String from,
            @Part("to_date") String to,
            @Part("leave_type") String type
    );

    @Multipart
    @POST("eduschool_app/parent_exam_detail.php")
    Call<parentExamBean> getStudentResult(
            @Part("school_id") String schoolId,
            @Part("class") String clas,
            @Part("section") String sec,
            @Part("stu_id") String stuid,
            @Part("exam_id") String examid
    );

    @Multipart
    @POST("eduschool_app/teacher_syllabus_data.php")
    Call<academicTeacherBean> getTeacherAcademic(
            @Part("school_id") String schoolId,
            @Part("teacher_id") String userId
    );

    @Multipart
    @POST("eduschool_app/sllabus_databyclass.php")
    Call<academicTeacherBean> getAcademicBySubject(
            @Part("school_id") String schoolId,
            @Part("subject_id") String subId,
            @Part("class") String cls,
            @Part("section") String sec
    );


    @Multipart
    @POST("eduschool_app/view_eventtecher.php")
    Call<viewEventBean> getEvents(
            @Part("school_id") String schoolId,
            @Part("month_year") String monthYear,
            @Part("usertype") String type,
            @Part("userid") String userId
    );

    @Multipart
    @POST("eduschool_app/get_own_result.php")
    Call<ownResultBean> getOwnClassResult(
            @Part("school_id") String schoolId,
            @Part("class") String cls,
            @Part("section") String sec,
            @Part("exam_id") String examId
    );

    @Multipart
    @POST("eduschool_app/class_result.php")
    Call<classResultBean> getResultData(
            @Part("school_id") String schoolId,
            @Part("class") String cls,
            @Part("section") String sec,
            @Part("exam_id") String examId
    );

    @Multipart
    @POST("eduschool_app/all_event.php")
    Call<allEventBean> getAllEvents(
            @Part("school_id") String schoolId,
            @Part("usertype") String type,
            @Part("userid") String userId
    );


    @Multipart
    @POST("eduschool_app/recev_comunication_teachr.php")
    Call<communicationTeacherBean> getTeacherReceived(
            @Part("school_id") String schoolId,
            @Part("userid") String useRId,
            @Part("type") String type
    );

    @Multipart
    @POST("eduschool_app/sylabus_summery.php")
    Call<syllabusManagementBean> getSummary(
            @Part("school_id") String schoolId,
            @Part("class") String classs,
            @Part("section") String setion,
            @Part("subject") String subject
    );


    @Multipart
    @POST("eduschool_app/stu_prsnl_detail.php")
    Call<parentPersonalBean> getParentPersonal(
            @Part("school_id") String schoolId,
            @Part("userid") String userId
    );

    @Multipart
    @POST("eduschool_app/stu_child_info.php")
    Call<parenChildBean> getParentChild(
            @Part("school_id") String schoolId,
            @Part("userid") String userId
    );

    @Multipart
    @POST("eduschool_app/student_contact_dettail.php")
    Call<parentcontactBean> getParentContact(
            @Part("school_id") String schoolId,
            @Part("userid") String userId
    );

    @Multipart
    @POST("eduschool_app/child_attendance_summery.php")
    Call<parentAttendanceSummaryBean> getParentAttendance(
            @Part("school_id") String schoolId,
            @Part("stuid") String stuId,
            @Part("class") String classs,
            @Part("section_id") String sectionId
    );

    @Multipart
    @POST("eduschool_app/fees_detail.php")
    Call<parentFeesBean> getParentFees(
            @Part("school_id") String schoolId,
            @Part("userid") String stuId,
            @Part("class") String classs,
            @Part("section") String sectionId
    );

    @Multipart
    @POST("eduschool_app/unread_notifyteacher.php")
    Call<notificationsBean> getNotifications(
            @Part("school_id") String schoolId,
            @Part("teacher_id") String stuId
    );

    @Multipart
    @POST("eduschool_app/unread_notify.php")
    Call<allNotificationBean> getAllNotifications(
            @Part("school_id") String schoolId,
            @Part("stuid") String stuId
    );



    @Multipart
    @POST("eduschool_app/update_notify_status.php")
    Call<updateNotificationBean> updateParentNotifications(
            @Part("notify_id") String notifyId,
            @Part("userid") String userId,
            @Part("type") String type
    );

    @Multipart
    @POST("eduschool_app/update_notify_status.php")
    Call<updateNotificationBean> updateTeacherNotifications(
            @Part("notify_id") String notifyId,
            @Part("userid") String userId,
            @Part("type") String type
    );


    @Multipart
    @POST("eduschool_app/home_notification.php")
    Call<teacherHomeBean> getTeacherHome(
            @Part("school_id") String schoolId,
            @Part("teacher_id") String teacherId
    );


    @Multipart
    @POST("eduschool_app/parent_home_notification.php")
    Call<parentHomeBean> getParentHome(
            @Part("school_id") String schoolId,
            @Part("class") String classs,
            @Part("section") String sectionId
    );

    @Multipart
    @POST("eduschool_app/student_transport.php")
    Call<transportBean> getTransport(
            @Part("school_id") String schoolId,
            @Part("student_id") String studId,
            @Part("class_id") String classs,
            @Part("section_id") String sectionId
    );

    @Multipart
    @POST("eduschool_app/parent_create_request.php")
    Call<raiseREquestBean> raiseRequest(
            @Part("school_id") String schoolId,
            @Part("userid") String userId,
            @Part("type") String type,
            @Part("class") String classs,
            @Part("section") String section,
            @Part("request_type") String reqType,
            @Part("from_date") String from,
            @Part("to_date") String to,
            @Part("reason") String reason,
            @Part("total_day") String total,
            @Part("query") String query,
            @Part("type_exam") String typeExam
    );


}

