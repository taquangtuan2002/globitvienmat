import React from "react";
import { useField } from "formik";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker,
  KeyboardDateTimePicker,
  DatePicker,
} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import { useFormikContext } from "formik";
import DateRangeIcon from '@material-ui/icons/DateRange';
import { IconButton, InputAdornment } from "@material-ui/core";
const GlobitsDateTimePicker = ({
  disablePast,
  disableFuture,
  name,
  size,
  format,
  inputVariant,
  defaultValue,
  isDateTimePicker,
  label,
  ...otherProps
}) => {
  const { setFieldValue } = useFormikContext();

  const [field, meta] = useField(name);

  const onChange = (value) => {
    setFieldValue(name, value);
  };
  const configDateTimePicker = {
    ...field,
    ...otherProps,
    disablePast: disablePast ? disablePast : false,
    disableFuture: disableFuture ? disableFuture : false,
    inputVariant: inputVariant ? inputVariant : "outlined",
    size: size ? size : "small",
    format: format ? format : "dd/MM/yyyy",
    fullWidth: true,
    value: field?.value ? field.value : otherProps?.value || null,
    onChange: otherProps?.onChange ? otherProps.onChange : onChange,
    InputLabelProps: {
      htmlFor: name,
      shrink: true,
    },
    invalidDateMessage: "Ngày không hợp lệ",
  };

  if (meta && meta.touched && meta.error) {
    configDateTimePicker.error = true;
    configDateTimePicker.helperText = meta.error;
  }

  // return <TextField {...configDateTimePicker} />;
  return (
    <MuiPickersUtilsProvider utils={DateFnsUtils}>
      <label htmlFor={name}>{label}</label>
      {isDateTimePicker ? (
        <DatePicker {...configDateTimePicker} 
        autoOk
        InputProps={{
          endAdornment: (
            <InputAdornment position="end">
              <IconButton>
                <DateRangeIcon />
              </IconButton>
            </InputAdornment>
          ),
        }} />
      ) : (
        <DatePicker {...configDateTimePicker} 
        autoOk
        InputProps={{
          endAdornment: (
            <InputAdornment position="end">
              <IconButton>
                <DateRangeIcon />
              </IconButton>
            </InputAdornment>
          ),
        }} />
      )}
    </MuiPickersUtilsProvider>
  );
};

export default GlobitsDateTimePicker;
