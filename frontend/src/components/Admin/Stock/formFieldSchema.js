import FormInputs from '../../shared/FormInputs/';

export const formFormFields = [
  {
    type: 'text',
    name: 'name',
    label: 'Name',
    component: FormInputs,
    required: true,
  },
  {
    type: 'number',
    name: 'price',
    label: 'Price',
    component: FormInputs,
    required: true,
  },
  {
    type: 'number',
    name: 'quantity',
    label: 'Quantity',
    component: FormInputs,
    required: true,
  },
  {
    type: 'text',
    name: 'description',
    label: 'Description',
    component: FormInputs,
    required: true,
  },
];
