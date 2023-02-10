import React from "react";
import { useStore } from "../../stores";
import GlobitsSearchInput from "../../common/GlobitsSearchInput";
import { useTranslation } from "react-i18next";
import { observer } from "mobx-react";

export default observer(function ConceptDataTypeFilters() {
  const { conceptDataTypeStore } = useStore();
  const { t } = useTranslation();
  const { updatePageData } = conceptDataTypeStore;
  return (
    <>
      <GlobitsSearchInput search={updatePageData} t={t} />
    </>
  );
});
