import React from "react";
import { useField } from "formik";
import {
  MuiPickersUtilsProvider,
  KeyboardTimePicker,
} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import { useFormikContext } from "formik";
// import time

const GlobitsTimePicker = ({
  disablePast,
  disableFuture,
  name,
  size,
  format,
  inputVariant,
  defaultValue,
  isTimePicker,
  label,
  ...otherProps
}) => {
  const { setFieldValue } = useFormikContext();

  const [field, meta] = useField(name);

  const onChange = (value) => {
    setFieldValue(name, value);
  };
  const configTimePicker = {
    ...field,
    ...otherProps,
    disablePast: disablePast ? disablePast : false,
    disableFuture: disableFuture ? disableFuture : false,
    inputVariant: inputVariant ? inputVariant : "outlined",
    size: size ? size : "small",
    format: format ? format : "HH:mm",
    fullWidth: true,
    value: field?.value ? field.value : otherProps?.value || null,
    onChange: otherProps?.onChange ? otherProps.onChange : onChange,
    InputLabelProps: {
      htmlFor: name,
      shrink: true,
    },
    invalidDateMessage: "Thời gian không hợp lệ",
  };
  if (meta && meta.error && meta.initialValue) {
    configTimePicker.error = true;
    configTimePicker.helperText = meta.error;
  }

  // return <TextField {...configTimePicker} />;
  return (
    <MuiPickersUtilsProvider utils={DateFnsUtils}>
      <label htmlFor={name}>{label}</label>
      {isTimePicker ? (
        <KeyboardTimePicker {...configTimePicker} />
      ) : (
        <KeyboardTimePicker {...configTimePicker} />
      )}
    </MuiPickersUtilsProvider>
  );
};

export default GlobitsTimePicker;
