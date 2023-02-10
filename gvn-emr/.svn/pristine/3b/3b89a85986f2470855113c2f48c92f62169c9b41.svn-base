import React from "react";
import { Formik, Form } from "formik";
import { Grid, DialogActions, Button, DialogContent } from "@material-ui/core";
import SaveIcon from "@material-ui/icons/Save";
import BlockIcon from "@material-ui/icons/Block";
import { useTranslation } from "react-i18next";
import { useStore } from "../../stores";
import * as Yup from "yup";
import { observer } from "mobx-react";
import GlobitsTextField from "../../common/form/GlobitsTextField";
import Draggable from "react-draggable";
import Paper from "@material-ui/core/Paper";
import { Dialog, DialogTitle, Icon, IconButton } from "@material-ui/core";

function PaperComponent(props) {
  return (
    <Draggable handle="#draggable-dialog-title" cancel={'[class*="MuiDialogContent-root"]'} >
      <Paper {...props} />
    </Draggable>
  );
}

export default observer(function ConceptTypeForm({ title }) {
  const { t } = useTranslation();
  
  const { conceptTypeStore } = useStore();

  const { openPopup, dataConceptTypeEdit, handleSubmitFormConceptType, handleClosePopup, getListConceptType } = conceptTypeStore;

  const validationSchema = Yup.object({
    code: Yup.string().required('Chưa có mã concept type!').nullable(),
    name: Yup.string().required(t("Chưa có tên concept type!")).nullable(),
  });

  return (
    <Dialog className="dialog-container" open={openPopup} PaperComponent={PaperComponent} fullWidth maxWidth="sm" >
      <DialogTitle className="dialog-header bgc-primary" style={{ cursor: "move" }} id="draggable-dialog-title"  >
        <span className="mb-20 text-white">
          {(dataConceptTypeEdit && dataConceptTypeEdit.id) ? 'Chỉnh sửa Concept Type ' : " Thêm mới Concept Type"}
        </span>
      </DialogTitle>
      <IconButton
        style={{ position: "absolute", right: "10px", top: "10px" }}
        onClick={() => handleClosePopup()}
      >
        <Icon color="disabled" title={t("general.close")}>
          close
        </Icon>
      </IconButton>
      <Formik
        validationSchema={validationSchema}
        enableReinitialize
        initialValues={dataConceptTypeEdit}
        onSubmit={(values, actions) => {
          handleSubmitFormConceptType(values).then(() => {
            getListConceptType();
            handleClosePopup();
          });
          actions.setSubmitting(false);
        }}
      >
        {({ isSubmitting }) => (
          <Form autoComplete="off">
            <div className="dialog-body">
              <DialogContent className="o-hidden">
                <Grid container spacing={2}>
                  <Grid item md={12} sm={12} xs={12}>
                    <GlobitsTextField label={'Mã Concept Type'} name="code" validate={true} />
                  </Grid>
                  <Grid item md={12} sm={12} xs={12}>
                    <GlobitsTextField label={'Tên Concept Type'} name="name" validate={true} />
                  </Grid>
                  <Grid item md={12} sm={12} xs={12}>
                    <GlobitsTextField label={'Mô tả'} name="description" multiline rows={3} />
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
                    onClick={() => handleClosePopup()}
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
    </Dialog>
  );
});
