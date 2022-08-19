import React from 'react';
import { QueryClient, QueryClientProvider } from 'react-query';
import './App.css';
import Table from './views/Table';

function App() {
  const queryClient = new QueryClient();

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
