import React from "react";
import GlobitsTable from "../../common/GlobitsTable";
import { useStore } from "../../stores";
import { useTranslation } from "react-i18next";
import { IconButton, Icon } from "@material-ui/core";
import { observer } from "mobx-react";

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

export default observer(function ConceptAttributeTypeList() {
  const { conceptAttributeTypeStore } = useStore();
  const { t } = useTranslation();

  const {
    conceptAttributeTypeList,
    totalPages,
    totalElements,
    rowsPerPage,
    page,
    handleChangePage,
    setRowsPerPage,
    handleDelete,
    handleEditConceptAttributeType,
    handleSelectListConceptAttributeType,
  } = conceptAttributeTypeStore;

  let columns = [
    {
      title: t("general.action"),
      minWidth: "100px",
      render: (rowData) => (
        <MaterialButton
          item={rowData}
          onSelect={(rowData, method) => {
            if (method === 0) {
              handleEditConceptAttributeType(rowData.id);
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
      title: t("conceptAttributeType.code"),
      minWidth: "100px",
      field: "code",
      align: "left",
      width: "150",
    },
    {
      title: t("conceptAttributeType.name"),
      minWidth: "150px",
      field: "name",
      width: "150",
    },
    {
      title: t("conceptAttributeType.description"),
      minWidth: "100px",
      field: "description",
      width: "150",
    },
  ];
  return (
    <GlobitsTable
      selection
      data={conceptAttributeTypeList}
      handleSelectList={handleSelectListConceptAttributeType}
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
