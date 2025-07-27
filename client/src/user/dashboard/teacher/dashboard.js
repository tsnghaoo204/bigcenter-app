import { useState, useEffect } from "react";
import { FiSun, FiMoon, FiBell, FiLogOut, FiBook, FiVideo, FiCalendar, FiDownload, FiUsers, FiClipboard, FiMessageSquare, FiSettings } from "react-icons/fi";
import { Bar, Line } from "react-chartjs-2";
import { Chart as ChartJS } from "chart.js/auto";
import { useNavigate } from "react-router-dom";

const TeacherDashboard = () => {
    const [darkMode, setDarkMode] = useState(false);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();
    const mockTeacher = {
        name: "Prof. Sarah Wilson",
        avatar: "https://images.unsplash.com/photo-1494790108377-be9c29b29330",
        department: "English Language",
        teachingHours: 120,
        classes: [
            {
                id: 1,
                name: "Business English - Advanced",
                students: 15,
                schedule: "Mon, Wed 9:00 AM",
                image: "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40"
            },
            {
                id: 2,
                name: "IELTS Preparation Course",
                students: 12,
                schedule: "Tue, Thu 2:00 PM",
                image: "https://images.unsplash.com/photo-1434030216411-0b793f4b4173"
            }
        ],
        performance: {
            studentSatisfaction: 92,
            attendanceRate: 95,
            assignmentCompletion: 88,
            studentProgress: 85
        },
        upcomingTasks: [
            {
                id: 1,
                title: "Grade IELTS Essays",
                deadline: "2024-02-15",
                priority: "High"
            },
            {
                id: 2,
                title: "Prepare Mock Tests",
                deadline: "2024-02-20",
                priority: "Medium"
            }
        ]
    };

    useEffect(() => {
        setTimeout(() => setLoading(false), 1500);
    }, []);

    const performanceData = {
        labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun"],
        datasets: [
            {
                label: "Student Progress",
                data: [75, 78, 82, 85, 88, 90],
                borderColor: "#4F46E5",
                tension: 0.4
            },
            {
                label: "Class Average",
                data: [70, 72, 75, 78, 80, 82],
                borderColor: "#9CA3AF",
                tension: 0.4
            }
        ]
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
                        <div className="flex items-center space-x-4">
                            <h1 className="text-2xl font-bold text-indigo-600 dark:text-indigo-400">Teacher Portal</h1>
                        </div>
                        <div className="flex items-center space-x-4">
                            <button
                                onClick={() => setDarkMode(!darkMode)}
                                className="p-2 rounded-full hover:bg-gray-100 dark:hover:bg-gray-700"
                            >
                                {darkMode ? <FiSun className="w-5 h-5" /> : <FiMoon className="w-5 h-5" />}
                            </button>
                            <button className="p-2 rounded-full hover:bg-gray-100 dark:hover:bg-gray-700">
                                <FiBell className="w-5 h-5" />
                            </button>
                            <div className="flex items-center space-x-2">
                                <img
                                    src={mockTeacher.avatar}
                                    alt="Profile"
                                    className="w-8 h-8 rounded-full"
                                />
                                <span className="font-medium">{mockTeacher.name}</span>
                            </div>
                            <button onClick={navigate("/login")} className="p-2 rounded-full hover:bg-gray-100 dark:hover:bg-gray-700 text-red-600 dark:text-red-400">
                                <FiLogOut className="w-5 h-5" />
                            </button>
                        </div>
                    </div>
                </header>

                <main className="max-w-7xl mx-auto px-4 py-8 sm:px-6 lg:px-8">
                    <div className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-8">
                        <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-sm">
                            <div className="flex items-center justify-between">
                                <div>
                                    <p className="text-gray-500 dark:text-gray-400">Total Students</p>
                                    <h3 className="text-2xl font-bold">27</h3>
                                </div>
                                <FiUsers className="w-8 h-8 text-indigo-600" />
                            </div>
                        </div>
                        <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-sm">
                            <div className="flex items-center justify-between">
                                <div>
                                    <p className="text-gray-500 dark:text-gray-400">Teaching Hours</p>
                                    <h3 className="text-2xl font-bold">{mockTeacher.teachingHours}h</h3>
                                </div>
                                <FiCalendar className="w-8 h-8 text-indigo-600" />
                            </div>
                        </div>
                        <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-sm">
                            <div className="flex items-center justify-between">
                                <div>
                                    <p className="text-gray-500 dark:text-gray-400">Satisfaction Rate</p>
                                    <h3 className="text-2xl font-bold">{mockTeacher.performance.studentSatisfaction}%</h3>
                                </div>
                                <FiMessageSquare className="w-8 h-8 text-indigo-600" />
                            </div>
                        </div>
                        <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-sm">
                            <div className="flex items-center justify-between">
                                <div>
                                    <p className="text-gray-500 dark:text-gray-400">Attendance Rate</p>
                                    <h3 className="text-2xl font-bold">{mockTeacher.performance.attendanceRate}%</h3>
                                </div>
                                <FiClipboard className="w-8 h-8 text-indigo-600" />
                            </div>
                        </div>
                    </div>

                    <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
                        <div className="lg:col-span-2">
                            <h2 className="text-xl font-semibold mb-4">My Classes</h2>
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                                {mockTeacher.classes.map((course) => (
                                    <div
                                        key={course.id}
                                        className="bg-white dark:bg-gray-800 rounded-lg shadow-sm p-4 hover:shadow-md transition-shadow"
                                    >
                                        <img
                                            src={course.image}
                                            alt={course.name}
                                            className="w-full h-40 object-cover rounded-md mb-4"
                                        />
                                        <h3 className="font-medium text-lg mb-2">{course.name}</h3>
                                        <div className="flex justify-between items-center text-sm">
                                            <span className="text-gray-600 dark:text-gray-400">
                                                {course.students} Students
                                            </span>
                                            <span className="text-gray-600 dark:text-gray-400">
                                                {course.schedule}
                                            </span>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>

                        <div className="bg-white dark:bg-gray-800 rounded-lg shadow-sm p-4">
                            <h2 className="text-xl font-semibold mb-4">Teaching Performance</h2>
                            <div className="mb-6">
                                <Line data={performanceData} options={{ responsive: true }} />
                            </div>
                            <div className="space-y-4">
                                {Object.entries(mockTeacher.performance).map(([metric, value]) => (
                                    <div key={metric}>
                                        <div className="flex justify-between text-sm mb-1">
                                            <span className="capitalize">{metric.replace(/([A-Z])/g, " $1").trim()}</span>
                                            <span>{value}%</span>
                                        </div>
                                        <div className="w-full bg-gray-200 rounded-full h-2">
                                            <div
                                                className="bg-indigo-600 h-2 rounded-full"
                                                style={{ width: `${value}%` }}
                                            ></div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>

                    <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 mt-8">
                        <div className="bg-white dark:bg-gray-800 rounded-lg shadow-sm p-4">
                            <h2 className="text-xl font-semibold mb-4">Upcoming Tasks</h2>
                            <div className="space-y-4">
                                {mockTeacher.upcomingTasks.map((task) => (
                                    <div
                                        key={task.id}
                                        className="flex items-center justify-between p-4 bg-gray-50 dark:bg-gray-700 rounded-lg"
                                    >
                                        <div>
                                            <h3 className="font-medium">{task.title}</h3>
                                            <p className="text-sm text-gray-600 dark:text-gray-400">
                                                Due: {task.deadline} | Priority: {task.priority}
                                            </p>
                                        </div>
                                        <button className="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700 transition-colors">
                                            Complete
                                        </button>
                                    </div>
                                ))}
                            </div>
                        </div>

                        <div className="bg-white dark:bg-gray-800 rounded-lg shadow-sm p-4">
                            <h2 className="text-xl font-semibold mb-4">Quick Actions</h2>
                            <div className="grid grid-cols-2 gap-4">
                                <button className="flex items-center justify-center p-4 bg-gray-50 dark:bg-gray-700 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-600 transition-colors">
                                    <FiBook className="w-6 h-6 mr-2" />
                                    <span>Course Materials</span>
                                </button>
                                <button className="flex items-center justify-center p-4 bg-gray-50 dark:bg-gray-700 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-600 transition-colors">
                                    <FiUsers className="w-6 h-6 mr-2" />
                                    <span>Student List</span>
                                </button>
                                <button className="flex items-center justify-center p-4 bg-gray-50 dark:bg-gray-700 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-600 transition-colors">
                                    <FiMessageSquare className="w-6 h-6 mr-2" />
                                    <span>Messages</span>
                                </button>
                                <button className="flex items-center justify-center p-4 bg-gray-50 dark:bg-gray-700 rounded-lg hover:bg-gray-100 dark:hover:bg-gray-600 transition-colors">
                                    <FiSettings className="w-6 h-6 mr-2" />
                                    <span>Settings</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    );
};

export default TeacherDashboard;