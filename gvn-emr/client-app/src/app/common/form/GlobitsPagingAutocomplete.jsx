import React, { useEffect } from "react";
import { TextField, makeStyles, Paper, MenuItem } from "@material-ui/core";
import { useField } from "formik";
import { AsyncPaginate, wrapMenuList } from "react-select-async-paginate";
import { useFormikContext } from "formik";
import PropTypes from "prop-types";

const useStyles = makeStyles((theme) => ({
  input: {
    "& .MuiInputBase-input": {
      display: "flex",
      padding: 0,
      height: "auto",
    },

    "& .Mui-error": {
      border: "1px solid red",
    },
  },
  paper: {
    position: "absolute",
    zIndex: 100,
    marginTop: theme.spacing(1),
    left: 0,
    right: 0,
  },
  option: {
    display: "block",
    maxHeight: "200px",
    overflow: "scroll",
    padding: 0,
    position: "absolute",
    zIndex: 100,
    left: 0,
    right: 0,
    background: "#fff",
    "&.MuiListItem-button:hover": {
      backgroundColor: "#fff!important",
    },
  },
}));

const GlobitsPagingAutocomplete = ({
  name,
  label,
  variant,
  size,
  api,
  displayName,
  searchObject,
  disabled,
  defaultValue,
  getOptionLabel,
  getOptionValue,
  allowLoadMoreOption = true,
  clearOption = false,
  ...otherProps
}) => {
  const [field, meta] = useField(name);
  const classes = useStyles();
  const MenuList = wrapMenuList(Option);
  const { setFieldValue } = useFormikContext();

  useEffect(() => {
    if (defaultValue) handleChange(defaultValue);
  }, [defaultValue]);

  const loadOptions = async (search, loadedOptions, { page }) => {
    let searchObj = {
      keyword: search,
      pageIndex: page,
      pageSize: 10,
    };
    if (searchObject) {
      searchObj = {
        ...searchObject,
        keyword: search,
        pageIndex: page,
        pageSize: 10,
      };
    }
    if (!allowLoadMoreOption) {
      return {
        options: [],
        hasMore: false,
        additional: {
          page: 1,
        },
      };
    }

    let response = await api(searchObj);
    if (response.data && response.data.content) {
      return {
        options: response.data.content,
        hasMore: response.data.content.length > 1,
        additional: {
          page: page + 1,
        },
      };
    } else {
      return {
        options: [],
        hasMore: false,
        additional: {
          page: 1,
        },
      };
    }
  };

  const handleChange = (value) => {
    if (otherProps?.handleChange) {
      otherProps.handleChange(value);
      setFieldValue(name, value ? value : null);
    } else {
      setFieldValue(name, value ? value : null);
    }
  };

  const getDefaultOptionLabel = (option) => {
    return option[displayName ? displayName : "name"];
  };
  const getDefaultOptionValue = (option) => {
    return option[displayName ? displayName : "name"];
  };

  function inputComponent({ inputRef, ...props }) {
    return <div ref={inputRef} {...props} />;
  }

  inputComponent.propTypes = {
    inputRef: PropTypes.oneOfType([PropTypes.func, PropTypes.object]),
  };

  function Control(props) {
    const {
      children,
      innerProps,
      innerRef,
      selectProps: { TextFieldProps },
    } = props;

    return (
      <>
        <label htmlFor={name}>{label}</label>
        <TextField
          InputProps={{
            inputComponent,
            className: classes.input,
            inputProps: {
              ref: innerRef,
              children,
              ...innerProps,
            },
          }}
          {...TextFieldProps}
        />
      </>
    );
  }

  function Option(props) {
    return (
      <MenuItem
        disabled={disabled}
        className={classes.option}
        ref={props.innerRef}
        selected={props.isFocused}
        component="div"
        style={{
          fontWeight: props.isSelected ? 500 : 400,
        }}
        {...props.innerProps}
      >
        {props.children}
      </MenuItem>
    );
  }

  Option.propTypes = {
    children: PropTypes.node,
    innerProps: PropTypes.object,
    innerRef: PropTypes.oneOfType([PropTypes.func, PropTypes.object]),
    isFocused: PropTypes.bool,
    isSelected: PropTypes.bool,
  };

  Control.propTypes = {
    children: PropTypes.node,
    innerProps: PropTypes.object,
    innerRef: PropTypes.oneOfType([PropTypes.func, PropTypes.object]),
    selectProps: PropTypes.object.isRequired,
  };

  function Menu(props) {
    return (
      <Paper square className={classes.paper} {...props.innerProps}>
        {props.children}
      </Paper>
    );
  }

  Menu.propTypes = {
    children: PropTypes.node,
    innerProps: PropTypes.object,
    selectProps: PropTypes.object,
  };

  const components = {
    Control,
    Menu,
    MenuList,
  };

  const configPagingAutocomplete = {
    ...field,
    ...otherProps,
    placeholder: false,
    isClearable: true,
    loadOptionsOnMenuOpen: !disabled,
    isDisabled: disabled,
    loadOptions: loadOptions,
    onChange: handleChange,
    getOptionLabel: getOptionLabel ? getOptionLabel : getDefaultOptionLabel,
    getOptionValue: getOptionValue ? getOptionValue : getDefaultOptionValue,

    additional: {
      page: 1,
    },
    inputId: name,
    TextFieldProps: {
      fullWidth: true,
      size: size ? size : "small",
      // label: label,
      variant: variant ? variant : "outlined",
      InputLabelProps: {
        htmlFor: name,
        shrink: true,
      },
    },
    components: components,
  };

  if (meta && meta.touched && meta.error) {
    configPagingAutocomplete.TextFieldProps.error = true;
    configPagingAutocomplete.TextFieldProps.helperText = meta.error;
  }

  return (
    <>
      <AsyncPaginate {...configPagingAutocomplete} />
      {/* <ErrorMessage
        component="p"
        name={name}
        style={{ color: "red", fontSize: "12px", margin: "4px 14px 0" }}
      /> */}
    </>
  );
};

export default GlobitsPagingAutocomplete;
