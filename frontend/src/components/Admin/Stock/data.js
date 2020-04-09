export const columns = [
  {
    Header: 'Name',
    accessor: 'name',
  },
  {
    Header: 'Price',
    accessor: 'price',
  },
  {
    Header: 'Quantity',
    accessor: 'quantity',
    disableFilters: true,
  },
  {
    Header: 'Description',
    accessor: 'description',
    disableFilters: true,
  },
  {
    Header: 'Actions',
    accessor: 'actions',
    disableSortBy: true,
    disableFilters: true,
  },
];
