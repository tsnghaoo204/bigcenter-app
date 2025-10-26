import { useState } from "react";
import { Layout } from "./components/Layout";
import Login from "./auth/login/Login";
import Register from "./auth/register/register";
import Confirm from "./auth/confirm/Confirm";
import { StudentDashboard } from "./pages/StudentDashboard";
import { TeacherDashboard } from "./pages/TeacherDashboard";
import { AdminDashboard } from "./pages/AdminDashboard";
import { ClassDetailsPage } from "./pages/ClassDetailsPage";

type Page = 
  | "login" 
  | "register" 
  | "confirm"
  | "student-dashboard" 
  | "teacher-dashboard" 
  | "admin-dashboard" 
  | "class-details"
  | "dashboard"
  | "classes"
  | "schedule"
  | "settings"
  | "users"
  | "analytics";

export default function App() {
  const [currentPage, setCurrentPage] = useState<Page>("login");
  const [userRole, setUserRole] = useState<"student" | "teacher" | "admin">("student");

  const handleNavigate = (page: string) => {
    // Handle role-specific navigation
    if (page === "student-dashboard") {
      setUserRole("student");
      setCurrentPage("student-dashboard");
    } else if (page === "teacher-dashboard") {
      setUserRole("teacher");
      setCurrentPage("teacher-dashboard");
    } else if (page === "admin-dashboard") {
      setUserRole("admin");
      setCurrentPage("admin-dashboard");
    } else if (page === "dashboard") {
      // Navigate to role-specific dashboard
      if (userRole === "student") {
        setCurrentPage("student-dashboard");
      } else if (userRole === "teacher") {
        setCurrentPage("teacher-dashboard");
      } else {
        setCurrentPage("admin-dashboard");
      }
    } else {
      setCurrentPage(page as Page);
    }
  };

  // User data based on role
  const userData = {
    student: {
      name: "Alex Thompson",
      avatar: "https://i.pravatar.cc/150?img=12",
    },
    teacher: {
      name: "Dr. Sarah Johnson",
      avatar: "https://i.pravatar.cc/150?img=10",
    },
    admin: {
      name: "Admin User",
      avatar: "https://i.pravatar.cc/150?img=20",
    },
  };

  const currentUser = userData[userRole];

  // Pages that don't need the layout (login, signup)
  if (currentPage === "login") {
    return <Login onNavigate={handleNavigate} />;
  }

  if (currentPage === "register") {
    return <Register onNavigate={handleNavigate} />;
  }
  
  if (currentPage === "confirm") {
    return <Confirm onNavigate={handleNavigate} />;
  }

  // Map navigation items to actual pages
  const getActivePage = () => {
    if (currentPage === "student-dashboard" || currentPage === "teacher-dashboard" || currentPage === "admin-dashboard") {
      return "dashboard";
    }
    return currentPage;
  };

  // Render content based on current page
  const renderContent = () => {
    switch (currentPage) {
      case "student-dashboard":
        return <StudentDashboard onNavigate={handleNavigate} />;
      case "teacher-dashboard":
        return <TeacherDashboard onNavigate={handleNavigate} />;
      case "admin-dashboard":
        return <AdminDashboard onNavigate={handleNavigate} />;
      case "class-details":
        return <ClassDetailsPage onNavigate={handleNavigate} />;
      case "classes":
        return <StudentDashboard onNavigate={handleNavigate} />;
      case "schedule":
        return (
          <div className="text-center py-12">
            <h2 className="text-gray-900 mb-2">Schedule Page</h2>
            <p className="text-gray-600">Schedule management coming soon...</p>
          </div>
        );
      case "settings":
        return (
          <div className="text-center py-12">
            <h2 className="text-gray-900 mb-2">Settings Page</h2>
            <p className="text-gray-600">Settings management coming soon...</p>
          </div>
        );
      case "users":
        return <AdminDashboard onNavigate={handleNavigate} />;
      case "analytics":
        return (
          <div className="text-center py-12">
            <h2 className="text-gray-900 mb-2">Analytics Page</h2>
            <p className="text-gray-600">Analytics dashboard coming soon...</p>
          </div>
        );
      default:
        return <StudentDashboard onNavigate={handleNavigate} />;
    }
  };

  return (
    <Layout
      userRole={userRole}
      userName={currentUser.name}
      userAvatar={currentUser.avatar}
      activePage={getActivePage()}
      onNavigate={handleNavigate}
    >
      {renderContent()}
    </Layout>
  );
}