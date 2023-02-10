import React from "react";
import { useStore } from "../../stores";
import GlobitsSearchInput from "../../common/GlobitsSearchInput";
import { observer } from "mobx-react";

export default observer(function EthnicsFilters() {
  const { countryStore } = useStore();
  const { updatePageData } = countryStore;
  return (
    <>
      <GlobitsSearchInput search={updatePageData} />
    </>
  );
});
