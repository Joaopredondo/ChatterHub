import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { Container } from '@mui/material';
import { PrivateRoute } from './components/PrivateRoute';
import Login from './pages/Login';
import Chat from './pages/Chat';

function App() {
  return (
    <BrowserRouter>
      <Container maxWidth="lg" sx={{ py: 4 }}>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route 
            path="/chat" 
            element={
              <PrivateRoute>
                <Chat />
              </PrivateRoute>
            } 
          />
          <Route path="/" element={<Navigate to="/chat" replace />} />
        </Routes>
      </Container>
    </BrowserRouter>
  );
}

export default App;
