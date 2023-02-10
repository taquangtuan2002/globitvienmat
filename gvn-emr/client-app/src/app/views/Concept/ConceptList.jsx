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

export default observer(function ConceptList() {
  const { conceptStore } = useStore();
  const { t } = useTranslation();

  const {
    conceptList,
    totalPages,
    totalElements,
    rowsPerPage,
    page,
    handleChangePage,
    setRowsPerPage,
    handleDelete,
    handleEditConcept,
    handleSelectListConcept,
  } = conceptStore;

  let columns = [
    {
      title: t("general.action"),
      minWidth: "100px",
      render: (rowData) => (
        <MaterialButton
          item={rowData}
          onSelect={(rowData, method) => {
            if (method === 0) {
              handleEditConcept(rowData.id);
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
      title: t("concept.code"),
      minWidth: "100px",
      field: "code",
      align: "left",
      width: "150",
    },
    {
      title: t("concept.name"),
      minWidth: "150px",
      field: "name",
      width: "150",
    },
    {
      title: t("concept.description"),
      minWidth: "100px",
      field: "description",
      width: "150",
    },
  ];
  return (
    <GlobitsTable
      selection
      data={conceptList}
      handleSelectList={handleSelectListConcept}
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
