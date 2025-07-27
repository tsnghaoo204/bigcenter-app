import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './login.css';

const Login = () => {
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username: username, password }),
            });

            if (!response.ok) {
                throw new Error('Login failed');
            }

            const data = await response.json();
            localStorage.setItem('accessToken', data.accessToken);
            if (data.role.includes('TEACHER')) {
                navigate('/teacher-dashboard');
            } else if (data.role.includes('STUDENT')) {
                navigate('/dashboard');
            }

        } catch (error) {
            console.error('Login error:', error);
            alert('Login failed. Please check your credentials.');
        }
    };

    return (
        <div className="login-container">
            <div className="login-banner">
                <h1>Hello, Future Leader!</h1>
                <p>Let's begin today's adventure in learning. We're excited to see you!</p>
            </div>
            <div className="login-form-container">
                <form className="login-form" onSubmit={handleLogin}>
                    <h2>Welcome Back!</h2>
                    <p>So happy to see you again! Please log in.</p>

                    <div className="input-group">
                        <label htmlFor="email">Your Email</label>
                        <input
                            type="email"
                            id="email"
                            className="input-field"
                            placeholder="e.g., student@email.com"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>

                    <div className="input-group">
                        <label htmlFor="password">Your Password</label>
                        <div className="password-group">
                            <input
                                type="password"
                                id="password"
                                className="input-field"
                                placeholder="Enter your password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>
                    </div>

                    <div className="form-options">
                        <div className="remember-me">
                            <input type="checkbox" id="remember-me" />
                            <label htmlFor="remember-me">Stay logged in</label>
                        </div>
                        <div className="forgot-password">
                            <a href="#">Forgot Password?</a>
                        </div>
                    </div>

                    <button type="submit" className="login-button">
                        Let's Go!
                    </button>

                    <p className="signup-link">
                        New here? <span onClick={() => navigate('/register')} className="link-text">Create an account</span>
                    </p>
                </form>
            </div>
        </div>
    );
};

export default Login;