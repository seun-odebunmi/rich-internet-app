import { getData, postData, putData, deleteData } from './apiUtil';

export const getProducts = (params) => getData('/product/', params);
export const createProduct = (product) => postData('/product/', product);
export const updateProduct = (product) => putData('/product/', product);
export const deleteProduct = (productId) => deleteData(`/product/${productId}`);
