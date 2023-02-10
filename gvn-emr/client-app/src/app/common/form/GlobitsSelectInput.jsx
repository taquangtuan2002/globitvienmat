import React from "react";
import { TextField, MenuItem } from "@material-ui/core";
import { useField, useFormikContext } from "formik";

const GlobitsSelectInput = ({
  name,
  keyValue,
  displayvalue,
  options,
  size,
  variant,
  label,
  ...otherProps
}) => {
  const { setFieldValue } = useFormikContext();
  const [field, meta] = useField(name);
  const handleChange = (evt) => {
    const { value } = evt.target;
    setFieldValue(name, value);
  };

  const configSelectInput = {
    ...field,
    ...otherProps,
    select: true,
    variant: variant ? variant : "outlined",
    size: size ? size : "small",
    fullWidth: true,
    onChange: otherProps?.handleChange
      ? otherProps?.handleChange
      : handleChange,
    InputLabelProps: {
      htmlFor: name,
      shrink: true,
    },
  };

  if (meta && meta.touched && meta.error) {
    configSelectInput.error = true;
    configSelectInput.helperText = meta.error;
  }

  return (
    <>
      <label htmlFor={name}>{label}</label>
      <TextField {...configSelectInput}>
        <MenuItem value={null}>
          <em>---</em>
        </MenuItem>
        {options?.map((item, pos) => {
          return (
            <MenuItem key={pos} value={item[keyValue]}>
              {item[displayvalue ? displayvalue : "name"]}
            </MenuItem>
          );
        })}
      </TextField>
    </>
  );
};

export default GlobitsSelectInput;
