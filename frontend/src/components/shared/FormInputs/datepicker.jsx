import React from 'react';
import { Form } from 'semantic-ui-react';
import DatePicker from 'react-datepicker';
import moment from 'moment';

const FormInputs = ({ field, form, label, ...rest }) => {
  return (
    <Form.Group widths="equal">
      <Form.Field>
        <label htmlFor={field.name}>{label}</label>
        <DatePicker
          className="form-control"
          {...field}
          {...rest}
          id={field.name}
          onChange={value => form.setFieldValue(field.name, value.format('YYYY-MM-DD'))}
          onBlur={() => null}
          selected={moment(form.values[field.name])}
        />
      </Form.Field>
    </Form.Group>
  );
};

export default FormInputs;
