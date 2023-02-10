import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH_2 = ConstantList.API_ENPOINT + "/api/concept";

export const pagingConcept = (searchObject) => {
  var url = API_PATH_2 + "/search-by-page";
  return axios.post(url, searchObject);
};

export const getConcept = (id) => {
  let url = API_PATH_2 + "/" + id;
  return axios.get(url);
};

export const createConcept = (obj) => {
  let url = API_PATH_2;
  return axios.post(url, obj);
};

export const editConcept = (obj) => {
  let url = API_PATH_2 + "/" + obj.id;
  return axios.put(url, obj);
};

export const deleteConcept = (id) => {
  let url = API_PATH_2 + "/" + id;
  return axios.delete(url);
};


export const checkCode = (id,code) => {
  const param = { params: { id: id, code: code } };
  var url = API_PATH_2 + "/check-code";
  return axios.get(url, param);
};
