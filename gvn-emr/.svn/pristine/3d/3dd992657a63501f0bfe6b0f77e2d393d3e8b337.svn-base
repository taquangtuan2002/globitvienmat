import React from "react";
import { observer } from "mobx-react";
import GlobitsTable from "../../common/GlobitsTable";
import { useStore } from "../../stores";
import { useTranslation } from "react-i18next";
import { IconButton, Icon } from "@material-ui/core";
import Config from "../../common/GlobitsConfigConst";

function MaterialButton(props) {
  const { item } = props;
  return (
    <div>
      <IconButton size="small" onClick={() => props.onSelect(item, 0)}>
        <Icon fontSize="small" color="primary">
          edit
        </Icon>
      </IconButton>
      <IconButton size="small" onClick={() => props.onSelect(item, 1)}>
        <Icon fontSize="small" color="secondary">
          delete
        </Icon>
      </IconButton>
    </div>
  );
}

export default observer(function DepartmaentList() {
  const { departmentStore } = useStore();
  const { t } = useTranslation();

  const {
    departmentList,
    totalPages,
    totalElements,
    rowsPerPage,
    page,
    handleChangePage,
    setRowsPerPage,
    handleDelete,
    handleEditDepartment,
    handleSelectListDepartment,
  } = departmentStore;

  let columns = [
    {
      title: t("general.action"),
      minWidth:"100px",
      ...Config.tableCellConfig,
      render: (rowData) => (
        <MaterialButton
          item={rowData}
          onSelect={(rowData, method) => {
            if (method === 0) {
              handleEditDepartment(rowData.id);
            } else if (method === 1) {
              handleDelete(rowData.id);
            } else {
              alert("Call Selected Here:" + rowData.id);
            }
          }}
        />
      ),
    },
    {
      title: t("department.code"),
      minWidth:"150px",
      field: "code",
      ...Config.tableCellConfig,
    },
    { title: t("department.name"),minWidth:"200px", field: "name", ...Config.tableCellConfig },
    {
      title: t("department.description"),
      minWidth:"250px",
      field: "description",
      ...Config.tableCellConfig,
    },
  ];
  return (
    <GlobitsTable
      selection
      handleSelectList={handleSelectListDepartment}
      data={departmentList}
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
