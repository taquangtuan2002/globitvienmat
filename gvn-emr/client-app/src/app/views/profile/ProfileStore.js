import { makeAutoObservable } from "mobx";
import { getCurrentStaff, uploadImage, getCurrentUser } from "./ProfileService";
import i18n from "i18n";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

export default class ProfileStore {
  currentStaff = {
    id: "",
    //tab1 start
    firstName: "",
    lastName: "",
    displayName: "",
    gender: "",
    birthDate: null,
    birthPlace: "",
    permanentResidence: "",
    currentResidence: "",
    accommodationToday: "",
    idNumber: "",
    idNumberIssueDate: null,
    idNumberIssueBy: "",
    nationality: null,
    ethnics: null,
    religion: null,
    email: "",
    phoneNumber: "",
    maritalStatus: "",

    /* new */

    administrativeunit: null,
    district: null,
    province: null,
    familyComeFrom: null,
    familyPriority: null,
    familyYourself: null,
    //tab1 end

    //tab2 start
    status: null,
    department: null,
    staffCode: null,
    civilServantType: null,
    civilServantCategory: null,
    grade: null,
    labourAgreementType: null,
    contractDate: null,
    recruitmentDate: null,
    professionalTitles: "",
    profession: null,
    highestPosition: "",
    dateOfReceivingPosition: null,
    positionDecisionNumber: "",
    currentWorkingStatus: null,
    startDate: null,
    jobTitle: "",
    allowanceCoefficient: "",
    dateOfReceivingAllowance: null,
    salaryLeve: "",
    salaryCoefficient: "",
    salaryStartDate: null,
    socialInsuranceNumber: "",

    //tab2 end

    //tab3 start
    personCertificate: [
      {
        certificate: null,
        issueDate: null,
        level: "",
        name: "",
      },
    ],

    ethnicLanguage: false,
    physicalEducationTeacher: false,

    studying: null,
    highSchoolEducation: "",

    qualification: "",
    specializedName: "",

    formsOfTraining: "",
    trainingPlaces: "",

    graduationYear: null,
    trainingCountry: "",

    academicRank: null,
    yearOfRecognitionAcademicRank: null,

    degree: null,
    yearOfRecognitionDegree: null,

    /* new */
    politicalTheoryLevel: null,
    stateManagementQualifications: null,
    educationalManagementQualifications: null,
    computerSkill: null,

    englishLevel: null,
    // graduationYear: null,

    englishCertificate: null,
    certificationScore: "",

    yearOfCertification: null,
    note: "",

    otherLanguage: null,
    otherLanguageLevel: null,

    conferred: null,
    yearOfConferred: null,

    //tab3 end

    //qua trinh chuc vu
    positions: [
      {
        decisionCode: null,
        decisionDate: null,
        fromDate: null,
        toDate: null,
        position: null,
        department: null,
        allowanceCoefficient: null,
        note: "",
        current: false,
        connectedAllowanceProcess: false,
        positionSelect: "1",
      },
    ],

    // quan he than nhan
    familyRelationships: [
      {
        fullName: "",
        profession: null,
        birthDate: null,
        familyRelationship: null,
        address: "",
        workingPlace: "",
      },
    ],
    // qua trinh dao tao
    educationHistory: [
      {
        startDate: null,
        endDate: null,

        actualGraduationYear: null,
        returnDate: null,

        educationalInstitution: null,
        country: null,

        decisionCode: null,
        decisionDate: null,

        fundingSource: null,

        extendDateByDecision: null,
        extendDecisionCode: null,
        extendDecisionDate: null,

        speciality: null,
        major: null,
        educationType: null,

        educationDegree: null,
        isConfirmation: false,

        basis: "",
        description: "",

        isCurrent: false,
        isCountedForSeniority: false,
        isExtended: false,
        notFinish: false,
      },
    ],

    // Hop dong
    agreements: [
      {
        signedDate: null,
        startDate: null,
        endDate: null,
        agreementStatus: null,
        labourAgreementType: null,
      },
    ],

    // Qua trinh dong BHXH
    stafInsuranceHistory: [
      {
        startDate: null,
        endDate: null,
        note: "",
        salaryCofficient: null,
        insuranceSalary: null,
        staffPercentage: null,
        orgPercentage: null,
        staffInsuranceAmount: null,
        orgInsuranceAmount: null,
      },
    ],

    // Qua trinh luong
    salaryHistory: [
      {
        coefficient: null,
        staffTypeCode: "",
        coefficientOverLevel: null,
        percentage: null,
        decisionDate: null,
        decisionCode: "",
        salaryIncrementType: null,
      },
    ],

    // Qua trinh cong tac nuoc ngoai
    overseasWorkHistory: [
      {
        startDate: null,
        endDate: null,
        country: null,
        companyName: "",
        decisionNumber: null,
        decisionDate: null,
        purpose: "",
      },
    ],

    // Qua trinh khen thuong
    rewardHistory: [
      { organizationName: "", rewardDate: null, rewardType: null },
    ],

    //Qua trinh thai san
    maternityHistory: [
      {
        startDate: null,
        endDate: null,
        birthNumber: null,
        note: "",
      },
    ],

    // Qua trinh buoi duong
    trainingHistory: [
      {
        startDate: null,
        endDate: null,
        trainingPlace: "",
        trainingCountry: null,
        certificate: null,
        trainingContent: "",
      },
    ],

    //Qua trinh cong tac
    workingHistory: [
      {
        employeeStatus: null,
        startDate: null,
        endDate: null,
        position: null,
        department: null,
        note: "",
        unpaidLeave: false,
      },
    ],

    //Qua trinh phu cap
    allowanceHistory: [
      {
        startDate: null,
        endDate: null,
        allowanceType: null,
        coefficient: null,
        note: "",
      },
    ],

    // Qua trinh phu cap tham nien nghe giao
    allowanceSeniorityHistory: [
      {
        startDate: null,
        quotaCode: null,
        percentReceived: null,
        note: "",
      },
    ],
  };
  currentUser = {};
  // currentStaff = {};

  constructor() {
    makeAutoObservable(this);
  }

  setLoadingInitial = (state) => {
    this.loadingInitial = state;
  };

  updatePageData = async () => {
    // console.log("Data:", data.data);
    let data = await getCurrentStaff();
    let data2 = await getCurrentUser();
    this.currentStaff = data.data;
    this.currentUser = data2.data;
    this.setLoadingInitial(false);
  };

  updatePageDataStaff = async () => {
    let data = await getCurrentStaff();
    this.currentStaff = data.data;
    this.setLoadingInitial(false);
  };

  uploadImage = async (file) => {
    try {
      if (file != null) {
        const formData = new FormData();
        formData.append("uploadfile", file);
        let newObj = {
          formData: formData,
          id: this.currentStaff.id,
        };
        await uploadImage(newObj);
      }
      toast.success(i18n.t("toast.add_success"));
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.add_fail"));
    }
  };

}
