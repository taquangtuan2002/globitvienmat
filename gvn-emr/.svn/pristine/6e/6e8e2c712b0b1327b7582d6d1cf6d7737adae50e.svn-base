import { makeAutoObservable } from "mobx";
import {
  pagingEthnicities,
  getEthnics,
  createEthnics,
  editEthnics,
  deleteEthnics,
  checkCode,
} from "./EthnicsService";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import i18n from "i18n";
// import { withTranslation, WithTranslation } from 'react-i18next';

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

export default class EthnicsStore {
  ethnicsList = [];
  selectedEthnics = null;
  selectedEthnicsList = [];
  totalElements = 0;
  totalPages = 0;
  page = 1;
  rowsPerPage = 10;
  keyword = "";
  loadingInitial = false;
  shouldOpenEditorDialog = false;
  shouldOpenConfirmationDialog = false;
  shouldOpenConfirmationDeleteListDialog = false;

  constructor() {
    makeAutoObservable(this);
  }

  setLoadingInitial = (state) => {
    this.loadingInitial = state;
  };

  updatePageData = (item) => {
    if (item != null) {
      this.page = 1;
      this.keyword = item.keyword;
      this.search();
    } else {
      this.search();
    }
  };

  search = async () => {
    this.loadingInitial = true;
    var searchObject = {
      keyword: this.keyword,
      pageIndex: this.page,
      pageSize: this.rowsPerPage,
    };

    try {
      let data = await pagingEthnicities(searchObject);
      this.ethnicsList = data.data.content;
      this.totalElements = data.data.totalElements;
      this.totalPages = data.data.totalPages;
      this.setLoadingInitial(false);
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.load_fail"));
      this.setLoadingInitial(false);
    }
  };

  setPage = (page) => {
    this.page = page;
    this.updatePageData();
  };

  setRowsPerPage = (event) => {
    this.rowsPerPage = event.target.value;
    this.page = 1;
    this.updatePageData();
  };

  handleChangePage = (event, newPage) => {
    this.setPage(newPage);
  };

  handleEditEthnics = (id) => {
    this.getEthnics(id).then(() => {
      this.shouldOpenEditorDialog = true;
    });
  };

  handleClose = () => {
    this.shouldOpenEditorDialog = false;
    this.shouldOpenConfirmationDialog = false;
    this.shouldOpenConfirmationDeleteListDialog = false;
    this.updatePageData();
  };

  handleDelete = (id) => {
    this.getEthnics(id).then(() => {
      this.shouldOpenConfirmationDialog = true;
    });
  };

  handleDeleteList = () => {
    this.shouldOpenConfirmationDeleteListDialog = true;
  };

  handleConfirmDelete = async () => {
    try {
      await deleteEthnics(this.selectedEthnics.id);
      toast.success(i18n.t("toast.update_success"));
      this.handleClose();
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.load_fail"));
    }
  };

  handleConfirmDeleteList = async () => {
    let listAlert = [];
    for (var i = 0; i < this.selectedEthnicsList.length; i++) {
      try {
        await deleteEthnics(this.selectedEthnicsList[i].id);
      } catch (error) {
        listAlert.push(this.selectedEthnicsList[i].name);
        console.log(error);
        console.log(listAlert.toString());
        toast.warning(i18n.t("toast.load_fail"));
      }
    }
    this.handleClose();
    toast.success(i18n.t("toast.update_success"));
  };

  getEthnics = async (id) => {
    if (id != null) {
      try {
        let data = await getEthnics(id);
        this.handleSelectEthnics(data.data);
      } catch (error) {
        console.log(error);
        toast.warning(i18n.t("toast.load_fail"));
      }
    } else {
      this.handleSelectEthnics(null);
    }
  };

  handleSelectEthnics = (ethnics) => {
    this.selectedEthnics = ethnics;
  };

  handleSelectListEthnics = (ethnics) => {
    this.selectedEthnicsList = ethnics;
  };

  createEthnics = async (ethnics) => {
    try {
      let response = await checkCode(ethnics.id, ethnics.code);
      if (response.data) {
        toast.warning(i18n.t("toast.duplicate_code"));
      } else {
        await createEthnics(ethnics);
        toast.success(i18n.t("toast.update_success"));
        this.handleClose();
      }
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.load_fail"));
    }
  };

  editEthnics = async (ethnics) => {
    try {
      let response = await checkCode(ethnics.id, ethnics.code);
      if (response.data) {
        toast.warning(i18n.t("toast.duplicate_code"));
      } else {
        await editEthnics(ethnics);
        toast.success(i18n.t("toast.update_success"));
        this.handleClose();
      }
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.load_fail"));
    }
  };
}
