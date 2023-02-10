import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH = ConstantList.API_ENPOINT + "/api/country";
const API_PATH_2 = ConstantList.API_ENPOINT + "/api/concept-type";

export const pagingConceptType = (searchObject) => {
  var url = API_PATH_2 + "/search-by-page";
  return axios.post(url, searchObject);
};

export const getAllCountries = () => {
  var url = API_PATH_2 + "/getAllCountries";
  return axios.get(url);
};

export const getConceptType = (id) => {
  let url = API_PATH_2 + "/" + id;
  return axios.get(url);
};

export const createConceptType = (obj) => {
  let url = API_PATH_2;
  return axios.post(url, obj);
};

export const editConceptType = (obj) => {
  let url = API_PATH_2 + "/" + obj.id;
  return axios.put(url, obj);
};

export const deleteConceptType = (id) => {
  let url = API_PATH_2 + "/" + id;
  return axios.delete(url);
};

export const checkCodeConceptType = (obj) => {
  let url = API_PATH_2 + "/check-code";
  return axios.post(url, obj);
};
