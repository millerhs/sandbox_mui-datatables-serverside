import axios from 'axios';

const baseUrl = 'http://localhost:8080/';

export const get = async (path: string) => {
  return await axios.get(`${baseUrl}${path}`);
};

export const getPaged = async (
  path: string,
  pageNumber: number,
  pageSize: number,
  sortColumn: string,
  sortDirection: string
) => {
  return await get(
    `${path}?pageNumber=${pageNumber}&pageSize=${pageSize}&sortColumn=${sortColumn}&sortDirection=${sortDirection}`
  );
};

export const download = (path: string) => {
  axios({
    url: `${baseUrl}${path}`,
    method: 'GET',
    responseType: 'blob', // important
  }).then((response) => {
    const url = window.URL.createObjectURL(new Blob([response.data]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', 'file.csv'); //or any other extension
    document.body.appendChild(link);
    link.click();
  });
};
