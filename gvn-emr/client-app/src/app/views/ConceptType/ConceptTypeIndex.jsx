import { observer } from "mobx-react";
import React, { useEffect } from "react";
import { useStore } from "../../stores";
import { Grid, Button } from "@material-ui/core";
import GlobitsBreadcrumb from "../../common/GlobitsBreadcrumb";
import DeleteIcon from "@material-ui/icons/Delete";
import AddIcon from "@material-ui/icons/Add";
import { useTranslation } from "react-i18next";
import GlobitsConfirmationDialog from "../../common/GlobitsConfirmationDialog";
import { useTheme } from "@material-ui/core/styles";
import useMediaQuery from "@material-ui/core/useMediaQuery";
import NoteIcon from "@material-ui/icons/Note";
import { IconButton, Icon } from "@material-ui/core";
import GlobitsTable from "../../common/GlobitsTable";
import ConceptTypeForm from "./ConceptTypeForm";
import GlobitsSearchInput from "app/common/GlobitsSearchInput";

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

export default observer(function ConceptTypeIndex() {
  const { countryStore, conceptTypeStore } = useStore();
  const { t } = useTranslation();
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down("sm"));
  const isExtraSmall = useMediaQuery(theme.breakpoints.down("xs"));

  const { selectedCountryList, handleDeleteList, importExcel, } = countryStore;

  const { getListConceptType, listCocneptType, totalElements, totalPages, handleOpenPopupForm, pageIndex, pageSize, handleChangePageIndex, handleChangePageSize,
    handleChangeKeyWord, openPopupConfirmDetele, handleDeleteConceptType, handleClosePopup, openPopupConfirmDelete } = conceptTypeStore;

  const {
    handleSelectListCountry,
  } = countryStore;

  useEffect(() => {
    getListConceptType();
  }, []);

  let columns = [
    {
      title: t("general.action"),
      minWidth: "100px",
      render: (rowData) => (
        <MaterialButton
          item={rowData}
          onSelect={(rowData, method) => {
            if (method === 0) {
              handleOpenPopupForm(rowData);
            } else if (method === 1) {
              openPopupConfirmDetele(rowData.id);
            } else {
              alert("Call Selected Here:" + rowData.id);
            }
          }}
        />
      ),
    },
    {
      title: 'Mã Concept Type',
      field: "code",
      align: "left",
      minWidth: "100px",
      width: "150",
    },
    {
      title: "Tên Concept Type",
      field: "name",
      minWidth: "150px",
      width: "150",
    },
    {
      title: "Mô tả",
      field: "description",
      minWidth: "100px",
      width: "150",
    },
  ];

  return (
    <div className="content-index">
      <div className="index-breadcrumb">
        <GlobitsBreadcrumb routeSegments={[{ name: 'Concept Type' }]} />
      </div>
      <Grid className="index-card" container spacing={2}>
        {!isExtraSmall && (
          <>
            <Grid item lg={6} md={6} sm={4} xs={4}>
              <Button
                className="mb-16 mr-16 btn btn-info d-inline-flex"
                startIcon={<AddIcon />}
                variant="contained"
                onClick={() => {
                  handleOpenPopupForm();
                }}
              >
                {!isMobile && t("general.button.add")}
              </Button>
              <Button
                className="mb-16 mr-16 btn btn-danger d-inline-flex"
                startIcon={<NoteIcon />}
                variant="contained"
                onClick={importExcel}
              >
                {!isMobile && t("general.button.importExcel")}
              </Button>


              {selectedCountryList.length > 0 && (
                <Button
                  className="mb-16 mr-36 btn btn-warning d-inline-flex"
                  variant="contained"
                  startIcon={<DeleteIcon />}
                  onClick={() => {
                    handleDeleteList();
                  }}
                >
                  {!isMobile && t("general.button.delete")}
                </Button>
              )}
            </Grid>

            <Grid item lg={6} md={6} sm={8} xs={8}>
              <GlobitsSearchInput search={handleChangeKeyWord} />
            </Grid>
          </>
        )}

        {
          isExtraSmall && (
            <>
              <Grid item sm={4} xs={4}>
                <Button
                  className="btn btn-info d-inline-flex"
                  startIcon={<AddIcon />}
                  variant="contained"
                  onClick={() => handleOpenPopupForm()}
                  fullWidth
                >
                  {!isMobile && t("general.button.add")}
                </Button>
              </Grid>

              <Grid item sm={4} xs={4}>
                <Button
                  className="btn btn-danger d-inline-flex"
                  startIcon={<NoteIcon />}
                  variant="contained"
                  onClick={importExcel}
                  fullWidth
                >
                  {!isMobile && t("general.button.importExcel")}
                </Button>
              </Grid>

              <Grid item sm={4} xs={4}>
                {selectedCountryList.length > 0 && (
                  <Button
                    className="btn btn-warning d-inline-flex"
                    variant="contained"
                    startIcon={<DeleteIcon />}
                    onClick={() => {
                      handleDeleteList();
                    }}
                    fullWidth
                  >
                    {!isMobile && t("general.button.delete")}
                  </Button>
                )}
              </Grid>
              <Grid item sm={12} xs={12}>
                <GlobitsSearchInput search={handleChangeKeyWord} />
              </Grid>
            </>
          )
        }

        <ConceptTypeForm />

        <GlobitsConfirmationDialog
          open={openPopupConfirmDelete}
          onConfirmDialogClose={handleClosePopup}
          onYesClick={handleDeleteConceptType}
          title={t("confirm_dialog.delete.title")}
          text={t("confirm_dialog.delete.text")}
          agree={t("confirm_dialog.delete.agree")}
          cancel={t("confirm_dialog.delete.cancel")}
        />

        <Grid item xs={12}>
          <GlobitsTable
            selection
            data={listCocneptType}
            handleSelectList={handleSelectListCountry}
            columns={columns}
            totalPages={totalPages}
            handleChangePage={handleChangePageIndex}
            setRowsPerPage={handleChangePageSize}
            pageSize={pageSize}
            pageSizeOption={[1, 2, 3, 5, 10, 25]}
            totalElements={totalElements}
            page={pageIndex}
          />
        </Grid>
      </Grid>
    </div>
  );
});
