import React from "react";
import GlobitsTableNotPagination from "../../common/GlobitsTableNotPagination";
import { useStore } from "../../stores";
import { observer } from "mobx-react";
import { useHistory } from "react-router-dom";
import ConstantList from "../../appConfig";
import { Link } from "react-router-dom";
export default observer(function TotalTimeList() {
  const history = useHistory();
  const { dashboardStore } = useStore();

  const {  totalProjectTimeReport, roundHour } =
    dashboardStore;

  let columns = [
    {
      //title: "Số nhân viên",
      render: (rowData) => {
        return (
          <Link
            onClick={() => {
              history.push(
                `${ConstantList.ROOT_PATH}timesheet/list/${rowData.projectId}`
              );
            }}
          >
            {rowData.project ? (
              <div style={{ marginLeft: "16px" }}>
                <strong>Dự án: </strong>
                <strong>{rowData.project}</strong>
              </div>
            ) : (
              ""
            )}
            <div style={{ display: "flex", marginLeft: "16px" }}>
              <div style={{ width: "50%" }}>
                <span>Số nhân viên: </span>
                <span>{rowData.numberStaff ? rowData.numberStaff : 0}</span>
              </div>
              <div>
                <span>Tổng thời gian: </span>
                <span>
                  {rowData.totalHours ? roundHour(rowData.totalHours) : "0.0"}
                </span>
              </div>
            </div>
          </Link>
        );
      },
    },
  ];

  return (
    <GlobitsTableNotPagination
      //handleSelectList={handleSelectListTotalHour}
      data={totalProjectTimeReport}
      columns={columns}
      title={true}
    />
  );
});
