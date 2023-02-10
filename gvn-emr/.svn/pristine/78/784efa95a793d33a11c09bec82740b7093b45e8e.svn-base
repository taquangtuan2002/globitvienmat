import React, { useEffect, useState } from "react";
import { useStore } from "../../stores";
import { observer } from "mobx-react";
import { useFormikContext } from "formik";
import Draggable from "react-draggable";
import {
  Dialog,
  DialogTitle,
  Icon,
  IconButton,
  DialogContent,
  Grid,
  DialogActions,
  Button,
} from "@material-ui/core";
import Paper from "@material-ui/core/Paper";
import { useTranslation } from "react-i18next";
import DepartmentFilters from "./DepartmentFilters";
import SelectDepartmentList from "./SelectDepartmentList";

function PaperComponent(props) {
  return (
    <Draggable
      handle="#draggable-dialog-title"
      cancel={'[class*="MuiDialogContent-root"]'}
    >
      <Paper {...props} />
    </Draggable>
  );
}

export default observer(function SelectDepartmentPopup(props) {
  const { setFieldValue, values } = useFormikContext();
  const { t } = useTranslation();
  const { departmentStore } = useStore();
  const { open, handleClose } = props;

  const { handleToggleDepartmentPopup } = departmentStore;

  const [selectedParentDepartment, setSelectedParentDepartment] = useState(
    values?.parent
  );

  // const [openDepartmentPopup, shouldOpenDepartmentPopup] = useState(false);

  const handleSelectParentDepartment = (_, department) => {
    if (
      selectedParentDepartment &&
      selectedParentDepartment.id === department.id
    ) {
      setSelectedParentDepartment(null);
    } else {
      setSelectedParentDepartment(department);
    }
  };

  useEffect(() => {
    setFieldValue("parent", selectedParentDepartment);
  }, [setFieldValue, selectedParentDepartment]);

  const handleConfirmSelectParentDepartment = () => {
    handleClose();
  };

  console.log(values);

  return (
    <Dialog
      className="dialog-container"
      open={open}
      PaperComponent={PaperComponent}
      fullWidth
      maxWidth="sm"
    >
      <DialogTitle
        className="dialog-header bgc-primary"
        style={{ cursor: "move" }}
        id="draggable-dialog-title"
      >
        <span className="mb-20 text-white">{t("department.select")}</span>
      </DialogTitle>
      <IconButton
        style={{ position: "absolute", right: "10px", top: "10px" }}
        onClick={() => handleToggleDepartmentPopup()}
      >
        <Icon color="disabled" title={t("general.close")}>
          close
        </Icon>
      </IconButton>
      <DialogContent>
        <Grid container className="mb-16">
          <Grid item lg={6} md={6} sm={4} xs={4}></Grid>
          <Grid item lg={6} md={6} sm={8} xs={8}>
            <DepartmentFilters />
          </Grid>
        </Grid>
        <Grid item xs={12}>
          <SelectDepartmentList
            selectedItem={selectedParentDepartment}
            handleSelectItem={handleSelectParentDepartment}
          />
        </Grid>
      </DialogContent>
      <DialogActions>
        <Button
          className="mb-16 mr-36 btn btn-secondary"
          variant="contained"
          onClick={() => handleToggleDepartmentPopup()}
        >
          {t("general.button.cancel")}
        </Button>
        <Button
          className="mb-16 mr-16 btn btn-primary"
          variant="contained"
          onClick={() => {
            handleConfirmSelectParentDepartment();
          }}
        >
          {t("general.button.select")}
        </Button>
      </DialogActions>
    </Dialog>
  );
});
