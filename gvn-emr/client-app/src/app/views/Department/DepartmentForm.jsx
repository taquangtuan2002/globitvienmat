import { observer } from "mobx-react";
import React, { useState, useEffect } from "react";
import { Formik, Form } from "formik";
import {
  Grid,
  makeStyles,
  DialogActions,
  Button,
  DialogContent,
} from "@material-ui/core";
import SaveIcon from "@material-ui/icons/Save";
import BlockIcon from "@material-ui/icons/Block";
import { useTranslation } from "react-i18next";
import { useStore } from "../../stores";
import * as Yup from "yup";

import GlobitsTextField from "../../common/form/GlobitsTextField";
import GlobitsDateTimePicker from "../../common/form/GlobitsDateTimePicker";
import SelectDepartmentPopup from "./SelectDepartmentPopup";

const useStyles = makeStyles((theme) => ({
  gridContainerForm: {
    maxHeight: "68vh",
    overflowY: "auto",
    marginBottom: 10,
  },
  textField: {
    width: "100%",
    margin: "10px 0px !important",
  },
}));

export default observer(function DepartmentForm() {
  const classes = useStyles();
  const { departmentStore } = useStore();
  const { t } = useTranslation();
  const {
    handleClose,
    createDepartment,
    editDepartment,
    selectedDepartment,
    shouldOpenDepartmentPopup,
    handleToggleDepartmentPopup,
  } = departmentStore;

  const [department, setDepartment] = useState({
    id: "",
    code: "",
    name: "",
    value: "",
    parent: null,
  });

  const validationSchema = Yup.object({
    code: Yup.string().required(t("validation.required")),
    name: Yup.string().required(t("validation.required")),
  });

  useEffect(() => {
    if (selectedDepartment) setDepartment(selectedDepartment);
  }, [selectedDepartment]);

  // function hanledFormSubmit(department) {
  //   // console.log(department);
  //   if (department.id.length === 0) {
  //     createDepartment(department);
  //   } else {
  //     editDepartment(department);
  //   }
  // }

  return (
    <Formik
      validationSchema={validationSchema}
      enableReinitialize
      initialValues={department}
      onSubmit={(values) =>
        values.id.length === 0
          ? createDepartment(values)
          : editDepartment(values)
      }
    >
      {({ isSubmitting, values }) => (
        <Form autoComplete="off">
          <div className="dialog-body">
            <DialogContent className="o-hidden">
              <Grid container className={classes.gridContainerForm} spacing={2}>
                <Grid item sm={12} xs={12}>
                  <div className="input-popup-container">
                    <GlobitsTextField
                      label={t("department.parent")}
                      name="parent"
                      disabled
                      value={values.parent ? values.parent.name : ""}
                    />
                    <Button
                      variant="contained"
                      // color="primary"
                      style={{
                        marginTop: "21.5px",
                      }}
                      className="btn-primary"
                      onClick={() => handleToggleDepartmentPopup()}
                    >
                      {t("general.button.select")}
                    </Button>
                    <SelectDepartmentPopup
                      open={shouldOpenDepartmentPopup}
                      handleClose={handleToggleDepartmentPopup}
                    />
                  </div>
                </Grid>

                <Grid item sm={6}>
                  <GlobitsTextField
                    label={
                      <span>
                        {t("department.code")}
                        <span style={{ color: "red" }}> * </span>
                      </span>
                    }
                    name="code"
                  />
                </Grid>
                <Grid item sm={6}>
                  <GlobitsTextField
                    label={
                      <span>
                        {t("department.name")}
                        <span style={{ color: "red" }}> * </span>
                      </span>
                    }
                    name="name"
                  />
                </Grid>
                <Grid item sm={6}>
                  <GlobitsTextField
                    label={t("department.function")}
                    name="func"
                  />
                </Grid>
                <Grid item sm={6}>
                  <GlobitsTextField
                    label={t("department.industryBlock")}
                    name="industryBlock"
                  />
                </Grid>
                <Grid item sm={6}>
                  <GlobitsTextField
                    label={t("department.foundedNumber")}
                    name="foundedNumber"
                  />
                </Grid>
                <Grid item xs={6}>
                  <GlobitsDateTimePicker
                    label={t("department.foundedDate")}
                    name="foundedDate"
                  />
                </Grid>
                <Grid item sm={12}>
                  <GlobitsTextField
                    label={t("department.displayOrder")}
                    name="displayOrder"
                  />
                </Grid>
                <Grid item md={12} sm={12} xs={12}>
                  <GlobitsTextField
                    label={t("department.description")}
                    name="description"
                    multiline
                    rows={3}
                  />
                </Grid>
              </Grid>
            </DialogContent>
          </div>
          <div className="dialog-footer">
            <DialogActions className="p-0">
              <div className="flex flex-space-between flex-middle">
                <Button
                  startIcon={<BlockIcon />}
                  variant="contained"
                  className="mr-12 btn btn-secondary d-inline-flex"
                  color="secondary"
                  onClick={() => handleClose()}
                >
                  {t("general.button.cancel")}
                </Button>
                <Button
                  startIcon={<SaveIcon />}
                  className="mr-0 btn btn-primary d-inline-flex"
                  variant="contained"
                  color="primary"
                  type="submit"
                  disabled={isSubmitting}
                >
                  {t("general.button.save")}
                </Button>
              </div>
            </DialogActions>
          </div>
        </Form>
      )}
    </Formik>
  );
});
