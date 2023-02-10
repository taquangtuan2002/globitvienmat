import { makeAutoObservable } from "mobx";
import { pagingConceptType, editConceptType, createConceptType, getConceptType, deleteConceptType, } from "./ConceptTypeService";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import i18n from "i18n";

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

export default class CountryStore {
  listCocneptType = [];
  totalElements = 0;
  totalPages = 0;
  pageIndex = 1;
  pageSize = 10;
  keyword = "";
  openPopup = false;
  dataConceptTypeEdit = null;
  openPopupConfirmDelete = false;
  idConceptTypeDelete = null;
  loadingInitial = false;
  selectedCountryList = [];

  constructor() {
    makeAutoObservable(this);
  }

  getListConceptType = () => {
    this.loadingInitial = true;
    pagingConceptType({ pageIndex: this.pageIndex, pageSize: this.pageSize, keyword: this.keyword }).then((response) => {
      this.listCocneptType = response.data.content;
      this.totalElements = response.data.totalElements;
      this.totalPages = response.data.totalPages;
    }).catch(() => {
      toast.warning(i18n.t("toast.load_fail"));
    });
    this.loadingInitial = false;;
  };

  handleSubmitFormConceptType = (objectConceptType) => {
    if (objectConceptType.id) {
      editConceptType(objectConceptType);
    } else {
      createConceptType(objectConceptType)
    }
  }

  openPopupConfirmDetele = (idConceptType) => {
    this.idConceptTypeDelete = idConceptType;
    this.openPopupConfirmDelete = true;
  }

  handleDeleteConceptType = () => {
    deleteConceptType(this.idConceptType).then(() => {
      this.getListConceptType();
    }).catch(() => {
      toast.warning(i18n.t("toast.load_fail"));
    })
  }

  handleOpenPopupForm = (idConceptType) => {
    this.openPopup = true;
    if (idConceptType) {
      getConceptType(idConceptType).then(({ data }) => {
        this.dataConceptTypeEdit = data;
      }).catch(() => {
        toast.warning(i18n.t("toast.load_fail"));
      });
    } else {
      this.dataConceptTypeEdit = { id: null, code: null, name: null, description: null, };
    }
  }

  handleClosePopup = () => {
    this.openPopup = false;
    this.openPopupConfirmDelete = false;
  };

  handleChangePageIndex = (event, values) => {
    this.pageIndex = values;
    this.getListConceptType();
  }

  handleChangePageSize = (event, values) => {
    this.pageSize = event.target.value;
    this.getListConceptType();
  }

  handleChangeKeyWord = ({ keyword }) => {
    this.keyword = keyword;
    this.getListConceptType();
  }
}
