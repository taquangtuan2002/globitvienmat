import { observer } from "mobx-react";
import React, { useEffect } from "react";
import { useStore } from "../../stores";
import { Grid, Button } from "@material-ui/core";
import GlobitsBreadcrumb from "../../common/GlobitsBreadcrumb";
import DeleteIcon from "@material-ui/icons/Delete";
import AddIcon from "@material-ui/icons/Add";
import { useTranslation } from "react-i18next";
import CountryList from "./CountryList";
import CountryFilters from "./CountryFilters";
import CountryCreateEditPopup from "./CountryCreateEditPopup";
import GlobitsConfirmationDialog from "../../common/GlobitsConfirmationDialog";
import { useTheme } from "@material-ui/core/styles";
import useMediaQuery from "@material-ui/core/useMediaQuery";
import NoteIcon from "@material-ui/icons/Note";
import ImportExcelDialogCountry from "./ImportExcelDialogCountry";

export default observer(function CountryIndex() {
  const { countryStore } = useStore();
  const { t } = useTranslation();

  const {
    updatePageData,
    handleEditCountry,
    shouldOpenEditorDialog,
    shouldOpenConfirmationDialog,
    handleClose,
    handleConfirmDelete,
    selectedCountryList,
    handleDeleteList,
    shouldOpenConfirmationDeleteListDialog,
    handleConfirmDeleteList,
    shouldOpenImportExcelDialog,
    importExcel,
  } = countryStore;

  useEffect(() => {
    updatePageData();
  }, [updatePageData]);

  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down("sm"));
  const isExtraSmall = useMediaQuery(theme.breakpoints.down("xs"));

  return (
    <div className="content-index">
      <div className="index-breadcrumb">
        <GlobitsBreadcrumb routeSegments={[{ name: t("country.title") }]} />
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
                  handleEditCountry();
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
              <CountryFilters />
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
                    handleEditCountry();
                  }}
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
                <CountryFilters />
              </Grid>
            </>
          )
        }

        <ImportExcelDialogCountry
          t={t}
          handleClose={handleClose}
          open={shouldOpenImportExcelDialog}
        />

        <CountryCreateEditPopup open={shouldOpenEditorDialog} />

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
          <CountryList />
        </Grid>
      </Grid>
    </div>
  );
});
