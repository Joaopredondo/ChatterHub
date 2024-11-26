import { Box, Typography } from '@mui/material';
import { useAuth } from '../../contexts/AuthContext';

export default function Chat() {
  const { user } = useAuth();

  return (
    <Box>
      <Typography variant="h4" component="h1" gutterBottom>
        Bem-vindo, {user?.fullName}!
      </Typography>
    </Box>
  );
}
