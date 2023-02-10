import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";

const ConceptTypeIndex = EgretLoadable({ loader: () => import("./ConceptTypeIndex") });

const Routes = [
  {
    path: ConstantList.ROOT_PATH + "category/concepttype",
    exact: true,
    component: ConceptTypeIndex,
  },
];

export default Routes;
