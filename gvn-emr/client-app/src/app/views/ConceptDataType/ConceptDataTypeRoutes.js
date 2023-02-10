import { EgretLoadable } from "egret";
import ConstantList from "../../appConfig";

const ConceptDataTypeIndex = EgretLoadable({ loader: () => import("./ConceptDataTypeIndex") });

const Routes = [
  {
    path: ConstantList.ROOT_PATH + "category/conceptdatatype",
    exact: true,
    component: ConceptDataTypeIndex,
  },
];

export default Routes;
