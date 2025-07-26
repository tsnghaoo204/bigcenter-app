import React, { useState, useCallback } from "react";
import { FiUser, FiMail, FiPhone, FiLock } from "react-icons/fi";
import { useNavigate } from "react-router-dom";

const Register = () => {
    const [showPassword, setShowPassword] = useState(false);
    const [formData, setFormData] = useState({
        fullname: "",
        email: "",
        phone: "",
        password: ""
    });

    const [errors, setErrors] = useState({});
    const [touched, setTouched] = useState({});
    const navigate = useNavigate();

    const validateField = (name, value) => {
        switch (name) {
            case "fullname":
                return value.length < 2 ? "Full name must be at least 2 characters" : "";
            case "email":
                return !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value)
                    ? "Please enter a valid email address"
                    : "";
            case "phone":
                return !/^\+?[1-9]\d{9,11}$/.test(value)
                    ? "Please enter a valid phone number"
                    : "";
            case "password":
                return !/^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/.test(value)
                    ? "Password must contain at least 8 characters, one uppercase letter, one number, and one special character"
                    : "";
            default:
                return "";
        }
    };

    const handleChange = useCallback((e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
        setErrors((prev) => ({ ...prev, [name]: validateField(name, value) }));
    }, []);

    const handleBlur = useCallback((e) => {
        const { name } = e.target;
        setTouched((prev) => ({ ...prev, [name]: true }));
    }, []);

    const isFormValid = () => {
        return (
            Object.keys(formData).every((key) => formData[key].length > 0) &&
            Object.keys(errors).every((key) => !errors[key])
        );
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (isFormValid()) {
            try {
                const response = await fetch("http://localhost:8080/auth/register", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(formData),
                });

                if (!response.ok) {
                    const errorText = await response.text();
                    throw new Error(errorText || "Registration failed");
                }

                alert("Registration successful! Please check your email to confirm.");
                localStorage.setItem("email", formData.email);
                navigate("/confirm");
            } catch (error) {
                console.error("Registration failed:", error);
                alert("Error: " + error.message);
            }
        }
    };


    return (
        <div className="min-h-screen flex flex-col lg:flex-row">
            <div className="lg:w-1/2 bg-gradient-to-br from-blue-500 to-purple-600 p-12 flex items-center justify-center">
                <div className="text-white max-w-lg">
                    <h1 className="text-4xl font-bold mb-6">Welcome to Our Platform</h1>
                    <p className="text-xl mb-8">
                        Join our community and experience the best services we offer. Create
                        your account today!
                    </p>
                    <img
                        src="https://images.unsplash.com/photo-1432888498266-38ffec3eaf0a?ixlib=rb-4.0.3"
                        alt="Welcome"
                        className="rounded-lg shadow-xl"
                    />
                </div>
            </div>

            <div className="lg:w-1/2 p-12 flex items-center justify-center bg-white">
                <div className="w-full max-w-md">
                    <h2 className="text-3xl font-bold mb-8 text-gray-800">Create Account</h2>
                    <form onSubmit={handleSubmit} className="space-y-6">
                        <div>
                            <label className="block text-gray-700 mb-2" htmlFor="fullname">
                                Full Name
                            </label>
                            <div className="relative">
                                <FiUser className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" />
                                <input
                                    type="text"
                                    id="fullname"
                                    name="fullname"
                                    value={formData.fullname}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    className={`w-full pl-10 pr-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all ${touched.fullname && errors.fullname ? "border-red-500" : "border-gray-300"}`}
                                    placeholder="John Doe"
                                />
                            </div>
                            {touched.fullname && errors.fullname && (
                                <p className="mt-1 text-red-500 text-sm">{errors.fullname}</p>
                            )}
                        </div>

                        <div>
                            <label className="block text-gray-700 mb-2" htmlFor="email">
                                Email
                            </label>
                            <div className="relative">
                                <FiMail className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" />
                                <input
                                    type="email"
                                    id="email"
                                    name="email"
                                    value={formData.email}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    className={`w-full pl-10 pr-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all ${touched.email && errors.email ? "border-red-500" : "border-gray-300"}`}
                                    placeholder="john@example.com"
                                />
                            </div>
                            {touched.email && errors.email && (
                                <p className="mt-1 text-red-500 text-sm">{errors.email}</p>
                            )}
                        </div>

                        <div>
                            <label className="block text-gray-700 mb-2" htmlFor="phone">
                                Phone Number
                            </label>
                            <div className="relative">
                                <FiPhone className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" />
                                <input
                                    type="tel"
                                    id="phone"
                                    name="phone"
                                    value={formData.phone}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    className={`w-full pl-10 pr-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all ${touched.phone && errors.phone ? "border-red-500" : "border-gray-300"}`}
                                    placeholder="+1234567890"
                                />
                            </div>
                            {touched.phone && errors.phone && (
                                <p className="mt-1 text-red-500 text-sm">{errors.phone}</p>
                            )}
                        </div>

                        <div>
                            <label className="block text-gray-700 mb-2" htmlFor="password">
                                Password
                            </label>
                            <div className="relative">
                                <FiLock className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400" />
                                <input
                                    type={showPassword ? "text" : "password"}
                                    id="password"
                                    name="password"
                                    value={formData.password}
                                    onChange={handleChange}
                                    onBlur={handleBlur}
                                    className={`w-full pl-10 pr-12 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 outline-none transition-all ${touched.password && errors.password ? "border-red-500" : "border-gray-300"}`}
                                    placeholder="••••••••"
                                />
                            </div>
                            {touched.password && errors.password && (
                                <p className="mt-1 text-red-500 text-sm">{errors.password}</p>
                            )}
                        </div>

                        <button
                            type="submit"
                            disabled={!isFormValid()}
                            className={`w-full py-3 rounded-lg text-white font-semibold transition-all ${isFormValid() ? "bg-blue-600 hover:bg-blue-700" : "bg-gray-400 cursor-not-allowed"}`}
                        >
                            Create Account
                        </button>
                    </form>

                    <p className="mt-6 text-center text-gray-600">
                        Already have an account?{" "}
                        <button
                            onClick={() => console.log("Navigate to login")}
                            className="text-blue-600 hover:text-blue-700 font-semibold"
                        >
                            Sign in
                        </button>
                    </p>
                </div>
            </div>
        </div>
    );
};

export default Register;