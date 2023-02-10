import React, { useState, useEffect } from "react";
import Autocomplete from "@material-ui/lab/Autocomplete";
import { FastField, getIn } from "formik";
import { isEqual } from "lodash";
import {
  TextField,
  makeStyles,
} from "@material-ui/core";


// GlobitsPagingAutocompleteV2
const PAGE_SIZE = 20;
const useStyles = makeStyles((theme) => ({
  container: {
    "& .MuiAutocomplete-inputRoot": {
      padding: "2px 8px !important",
    },
  },
}));

const GlobitsPagingAutocompleteV2 = (props) => {
  return (
    <FastField
      {...props}
      name={props.name}
      shouldUpdate={shouldComponentUpdate}
    >
      {({ field, meta, form }) => {
        return (
          <MyPagingAutocomplete
            {...props}
            field={field}
            meta={meta}
            setFieldValue={form.setFieldValue}
          />
        );
      }}
    </FastField>
  );
};

function MyPagingAutocomplete({
  api,
  name,
  searchObject,
  allowLoadOptions = true,
  clearOptionOnClose,
  handleChange,
  field,
  meta,
  setFieldValue,
  label,
  ...otherProps
}) {
  const [page, setPage] = useState(1);
  const [options, setOptions] = useState([]);
  const [loading, setLoading] = React.useState(false);
  const [keyword, setKeyword] = useState("");
  const [firstLoading, setFirstLoading] = React.useState(true);
  const [totalPage, setTotalPage] = React.useState(1);
  const [open, setOpen] = React.useState(false);
  const [t, setT] = React.useState();
  const [typing, setTyping] = React.useState(false);

  const classes = useStyles();

  useEffect(() => {
    if (loading && allowLoadOptions) {
      loadMoreResults();
    }
  }, [page, loading]);

  useEffect(() => {
    if (open && allowLoadOptions) {
      getData();
    }
  }, [keyword, open, searchObject]);

  const getData = () => {
    let newPage = 1;
    setPage(newPage);
    api({
      ...searchObject,
      pageIndex: newPage,
      pageSize: PAGE_SIZE,
      keyword,
    }).then(({ data }) => {
      if (data && data.content) {
        setOptions([...data.content]);
        setTotalPage(data.totalPages);
      }
    });
  };

  const loadMoreResults = () => {
    const nextPage = page + 1;

    setPage(nextPage);
    api({
      ...searchObject,
      pageIndex: nextPage,
      pageSize: PAGE_SIZE,
      keyword,
    }).then(({ data }) => {
      if (data && data.content) {
        setOptions([...options, ...data.content]);
        setTotalPage(data.totalPages);
      }
    });
  };

  const handleScroll = (event) => {
    const listboxNode = event.currentTarget;

    const position = listboxNode.scrollTop + listboxNode.clientHeight;
    if (listboxNode.scrollHeight - position <= 8 && page < totalPage) {
      loadMoreResults();
    }
  };

  const onOpen = () => {
    setOpen(true);
    if (firstLoading && allowLoadOptions) {
      getData();
    }
    setFirstLoading(false);
  };

  const onClose = () => {
    setOpen(false);
    if (clearOptionOnClose) {
      setOptions([]);
      setTotalPage(1);
    }
  };

  const handleChangeText = (value) => {
    setTyping(true);
    if (t) clearTimeout(t);
    // @ts-ignore
    setT(
      setTimeout(() => {
        setKeyword(value);
        setTyping(false);
      }, 500)
    );
  };

  const defaultHandleChange = (_, value) => {
    setFieldValue(name, value ? value : null);
  };

  const defaultGetOptionLabel = (option) =>
    option[otherProps?.displayName ? otherProps?.displayName : "name"];

  return (
    <Autocomplete
      {...field}
      {...otherProps}
      id={name}
      options={options}
      loading={loading}
      onOpen={onOpen}
      open={open}
      onClose={onClose}
      className={classes.container}
      onChange={handleChange || defaultHandleChange}
      getOptionSelected={(option, value) => option?.id === value?.id}
      getOptionLabel={
        otherProps?.getOptionLabel
          ? otherProps.getOptionLabel
          : defaultGetOptionLabel
      }
      onKeyDown={(event) => {
        if (event.key === "Enter") {
          event.stopPropagation();
          event.preventDefault();
        }
        return true;
      }}
      onInputChange={(event) => handleChangeText(event?.target?.value)}
      renderInput={(params) => (
        <>
          <label htmlFor={name}>{label}</label>
          <TextField
            {...params}

            variant={otherProps?.variant || "outlined"}
            inputProps={{
              ...params.inputProps,
              autoComplete: "off", // disable autocomplete and autofill
            }}
            error={meta && meta.touched && meta.error}
            helperText={meta && meta.touched && meta.error ? meta.error : ""}
          />
        </>
      )}
      ListboxProps={{
        onScroll: handleScroll,
      }}
    />
  );
}

const shouldComponentUpdate = (nextProps, currentProps) => {
  return (
    nextProps.name !== currentProps.name ||
    nextProps.value !== currentProps.value ||
    nextProps.handleChange !== currentProps.handleChange ||
    nextProps.label !== currentProps.label ||
    nextProps.required !== currentProps.required ||
    nextProps.api !== currentProps.api ||
    nextProps.disabled !== currentProps.disabled ||
    nextProps.readOnly !== currentProps.readOnly ||
    !isEqual(nextProps.searchObject, currentProps.searchObject) ||
    nextProps.formik.isSubmitting !== currentProps.formik.isSubmitting ||
    Object.keys(nextProps).length !== Object.keys(currentProps).length ||
    getIn(nextProps.formik.values, currentProps.name) !==
      getIn(currentProps.formik.values, currentProps.name) ||
    getIn(nextProps.formik.errors, currentProps.name) !==
      getIn(currentProps.formik.errors, currentProps.name) ||
    getIn(nextProps.formik.touched, currentProps.name) !==
      getIn(currentProps.formik.touched, currentProps.name)
  );
};

export default React.memo(GlobitsPagingAutocompleteV2);

