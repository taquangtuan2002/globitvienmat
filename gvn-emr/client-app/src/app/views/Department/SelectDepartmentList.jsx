import React, { useEffect } from "react";
import { observer } from "mobx-react";
import GlobitsPagination from "../../common/GlobitsPagination";
import MaterialTable from "material-table";
import { useStore } from "../../stores";
import { useTranslation } from "react-i18next";
import { Radio } from "@material-ui/core";
import Config from "../../common/GlobitsConfigConst";

export default observer(function SelectDepartmentList(props) {
  const { departmentStore } = useStore();
  const { t } = useTranslation();

  const { selectedItem, handleSelectItem } = props;

  const {
    departmentList,
    totalPages,
    totalElements,
    rowsPerPage,
    page,
    handleChangePage,
    setRowsPerPage,
    // selectedParentDepartment,
  } = departmentStore;

  const onClickRow = (selectedRow) => {
    console.log(document.querySelector(`#radio${selectedRow.id}`));
    document.querySelector(`#radio${selectedRow.id}`).click();
  };

  let columns = [
    {
      title: t("general.popup.select"),
      render: (rowData) => (
        <Radio
          id={`radio${rowData.id}`}
          name="radSelected"
          value={rowData.id}
          checked={selectedItem?.id === rowData.id}
          onClick={(event) => handleSelectItem(event, rowData)}
        />
      ),
    },
    {
      title: t("department.code"),
      field: "code",
      ...Config.tableCellConfig,
    },
    { title: t("department.name"), field: "name", ...Config.tableCellConfig },
    {
      title: t("department.description"),
      field: "description",
      ...Config.tableCellConfig,
    },
  ];

  return (
    <>
      <MaterialTable
        data={departmentList}
        columns={columns}
        onRowClick={(evt, selectedRow) => onClickRow(selectedRow)}
        parentChildData={(row, rows) => {
          var list = rows.find((a) => a.id === row.parentId);
          return list;
        }}
        options={{
          selection: false,
          actionsColumnIndex: -1,
          paging: false,
          search: false,
          toolbar: false,
          maxBodyHeight: "300px",
          headerStyle: {
            backgroundColor: "#2a80c8",
            color: "#fff",
          },
          rowStyle: (rowData, index) => ({
            backgroundColor: index % 2 === 1 ? "rgb(237, 245, 251)" : "#FFF",
          }),
        }}
        onSelectionChange={(rows) => {
          this.data = rows;
        }}
      />
      <GlobitsPagination
        totalPages={totalPages}
        handleChangePage={handleChangePage}
        setRowsPerPage={setRowsPerPage}
        pageSize={rowsPerPage}
        pageSizeOption={[1, 2, 3, 5, 10, 25]}
        totalElements={totalElements}
        page={page}
      />
    </>
  );
});
