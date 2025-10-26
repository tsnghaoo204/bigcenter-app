import React, { useState, useEffect, useRef } from "react";

type ConfirmProps = {
  onNavigate: (page: string) => void;
};

const Confirm: React.FC<ConfirmProps> = ({ onNavigate }) => {
  const [code, setCode] = useState<string[]>(Array(6).fill(""));
  const [timer, setTimer] = useState(180);
  const [canResend, setCanResend] = useState(false);
  const [loading, setLoading] = useState(false);
  const inputsRef = useRef<(HTMLInputElement | null)[]>([]);

  useEffect(() => {
    if (timer > 0) {
      const interval = setInterval(() => setTimer((prev) => prev - 1), 1000);
      return () => clearInterval(interval);
    } else {
      setCanResend(true);
    }
  }, [timer]);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>, index: number) => {
    const { value } = e.target;
    if (/^[0-9]$/.test(value) || value === "") {
      const newCode = [...code];
      newCode[index] = value;
      setCode(newCode);
      if (value !== "" && index < 5) inputsRef.current[index + 1]?.focus();
    }
  };

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>, index: number) => {
    if (e.key === "Backspace" && code[index] === "" && index > 0) {
      inputsRef.current[index - 1]?.focus();
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const enteredCode = code.join("");
    const email = localStorage.getItem("email");

    if (!email || enteredCode.length !== 6) {
      alert("Please enter all 6 digits and ensure your email is available.");
      return;
    }

    setLoading(true);
    try {
      const response = await fetch("http://localhost:8080/auth/confirm", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, code: enteredCode }),
      });

      if (!response.ok) throw new Error("Invalid or expired code.");

      await response.text();
      alert("Verification successful!");
      onNavigate("login");
    } catch (err) {
      console.error("Verification failed:", err);
      alert("Invalid code or verification failed.");
    } finally {
      setLoading(false);
    }
  };

  const handleResend = async () => {
    const email = localStorage.getItem("email");
    if (!email) {
      alert("Missing email. Please log in again.");
      return;
    }

    setCanResend(false);
    setTimer(180);
    try {
      const response = await fetch("http://localhost:8080/auth/resend-code", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email }),
      });

      if (!response.ok) throw new Error("Failed to resend code");

      alert("A new code has been sent to your email.");
    } catch (err) {
      console.error("Resend failed:", err);
      alert("Unable to resend code. Try again later.");
      setCanResend(true);
    }
  };

  const formatTime = (seconds: number) => {
    const minutes = Math.floor(seconds / 60);
    const remainingSeconds = seconds % 60;
    return `${minutes}:${remainingSeconds < 10 ? "0" : ""}${remainingSeconds}`;
  };

  return (
    <div className="flex justify-center items-center h-screen bg-gray-50 px-4 font-[Nunito]">
      <form
        onSubmit={handleSubmit}
        className="w-full max-w-md bg-white p-10 rounded-2xl shadow-lg text-center transition-transform duration-300 hover:-translate-y-1"
      >
        <h2 className="text-2xl font-bold mb-4 text-gray-800">Check Your Email</h2>
        <p className="text-gray-500 mb-8">
          We've sent a 6-digit code to your email. Please enter it below.
        </p>

        <div className="flex justify-center gap-2 mb-8">
          {code.map((digit, index) => (
            <input
              key={index}
              ref={(el) => (inputsRef.current[index] = el)}
              type="text"
              maxLength={1}
              value={digit}
              onChange={(e) => handleInputChange(e, index)}
              onKeyDown={(e) => handleKeyDown(e, index)}
              className="w-10 h-12 text-center text-xl font-semibold border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-300"
            />
          ))}
        </div>

        <button
          type="submit"
          disabled={loading}
          className="w-full py-3 rounded-lg bg-gradient-to-r from-blue-300 to-blue-200 text-gray-800 font-semibold hover:shadow-lg transition-all"
        >
          {loading ? "Verifying..." : "Verify Code"}
        </button>

        <div className="mt-6 text-gray-500 text-sm">
          {timer > 0 ? (
            <p>
              Resend code in{" "}
              <span className="font-semibold text-gray-700">{formatTime(timer)}</span>
            </p>
          ) : (
            <p>
              Didn't get the code?{" "}
              <button
                type="button"
                onClick={handleResend}
                disabled={!canResend}
                className={`font-semibold ${
                  canResend
                    ? "text-blue-400 hover:underline"
                    : "text-gray-400 cursor-not-allowed"
                }`}
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