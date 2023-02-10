import React, { Component } from "react";
import { Breadcrumb } from "egret";

import { withStyles } from "@material-ui/styles";

class Analytics extends Component {
  render() {
    const { t } = this.props;
    return (
      <div className="analytics m-sm-30">
        <div className="mb-sm-30">
          <Breadcrumb
            routeSegments={[
              { name: "Dashboard", path: "/dashboard" },
              { name: t("Dashboard.analytics.title") },
            ]}
          />
        </div>
      </div>
    );
  }
}

export default withStyles({}, { withTheme: true })(Analytics);
