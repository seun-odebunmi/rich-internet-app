import React from 'react';

const Search = () => {
  return (
    <div className="dataTables_filter">
      <label>
        Search:{' '}
        <select
          //   value="Name"
          className="custom-select-sm form-control-sm"
          //   onChange={(e) => {
          //     setPageSize(Number(e.target.value));
          //   }}
        >
          {['Name', 'Price'].map((option) => (
            <option key={option} value={option}>
              {option}
            </option>
          ))}
        </select>
        <input type="search" className="form-control form-control-sm" placeholder=""></input>
      </label>
    </div>
  );
};

export default Search;
