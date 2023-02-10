const ListMaritalStatus = [
  { value: 0, name: "Độc thân" }, //Single
  { value: 1, name: "Đính hôn" }, //Engaged
  { value: 2, name: "Đã kết hôn" }, //Married
  { value: 3, name: "Ly thân" }, //Separated
  { value: 4, name: "Đã ly hôn" }, //Divorced
];
const ListGender = [
  { id: "M", name: "Nam" },
  { id: "F", name: "Nữ" },
  { id: "U", name: "Không rõ" },
];
const ListSortItem = [
  { value: 3, name: "Năm" },
  { value: 2, name: "Tháng" },
  { value: 1, name: "Tuần" },
];

const ListMonth = [
  { value: 1, name: "Tháng 1" },
  { value: 2, name: "Tháng 2" },
  { value: 3, name: "Tháng 3" },
  { value: 4, name: "Tháng 4" },
  { value: 5, name: "Tháng 5" },
  { value: 6, name: "Tháng 6" },
  { value: 7, name: "Tháng 7" },
  { value: 8, name: "Tháng 8" },
  { value: 9, name: "Tháng 9" },
  { value: 10, name: "Tháng 10" },
  { value: 11, name: "Tháng 11" },
  { value: 12, name: "Tháng 12" },
];

const ListFourWeek = [
  { value: 1, name: "Tuần 1" },
  { value: 2, name: "Tuần 2" },
  { value: 3, name: "Tuần 3" },
  { value: 4, name: "Tuần 4" },
];

const ListFiveWeek = [
  { value: 1, name: "Tuần 1" },
  { value: 2, name: "Tuần 2" },
  { value: 3, name: "Tuần 3" },
  { value: 4, name: "Tuần 4" },
  { value: 5, name: "Tuần 5" },
];

const ListYear = [{ value: 2022, name: "2022" }];

const ListFamilyRelationship = [
  { value: 0, name: "Vợ chồng" },
  { value: 1, name: "Con" },
  { value: 2, name: "Bố mẹ" },
  { value: 3, name: "Anh chị em" },
  { value: 4, name: "Ông bà" },
  { value: 5, name: "Cháu" },
];
const ListHighSchoolEducation = [
  { value: 1, name: "1/12" },
  { value: 2, name: "2/12" },
  { value: 3, name: "3/12" },
  { value: 4, name: "4/12" },
  { value: 5, name: "5/12" },
  { value: 6, name: "6/12" },
  { value: 7, name: "7/12" },
  { value: 8, name: "8/12" },
  { value: 9, name: "9/12" },
  { value: 10, name: "10/12" },
  { value: 11, name: "11/12" },
  { value: 12, name: "12/12" },
  { value: 13, name: "Cấp I" },
  { value: 14, name: "Tốt nghiệp THCS/BTCS" },
  { value: 15, name: "Tốt nghiệp THPT/BTTH" },
];
const ListCivilServantType = [
  { value: 0, name: "Công chức hợp đồng" },
  { value: 1, name: "Công chức được tuyển dụng" },
];
const ListPosition = [
  { value: "1", name: "Chức vụ chính" },
  { value: "2", name: "Chức vụ kiêm nhiệm" },
];

const ListStatus = [
  { value: "-1", name: "Nghỉ" },
  { value: "0", name: "Đi làm" },
  { value: "1", name: "Làm online" },
  {
    value: "2",
    name: "Đi công tác",
  },
];

const ListApproveStatus = [
  { id: 1, name: "Chưa phê duyệt" },
  { id: 2, name: "Đã phê duyệt" },
];
const Priority = [
  { id: 1, name: "Thấp" },
  { id: 2, name: "Trung bình" },
  { id: 3, name: "Cao" },
  { id: 4, name: "Cấp bách" },
];

const ListFamilyComeFrom = [
  { id: 1, name: "Cán bộ" },
  { id: 2, name: "Công chức NN" },
  { id: 3, name: "Công chức(chế độ cũ)" },
  { id: 4, name: "Công nhân" },
  { id: 5, name: "Nông dân" },
  { id: 6, name: "Ngư dân" },
  { id: 7, name: "Quân nhân" },
  { id: 8, name: "Quân nhân(chế độ cũ)" },
  { id: 9, name: "Tiểu chủ" },
  { id: 10, name: "Tiểu thương" },
  { id: 11, name: "Thợ thủ công" },
];

const ListFamilyPriority = [
  { id: 1, name: "Anh hùng Lao động" },
  { id: 2, name: "Anh hùng LLVT" },
  { id: 3, name: "Bà mẹ VN anh hùng" },
  { id: 4, name: "BB có thương tật đặc biệt" },
  { id: 5, name: "Bệnh binh" },
  { id: 6, name: "GĐ có người bị địch bắt tù đày" },
  { id: 7, name: "GĐ liệt sĩ" },
  { id: 8, name: "Gia đình có công với CM" },
  { id: 9, name: "Gia đình thương binh" },
  { id: 10, name: "Lão thành CM" },
  { id: 11, name: "Người hưởng CS như T.binh" },
  { id: 12, name: "Quân nhân bị bệnh nghề nghiệp" },
  { id: 13, name: "TB có thương tật đặc biệt" },
];

const ListPriorityYourself = [
  { id: 1, name: "Anh hùng Lao động" },
  { id: 2, name: "Anh hùng LLVT" },
  { id: 3, name: "BB 1/4" },
  { id: 4, name: "BB 2/4" },
  { id: 5, name: "BB 3/4" },
  { id: 6, name: "BB 4/4" },
  { id: 7, name: "BB hạng 1 có thương tật đặc biệt" },
  { id: 8, name: "Bị địch bắt tù đày" },
  { id: 9, name: "Con thương/bệnh binh" },
  { id: 10, name: "Hạng khác" },
  { id: 11, name: "Người có công với cách mạng" },
  { id: 12, name: "Người hưởng CS như thương binh" },
  { id: 13, name: "Người tham gia kháng chiến" },
  { id: 14, name: "Quân nhân bị bệnh nghề nghiệp" },
  { id: 15, name: "Quân nhân bị tai nạn lao động" },
  { id: 16, name: "TB 1/4" },
  { id: 17, name: "TB 2/4" },
  { id: 18, name: "TB 3/4" },
  { id: 19, name: "TB 4/4" },
  { id: 20, name: "TB hạng 1 có thương tật đặc biệt" },
  { id: 21, name: "Thân nhân liệt sỹ" },
];

const AdminitractiveLevel = [
  { value: 1, name: "Cấp Tỉnh/Thành phố" },
  { value: 2, name: "Cấp Quận/Huyện" },
  { value: 3, name: "Cấp Xã/Phường" },
];

const ListLanguage=[
 {id: 1	, name: "Tiếng Anh" },
 {id: 2	, name: "Tiếng Trung Quốc (Quan Thoại)" },
 {id: 3	, name: "Tiếng Hindi" },
 {id: 4	, name: "Tiếng Tây Ban Nha" },
 {id: 5	, name: "Tiếng Pháp" },
 {id: 6	, name: "Tiếng Ả Rập (Chuẩn)" },
 {id: 7	, name: "Tiếng Bengal" },
 {id: 8	, name: "Tiếng Nga" },
 {id: 9	, name: "Tiếng Bồ Đào Nha" },
  {id:10	, name: "Tiếng Indonesia" },
  {id:11	, name: "Tiếng Urdu" },
  {id:12	, name: "Tiếng Đức" },
  {id:13	, name: "Tiếng Nhật" },
  {id:14	, name: "Tiếng Swahili" },
  {id:15	, name: "Tiếng Marathi" },
  {id:16	, name: "Tiếng Telugu" },
  {id:17	, name: "Tiếng Thổ Nhĩ Kì" },
  {id:18	, name: "Tiếng Trung Quốc (Quảng Đông)" },
  {id:19	, name: "Tiếng Tamil" },
  {id:20	, name: "Tiếng Punjab (Tây)" },
  {id:21	, name: "Tiếng Trung Quốc (Ngô)" },
  {id:22	, name: "Tiếng Hàn" },
  {id:23	, name: "Tiếng Việt" },
  {id:24	, name: "Tiếng Hausa" },
  {id:25	, name: "Tiếng Java" },
  {id:26	, name: "Tiếng Ả Rập (Ai Cập)" },
  {id:27	, name: "Tiếng Italia" },
  {id:28	, name: "Tiếng Gujarat" },
  {id:29	, name: "Tiếng Thái" },
  {id:30	, name: "Tiếng Amhara" },
]

const REGEX_SPECIAL_CHARACTERS = /^[^<>{}^$`~]*$/;

const hrmFileFolder = "D:/Working/GLOBITS/ProjectV3/Hr-v3/Data/";

const Admin = "ROLE_ADMIN";
const HRManager = "HR_MANAGER";
const User = "HR_USER";
module.exports = Object.freeze({
  //ROOT_PATH : "/egret/",
  ListGender: ListGender,
  ListMaritalStatus: ListMaritalStatus,
  ListFamilyRelationship: ListFamilyRelationship,
  ListCivilServantType: ListCivilServantType,
  ListHighSchoolEducation: ListHighSchoolEducation,

  hrmFileFolder: hrmFileFolder,
  ListApproveStatus: ListApproveStatus,
  ListPosition: ListPosition,
  ListSortItem: ListSortItem,
  ListMonth: ListMonth,
  ListFourWeek: ListFourWeek,
  ListFiveWeek: ListFiveWeek,
  ListYear: ListYear,
  ListFamilyComeFrom: ListFamilyComeFrom,
  ListFamilyPriority: ListFamilyPriority,
  ListPriorityYourself: ListPriorityYourself,
  ListStatus: ListStatus,
  ListLanguage: ListLanguage,
  Priority: Priority,
  Admin: Admin,
  HRManager: HRManager,
  User: User,
  AdminitractiveLevel: AdminitractiveLevel,
  REGEX_SPECIAL_CHARACTERS: REGEX_SPECIAL_CHARACTERS,
});
