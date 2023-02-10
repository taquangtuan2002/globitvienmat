import { createContext, useContext } from "react";
import RoleStore from "./views/Role/RoleStore";
import EthnicsStore from "./views/Ethnics/EthnicsStore";
import ConceptAttributeTypeStore from "./views/ConceptAttributeType/ConceptAttributeTypeStore";
import ConceptStore from "./views/Concept/ConceptStore";
import ConceptDataTypeStore from "./views/ConceptDataType/ConceptDataTypeStore";
import CountryStore from "./views/Country/CountryStore";
import DepartmentStore from "./views/Department/DepartmentStore";
import DashboardStore from "./views/dashboard/DashboardStore";
import ProfileStore from "./views/profile/ProfileStore";
import ConceptTypeStore from './views/ConceptType/ConceptTypeStore';

export const store = {
  roleStore: new RoleStore(),
  ethnicsStore: new EthnicsStore(),
  conceptAttributeTypeStore: new ConceptAttributeTypeStore(),
  conceptStore: new ConceptStore(),
  conceptDataTypeStore: new ConceptDataTypeStore(),
  countryStore: new CountryStore(),
  departmentStore: new DepartmentStore(),
  dashboardStore: new DashboardStore(),
  profileStore: new ProfileStore(),
  conceptTypeStore:new ConceptTypeStore(),
};

export const StoreContext = createContext(store);

export function useStore() {
  return useContext(StoreContext);
}
