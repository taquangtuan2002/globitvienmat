import React from "react";
import Draggable from "react-draggable";
import Paper from "@material-ui/core/Paper";
import { observer } from "mobx-react";

function PaperComponent(props) {
    return (
      <Draggable
        className="paper-container fullWidth"
        handle="#draggable-dialog-title"
        cancel={'[class*="MuiDialogContent-root"]'}
      >
        <Paper {...props} />
      </Draggable>
    );
  }

export default observer(PaperComponent);