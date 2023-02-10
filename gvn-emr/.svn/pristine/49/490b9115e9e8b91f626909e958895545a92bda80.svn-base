import { observer } from "mobx-react";
import React, { useEffect } from "react";
import { useStore } from "../../stores";
import { Grid, Button } from "@material-ui/core";
import GlobitsBreadcrumb from "../../common/GlobitsBreadcrumb";
import DeleteIcon from "@material-ui/icons/Delete";
import AddIcon from "@material-ui/icons/Add";
import { useTranslation } from "react-i18next";
import ConceptAttributeTypeList from "./ConceptAttributeTypeList";
import ConceptAttributeTypeCreateEditPopup from "./ConceptAttributeTypeCreateEditPopup";
import GlobitsConfirmationDialog from "../../common/GlobitsConfirmationDialog";
import { useTheme } from "@material-ui/core/styles";
import useMediaQuery from "@material-ui/core/useMediaQuery";
import NoteIcon from "@material-ui/icons/Note";
import ConceptAttributeTypeFilters from "./ConceptAttributeTypeFilters";

export default observer(function ConceptAttributeTypeIndex() {
  const { conceptAttributeTypeStore } = useStore();
  const { t } = useTranslation();

  const {
    updatePageData,
    handleEditConceptAttributeType,
    shouldOpenEditorDialog,
    shouldOpenConfirmationDialog,
    handleClose,
    handleConfirmDelete,
    selectedConceptAttributeTypeList,
    handleDeleteList,
    shouldOpenConfirmationDeleteListDialog,
    handleConfirmDeleteList,
    shouldOpenImportExcelDialog,
    importExcel,
  } = conceptAttributeTypeStore;

  useEffect(() => {
    updatePageData();
  }, [updatePageData]);

  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down("sm"));
  const isExtraSmall = useMediaQuery(theme.breakpoints.down("xs"));

  return (
    <div className="content-index">
      <div className="index-breadcrumb">
        <GlobitsBreadcrumb routeSegments={[{ name: t("conceptAttributeType.title") }]} />
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
                  handleEditConceptAttributeType();
                }}
              >
                {!isMobile && t("general.button.add")}
              </Button>

              {selectedConceptAttributeTypeList.length > 0 && (
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
              <ConceptAttributeTypeFilters />
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
                  onClick={() => {
                    handleEditConceptAttributeType();
                  }}
                  fullWidth
                >
                  {!isMobile && t("general.button.add")}
                </Button>
              </Grid>

              <Grid item sm={4} xs={4}>
                {selectedConceptAttributeTypeList.length > 0 && (
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
                {/* <ConceptAttributeTypeFilters /> */}
              </Grid>
            </>
          )
        }

        {/* <ImportExcelDialogConceptAttributeType
          t={t}
          handleClose={handleClose}
          open={shouldOpenImportExcelDialog}
        /> */}

        <ConceptAttributeTypeCreateEditPopup open={shouldOpenEditorDialog} />

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
          <ConceptAttributeTypeList />
        </Grid>
      </Grid>
    </div>
  );
});
