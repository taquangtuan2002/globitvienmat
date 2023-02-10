import React, { useState } from "react";
import PropTypes from "prop-types";
import { makeStyles } from "@material-ui/core/styles";
import { useTranslation } from "react-i18next";
import { Typography, Tabs, Box, Tab, AppBar } from "@material-ui/core";
import NumberFormat from "react-number-format";
import ProfileInformation from "./ProfileInformation";

function NumberFormatCustom(props) {
  const { inputRef, onChange, ...other } = props;
  return (
    <NumberFormat
      {...other}
      getInputRef={inputRef}
      onValueChange={(values) => {
        props.onChange({
          target: {
            name: props.name,
            value: values.value,
          },
        });
      }}
      name={props.name}
      value={props.value}
      thousandSeparator
      isNumericString
    />
  );
}

NumberFormatCustom.propTypes = {
  inputRef: PropTypes.func.isRequired,
  name: PropTypes.string.isRequired,
  onChange: PropTypes.func.isRequired,
};

function TabPanel(props) {
  const { children, value, index, ...other } = props;
  return (
    <React.Fragment>
      <div
        role="tabpanel"
        hidden={value !== index}
        id={`scrollable-force-tabpanel-${index}`}
        aria-labelledby={`scrollable-force-tab-${index}`}
        {...other}
      >
        {value === index && (
          <Box p={3}>
            <Typography>{children}</Typography>
          </Box>
        )}
      </div>
    </React.Fragment>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.any.isRequired,
  value: PropTypes.any.isRequired,
};

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    width: "100%",
    backgroundColor: theme.palette.background.paper,
  },
  tabHeader: {
    // boxShadow: "none!important",
    boxShadow:
      "rgba(50, 50, 105, 0.15) 0px 2px 5px 0px, rgba(0, 0, 0, 0.05) 0px 1px 1px 0px !important",
    marginBottom: "15px",

    "& .MuiTab-wrapper": {
      fontWeight: "500",
    },
  },
  indicator: {
    display: "none",
  },
}));

export default function ProfileTab() {
  const { t } = useTranslation();
  const classes = useStyles();

  const [value, setValue] = useState(0);

  const handleChangeValue = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <div className={`${classes.root} profile-tab`} value={value} index={0}>
      <AppBar className={classes.tabHeader} position="static" color="#ffffff">
        <Tabs
          orientation="horizontal"
          value={value}
          onChange={handleChangeValue}
          variant="scrollable"
          scrollButtons="on"
          textColor="primary"
          aria-label="staff tabs"
          classes={{
            indicator: classes.indicator,
          }}
        >
          <Tab label={t("humanResourcesInformation.personalInformation")} />
          <Tab label={t("humanResourcesInformation.accountInformation")} />
        </Tabs>
      </AppBar>
      <div className="dialog-body">
        <TabPanel value={value} index={0} style={{ height: "auto" }} color="#ffffff" >
          <ProfileInformation />
        </TabPanel>
        <TabPanel value={value} index={1} style={{ height: "auto" }} color="#ffffff"   >
        </TabPanel>
      </div>
    </div>
  );
}
