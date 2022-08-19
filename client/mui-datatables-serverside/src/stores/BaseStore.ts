import axios from 'axios';

const baseUrl = 'http://localhost:8080/';

export const get = async (path: string) => {
  return await axios.get(`${baseUrl}${path}`);
};
