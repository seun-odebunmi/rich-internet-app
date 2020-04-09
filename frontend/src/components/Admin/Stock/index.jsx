import React, { Component, Fragment } from 'react';

import Layout from '../layout';
import Button from './Button';
import ModalForm from './modalForm';
import Datatable from '../../shared/Datatable';
import { columns } from './data';

import { getProducts, createProduct, updateProduct, deleteProduct } from '../../../apis';

class StockPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: {
        columns,
        rows: [],
        pageCount: 0,
      },
      pageInitialState: {
        pageIndex: 0,
        pageSize: 10,
        sortBy: [{ id: 'name', desc: false }],
      },
      modalFormData: {},
      modalAction: '',
      modal: false,
    };
    this.handleToggle = this.handleToggle.bind(this);
    this.handleBtnClick = this.handleBtnClick.bind(this);
    this.handleDelete = this.handleDelete.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.modifyRowObject = this.modifyRowObject.bind(this);
    this.fetchData = this.fetchData.bind(this);
  }

  fetchData({ pageSize, pageIndex, sortBy, filters }) {
    const sort = sortBy.reduce((prev, s) => `${prev}${s.id},${s.desc ? 'DESC' : 'ASC'}`, '');
    const filter = filters.reduce((prev, f) => ({ ...prev, [f.id]: f.value }), {});
    const param = { page: pageIndex, size: pageSize, sort, ...filter };

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
          <Button onClick={this.handleDelete} rowData={row} icon="trash-2" action="Delete" />
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

  handleDelete(rowData) {
    const { pageInitialState } = this.state;
    deleteProduct(rowData.id).then(() => {
      this.fetchData(pageInitialState);
    });
  }

  handleToggle() {
    this.setState((prevState) => ({ ...prevState, modal: !this.state.modal }));
  }

  handleSubmit(values, { setSubmitting }) {
    const { modalAction, pageInitialState } = this.state;
    if (modalAction === 'Add') {
      createProduct(values).then(() => {
        this.fetchData(pageInitialState);
      });
    } else if (modalAction === 'Edit') {
      updateProduct(values).then(() => {
        this.fetchData(pageInitialState);
      });
    }
    this.handleToggle();
    setSubmitting(false);
  }

  render() {
    const { history } = this.props;
    const { data, pageInitialState, modal, modalAction, modalFormData } = this.state;

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
                pageInitialState={pageInitialState}
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
