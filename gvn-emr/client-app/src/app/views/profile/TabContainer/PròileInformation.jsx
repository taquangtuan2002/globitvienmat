import { Grid, makeStyles, } from "@material-ui/core";
import { observer } from "mobx-react";
import React from "react";
import { useTranslation } from "react-i18next";
import GlobitsTextField from "../../../common/form/GlobitsTextField";
import GlobitsSelectInput from "../../../common/form/GlobitsSelectInput";
import LocalConstants from "../../../LocalConstants";
import { pagingCountry } from "../../Country/CountryService";
import { pagingEthnicities } from "../../Ethnics/EthnicsService";
import GlobitsDateTimePicker from "../../../common/form/GlobitsDateTimePicker";
import { useFormikContext } from "formik";
import GlobitsPagingAutocomplete from "../../../common/form/GlobitsPagingAutocomplete";
import GlobitsPagingAutocompleteV2 from "../../../common/form/GlobitsPagingAutocompleteV2";
import "react-toastify/dist/ReactToastify.css";

const useStyles = makeStyles((theme) => ({
  textfieldClass: {
    "& .MuiInput-input": {
      "&::-webkit-outer-spin-button, &::-webkit-inner-spin-button": {
        "-webkit-appearance": "none",
      },
    },
  },
  root: {
    padding: "15px",
  },
  formControll: {
    display: "flex",
    marginBottom: "8px",
    "& p": {
      marginTop: 0,
      marginLeft: "15px",
      marginBottom: 0,
    },
  },
  fieldset: {
    borderRadius: "8px",
    marginBottom: "15px",
    padding: "20px",
    border: "3px solid #01c0c8",
  },
  legend: {
    background: "#01c0c8",
    color: "#fff",
    padding: "5px 10px ",
    borderRadius: "5px",
    marginLeft: "20px",
    fontWeight: "500",
  },
  flexBox: {
    display: "flex",
    flexDirection: "column",
  },
}));

export default observer(function GeneralInformation() {
  const { t } = useTranslation();
  const { values, setFieldValue } = useFormikContext();

  const classes = useStyles();

  return (
    <>
      <fieldset className={classes.fieldset}>
        <legend className={classes.legend}>Thông tin cơ bản</legend>
        <Grid container spacing={2}>
          <Grid item md={4} sm={6} xs={12}>
            <GlobitsTextField label={t("user.lastName")} name="lastName" validate={true} />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsTextField label={t("user.firstName")} name="firstName" validate={true} />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsTextField
              disabled
              label={t("user.name")}
              name="displayName"
              value={
                (!values?.lastName ? "" : values?.lastName) +
                (values?.lastName ? " " : "") +
                (!values?.firstName ? "" : values?.firstName)
              }
            />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsDateTimePicker label={t("user.birthDate")} name="birthDate" disableFuture={true} />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsTextField label={t("user.birthPlace")} name="birthPlace" />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsSelectInput label={t("user.gender")} name="gender" keyValue="id" options={LocalConstants.ListGender} />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsPagingAutocomplete label={t("user.ethnic")} name="ethnics" api={pagingEthnicities} />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsPagingAutocomplete label={t("user.nationality")} name="nationality" api={pagingCountry} />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsSelectInput label={t("user.maritalStatus")} name="maritalStatus" keyValue="value" options={LocalConstants.ListMaritalStatus} />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsTextField label={t("user.identityCardNumber")} name="idNumber" />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsDateTimePicker label={t("user.dateRange")} name="idNumberIssueDate" disableFuture={true} />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsTextField label={t("user.licensePlace")} name="idNumberIssueBy" />
          </Grid>
        </Grid>
      </fieldset>

      <fieldset className={classes.fieldset}>
        <legend className={classes.legend}>Thông tin liên lạc</legend>
        <Grid container spacing={2}>
         
          <Grid item md={4} sm={6} xs={12}>
            <GlobitsPagingAutocompleteV2
              label={t("user.province")}
              name="province"
              value={values?.province}
              searchObject={{ level: 1 }}
              handleChange={(_, value) => {
                setFieldValue("province", value);
                setFieldValue("district", null);
                setFieldValue("administrativeunit", null);
              }}
            />
          </Grid>
          <Grid item md={4} sm={6} xs={12}>
            <GlobitsPagingAutocompleteV2
              label={t("user.district")}
              name="district"
              value={values?.district}
              searchObject={{
                level: 2,
                parentId: values?.province?.id,
              }}
              allowLoadOptions={!!values?.province?.id}
              clearOptionOnClose
              handleChange={(_, value) => {
                setFieldValue("district", value);
                setFieldValue("administrativeunit", null);
              }}
            />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsPagingAutocompleteV2
              label={t("user.wards")}
              name="administrativeunit"
              searchObject={{
                level: 3,
                parentId: values?.district?.id,
              }}
              allowLoadOptions={!!values?.district?.id}
              clearOptionOnClose
            />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsTextField label={t("user.email")} name="email" />
          </Grid>

          <Grid item md={4} sm={6} xs={12}>
            <GlobitsTextField label={t("user.phoneNumber")} name="phoneNumber" />
          </Grid>
        </Grid>
      </fieldset>
    </>
  );
});
