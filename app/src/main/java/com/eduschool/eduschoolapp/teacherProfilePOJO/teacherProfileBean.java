package com.eduschool.eduschoolapp.teacherProfilePOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TBX on 11/4/2017.
 */

public class teacherProfileBean {

    @SerializedName("teacher_id")
    @Expose
    private String teacherId;
    @SerializedName("teacher_name")
    @Expose
    private String teacherName;
    @SerializedName("profile_pic")
    @Expose
    private String profilePic;
    @SerializedName("work_info")
    @Expose
    private WorkInfo workInfo;
    @SerializedName("persional_info")
    @Expose
    private PersionalInfo persionalInfo;
    @SerializedName("education_detail")
    @Expose
    private List<EducationDetail> educationDetail = null;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public WorkInfo getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(WorkInfo workInfo) {
        this.workInfo = workInfo;
    }

    public PersionalInfo getPersionalInfo() {
        return persionalInfo;
    }

    public void setPersionalInfo(PersionalInfo persionalInfo) {
        this.persionalInfo = persionalInfo;
    }

    public List<EducationDetail> getEducationDetail() {
        return educationDetail;
    }

    public void setEducationDetail(List<EducationDetail> educationDetail) {
        this.educationDetail = educationDetail;
    }

}
