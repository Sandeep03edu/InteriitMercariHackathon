package com.techmeet.common.Utils;

public class Constants {
    public static final String MOBILE_NUMBER = "MobileNumber";
    public static final String PREFIXED_MOBILE_NUMBER = "PrefixedMobileNumber";
    public static final String VERIFICATION_ID = "VerificationId";
    public static final String USER_DETAILS = "UserDetails";

    public static final String NUMBER_PATTERN_REGEX = "[0-9]+";

    public static final int PICK_IMAGES_CODE=101;
    public static final int READ_EXTERNAL_STORAGE=213;
    public static final int CAMERA_REQUEST = 102;

    // Registration constants
    public static final int TYPE_LOGIN = 1;
    public static final int TYPE_REGISTER = 2;

    // Hospital Constants
    public static final String HOSPITAL_TYPE = "HospitalType";
    public static final int HOSPITAL_ADMIN = 101;
    public static final int HOSPITAL_DOCTOR = 102;
    public static final String HOSPITAL_ID = "hId";
    public static final String DOCTOR_ID = "dId";
    // Hospital Roles Constants
    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_DOCTOR = "Doctor";

    // Appointments
    public static final String APPOINTMENT = "Appointment";

    // Gender Constants
    public static final String GENDER_MALE = "Male";
    public static final String GENDER_FEMALE = "Female";
    public static final String GENDER_OTHER = "Other";


    // Crop ImageView Constants
    public static final String IMAGES = "Images";
    public static final String IMAGES_BUNDLE = "ImagesBundle";
    public static final String DISABLE_ASPECT_CROP = "Disable";

    public static final int EXTERNAL_STORAGE_PERMISSION = 11;
    public static final int IMAGE_REQUEST_CODE = 12;
    public static final int CROP_IMAGE_REQUEST_CODE = 13;

    //APi
    private static final String BASE_API_URI="http://134.209.149.17:8000/";
}