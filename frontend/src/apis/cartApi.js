import { getData, postData } from './apiUtil';

export const addToCart = quantity => postData(`/cart/add/${quantity}`);
export const getCart = () => getData('/cart/list');
