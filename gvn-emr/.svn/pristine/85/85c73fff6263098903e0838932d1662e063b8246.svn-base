import React from "react";
import GlobitsTableNoHeader from "../../common/GlobitsTableNoHeader";
import { useStore } from "../../stores";
import { observer } from "mobx-react";

export default observer(function TotalTimeList() {
    const { dashboardStore } = useStore();

    const {
        totalTimeReportList,
        totalPages,
        totalElements,
        rowsPerPage,
        page,
        handleChangePage,
        setRowsPerPage,
        roundHour
    } = dashboardStore;

    let columns = [
        
        {
            title: "Nhân viên",
            headerStyle : { fontSize: "16px", paddingLeft: "16px",},
            minWidth: "50%",
            render: (rowData) => {
                return (
                    < >
                        {rowData.staffName ? (
                            <div style={{marginLeft: "16px"}}>
                                <strong>{rowData.staffName}</strong>
                            </div>
                        ) : (
                            ""
                        )}
                    </>
                );
            },
        },
        {
            title: "Giờ làm việc",
            headerStyle : { fontSize: "16px", },
            render: (rowData) => {
                return <>{rowData.totalTime ? roundHour(rowData.totalTime) : "0.0"}</>;
            },
        },
    ];

    return (
    <GlobitsTableNoHeader
      //selection
      //handleSelectList={handleSelectListTotalHour}
      data={totalTimeReportList}
      columns={columns}
      totalPages={totalPages}
      handleChangePage={handleChangePage}
      setRowsPerPage={setRowsPerPage}
      pageSize={rowsPerPage}
      pageSizeOption={[1, 2, 3, 5, 10, 25]}
      totalElements={totalElements}
      page={page}
    />
    );
});
