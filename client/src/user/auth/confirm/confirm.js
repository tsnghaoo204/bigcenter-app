import React, { useState, useEffect, useRef } from 'react';
import './confirm.css';
import { useNavigate } from 'react-router-dom';

const Confirm = () => {
  const navigate = useNavigate();
  const [code, setCode] = useState(Array(6).fill(''));
  const [timer, setTimer] = useState(180);
  const [canResend, setCanResend] = useState(false);
  const [loading, setLoading] = useState(false);
  const inputsRef = useRef([]);

  useEffect(() => {
    if (timer > 0) {
      const interval = setInterval(() => {
        setTimer((prevTimer) => prevTimer - 1);
      }, 1000);
      return () => clearInterval(interval);
    } else {
      setCanResend(true);
    }
  }, [timer]);

  const handleInputChange = (e, index) => {
    const { value } = e.target;
    if (/^[0-9]$/.test(value) || value === '') {
      const newCode = [...code];
      newCode[index] = value;
      setCode(newCode);

      if (value !== '' && index < 5) {
        inputsRef.current[index + 1].focus();
      }
    }
  };

  const handleKeyDown = (e, index) => {
    if (e.key === 'Backspace' && code[index] === '' && index > 0) {
      inputsRef.current[index - 1].focus();
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const enteredCode = code.join('');
    const email = localStorage.getItem('email');

    if (!email || enteredCode.length !== 6) {
      alert('Please enter all 6 digits and ensure your email is available.');
      return;
    }

    setLoading(true);
    try {
      const response = await fetch('http://localhost:8080/auth/confirm', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, code: enteredCode }),
      });

      if (!response.ok) {
        throw new Error('Invalid or expired code.');
      }

      await response.text();    
      alert('Verification successful!');
      navigate('/login');
    } catch (err) {
      console.error('Verification failed:', err);
      alert('Invalid code or verification failed.');
    } finally {
      setLoading(false);
    }
  };

  const handleResend = async () => {
    const email = localStorage.getItem('email');
    if (!email) {
      alert('Missing email. Please log in again.');
      return;
    }

    setCanResend(false);
    setTimer(180);
    try {
      const response = await fetch('http://localhost:8080/auth/resend-code', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email }),
      });

      if (!response.ok) {
        throw new Error('Failed to resend code');
      }

      alert('A new code has been sent to your email.');
    } catch (err) {
      console.error('Resend failed:', err);
      alert('Unable to resend code. Try again later.');
      setCanResend(true);
    }
  };

  const formatTime = (seconds) => {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes}:${remainingSeconds < 10 ? '0' : ''}${remainingSeconds}`;
  };

  return (
    <div className="confirm-container">
      <form className="confirm-form" onSubmit={handleSubmit}>
        <h2>Check Your Email</h2>
        <p>We've sent a 6-digit code to your email. Please enter it below.</p>

        <div className="code-inputs">
          {code.map((digit, index) => (
            <input
              key={index}
              ref={(el) => (inputsRef.current[index] = el)}
              type="text"
              className="code-input"
              maxLength="1"
              value={digit}
              onChange={(e) => handleInputChange(e, index)}
              onKeyDown={(e) => handleKeyDown(e, index)}
            />
          ))}
        </div>

        <button type="submit" className="confirm-button" disabled={loading}>
          {loading ? 'Verifying...' : 'Verify Code'}
        </button>

        <div className="timer-resend">
          {timer > 0 ? (
            <p>
              Resend code in <span className="timer">{formatTime(timer)}</span>
            </p>
          ) : (
            <p>
              Didn't get the code?{' '}
              <button
                type="button"
                className="resend-link"
                onClick={handleResend}
                disabled={!canResend}
              >
                Resend Code
              </button>
            </p>
          )}
        </div>
      </form>
    </div>
  );
};

export default Confirm;
