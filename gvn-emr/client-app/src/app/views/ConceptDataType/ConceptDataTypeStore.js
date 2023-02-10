import { makeAutoObservable } from "mobx";
import { pagingConceptDataType, getConceptDataType, createConceptDataType, editConceptDataType, deleteConceptDataType, checkCode, } from "./ConceptDataTypeService";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import i18n from "i18n";

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

export default class ConceptDataTypeStore {
  conceptDataTypeList = [];
  selectedConceptDataType = null;
  selectedConceptDataTypeList = [];
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
      let data = await pagingConceptDataType(searchObject);
      this.conceptDataTypeList = data.data.content;
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

  handleEditConceptDataType = (id) => {
    this.getConceptDataType(id).then(() => {
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
    this.getConceptDataType(id).then(() => {
      this.shouldOpenConfirmationDialog = true;
    });
  };

  handleDeleteList = () => {
    this.shouldOpenConfirmationDeleteListDialog = true;
  };

  handleConfirmDelete = () => {
    this.deleteConceptDataType(this.selectedConceptDataType.id);
  };

  handleConfirmDeleteList = async () => {
    let listAlert = [];
    for (var i = 0; i < this.selectedConceptDataTypeList.length; i++) {
      try {
        await deleteConceptDataType(this.selectedConceptDataTypeList[i].id);
      } catch (error) {
        listAlert.push(this.selectedConceptDataTypeList[i].name);
        console.log(error);
        console.log(listAlert.toString());
        toast.warning(i18n.t("toast.delete_fail"));
      }
    }
    this.handleClose();
    toast.success(i18n.t("toast.delete_success"));
  };

  handleSelectConceptDataType = (conceptDataType) => {
    this.selectedConceptDataType = conceptDataType;
  };

  handleSelectListConceptDataType = (conceptDataTypes) => {
    this.selectedConceptDataTypeList = conceptDataTypes;
  };

  getConceptDataType = async (id) => {
    if (id != null) {
      try {
        let data = await getConceptDataType(id);
        this.handleSelectConceptDataType(data.data);
      } catch (error) {
        console.log(error);
        toast.warning(i18n.t("toast.get_fail"));
      }
    } else {
      this.handleSelectConceptDataType(null);
    }
  };

  createConceptDataType = async (conceptDataType) => {
    try {
      let responseCheckCode = await checkCode(conceptDataType.id ,conceptDataType.code );
      if (responseCheckCode.data) {
        toast.warning(i18n.t("toast.duplicate_code"));
      } else {
        await createConceptDataType(conceptDataType);
        toast.success(i18n.t("toast.add_success"));
        this.handleClose();
      }
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.add_fail"));
    }
  };

  editConceptDataType = async (conceptDataType) => {
    try {
      let responseCheckCode = await checkCode(conceptDataType.id ,conceptDataType.code);
      if (responseCheckCode.data) {
        toast.warning(i18n.t("toast.duplicate_code"));
      } else {
        await editConceptDataType(conceptDataType);
        toast.success(i18n.t("toast.update_success"));
        this.handleClose();
      }

    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.update_fail"));
    }
  };

  deleteConceptDataType = async (id) => {
    try {
      await deleteConceptDataType(id);
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
