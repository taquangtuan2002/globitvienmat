import { observer } from "mobx-react";
import React from "react";
import { useStore } from "../../stores";
import { Grid, makeStyles, useMediaQuery, useTheme } from "@material-ui/core";
import GlobitsImageUpload from "../../common/form/FileUpload/GlobitsImageUpload";

const useStyles = makeStyles((theme) => ({
  root: {
    padding: "15px",
  },
  personCard: {
    textAlign: "center",
  },
  personName: {
    fontWeight: 500,
    marginTop: "10px",
    marginBottom: "0.75rem",
  },
  personTitle: {
    fontWeight: 300,
    marginBottom: "15px",
    color: "#6c757d",
  },
  personIdent: {
    "& p": {
      marginTop: 0,
    },
  },
  devider: {
    marginTop: "1rem",
    marginBottom: "1rem",
    border: 0,
    borderTop: "1px solid rgba(0,0,0,.1)",
    width: "100%",
  },
}));

export default observer(function BriefInformation() {
  const { profileStore } = useStore();
  const classes = useStyles();

  const theme = useTheme();
  const isMobileXS = useMediaQuery(theme.breakpoints.down("xs"));
  const isMD_UP = useMediaQuery(theme.breakpoints.up("md"));

  const { currentStaff, uploadImage, currentUser } = profileStore;

  const handleChangeImg = (name, value) => {
    uploadImage(value);
  };
  return (
    <Grid container className={`index-card ${classes.root}`}>
      <Grid item xs={6} md={12} className={classes.personCard}>
        <GlobitsImageUpload
          field="file"
          onChange={handleChangeImg}
          imagePath={currentStaff?.imagePath}
          disableButton
        />
        <h4 className={classes.personName}>{currentStaff?.displayName}</h4>
        <h6 className={classes.personTitle}>
          {currentStaff?.department?.name}
        </h6>
      </Grid>
      {(isMobileXS || isMD_UP) && <div className={classes.devider} />}
      <Grid item xs={6} md={12} className={classes.personIdent}>
        <small className="text-muted">Tên tài khoản</small>
        <p>{currentUser?.username}</p>
        <small className="text-muted">Quyền</small>
        <p>
          {currentUser?.roles?.length === 1
            ? currentUser?.roles?.[0]?.name
            : currentUser?.roles?.map((role) => {
                const string = ", ";
                return role?.name + string;
              })}
        </p>
        <small className="text-muted">Email</small>
        <p>{currentStaff?.email}</p>
        <small className="text-muted">Số điện thoại</small>
        <p>{currentStaff?.phoneNumber}</p>
        <small className="text-muted">Địa chỉ</small>
        <p>{currentStaff?.permanentResidence}</p>
      </Grid>
    </Grid>
  );
});
