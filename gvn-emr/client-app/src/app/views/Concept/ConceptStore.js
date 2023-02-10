import { makeAutoObservable } from "mobx";
import { pagingConcept, getConcept, createConcept, editConcept, deleteConcept, checkCode, } from "./ConceptService";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import i18n from "i18n";

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

export default class ConceptStore {
  conceptList = [];
  selectedConcept = null;
  selectedConceptList = [];
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
      let data = await pagingConcept(searchObject);
      this.conceptList = data.data.content;
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

  handleEditConcept = (id) => {
    this.getConcept(id).then(() => {
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
    this.getConcept(id).then(() => {
      this.shouldOpenConfirmationDialog = true;
    });
  };

  handleDeleteList = () => {
    this.shouldOpenConfirmationDeleteListDialog = true;
  };

  handleConfirmDelete = () => {
    this.deleteConcept(this.selectedConcept.id);
  };

  handleConfirmDeleteList = async () => {
    let listAlert = [];
    for (var i = 0; i < this.selectedConceptList.length; i++) {
      try {
        await deleteConcept(this.selectedConceptList[i].id);
      } catch (error) {
        listAlert.push(this.selectedConceptList[i].name);
        console.log(error);
        console.log(listAlert.toString());
        toast.warning(i18n.t("toast.delete_fail"));
      }
    }
    this.handleClose();
    toast.success(i18n.t("toast.delete_success"));
  };

  handleSelectConcept = (concept) => {
    this.selectedConcept = concept;
  };

  handleSelectListConcept = (concepts) => {
    this.selectedConceptList = concepts;
  };

  getConcept = async (id) => {
    if (id != null) {
      try {
        let data = await getConcept(id);
        this.handleSelectConcept(data.data);
      } catch (error) {
        console.log(error);
        toast.warning(i18n.t("toast.get_fail"));
      }
    } else {
      this.handleSelectConcept(null);
    }
  };

  createConcept = async (concept) => {
    try {
      let responseCheckCode = await checkCode(concept.id ,concept.code );
      if (responseCheckCode.data) {
        toast.warning(i18n.t("toast.duplicate_code"));
      } else {
        await createConcept(concept);
        toast.success(i18n.t("toast.add_success"));
        this.handleClose();
      }
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.add_fail"));
    }
  };

  editConcept = async (concept) => {
    try {
      let responseCheckCode = await checkCode(concept.id ,concept.code);
      if (responseCheckCode.data) {
        toast.warning(i18n.t("toast.duplicate_code"));
      } else {
        await editConcept(concept);
        toast.success(i18n.t("toast.update_success"));
        this.handleClose();
      }

    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.update_fail"));
    }
  };

  deleteConcept = async (id) => {
    try {
      await deleteConcept(id);
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
