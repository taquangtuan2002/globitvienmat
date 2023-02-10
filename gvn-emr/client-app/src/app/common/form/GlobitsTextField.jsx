import React, { useEffect, useState } from "react";
import { makeStyles } from "@material-ui/core";
import { TextField } from "@material-ui/core";
import { useField } from "formik";
import { FormControl } from "@material-ui/core";

const useStyles = makeStyles((theme) => ({
  endAdo: {
    position: "relative",
    left: 235,
    bottom: 29,
  },
}));

const GlobitsTextField = ({ label,name, variant, size, type, endAdornment,validate, ...otherProps}) => {
  const [field, meta] = useField(name);

  const [value, setValue] = useState(field.value);

  const [t, setT] = useState(undefined);
  const classes = useStyles();
  const onChange = (e) => {
    e.persist();

    setValue(e.target.value);
    if (!otherProps.notDelay) {
      if (t) clearTimeout(t);
      // @ts-ignore
      setT(
        setTimeout(() => {
          if (otherProps.onChange) {
            otherProps.onChange(e);
          } else {
            field.onChange(e);
          }
        }, 500)
      );
    } else {
      if (otherProps.onChange) {
        otherProps.onChange(e);
      } else {
        field.onChange(e);
      }
    }
  };

  useEffect(() => {
    setValue(field.value);
  }, [field.value]);

  useEffect(() => {
    setValue(otherProps.value);
  }, [otherProps.value]);

  const configTextfield = {
    ...field,
    ...otherProps,
    value: value,
    id: name,
    onChange: onChange,
    fullWidth: true,
    variant: variant ? variant : "outlined",
    size: size ? size : "small",
    type: type ? type : "",
    //note
    //endAdornment: otherProps.endAdornment,
    // height: "32px",//???????????????
    InputLabelProps: {
      htmlFor: name,
      shrink: true,
    },
  };

  if (meta && meta.touched && meta.error) {
    configTextfield.error = true;
    configTextfield.helperText = meta.error;
  }

  return (
    <>
      <label htmlFor={name}>{label} {validate ? <span className="text-danger"> * </span> : <></>}</label>
      <FormControl fullWidth>
        <TextField {...configTextfield} />
        <span className={classes.endAdo}>{endAdornment}</span>
      </FormControl>
    </>
  );

  // , [value, meta.error, otherProps])
};
export default GlobitsTextField;
