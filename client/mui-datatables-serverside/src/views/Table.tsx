import { PersonStore, usePeople } from '../stores/PersonStore';
import MUIDataTable, { debounceSearchRender } from 'mui-datatables';
import React from 'react';

const personStore = new PersonStore();

const Table = () => {
  const [pageNumber, setPageNumber] = React.useState(0);
  const [pageSize, setPageSize] = React.useState(5);
  const [sortColumn, setSortColumn] = React.useState('name');
  const [sortDirection, setSortDirection] = React.useState('desc');
  const [search, setSearch] = React.useState<string | null>(null);

  const { data: pagedPeople, isLoading } = usePeople(
    pageNumber,
    pageSize,
    sortColumn,
    sortDirection,
    search
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

  const handleSearch = (searchText: string | null) => {
    setSearch(searchText);
  };

  return (
    <MUIDataTable
      title='People'
      data={pagedPeople?.data.content ?? []}
      columns={columns}
      options={{
        enableNestedDataAccess: '.',
        onDownload: handleDownload,
        serverSide: true,
        onSearchChange: setSearch,
        customSearchRender: debounceSearchRender(500),
        selectableRows: 'none',
        page: pageNumber,
        rowsPerPage: pageSize,
        rowsPerPageOptions: [5, 10, 20],
        count: pagedPeople?.data?.totalElements ?? 0,
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
