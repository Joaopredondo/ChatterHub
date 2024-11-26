import axios from 'axios';
import { API_CONFIG } from '../config/api';
import { AuthResponse, LoginCredentials } from '../types/auth';

const api = axios.create(API_CONFIG);

export const authService = {
  async login(credentials: LoginCredentials): Promise<AuthResponse> {
    const response = await api.post<AuthResponse>('/auth/login', credentials);
    return response.data;
  },

  logout() {
    localStorage.removeItem('token');
  },

  getToken(): string | null {
    return localStorage.getItem('token');
  },

  setToken(token: string) {
    localStorage.setItem('token', token);
  }
};
