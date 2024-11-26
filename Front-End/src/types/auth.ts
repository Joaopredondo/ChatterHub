export interface User {
  id: number;
  username: string;
  email: string;
  fullName: string;
  role: 'USER' | 'ADMIN';
}

export interface LoginCredentials {
  username: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  user: User;
}
