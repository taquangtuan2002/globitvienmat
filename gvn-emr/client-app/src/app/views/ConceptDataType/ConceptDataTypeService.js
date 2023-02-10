import axios from "axios";
import ConstantList from "../../appConfig";
const API_PATH_2 = ConstantList.API_ENPOINT + "/api/concept-data-type";

export const pagingConceptDataType = (searchObject) => {
  var url = API_PATH_2 + "/search-by-page";
  return axios.post(url, searchObject);
};

export const getConceptDataType = (id) => {
  let url = API_PATH_2 + "/" + id;
  return axios.get(url);
};

export const createConceptDataType = (obj) => {
  let url = API_PATH_2;
  return axios.post(url, obj);
};

export const editConceptDataType = (obj) => {
  let url = API_PATH_2 + "/" + obj.id;
  return axios.put(url, obj);
};

export const deleteConceptDataType = (id) => {
  let url = API_PATH_2 + "/" + id;
  return axios.delete(url);
};


export const checkCode = (id,code) => {
  const param = { params: { id: id, code: code } };
  var url = API_PATH_2 + "/check-code";
  return axios.get(url, param);
};
