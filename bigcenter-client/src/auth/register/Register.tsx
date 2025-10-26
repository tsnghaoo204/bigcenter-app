import React, { useState, useCallback, ChangeEvent, FocusEvent, FormEvent } from "react";
import { FiUser, FiMail, FiPhone, FiLock } from "react-icons/fi";

interface FormData {
  fullname: string;
  email: string;
  phone: string;
  password: string;
}

interface FormErrors {
  fullname?: string;
  email?: string;
  phone?: string;
  password?: string;
}

interface TouchedFields {
  fullname?: boolean;
  email?: boolean;
  phone?: boolean;
  password?: boolean;
}

interface RegisterProps {
  onNavigate: (page: string) => void;
}

const Register: React.FC<RegisterProps> = ({ onNavigate }) => {
  const [showPassword, setShowPassword] = useState(false);
  const [formData, setFormData] = useState<FormData>({
    fullname: "",
    email: "",
    phone: "",
    password: "",
  });
  const [errors, setErrors] = useState<FormErrors>({});
  const [touched, setTouched] = useState<TouchedFields>({});

  const validateField = (name: keyof FormData, value: string): string => {
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

  const handleChange = useCallback(
    (e: ChangeEvent<HTMLInputElement>) => {
      const { name, value } = e.target;
      setFormData((prev) => ({ ...prev, [name]: value }));
      setErrors((prev) => ({
        ...prev,
        [name]: validateField(name as keyof FormData, value),
      }));
    },
    []
  );

  const handleBlur = useCallback((e: FocusEvent<HTMLInputElement>) => {
    const { name } = e.target;
    setTouched((prev) => ({ ...prev, [name]: true }));
  }, []);

  const isFormValid = (): boolean => {
    return (
      Object.values(formData).every((v) => v.trim().length > 0) &&
      Object.values(errors).every((v) => !v)
    );
  };

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
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
        onNavigate("confirm");
      } catch (error: any) {
        console.error("Registration failed:", error);
        alert("Error: " + error.message);
      }
    }
  };

  return (
    <div className="flex flex-col lg:flex-row min-h-screen font-[Segoe_UI] bg-gray-50">
      {/* Banner */}
      <div className="flex flex-col justify-center flex-1 bg-gradient-to-br from-emerald-500 to-emerald-700 text-white p-10 lg:p-16">
        <h1 className="text-4xl font-bold mb-4">Welcome to Our Platform</h1>
        <p className="text-lg leading-relaxed mb-8">
          Join our community and experience the best services we offer. Create your account today!
        </p>
        <img
          src="https://images.unsplash.com/photo-1432888498266-38ffec3eaf0a?ixlib=rb-4.0.3"
          alt="Welcome"
          className="rounded-lg shadow-xl"
        />
      </div>

      {/* Form */}
      <div className="flex flex-1 justify-center items-center bg-white p-8 lg:p-16">
        <div className="w-full max-w-md bg-white rounded-lg shadow-lg p-8">
          <h2 className="text-3xl font-bold mb-2 text-emerald-600">Create Account</h2>
          <p className="mb-6 text-gray-500">Please fill in your details to sign up.</p>

          <form onSubmit={handleSubmit} className="space-y-5">
            {/* Full Name */}
            <div>
              <label className="block text-gray-700 mb-2 font-semibold" htmlFor="fullname">
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
                  placeholder="John Doe"
                  className={`w-full pl-10 pr-4 py-2 border rounded-lg text-gray-700 focus:ring-2 focus:ring-emerald-500 outline-none transition-all ${
                    touched.fullname && errors.fullname
                      ? "border-red-500"
                      : "border-gray-300"
                  }`}
                />
              </div>
              {touched.fullname && errors.fullname && (
                <p className="mt-1 text-sm text-red-500">{errors.fullname}</p>
              )}
            </div>

            {/* Email */}
            <div>
              <label className="block text-gray-700 mb-2 font-semibold" htmlFor="email">
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
                  placeholder="john@example.com"
                  className={`w-full pl-10 pr-4 py-2 border rounded-lg text-gray-700 focus:ring-2 focus:ring-emerald-500 outline-none transition-all ${
                    touched.email && errors.email ? "border-red-500" : "border-gray-300"
                  }`}
                />
              </div>
              {touched.email && errors.email && (
                <p className="mt-1 text-sm text-red-500">{errors.email}</p>
              )}
            </div>

            {/* Phone */}
            <div>
              <label className="block text-gray-700 mb-2 font-semibold" htmlFor="phone">
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
                  placeholder="+1234567890"
                  className={`w-full pl-10 pr-4 py-2 border rounded-lg text-gray-700 focus:ring-2 focus:ring-emerald-500 outline-none transition-all ${
                    touched.phone && errors.phone ? "border-red-500" : "border-gray-300"
                  }`}
                />
              </div>
              {touched.phone && errors.phone && (
                <p className="mt-1 text-sm text-red-500">{errors.phone}</p>
              )}
            </div>

            {/* Password */}
            <div>
              <label className="block text-gray-700 mb-2 font-semibold" htmlFor="password">
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
                  placeholder="••••••••"
                  className={`w-full pl-10 pr-12 py-2 border rounded-lg text-gray-700 focus:ring-2 focus:ring-emerald-500 outline-none transition-all ${
                    touched.password && errors.password
                      ? "border-red-500"
                      : "border-gray-300"
                  }`}
                />
              </div>
              {touched.password && errors.password && (
                <p className="mt-1 text-sm text-red-500">{errors.password}</p>
              )}
            </div>

            {/* Submit */}
            <button
              type="submit"
              disabled={!isFormValid()}
              className={`w-full py-3 rounded-lg text-white font-semibold transition-all ${
                isFormValid()
                  ? "bg-emerald-600 hover:bg-emerald-700"
                  : "bg-gray-400 cursor-not-allowed"
              }`}
            >
              Create Account
            </button>
          </form>

          <p className="mt-6 text-center text-gray-600">
            Already have an account?{" "}
            <button
              type="button"
              onClick={() => onNavigate("login")}
              className="text-emerald-600 hover:text-emerald-700 font-semibold"
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