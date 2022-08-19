import React from 'react';
import { QueryClient, QueryClientProvider } from 'react-query';
import './App.css';
import Table from './views/Table';

function App() {
  const queryClient = new QueryClient({
    defaultOptions: {
      queries: {
        refetchOnWindowFocus: false,
        refetchOnMount: false,
        refetchOnReconnect: true,
        retry: false,
        staleTime: 30 * 1000,
      },
    },
  });

  return (
    <div className='App'>
      <header className='App-header'>
        <QueryClientProvider client={queryClient}>
          <Table />
        </QueryClientProvider>
      </header>
    </div>
  );
}

export default App;
