import { getData, postData } from './apiUtil';

export const getProducts = (params) => getData('/product/', params);
export const createProduct = (data) => postData('/product/create', data);
