package com.wd.common.bean;

/**
 * Time:  2019-12-12
 * Author:  杨世博
 * Description:
 */
public class FindDoctorListBean {
    //"badNum": 0,
    //            "doctorId": 153,
    //            "doctorName": "像风一样",
    //            "imagePic": "http://172.17.8.100/images/health/doctor/system_image_pic/system_image7.jpg",
    //            "inauguralHospital": "清华大学附属医院",
    //            "jobTitle": "主治医师",
    //            "praise": "0.00%",
    //            "praiseNum": 0,
    //            "serverNum": 0,
    //            "servicePrice": 500
    public int badNum; //差评数
    public int doctorId; //医生id
    public String doctorName; //医生姓名
    public String imagePic; //形象照
    public String inauguralHospital; //就职医院
    public String jobTitle; //	职称
    public String praise; //好评率
    public int praiseNum; //好评数
    public int serverNum; //服务患者数
    public int servicePrice; //	咨询价格（H币）
}
