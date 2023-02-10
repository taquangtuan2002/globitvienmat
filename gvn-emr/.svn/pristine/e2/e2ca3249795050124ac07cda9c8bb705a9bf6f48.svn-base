import { observer } from "mobx-react";
import React, { useEffect } from "react";
import { useStore } from "../../stores";
import { Grid, Button } from "@material-ui/core";
import GlobitsBreadcrumb from "../../common/GlobitsBreadcrumb";
import DeleteIcon from "@material-ui/icons/Delete";
import AddIcon from "@material-ui/icons/Add";
import { useTranslation } from "react-i18next";
import EthnicsList from "./EthnicsList";
import EthnicsFilters from "./EthnicsFilters";
import EthnicsCreateEditPopup from "./EthnicsCreateEditPopup";
import GlobitsConfirmationDialog from "../../common/GlobitsConfirmationDialog";
import { useTheme } from "@material-ui/core/styles";
import useMediaQuery from "@material-ui/core/useMediaQuery";

export default observer(function EthnicsIndex() {
  const { ethnicsStore } = useStore();
  const { t } = useTranslation();

  const {
    updatePageData,
    handleEditEthnics,
    shouldOpenEditorDialog,
    shouldOpenConfirmationDialog,
    handleClose,
    handleConfirmDelete,
    selectedEthnicsList,
    handleDeleteList,
    shouldOpenConfirmationDeleteListDialog,
    handleConfirmDeleteList,
  } = ethnicsStore;

  useEffect(() => {
    updatePageData();
  }, [updatePageData]);

  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down("sm"));
  const isExtraSmall = useMediaQuery(theme.breakpoints.down("xs"));

  return (
    <div className="content-index">
      <div className="index-breadcrumb">
        <GlobitsBreadcrumb routeSegments={[{ name: t("ethnics.title") }]} />
      </div>
      <Grid className="index-card" container spacing={2}>
        {
          !isExtraSmall && (
            <>
              <Grid item lg={6} md={6} sm={4} xs={4}>
                <Button
                  className="mb-16 mr-16 btn btn-info d-inline-flex"
                  startIcon={<AddIcon />}
                  variant="contained"
                  onClick={() => {
                    handleEditEthnics();
                  }}
                >
                  {!isMobile && t("general.button.add")}
                </Button>
                {selectedEthnicsList.length > 0 && (
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
                <EthnicsFilters />
              </Grid>
            </>
          )
        }

        {
          isExtraSmall && (
            <>
              <Grid item sm={6} xs={6}>
                <Button
                  className="btn btn-info d-inline-flex"
                  startIcon={<AddIcon />}
                  variant="contained"
                  onClick={() => {
                    handleEditEthnics();
                  }}
                  fullWidth
                >
                  {t("general.button.add")}
                </Button>
              </Grid>
              <Grid item sm={6} xs={6}>
                {selectedEthnicsList.length > 0 && (
                  <Button
                    className="btn btn-warning d-inline-flex"
                    variant="contained"
                    startIcon={<DeleteIcon />}
                    onClick={() => {
                      handleDeleteList();
                    }}
                    fullWidth
                  >
                    {t("general.button.delete")}
                  </Button>
                )}
              </Grid>
              <Grid item sm={12} xs={12}>
                <EthnicsFilters />
              </Grid>
            </>
          )
        }

        <EthnicsCreateEditPopup open={shouldOpenEditorDialog} />

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
          <EthnicsList />
        </Grid>
      </Grid>
    </div>
  );
});
