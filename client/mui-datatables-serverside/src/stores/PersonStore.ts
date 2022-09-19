import axios from 'axios';
import { useQuery } from 'react-query';
import { download, getPaged } from './BaseStore';

export const usePeople = (
  pageNumber: number,
  pageSize: number,
  sortColumn: string,
  sortDirection: string,
  search: string | null
) => {
  return useQuery(
    ['people', pageNumber, pageSize, sortColumn, sortDirection, search],
    async () =>
      getPaged(
        'people',
        pageNumber,
        pageSize,
        sortColumn,
        sortDirection,
        search
      )
  );
};

export class PersonStore {
  export(
    pageNumber: number,
    pageSize: number,
    sortColumn: string,
    sortDirection: string
  ) {
    download(
      `people/export?pageNumber=${pageNumber}&pageSize=${pageSize}&sortColumn=${sortColumn}&sortDirection=${sortDirection}`
    );
  }
}
