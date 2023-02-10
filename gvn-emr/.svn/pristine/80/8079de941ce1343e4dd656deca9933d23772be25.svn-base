import { observer } from "mobx-react";
import React, { useEffect } from "react";
import { useStore } from "../../stores";
import { Grid, makeStyles } from "@material-ui/core";
import GlobitsBreadcrumb from "../../common/GlobitsBreadcrumb";
import { toast } from "react-toastify";
import { Link } from "react-router-dom";
import FaceIcon from "@material-ui/icons/Face";
import NoteOutlinedIcon from "@material-ui/icons/NoteOutlined";
import LocalMallOutlinedIcon from "@material-ui/icons/LocalMallOutlined";
import AdminView from './AdminView';
import UserView from './UserView';

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

const useStyles = makeStyles((theme) => ({
  root: {
    top: "10px",
  },
  card: {
    background: "#ffffff",
    padding: "1.25rem",
  },
  cardBody: {
    display: "flex",
    minHeight: "1px",
  },
  cardInfo: {
    marginLeft: "auto",
    fontSize: "30px",
  },
  cardDevider: {
    height: "6px",
    borderRadius: "0.25rem",
  },
  cardTotal: {
    height: "100%",
    border: "none",
    borderRadius: "0px",
    margin: "0 4px",
  },
}));

export default observer(function DashboardIndex(props) {

  const { dashboardStore } = useStore();

  const classes = useStyles();

  const {
    isAdmin,
    isUser,
    staffNumber,
    monthaskNumber,
    projectNumber,
    updatePageData,
  } = dashboardStore;

  useEffect(() => {
    updatePageData();
  }, [updatePageData]);

  return (
    <div className="content-index">
      <div className="index-breadcrumb">
        <GlobitsBreadcrumb routeSegments={[{ name: "Dashboard" }]} />
      </div>
      <Grid container spacing={1}>
        <Grid item md={4} xs={12} >
          <Link to="/staff">
            <div className={classes.card}>
              <div className={classes.cardBody}>
                <div className={classes.cardContent}>
                  <FaceIcon />
                  <p className="text-secondary">Tổng số nhân viên</p>
                </div>
                <div className={classes.cardInfo}>
                  <span className="text-secondary">{staffNumber}</span>
                </div>
              </div>
              <div className={`${classes.cardDevider} bgc-secondary`} />
            </div>
          </Link>
        </Grid>

        <Grid item md={4} xs={12} >
          <Link to="/timesheet/list">
            <div className={classes.card}>
              <div className={classes.cardBody}>
                <div className={classes.cardContent}>
                  <NoteOutlinedIcon />
                  <p className="text-primary">Đầu việc trong tháng</p>
                </div>
                <div className={classes.cardInfo}>
                  <span className="text-primary">{monthaskNumber}</span>
                </div>
              </div>
              <div className={`${classes.cardDevider} bgc-primary`} />
            </div>
          </Link>
        </Grid>

        <Grid item md={4} xs={12} >
          <Link to="/timesheet/project">
            <div className={classes.card}>
              <div className={classes.cardBody}>
                <div className={classes.cardContent}>
                  <LocalMallOutlinedIcon />
                  <p className="text-purple">Tổng dự án</p>
                </div>
                <div className={classes.cardInfo}>
                  <span className="text-purple">{projectNumber}</span>
                </div>
              </div>
              <div className={`${classes.cardDevider} bgc-purple`} />
            </div>
          </Link>
        </Grid>
        <Grid item xs={12}>
          {isAdmin && <AdminView />}
        </Grid>
        <Grid item xs={12}>
          {isUser && <UserView />}
        </Grid>
      </Grid>
    </div>
  );
});
