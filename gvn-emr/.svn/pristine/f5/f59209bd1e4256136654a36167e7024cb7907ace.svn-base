import { observer } from "mobx-react";
import React, { useEffect } from "react";
import { useStore } from "../../stores";
import { Grid } from "@material-ui/core";
import GlobitsBreadcrumb from "../../common/GlobitsBreadcrumb";
import ProfileDetail from "./ProfileDetail";
import BriefInformation from "./BriefInformation";

export default observer(function ProfileIndex() {
  const { profileStore } = useStore();
  const { updatePageData } = profileStore;

  useEffect(() => {
    updatePageData();
  }, [updatePageData]);

  return (
    <div className="content-index">
      <div className="index-breadcrumb">
        <GlobitsBreadcrumb routeSegments={[{ name: "Thông tin tài khoản" }]} />
      </div>
      <Grid container spacing={3}>
        <Grid item md={3} xs={12}>
          <BriefInformation />
        </Grid>
        <Grid item md={9} xs={12}>
          <ProfileDetail />
        </Grid>
      </Grid>
    </div>
  );
});
