import React, { Component, Fragment } from 'react';

import Layout from '../layout';
import Button from './Button';
import ModalForm from './modalForm';
import Datatable from '../../shared/Datatable';
import { columns } from './data';

import { getProducts, createProduct } from '../../../apis';

class StockPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: {
        columns,
        rows: [],
        pageCount: 0,
      },
      modalFormData: {},
      modalAction: '',
      modal: false,
    };
    this.handleToggle = this.handleToggle.bind(this);
    this.handleBtnClick = this.handleBtnClick.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.modifyRowObject = this.modifyRowObject.bind(this);
    this.fetchData = this.fetchData.bind(this);
  }

  fetchData({ pageSize: size, pageIndex: page, sortBy, filters }) {
    const sort = sortBy[0] ? `${sortBy[0].id},${sortBy[0].desc ? 'DESC' : 'ASC'}` : '';
    const param = { page, size, sort };
    console.log('filters', filters);

    getProducts(param).then(({ content, totalPages }) => {
      const modRows = content.map(this.modifyRowObject);
      this.setState((prevState) => ({
        ...prevState,
        data: { ...prevState.data, rows: modRows, pageCount: totalPages },
      }));
    });
  }

  modifyRowObject(row) {
    return {
      ...row,
      actions: (
        <Fragment>
          <Button onClick={this.handleBtnClick} rowData={row} icon="edit-2" action="Edit" />
          <Button onClick={() => null} rowData={row} icon="trash-2" action="Delete" />
        </Fragment>
      ),
    };
  }

  handleBtnClick(rowData, action) {
    this.setState((prevState) => ({
      ...prevState,
      modal: !this.state.modal,
      modalAction: action,
      modalFormData: rowData,
    }));
  }

  handleToggle() {
    this.setState((prevState) => ({ ...prevState, modal: !this.state.modal }));
  }

  handleSubmit(values, { setSubmitting }) {
    const { modalAction } = this.state;

    if (modalAction === 'Add') {
      createProduct(values).then((d) => {
        const newRow = this.modifyRowObject(d.data);

        this.setState((prevState) => ({
          ...prevState,
          data: { ...prevState.data, rows: [newRow, ...prevState.data.rows] },
        }));
        this.handleToggle();
      });
    }
    setSubmitting(false);
  }

  render() {
    const { history } = this.props;
    const { data, modal, modalAction, modalFormData } = this.state;

    return (
      <Fragment>
        <Layout history={history} pageTitle="Stock">
          <div className="card mb-4">
            <div className="card-header">
              Add New Item
              <Button onClick={this.handleBtnClick} rowData={{}} icon="plus" action="Add" />
            </div>
            <div className="card-body">
              <Datatable
                columns={data.columns}
                data={data.rows}
                fetchData={this.fetchData}
                pageCount={data.pageCount}
              />
            </div>
          </div>
        </Layout>
        {modal && (
          <ModalForm
            open={modal}
            toggle={this.handleToggle}
            onSubmit={this.handleSubmit}
            action={modalAction}
            data={modalFormData}
          />
        )}
      </Fragment>
    );
  }
}

export default StockPage;
