import Homepage from './components/Retail';

import AdminHomepage from './components/Admin';
import StockPage from './components/Admin/Stock';

const routeOptions = [
  { component: Homepage, path: '/', exact: true },
  { component: AdminHomepage, path: '/admin', exact: true },
  { component: StockPage, path: '/admin/stock', exact: true }
];

export default routeOptions;
