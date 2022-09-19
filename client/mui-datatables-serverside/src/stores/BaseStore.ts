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
  sortDirection: string,
  search: string | null
) => {
  let query = `${path}?page=${pageNumber}&size=${pageSize}&sortColumn=${sortColumn}&sortDirection=${sortDirection}`;
  if (search) {
    query = query.concat(`&search=${search}`);
  }

  return await get(query);
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
