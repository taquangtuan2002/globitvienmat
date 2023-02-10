import { makeAutoObservable } from "mobx";
import {
  pagingCountry,
  getCountry,
  createCountry,
  editCountry,
  deleteCountry,
  checkCode,
} from "./CountryService";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import i18n from "i18n";

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

export default class CountryStore {
  countryList = [];
  selectedCountry = null;
  selectedCountryList = [];
  totalElements = 0;
  totalPages = 0;
  page = 1;
  rowsPerPage = 10;
  text = "";
  loadingInitial = false;
  shouldOpenEditorDialog = false;
  shouldOpenConfirmationDialog = false;
  shouldOpenConfirmationDeleteListDialog = false;
  shouldOpenImportExcelDialog = false;

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
      let data = await pagingCountry(searchObject);
      this.countryList = data.data.content;
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

  handleEditCountry = (id) => {
    this.getCountry(id).then(() => {
      this.shouldOpenEditorDialog = true;
    });
  };

  handleClose = () => {
    this.shouldOpenEditorDialog = false;
    this.shouldOpenConfirmationDialog = false;
    this.shouldOpenConfirmationDeleteListDialog = false;
    this.shouldOpenImportExcelDialog = false;
    this.updatePageData();
  };

  handleDelete = (id) => {
    this.getCountry(id).then(() => {
      this.shouldOpenConfirmationDialog = true;
    });
  };

  handleDeleteList = () => {
    this.shouldOpenConfirmationDeleteListDialog = true;
  };

  handleConfirmDelete = () => {
    this.deleteCountry(this.selectedCountry.id);
  };

  handleConfirmDeleteList = async () => {
    let listAlert = [];
    for (var i = 0; i < this.selectedCountryList.length; i++) {
      try {
        await deleteCountry(this.selectedCountryList[i].id);
      } catch (error) {
        listAlert.push(this.selectedCountryList[i].name);
        console.log(error);
        console.log(listAlert.toString());
        toast.warning(i18n.t("toast.delete_fail"));
      }
    }
    this.handleClose();
    toast.success(i18n.t("toast.delete_success"));
  };

  handleSelectCountry = (country) => {
    this.selectedCountry = country;
  };

  handleSelectListCountry = (countrys) => {
    this.selectedCountryList = countrys;
  };

  getCountry = async (id) => {
    if (id != null) {
      try {
        let data = await getCountry(id);
        this.handleSelectCountry(data.data);
      } catch (error) {
        console.log(error);
        toast.warning(i18n.t("toast.get_fail"));
      }
    } else {
      this.handleSelectCountry(null);
    }
  };

  createCountry = async (country) => {
    try {
      let responseCheckCode = await checkCode(country);
      if (responseCheckCode.data) {
        toast.warning(i18n.t("toast.duplicate_code"));
      } else {
        await createCountry(country);
        toast.success(i18n.t("toast.add_success"));
        this.handleClose();
      }
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.add_fail"));
    }
  };

  editCountry = async (country) => {
    try {
      let responseCheckCode = await checkCode(country);
      if (responseCheckCode.data) {
        toast.warning(i18n.t("toast.duplicate_code"));
      } else {
        await editCountry(country);
        toast.success(i18n.t("toast.update_success"));
        this.handleClose();
      }
      
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.update_fail"));
    }
  };

  deleteCountry = async (id) => {
    try {
      await deleteCountry(id);
      toast.success(i18n.t("toast.delete_success"));
      this.handleClose();
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.delete_fail"));
    }
  };
  importExcel = () => {
    this.shouldOpenImportExcelDialog = true;
  };
}
