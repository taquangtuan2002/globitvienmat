import React, { useState, useEffect } from "react";
import { Formik, Form } from "formik";
import { Grid, DialogActions, Button, DialogContent } from "@material-ui/core";
import SaveIcon from "@material-ui/icons/Save";
import BlockIcon from "@material-ui/icons/Block";
import { useTranslation } from "react-i18next";
import { useStore } from "../../stores";
import * as Yup from "yup";
import { observer } from "mobx-react";

import GlobitsTextField from "../../common/form/GlobitsTextField";

export default observer(function CountryForm() {
  const { countryStore } = useStore();
  const { t } = useTranslation();
  const { handleClose, createCountry, editCountry, selectedCountry } =
    countryStore;

  const [country, setCountry] = useState({
    id: "",
    code: "",
    name: "",
    description: "",
  });

  const validationSchema = Yup.object({
    code: Yup.string().required(t("validation.code")),
    name: Yup.string().required(t("validation.name")),
  });

  useEffect(() => {
    if (selectedCountry) setCountry(selectedCountry);
  }, [selectedCountry]);

  function hanledFormSubmit(country) {
    if (country.id.length === 0) {
      createCountry(country);
    } else {
      editCountry(country);
    }
  }

  return (
    <Formik
      validationSchema={validationSchema}
      enableReinitialize
      initialValues={country}
      onSubmit={(values, actions) => {
        hanledFormSubmit(values);
        actions.setSubmitting(false);
      }}
    >
      {({ isSubmitting }) => (
        <Form autoComplete="off">
          <div className="dialog-body">
            <DialogContent className="o-hidden">
              <Grid container spacing={2}>
                <Grid item md={12} sm={12} xs={12}>
                  <GlobitsTextField
                    label={
                      <span>
                        {t("country.code")}
                        <span style={{ color: "red" }}> * </span>
                      </span>
                    }
                    name="code"
                  />
                </Grid>
                <Grid item md={12} sm={12} xs={12}>
                  <GlobitsTextField
                    label={
                      <span>
                        {t("country.name")}
                        <span style={{ color: "red" }}> * </span>
                      </span>
                    }
                    name="name"
                  />
                </Grid>
                <Grid item md={12} sm={12} xs={12}>
                  <GlobitsTextField
                    label={t("country.description")}
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
