import { makeAutoObservable } from "mobx";
import {
  pagingDepartments,
  getDepartment,
  createDepartment,
  editDepartment,
  deleteDepartment,
  checkCode,
} from "./DepartmentService";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import i18n from "i18n";
// import { withTranslation, WithTranslation } from 'react-i18next';

toast.configure({
  autoClose: 2000,
  draggable: false,
  limit: 3,
});

export default class DepartmentStore {
  departmentList = [];
  selectDepartmentList = [
    {
      label: "",
      value: "",
      children: [],
    },
  ];
  selectedDepartment = null;
  // selectedParentDepartment = null;
  selectedDepartmentList = [];
  totalElements = 0;
  totalPages = 0;
  page = 1;
  rowsPerPage = 10;
  keyword = "";
  loadingInitial = false;
  shouldOpenEditorDialog = false;
  shouldOpenConfirmationDialog = false;
  shouldOpenConfirmationDeleteListDialog = false;
  shouldOpenDepartmentPopup = false;
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
    var searchObject = {};
    searchObject.keyword = this.keyword;
    searchObject.pageIndex = this.page;
    searchObject.pageSize = this.rowsPerPage;

    try {
      let data = await pagingDepartments(searchObject);

      var treeValues = [];

      let itemListClone = data.data.content;

      itemListClone.forEach((item) => {
        var items = this.getListItemChild(item);

        treeValues.push(...items);
      });
      this.departmentList = treeValues;

      this.totalElements = data.data.totalElements;
      this.totalPages = data.data.totalPages;
      this.setLoadingInitial(false);
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.load_fail"));
      this.setLoadingInitial(false);
    }
  };

  searchSelectListDepartment = async () => {
    var searchObject = {};
    searchObject.keyword = this.keyword;
    searchObject.pageIndex = this.page;
    searchObject.pageSize = this.rowsPerPage;
    let data = await pagingDepartments(searchObject);
    var treeValues = [];
    let itemListClone = data.data.content;
    itemListClone.forEach((item) => {
      var selectList = this.getSelectListDepartment(item);
      treeValues.push(...selectList);
    });
    this.selectDepartmentList = treeValues;
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

  handleEditDepartment = (id) => {
    this.getDepartment(id).then(() => {
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
    this.getDepartment(id).then(() => {
      this.shouldOpenConfirmationDialog = true;
    });
  };

  handleToggleDepartmentPopup = () => {
    if (this.shouldOpenDepartmentPopup === true) {
      this.shouldOpenDepartmentPopup = false;
    } else {
      this.shouldOpenDepartmentPopup = true;
    }
  };

  handleDeleteList = () => {
    this.shouldOpenConfirmationDeleteListDialog = true;
  };

  handleConfirmDelete = async () => {
    try {
      await deleteDepartment(this.selectedDepartment.id);
      toast.success(i18n.t("toast.delete_success"));
      this.handleClose();
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.delete_fail"));
    }
  };

  handleConfirmDeleteList = async () => {
    let listAlert = [];
    for (var i = 0; i < this.selectedDepartmentList.length; i++) {
      try {
        await deleteDepartment(this.selectedDepartmentList[i].id);
      } catch (error) {
        listAlert.push(this.selectedDepartmentList[i].name);
        console.log(error);
        console.log(listAlert.toString());
        toast.warning(i18n.t("toast.delete_fail"));
      }
    }
    this.handleClose();
    toast.success(i18n.t("toast.delete_success"));
  };

  getDepartment = async (id) => {
    if (id != null) {
      try {
        let data = await getDepartment(id);
        this.handleSelectDepartment(data.data);
      } catch (error) {
        console.log(error);
        toast.warning(i18n.t("toast.get_fail"));
      }
    } else {
      this.handleSelectDepartment(null);
    }
  };

  handleSelectDepartment = (department) => {
    this.selectedDepartment = department;
  };

  getSelectListDepartment(item) {
    var result = [];
    var children = [];
    var root = {};
    root.label = item.name;
    root.value = item.id;
    result.push(root);
    if (item.children) {
      item.children.forEach((child) => {
        var childs = this.getSelectListDepartment(child);
        children.push(...childs);
      });

      root.children = children;
    }

    return result;
  }

  getListItemChild(item) {
    // debugger
    var result = [];
    var root = {};
    root.name = item.name;
    root.code = item.code;
    root.id = item.id;
    root.description = item.description;
    root.displayOrder = item.displayOrder;
    root.foundedDate = item.foundedDate;
    root.parentId = item.parentId;
    root.industryBlock = item.industryBlock;
    root.foundedNumber = item.foundedNumber;
    root.func = item.func;
    root.children = item.children;
    result.push(root);
    if (item.children) {
      item.children.forEach((child) => {
        var childs = this.getListItemChild(child);
        result.push(...childs);
      });
    }
    return result;
  }

  handleSelectListDepartment = (department) => {
    this.selectedDepartmentList = department;
  };

  createDepartment = async (department) => {
    try {
      let responseCheckCode = await checkCode(department.id, department.code);
      if (responseCheckCode.data) {
        toast.warning(i18n.t("toast.duplicate_code"));
      } else {
        await createDepartment(department);
        toast.success(i18n.t("toast.add_success"));
        this.handleClose();
      }
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.add_fail"));
    }
  };

  editDepartment = async (department) => {
    try {
      let responseCheckCode = await checkCode(department.id, department.code);
      if (responseCheckCode.data) {
        toast.warning(i18n.t("toast.duplicate_code"));
      } else {
        await editDepartment(department);
        toast.success(i18n.t("toast.update_success"));
        this.handleClose();
      }
    } catch (error) {
      console.log(error);
      toast.warning(i18n.t("toast.update_fail"));
    }
  };

  importExcel = () => {
    this.shouldOpenImportExcelDialog = true;
  };
}
