import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";

const ConceptIndex = EgretLoadable({ loader: () => import("./ConceptIndex") });

const Routes = [
  {
    path: ConstantList.ROOT_PATH + "category/concept",
    exact: true,
    component: ConceptIndex,
  },
];

export default Routes;
