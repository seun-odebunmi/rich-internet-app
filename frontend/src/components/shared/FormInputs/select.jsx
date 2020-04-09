import React from 'react';

const FormSelect = ({ field, form, label, options, ...rest }) => {
  return (
    <div className="form-group">
      <label className={`${rest.required && 'required'}`}>{label}</label>
      <select className="form-control" {...field} {...rest} id={field.name}>
        <option value="">--Select--</option>
        {options &&
          options.map((option, index) => (
            <option key={index} value={option.id}>
              {option.name}
            </option>
          ))}
      </select>
      {form.errors[field.name] && <small className="red">{form.errors[field.name]}</small>}
    </div>
  );
};

export default FormSelect;
