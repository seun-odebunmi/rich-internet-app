import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080';

export async function getData(url = '', params = {}) {
  return await axios
    .request({
      url,
      method: 'get',
      params,
    })
    .then((d) => d.data);
}

export async function postData(url = '', data = {}) {
  return await axios.request({
    url,
    method: 'post',
    data,
  });
}

export async function putData(url = '', data = {}) {
  return await axios.request({
    url,
    method: 'put',
    data,
  });
}

export async function deleteData(url = '') {
  return await axios.request({
    url,
    method: 'delete',
  });
}
