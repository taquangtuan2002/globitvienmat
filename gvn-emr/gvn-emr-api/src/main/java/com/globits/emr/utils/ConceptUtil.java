package com.globits.emr.utils;

public class ConceptUtil {
    public static final String CONCEPT_DATATYPE_NUMERIC = "numeric";
    public static final String CONCEPT_DATATYPE_CODED = "coded";

    public static final String CONCEPT_DATATYPE_TEXT = "text";
    public static final String CONCEPT_DATATYPE_DATE = "date";
    public static final String CONCEPT_DATATYPE_BOOLEAN = "boolean";

  
    public static final String CONCEPT_TYPE_LABTEST = "labtest";
    public static final String CONCEPT_TYPE_DIAGNOSTIC = "diagnostic";
    public static final String CONCEPT_TYPE_SYMPTOM = "symptom";
    public static final String CONCEPT_TYPE_ENCOUNTER = "encounter";
    public static final String CONCEPT_TYPE_MISC = "misc";
    public static final String CONCEPT_TYPE_DRUG = "drug";
    public static final String CONCEPT_TYPE_SPECIMEN = "specimen";

    public static final String CONCEPT_DHST_TL_NA = "vee-0003";
    public static final String CONCEPT_DHST_EYE_SIGHT = "vee-000301"; // Thị lực
    public static final String CONCEPT_DHST_VISION_NO_GLASSES_RIGHT = "vee-00030101"; // Thị lực không kính mắt phải
    public static final String CONCEPT_DHST_VISION_NO_GLASSES_LEFT = "vee-00030102"; // Thị lực không kính mắt trái
    public static final String CONCEPT_DHST_VISION_GLASSES_RIGHT = "vee-00030103"; // Thị lực có kính mắt phải
    public static final String CONCEPT_DHST_VISION_GLASSES_LEFT = "vee-00030104"; // Thị lực không kính mắt trái
    public static final String CONCEPT_DHST_VISION_GLASSES = "vee-00030105"; // Thị lực 2 mắt có kính
    public static final String CONCEPT_DHST_NOTE = "vee-00030106"; // Ghi chú
    public static final String CONCEPT_DHST_REFRACTION = "vee-000302"; // Khúc xạ
    public static final String CONCEPT_DHST_REFRACTION_RIGHT = "vee-00030201"; // Tật khúc xạ mắt phải
    public static final String CONCEPT_DHST_REFRACTION_LEFT = "vee-00030202"; // Tật khúc xạ mắt trái
    public static final String CONCEPT_DHST_CONTROL_GLASSES_RIGHT = "vee-00030203"; // Kính điều khiển mắt phải
    public static final String CONCEPT_DHST_CONTROL_GLASSES_LEFT = "vee-00030204"; // Kính điều khiển mắt trái
    public static final String CONCEPT_DHST_EYE_PRESSURE = "vee-000303"; // Nhãn áp
    public static final String CONCEPT_DHST_CACULATE_EYE_PRESSURE = "vee-00030301"; // Tính phí nhãn áp
    public static final String CONCEPT_DHST_CACULATE_NA = "vee-0003030101"; // Phí đo NA
    public static final String CONCEPT_DHST_CACULATE_MEASUREMENT  = "vee-0003030102"; // Phí đo nhỏ giãn
    public static final String CONCEPT_DHST_EYE_PRESSURE_RIGHT  = "vee-00030302"; // Nhãn áp mắt phải
    public static final String CONCEPT_DHST_EYE_PRESSURE_LEFT  = "vee-00030303"; // Nhãn áp mắt trái
    public static final String CONCEPT_DHST_COLOR_TEST  = "vee-000304"; // Color test
    public static final String CONCEPT_DHST_COLOR_TEST_RIGHT  = "vee-00030401"; // Color test mắt phải
    public static final String CONCEPT_DHST_COLOR_TEST_LEFT  = "vee-00030402"; // Color test mắt trái
    public static final String CONCEPT_DHST  = "vee-000305"; // Dấu hiệu sinh tồn
    public static final String CONCEPT_DHST_WEIGHT  = "vee-00030501"; // Dấu hiệu sinh tồn cân nặng

    public static final String CONCEPT_ANAMNESIS = "mh-0002";
    //Triệu chứng
    public static final String CONCEPT_SYMTOM="st-0";
    public static final String CONCEPT_SYMTOM_REASONS_FOR_VISITING="st-001";//lý do đến khám 
    public static final String CONCEPT_SYMTOM_SYMTOMS_TO_VISIT="st-002";//Triệu chứng đến khám
    public static final String CONCEPT_SYMTOM_SYMTOMCHILL="st-00201";//triệu chứng
    public static final String CONCEPT_SYMTOM_SUDDEN_lOSS_OF_VISION="st-00202";//Giảm thị lực đột ngột
    public static final String CONCEPT_SYMTOM_BlURRY_VISION_AT_NIGHT="st_00203";//Nhìn mờ vào ban đêm
    public static final String CONCEPT_SYMTOM_SPICES_EYES="st_00204";//Cay mắt 
    public static final String CONCEPT_SYMTOM_TRANSIENT_DECREASE_IN_VISION="st-00205";//Giảm thị lực thoáng qua
    public static final String CONCEPT_SYMTOM_SEE_TWO_PICTURES="st-00206";//nhìn thấy 2 hình
    public static final String CONCEPT_SYMTOM_WEEP="st-00207";//Chảy nước mắt 
    public static final String CONCEPT_SYMTOM_REFRESABLE_FROM_FROM="st-00208";//Giảm thị lực từ từ
    public static final String CONCEPT_SYMTOM_HEADER="st-00209";//Nhưcs đầu
    public static final String CONCEPT_SYMTOM_EYE_TWITCHING="st-00210";//Co giật mi
    public static final String CONCEPT_SYMTOM_GLUTTONY_EYES="st-00211";//Mắt đổ nghèn
    public static final String CONCEPT_SYMTOM_VOMITING_NAUSEA="st-00212";//Nôn , buồn nôn
    public static final String CONCEPT_SYMTOM_COMFY="st-00213";//Cộm xồm
    public static final String CONCEPT_SYMTOM_EYES_NOT_CLOSED="st-00214";//mắt nhắm ko kín
    public static final String CONCEPT_SYMTOM_FEAR_OF_LIGHT="st-00215";//Sợ ánh sáng
    public static final String CONCEPT_SYMTOM_FOREIGN_body_TRAUMA="00216";//Dị vật/chấn thương
    public static final String CONCEPT_SYMTOM_BLURED_EYES="00217";//Mờ mắt
    public static final String CONCEPT_SYMTOM_EYELIS_SWELLING="00218";//Sưng mi mắt
    public static final String CONCEPT_SYMTOM_MILD_EYE_PAIN="00219";//Đau nhức mắt nhẹ
    public static final String CONCEPT_SYMTOM_BLURRED_VISION_WHEN_LOOKING_UP_CLOSE="00220";//Mờ mắt khi nhìn gần
    public static final String CONCEPT_SYMTOM_MISTY="00221";//Sụp mi
    public static final String CONCEPT_SYMTOM_A_LOT_OF_EYE_PAIN="st-00222";//Đau nhưc mắt nhiều
    public static final String CONCEPT_SYMTOM_BLURRED_VISION_WHEN_LOOKING_AWAY="st-00223";//Mờ mắt khi nhìn xa
    public static final String CONCEPT_SYMTOM_SEE_THE_LIGHT="st-00224";//Thấy lóa sáng
    public static final String CONCEPT_SYMTOM_MODERATE_EYE_PAIN="st-00225";//Đau nhức mắt vừa
    public static final String CONCEPT_SYMTOM_EYESTRAIN="st-00226";//Mỏi mắt
    public static final String CONCEPT_SYMTOM_SEEING_THE_HALO_DISCOLORED="st-00227";//Thấy quầng tan sắc
    public static final String CONCEPT_SYMTOM_RED_EYES="st-00228";//đỏ mắt
    public static final String CONCEPT_SYMTOM_ITCHYS_EYES="st-00229";//Ngứa mắt
    public static final String CONCEPT_SYMTOM_SEE_THE_CURTAIN="st-00230";//Thấy rèm che 
    
    public static final String CONCEPT_PERSONAL_ANAMNESIS = "mh-000201";//tieu su ca nhan
    public static final String CONCEPT_FAMILY_ANAMNESIS = "mh-000202";//tieu su gia dinh
    public static final String CONCEPT_MEDICATIONS_USE_ANAMNESIS = "mh-000203";//thuoc da su dung
    public static final String CONCEPT_ALLERGY_ANAMNESIS = "mh-000204";//di ung
    public static final String CONCEPT__RISK_FACTOR_ANAMNESIS = "mh-000205";//yeu to nguy co
    public static final String CONCEPT_RISK_OF_SMOKE_ANAMNESIS = "mh-00020501";//hut thuoc la 
    public static final String CONCEPT_HIGH_BLOOD_SUGAR_ANAMNESIS = "mh-00020502";//duong huyet cao
    public static final String CONCEPT_HIGH_BLOOD_PRESSURE_ANAMNESIS = "mh-00020503";//cao huyet ap
    public static final String CONCEPT_OTHER_RISK_ANAMNESIS = "mh-00020504";//nguy co khac
    public static final String CONCEPT_NOTHING_ANAMNESIS = "mh-00020505";//khong co
    
}
