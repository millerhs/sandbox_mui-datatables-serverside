import { PersonStore, usePeople } from '../stores/PersonStore';
import MUIDataTable from 'mui-datatables';
import React from 'react';

const personStore = new PersonStore();

const Table = () => {
  const [pageNumber, setPageNumber] = React.useState(0);
  const [pageSize, setPageSize] = React.useState(5);
  const [sortColumn, setSortColumn] = React.useState('name');
  const [sortDirection, setSortDirection] = React.useState('desc');

  const { data: people, isLoading } = usePeople(
    pageNumber,
    pageSize,
    sortColumn,
    sortDirection
  );
  const columns = ['name', 'age', 'height', 'weight'];

  const handleDownload = (
    buildHead: (columns: any) => string,
    buildBody: (data: any) => string,
    columns: any,
    data: any
  ) => {
    personStore.export(pageNumber, pageSize, sortColumn, sortDirection);
    return false;
  };

  return (
    <MUIDataTable
      title='People'
      data={people?.data ?? []}
      columns={columns}
      options={{
        onDownload: handleDownload,
        serverSide: true,
        selectableRows: 'none',
        page: pageNumber,
        rowsPerPage: pageSize,
        rowsPerPageOptions: [5, 10, 20],
        count: 10,
        onChangePage: (currentPage: number) => setPageNumber(currentPage),
        onChangeRowsPerPage: (numberOfRows: number) =>
          setPageSize(numberOfRows),
        onColumnSortChange: (
          changedColumn: string,
          direction: 'desc' | 'asc'
        ) => {
          setSortColumn(changedColumn);
          setSortDirection(direction);
        },
      }}
    />
  );
};

export default Table;
