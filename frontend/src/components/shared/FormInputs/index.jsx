import React, { Fragment } from 'react';

const FormInputs = ({ field, form, label, ...rest }) => {
  // console.log('field', field.value || '');
  return (
    <div className="form-group">
      {rest.type === 'checkbox' ? (
        <Fragment>
          <label htmlFor={field.name}>{label}</label>
          <input
            className="custom-control-input"
            {...field}
            {...rest}
            id={field.name}
            checked={form.values[field.name]}
          />
        </Fragment>
      ) : (
        <Fragment>
          <label htmlFor={field.name} className={`${rest.required && 'required'}`}>
            {label}
          </label>
          <input
            className="form-control"
            value={field.value || ''}
            {...field}
            {...rest}
            id={field.name}
          />
        </Fragment>
      )}
      {form.errors[field.name] && <small className="red">{form.errors[field.name]}</small>}
    </div>
  );
};

export default FormInputs;
