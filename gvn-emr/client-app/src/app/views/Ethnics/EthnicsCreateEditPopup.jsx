import React from "react";
import { Dialog, DialogTitle, Icon, IconButton } from "@material-ui/core";
import Draggable from "react-draggable";
import Paper from "@material-ui/core/Paper";
import { useTranslation } from "react-i18next";
import { useStore } from "../../stores";
import EthnicsForm from "./EthnicsForm";
import { observer } from "mobx-react";

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

export default observer(function EthnicsCreateEditPopup(props) {
  const { ethnicsStore } = useStore();
  const { t } = useTranslation();
  const { handleClose, selectedItem } = ethnicsStore;
  const { open } = props;

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
        <span className="mb-20 text-white">
          {" "}
          {(selectedItem?.id
            ? t("general.button.edit")
            : t("general.button.add")) +
            " " +
            t("ethnics.title")}{" "}
        </span>
      </DialogTitle>
      <IconButton
        style={{ position: "absolute", right: "10px", top: "10px" }}
        onClick={() => handleClose()}
      >
        <Icon color="disabled" title={t("general.close")}>
          close
        </Icon>
      </IconButton>
      <EthnicsForm />
    </Dialog>
  );
});
