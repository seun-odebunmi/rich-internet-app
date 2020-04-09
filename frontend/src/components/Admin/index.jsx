import React from 'react';

import Layout from './layout';

const AdminHomepage = ({ history }) => {
  return (
    <Layout history={history} pageTitle="Dashboard">
      <div className="card mb-4">
        <div className="card-header">Title goes here</div>
        <div className="card-body">Content goes here</div>
      </div>
    </Layout>
  );
};

export default AdminHomepage;
