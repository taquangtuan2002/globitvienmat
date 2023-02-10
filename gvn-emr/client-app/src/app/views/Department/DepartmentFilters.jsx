import React from "react";
import { observer } from "mobx-react";
import { useStore } from "../../stores";
import GlobitsSearchInput from "../../common/GlobitsSearchInput";

export default observer(function DepartmentFilters() {
  const { departmentStore } = useStore();
  const { updatePageData } = departmentStore;
  return (
    <>
      <GlobitsSearchInput search={updatePageData} />
    </>
  );
})
