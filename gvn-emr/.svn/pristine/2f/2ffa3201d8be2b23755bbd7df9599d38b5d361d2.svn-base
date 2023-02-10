import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import { AsyncPaginate } from "react-select-async-paginate";
import { pagingCountry } from "../../views/Country/CountryService";

const SelectAsyncPaginate = (props) => {
  const [regionName, setRegionName] = useState(null);

  useEffect(() => {
    setRegionName(props.regionName);
  }, [props.regionName]);

  const loadOptions = async (searchQuery, loadedOptions, { page }) => {

    var searchObject = {
      keyword: searchQuery,
      pageIndex: page,
      pageSize: 10,
    };

    let data = await pagingCountry(searchObject);
    console.log(data);
    return {
      options: data.data.content,
      hasMore: data.data.content.length >= 1,
      additional: {
        page: searchQuery ? 2 : page + 1,
      },
    };
  };

  const onChange = (option) => {
    if (typeof props.onChange === "function") {
      props.onChange(option);
    }
  };

  return (
    <AsyncPaginate
      // key={JSON.stringify(regionName)}
      value={props.value || ""}
      loadOptions={loadOptions}
      getOptionValue={(option) => option.name}
      getOptionLabel={(option) => option.name}
      onChange={onChange}
      isSearchable={false}
      placeholder="Select House"
      additional={{
        page: 1,
      }}
    />
  );
};

SelectAsyncPaginate.propTypes = {
  regionName: PropTypes.string.isRequired,
  value: PropTypes.object,
  onChange: PropTypes.func,
};

export default SelectAsyncPaginate;
