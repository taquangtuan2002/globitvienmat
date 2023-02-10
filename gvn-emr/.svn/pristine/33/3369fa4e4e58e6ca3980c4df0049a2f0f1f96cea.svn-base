import React from "react";
import { useStore } from "../../stores";
import GlobitsSearchInput from "../../common/GlobitsSearchInput";
import { useTranslation } from "react-i18next";
import { observer } from "mobx-react";

export default observer(function EthnicsFilters() {
  const { ethnicsStore } = useStore();
  const { t } = useTranslation();
  const { updatePageData } = ethnicsStore;
  return (
    <>
      <GlobitsSearchInput search={updatePageData} t={t} />
    </>
  );
});
