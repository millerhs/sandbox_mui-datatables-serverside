import { useQuery } from 'react-query';
import { get } from './BaseStore';

export const usePeople = (
  pageNumber: number,
  pageSize: number,
  sortColumn: string,
  sortDirection: string
) => {
  return useQuery(
    ['people', pageNumber, pageSize, sortColumn, sortDirection],
    async () =>
      get(
        `people?pageNumber=${pageNumber}&pageSize=${pageSize}&sortColumn=${sortColumn}&sortDirection=${sortDirection}`
      )
  );
};
