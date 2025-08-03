import React, { useState, useEffect } from "react";
import {
    FiSun,
    FiMoon,
    FiBell,
    FiLogOut,
    FiBook,
    FiVideo,
    FiCalendar,
    FiDownload,
} from "react-icons/fi";
import { Line } from "react-chartjs-2";
import { Chart as ChartJS } from "chart.js/auto";

const Dashboard = () => {
    const [darkMode, setDarkMode] = useState(false);
    const [loading, setLoading] = useState(true);
    const [classes, setClasses] = useState([]);

    const handleLogout = async () => {
        const token = localStorage.getItem("accessToken");

        try {
            const response = await fetch("http://localhost:8080/auth/logout", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({ token }),
            });

            if (response.ok) {
                localStorage.removeItem("accessToken");
                window.location.href = "/login";
            } else {
                const text = await response.text();
                console.error("Logout failed:", text);
            }
        } catch (error) {
            console.error("Logout error:", error);
        }
    };

    const mockStudent = {
        name: "John Smith",
        avatar: "https://images.unsplash.com/photo-1633332755192-727a05c4013d",
        skills: {
            reading: 85,
            writing: 78,
            speaking: 82,
            listening: 88,
        },
        upcomingEvents: [
            {
                id: 1,
                title: "Conversation Club",
                date: "2024-02-15",
                time: "15:00",
            },
            {
                id: 2,
                title: "Mock IELTS Test",
                date: "2024-02-20",
                time: "10:00",
            },
        ],
    };

    useEffect(() => {
        const fetchClasses = async () => {
            const token = localStorage.getItem("accessToken");
            if (!token) return;

            try {
                const response = await fetch("http://localhost:8080/api/classes-students/student/classes", {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });

                if (!response.ok) {
                    throw new Error("Failed to fetch classes");
                }

                const data = await response.json();
                setClasses(data);
            } catch (error) {
                console.error("Error fetching classes:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchClasses();
    }, []);

    const performanceData = {
        labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],
        datasets: [
            {
                label: "Your Progress",
                data: [65, 70, 75, 78, 82, 85],
                borderColor: "#4F46E5",
                tension: 0.4,
            },
            {
                label: "Class Average",
                data: [60, 65, 68, 70, 72, 75],
                borderColor: "#9CA3AF",
                tension: 0.4,
            },
        ],
    };

    if (loading) {
        return (
            <div className="min-h-screen flex items-center justify-center bg-gray-50 dark:bg-gray-900">
                <div className="animate-spin rounded-full h-16 w-16 border-t-2 border-b-2 border-indigo-600"></div>
            </div>
        );
    }

    return (
        <div className={`min-h-screen ${darkMode ? "dark" : ""}`}>
            <div className="bg-gray-50 dark:bg-gray-900 text-gray-900 dark:text-white">
                <header className="bg-white dark:bg-gray-800 shadow-sm">
                    <div className="max-w-7xl mx-auto px-4 py-4 sm:px-6 lg:px-8 flex items-center justify-between">
                        <h1 className="text-2xl font-bold text-indigo-600 dark:text-indigo-400">
                            English Center
                        </h1>
                        <div className="flex items-center space-x-4">
                            <button
                                onClick={() => setDarkMode(!darkMode)}
                                className="p-2 rounded-full hover:bg-gray-100 dark:hover:bg-gray-700"
                            >
                                {darkMode ? <FiSun className="w-5 h-5" /> : <FiMoon className="w-5 h-5" />}
                            </button>
                            <FiBell className="w-5 h-5" />
                            <img
                                src={mockStudent.avatar}
                                alt="Profile"
                                className="w-8 h-8 rounded-full"
                            />
                            <span className="font-medium">{mockStudent.name}</span>
                            <button onClick={handleLogout} className="p-2 rounded-full hover:bg-gray-100 dark:hover:bg-gray-700 text-red-600 dark:text-red-400">
                                <FiLogOut className="w-5 h-5" />
                            </button>
                        </div>
                    </div>
                </header>

                <main className="max-w-7xl mx-auto px-4 py-8 sm:px-6 lg:px-8">
                    <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
                        <div className="lg:col-span-2">
                            <h2 className="text-xl font-semibold mb-4">My Courses</h2>
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                                {classes.map((course) => (
                                    <div
                                        key={course.id}
                                        className="bg-white dark:bg-gray-800 rounded-lg shadow-sm p-4 hover:shadow-md transition-shadow"
                                    >
                                        {/* Ảnh đã bị loại bỏ */}
                                        <h3 className="font-medium text-lg mb-2">{course.name}</h3>
                                        <p className="text-sm text-gray-600 dark:text-gray-400">
                                            Room: {course.room}<br />
                                            From: {course.startDate} to {course.endDate}
                                        </p>
                                    </div>
                                ))}
                            </div>
                        </div>


                        <div className="bg-white dark:bg-gray-800 rounded-lg shadow-sm p-4">
                            <h2 className="text-xl font-semibold mb-4">Performance</h2>
                            <Line data={performanceData} options={{ responsive: true }} />
                            <div className="space-y-4 mt-6">
                                {Object.entries(mockStudent.skills).map(([skill, score]) => (
                                    <div key={skill}>
                                        <div className="flex justify-between text-sm mb-1">
                                            <span className="capitalize">{skill}</span>
                                            <span>{score}%</span>
                                        </div>
                                        <div className="w-full bg-gray-200 rounded-full h-2">
                                            <div
                                                className="bg-indigo-600 h-2 rounded-full"
                                                style={{ width: `${score}%` }}
                                            ></div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>

                    <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 mt-8">
                        <div className="bg-white dark:bg-gray-800 rounded-lg shadow-sm p-4">
                            <h2 className="text-xl font-semibold mb-4">Learning Resources</h2>
                            <div className="grid grid-cols-2 gap-4">
                                <button className="resource-button flex items-center justify-center bg-indigo-100 text-indigo-700 dark:bg-indigo-900 dark:text-indigo-200 py-2 px-4 rounded-md">
                                    <FiBook className="w-6 h-6 mr-2" /> Study Materials
                                </button>
                                <button className="resource-button flex items-center justify-center bg-indigo-100 text-indigo-700 dark:bg-indigo-900 dark:text-indigo-200 py-2 px-4 rounded-md">
                                    <FiVideo className="w-6 h-6 mr-2" /> Video Tutorials
                                </button>
                                <button className="resource-button flex items-center justify-center bg-indigo-100 text-indigo-700 dark:bg-indigo-900 dark:text-indigo-200 py-2 px-4 rounded-md">
                                    <FiDownload className="w-6 h-6 mr-2" /> Downloads
                                </button>
                                <button className="resource-button flex items-center justify-center bg-indigo-100 text-indigo-700 dark:bg-indigo-900 dark:text-indigo-200 py-2 px-4 rounded-md">
                                    <FiCalendar className="w-6 h-6 mr-2" /> Schedule
                                </button>
                            </div>
                        </div>

                        <div className="bg-white dark:bg-gray-800 rounded-lg shadow-sm p-4">
                            <h2 className="text-xl font-semibold mb-4">Upcoming Events</h2>
                            <div className="space-y-4">
                                {mockStudent.upcomingEvents.map((event) => (
                                    <div
                                        key={event.id}
                                        className="flex items-center justify-between p-4 bg-gray-50 dark:bg-gray-700 rounded-lg"
                                    >
                                        <div>
                                            <h3 className="font-medium">{event.title}</h3>
                                            <p className="text-sm text-gray-600 dark:text-gray-400">
                                                {event.date} at {event.time}
                                            </p>
                                        </div>
                                        <button className="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700 transition-colors">
                                            Join
                                        </button>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    );
};

export default Dashboard;
