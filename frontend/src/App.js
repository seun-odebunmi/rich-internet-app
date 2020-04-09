import React from 'react';
import { Route, Switch } from 'react-router-dom';

import routeOptions from './routes';

const routes = routeOptions.map(({ exact, component, path }, index) => (
  <Route key={index} exact={exact} component={component} path={path} />
));

function App() {
  return <Switch>{routes}</Switch>;
}

export default App;
