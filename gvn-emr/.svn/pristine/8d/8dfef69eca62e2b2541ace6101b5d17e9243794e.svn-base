import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";
import { withTranslation } from "react-i18next";

const Profile = EgretLoadable({
  loader: () => import("./ProfileIndex"),
});

const ViewComponent = withTranslation()(Profile);

const ProfileRoutes = [
  {
    path: ConstantList.ROOT_PATH + "setting/profile",
    exact: true,
    component: ViewComponent,
  },
];

export default ProfileRoutes;
