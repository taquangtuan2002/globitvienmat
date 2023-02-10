import React from "react";
import { Redirect } from "react-router-dom";
import sessionRoutes from "./views/sessions/SessionRoutes";
import dashboardRoutes from "./views/dashboard/DashboardRoutes";
import roleRoutes from "./views/Role/RoleRoutes";
import ConstantList from "./appConfig";
import ethnicsRoutes from "./views/Ethnics/EthnicsRoutes";
import ConceptAttributeTypeRoutes from "./views/ConceptAttributeType/ConceptAttributeTypeRoutes";
import ConceptRoutes from "./views/Concept/ConceptRoutes";
import ConceptDataTypeRoutes from "./views/ConceptDataType/ConceptDataTypeRoutes";
import countryRoutes from "./views/Country/CountryRoutes";
import DepartmentRoutes from "./views/Department/DepartmentRoutes";
import ProfileRoutes from "./views/profile/ProfileRoutes";
import ConceptTypeRoutes from './views/ConceptType/ConceptTypeRoutes';

const redirectRoute = [
  {
    path: ConstantList.ROOT_PATH,
    exact: true,
    component: () => <Redirect to={ConstantList.HOME_PAGE} />, //Luôn trỏ về HomePage được khai báo trong appConfig
  },
];

const errorRoute = [
  {
    component: () => (
      <Redirect to={ConstantList.ROOT_PATH + "setting/profile"} />
    ),
  },
];

const setting = {
  auth: ["ROLE_ADMIN", "HR_MANAGER"],
  path: ConstantList.ROOT_PATH + "setting",
  children: [...roleRoutes],
};


const categoryRoutes = {
  auth: ["ROLE_ADMIN"],
  path: ConstantList.ROOT_PATH + "category",
  children: [
    ...ethnicsRoutes,
    ...countryRoutes,
    ...DepartmentRoutes,
  ],
};

const routes = [
  ...sessionRoutes,
  ...dashboardRoutes,
  ...redirectRoute,
  categoryRoutes,
  setting,
  ...ProfileRoutes,
  ...ConceptAttributeTypeRoutes,
  ...ConceptRoutes,
  ...ConceptDataTypeRoutes,
  ...ConceptTypeRoutes,    
  ...errorRoute,
];

export default routes;
