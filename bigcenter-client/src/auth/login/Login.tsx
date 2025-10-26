import React, { useState, FormEvent } from "react";
import { FiMail, FiLock, FiEye, FiEyeOff } from "react-icons/fi";

type LoginProps = {
  onNavigate: (page: string) => void;
};

const Login: React.FC<LoginProps> = ({ onNavigate }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  const handleLogin = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) throw new Error("Login failed");

      const data = await response.json();
      localStorage.setItem("accessToken", data.accessToken);

      if (data.role.includes("TEACHER")) {
        onNavigate("teacher-dashboard");
      } else if (data.role.includes("STUDENT")) {
        onNavigate("student-dashboard");
      } else {
        onNavigate("admin-dashboard");
      }
    } catch (error) {
      console.error("Login error:", error);
      alert("Login failed. Please check your credentials.");
    }
  };

  return (
    <div className="flex flex-col lg:flex-row h-screen w-full font-[Nunito] bg-white">
      {/* Left Banner */}
      <div className="hidden lg:flex flex-1 flex-col justify-center items-center bg-gradient-to-r from-blue-300 to-blue-200 text-gray-800 text-center p-8">
        <h1 className="text-4xl font-bold mb-4 text-gray-800">
          Hello, Future Leader!
        </h1>
        <p className="text-lg text-gray-700 max-w-md">
          Let's begin today's adventure in learning. We're excited to see you!
        </p>
      </div>

      {/* Right Form */}
      <div className="flex flex-1 justify-center items-center bg-gray-50 p-8">
        <form
          onSubmit={handleLogin}
          className="w-full max-w-md bg-white p-8 rounded-2xl shadow-lg hover:-translate-y-1 transition-all duration-300"
        >
          <h2 className="text-3xl font-bold mb-2 text-gray-800">Welcome Back!</h2>
          <p className="mb-8 text-gray-500">So happy to see you again! Please log in.</p>

          {/* Email */}
          <div className="mb-6">
            <label htmlFor="email" className="block text-gray-700 font-semibold mb-2">
              Your Email
            </label>
            <div className="relative">
              <FiMail className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400" />
              <input
                type="email"
                id="email"
                className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-300 outline-none transition-all"
                placeholder="e.g., student@email.com"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
              />
            </div>
          </div>

          {/* Password */}
          <div className="mb-6">
            <label htmlFor="password" className="block text-gray-700 font-semibold mb-2">
              Your Password
            </label>
            <div className="relative">
              <FiLock className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400" />
              <input
                type={showPassword ? "text" : "password"}
                id="password"
                className="w-full pl-10 pr-12 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-300 outline-none transition-all"
                placeholder="Enter your password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
              />
              <button
                type="button"
                onClick={() => setShowPassword((prev) => !prev)}
                className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600"
              >
                {showPassword ? <FiEyeOff /> : <FiEye />}
              </button>
            </div>
          </div>

          {/* Options */}
          <div className="flex justify-between items-center mb-6 text-sm">
            <label className="flex items-center text-gray-600">
              <input type="checkbox" className="mr-2 accent-blue-400" /> Stay logged in
            </label>
            <a href="#" className="text-blue-400 font-semibold hover:underline">
              Forgot Password?
            </a>
          </div>

          {/* Submit */}
          <button
            type="submit"
            className="w-full py-3 bg-gradient-to-r from-blue-300 to-blue-200 text-gray-800 font-bold rounded-lg shadow-sm hover:shadow-md hover:-translate-y-0.5 transition-all"
          >
            Let's Go!
          </button>

          {/* Signup link */}
          <p className="text-center mt-6 text-gray-500">
            New here?{" "}
            <button
              type="button"
              onClick={() => onNavigate("register")}
              className="text-blue-400 font-bold hover:underline"
            >
              Create an account
            </button>
          </p>
        </form>
      </div>
    </div>
  );
};

export default Login;