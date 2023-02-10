import { observer } from "mobx-react";
import React, { useEffect } from "react";
import { useStore } from "../../stores";
import { Grid, Button } from "@material-ui/core";
import GlobitsBreadcrumb from "../../common/GlobitsBreadcrumb";
import DeleteIcon from "@material-ui/icons/Delete";
import AddIcon from "@material-ui/icons/Add";
import NoteIcon from "@material-ui/icons/Note";
import { useTranslation } from "react-i18next";
import DepartmentFilters from "./DepartmentFilters";
import DepartmentList from "./DepartmentList";
import DepartmentCreateEditPopup from "./DepartmentCreateEditPopup";
import GlobitsConfirmationDialog from "../../common/GlobitsConfirmationDialog";
import { useTheme } from "@material-ui/core/styles";
import useMediaQuery from "@material-ui/core/useMediaQuery";
import ImportExcelDialogDepartment from "./ImportExcelDialogDepartment";

export default observer(function ColorIndex() {
  const { departmentStore } = useStore();
  const { t } = useTranslation();

  const {
    updatePageData,
    handleEditDepartment,
    shouldOpenEditorDialog,
    shouldOpenConfirmationDialog,
    shouldOpenConfirmationDeleteListDialog,
    handleClose,
    handleConfirmDelete,
    handleDeleteList,
    handleConfirmDeleteList,
    selectedDepartmentList,
    shouldOpenImportExcelDialog,
    importExcel,
  } = departmentStore;

  useEffect(() => {
    updatePageData();
  }, [updatePageData]);

  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down("sm"));
  const isExtraSmall = useMediaQuery(theme.breakpoints.down("xs"));

  return (
    <div className="content-index">
      <div className="index-breadcrumb">
        <GlobitsBreadcrumb routeSegments={[{ name: t("department.title") }]} />
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
                  handleEditDepartment();
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

              <ImportExcelDialogDepartment
                t={t}
                handleClose={handleClose}
                open={shouldOpenImportExcelDialog}
              />

              {selectedDepartmentList.length > 0 && (
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
              <DepartmentFilters />
            </Grid>
          </>
        )}

        {isExtraSmall && (
          <>
            <Grid item lg={6} md={6} sm={4} xs={4}>
              <Button
                className="btn btn-info d-inline-flex"
                startIcon={<AddIcon />}
                variant="contained"
                onClick={() => {
                  handleEditDepartment();
                }}
                fullWidth
              ></Button>
            </Grid>

            <Grid item sm={4} xs={4}>
              <Button
                className="btn btn-danger d-inline-flex"
                startIcon={<NoteIcon />}
                variant="contained"
                onClick={importExcel}
                fullWidth
              ></Button>
            </Grid>
            <ImportExcelDialogDepartment
              t={t}
              handleClose={handleClose}
              open={shouldOpenImportExcelDialog}
            />
            <Grid item sm={4} xs={4}>
              {selectedDepartmentList.length > 0 && (
                <Button
                  className="btn btn-warning d-inline-flex"
                  variant="contained"
                  startIcon={<DeleteIcon />}
                  onClick={() => {
                    handleDeleteList();
                  }}
                  fullWidth
                ></Button>
              )}
            </Grid>
            <Grid item lg={6} md={6} sm={12} xs={12}>
              <DepartmentFilters />
            </Grid>
          </>
        )}

        <DepartmentCreateEditPopup open={shouldOpenEditorDialog} />

        <GlobitsConfirmationDialog
          open={shouldOpenConfirmationDialog}
          onConfirmDialogClose={handleClose}
          onYesClick={handleConfirmDelete}
          title={t("confirm_dialog.delete.title")}
          text={t("confirm_dialog.delete.text")}
          agree={t("confirm_dialog.delete.agree")}
          cancel={t("confirm_dialog.delete.cancel")}
        />

        <GlobitsConfirmationDialog
          open={shouldOpenConfirmationDeleteListDialog}
          onConfirmDialogClose={handleClose}
          onYesClick={handleConfirmDeleteList}
          title={t("confirm_dialog.delete_list.title")}
          text={t("confirm_dialog.delete_list.text")}
          agree={t("confirm_dialog.delete_list.agree")}
          cancel={t("confirm_dialog.delete_list.cancel")}
        />

        <Grid item xs={12}>
          <DepartmentList />
        </Grid>
      </Grid>
    </div>
  );
});
