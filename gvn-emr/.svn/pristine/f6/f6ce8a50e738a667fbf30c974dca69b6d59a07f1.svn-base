import { observer } from "mobx-react";
import React, { useEffect, useState } from "react";
import { useTranslation } from "react-i18next";
import { useStore } from "../../stores";
import { Grid, makeStyles, DialogActions, Button } from "@material-ui/core";
import { Formik, Form } from "formik";
import * as Yup from "yup";
import ConstantList from "../../appConfig";
import SaveIcon from "@material-ui/icons/Save";
import BlockIcon from "@material-ui/icons/Block";
import ProfileTab from "./TabContainer/ProfileTab";
import { useHistory } from "react-router-dom";
import FormikFocusError from "../../common/FormikFocusError";

const useStyles = makeStyles((theme) => ({
  root: {
  },
  personCard: {
    textAlign: "center",
  },
  personName: {
    fontWeight: 500,
    marginTop: "10px",
    marginBottom: "0.75rem",
  },
  personTitle: {
    fontWeight: 300,
    marginBottom: "15px",
    color: "#6c757d",
  },
  personIdent: {
    "& p": {
      marginTop: 0,
    },
  },
}));

export default observer(function StaffDetail() {
  const { profileStore } = useStore();

  const { editStaff, createStaff, currentStaff, currentUser } = profileStore;
  const history = useHistory();

  const { t } = useTranslation();
  const classes = useStyles();

  const [staff, setStaff] = useState({
    id: "",
    idNumber: "",
    idNumberIssueDate: null,
    idNumberIssueBy: "",
    firstName: "",
    lastName: "",
    displayName: "",
    gender: "",
    birthDate: null,
    birthPlace: "",
    nationality: null,
    ethnics: null,
    email: "",
    phoneNumber: "",
    maritalStatus: "",
    administrativeunit: null,
    district: null,
    province: null,
  });

  useEffect(() => {
    if (currentStaff)
      setStaff({
        ...currentStaff,
        administrativeunit: currentStaff?.administrativeunit,
        district: currentStaff?.administrativeunit?.parent,
        province: currentStaff?.administrativeunit?.province,
      });
  }, [currentStaff, currentUser]);

  const validationSchema = Yup.object({
    firstName: Yup.string()
      .ensure()
      .matches(/^[^\d]*$/, "Dữ liệu sai")
      .required(t("validation.required"))
      .test("validat", "Dữ liệu chứa ký tự đặc biêt", (value) => {
        var specialChars = "<>@!#$%^&*()_+[]{}?:;|'\"\\,./~`-=";
        for (let i = 0; i < specialChars?.length; i++) {
          if (value.indexOf(specialChars[i]) > -1) {
            return false;
          }
        }
        return true;
      })
      .nullable(),
    lastName: Yup.string()
      .ensure()
      .matches(/^[^\d]*$/, "Dữ liệu sai")
      .required(t("validation.required"))
      .test("validat", "Dữ liệu chứa ký tự đặc biêt", (value) => {
        var specialChars = "<>@!#$%^&*()_+[]{}?:;|'\"\\,./~`-=";
        for (let i = 0; i < specialChars?.length; i++) {
          if (value.indexOf(specialChars[i]) > -1) {
            return false;
          }
        }
        return true;
      }),
    idNumber: Yup.string()
      .required(t("validation.required"))
      .nullable(),
    idNumberIssueDate: Yup.date()
      .transform(function transformDate(castValue, originalValue) {
        return originalValue ? new Date(originalValue) : castValue;
      })
      .typeError("Ngày không đúng định dạng")
      .required(t("validation.required"))
      .nullable(),
    email: Yup.string()
      .email("Sai định dạng email")
      .required(t("validation.required")),
    phoneNumber: Yup.number().required(t("validation.required")),
  });


  function handledFormSubmit(staff) {
    if (staff.id === "") {
      createStaff(staff).then((data) => {
        if (data === false) {
        } else {
          history.push(ConstantList.ROOT_PATH + "staff");
        }
      });
    } else {
      editStaff(staff).then((data) => {
        if (data === false) {
        } else {
          history.push(ConstantList.ROOT_PATH + "staff");
        }
      });
    }
  }
  return (
    <Grid container className={`index-card tab ${classes.root}`}>
      <Grid className="tab-item" item xs={12}>
        <Formik
          initialValues={staff}
          validationSchema={validationSchema}
          enableReinitialize
          validateOnBlur={true}
          validateOnChange={false}
          onSubmit={(values) => handledFormSubmit(values)}
        >
          {({ isSubmitting, errors }) => {
            return (
              <Form autoComplete="off">
                <FormikFocusError />
                <div className="tab-container">
                  <Grid container spacing={2}>
                    <Grid item xs={12}>
                      <ProfileTab />
                    </Grid>
                  </Grid>
                </div>
                <div
                  className="dialog-footer"
                  style={{
                    position: "fixed",
                    bottom: "0px",
                    left: "0px",
                    right: "0px",
                    border: "none",
                    backgroundColor: "#fff",
                  }}
                >
                  <DialogActions className="p-0">
                    <div className="flex flex-space-between flex-middle">
                      <Button
                        startIcon={<BlockIcon />}
                        variant="contained"
                        className="mr-12 btn btn-secondary d-inline-flex"
                        color="secondary"
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
            );
          }}
        </Formik>
      </Grid>
    </Grid>
  );
});
