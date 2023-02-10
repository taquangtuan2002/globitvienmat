import React from "react";
import {
  RadioGroup,
  Radio,
  FormControl,
  FormControlLabel,
  FormLabel,
  FormHelperText,
} from "@material-ui/core";
import { useField } from "formik";

const GlobitsRadioGroup = ({
  name,
  label,
  options,
  inARow,
  disabled,
  ...otherProps
}) => {
  const [field, meta] = useField(name);

  const configRadioGroup = {
    ...field,
    ...otherProps,
    row: otherProps?.row ? otherProps.row : true,
  };
  // value của radio trả về luôn là type string :(
  return (
    <FormControl
      component="fieldset"
      // className={className}
      error={meta && meta.touched && meta.error}
    >
      {!inARow && <FormLabel className="mr-12">{label}</FormLabel>}
      <RadioGroup aria-label={name} {...configRadioGroup}>
        {inARow && <FormLabel className="mr-12">{label}</FormLabel>}
        {options.map((option) => {
          return (
            <FormControlLabel
              disabled={disabled}
              value={option.value}
              label={option.name}
              control={<Radio />}
            />
          );
        })}
      </RadioGroup>
      <FormHelperText>{meta?.error}</FormHelperText>
    </FormControl>
  );
};

export default GlobitsRadioGroup;
