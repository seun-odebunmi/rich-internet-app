import { getData, postData, putData, deleteData } from './apiUtil';

export const getProducts = (params) => getData('/product/', params);
export const createProduct = (product) => postData('/product/create', product);
export const updateProduct = (product) => putData('/product/update', product);
export const deleteProduct = (productId) => deleteData(`/product/delete/${productId}`);
