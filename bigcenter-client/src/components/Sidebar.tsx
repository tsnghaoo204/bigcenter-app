import { Home, BookOpen, Calendar, Settings, LogOut, Users, BarChart } from "lucide-react";
import { cn } from "./ui/utils";

interface SidebarProps {
  userRole: "student" | "teacher" | "admin";
  activePage: string;
  onNavigate: (page: string) => void;
}

export function Sidebar({ userRole, activePage, onNavigate }: SidebarProps) {
  const studentMenuItems = [
    { id: "dashboard", label: "Dashboard", icon: Home },
    { id: "classes", label: "My Classes", icon: BookOpen },
    { id: "schedule", label: "Schedule", icon: Calendar },
    { id: "settings", label: "Settings", icon: Settings },
  ];

  const teacherMenuItems = [
    { id: "dashboard", label: "Dashboard", icon: Home },
    { id: "classes", label: "My Classes", icon: BookOpen },
    { id: "schedule", label: "Schedule", icon: Calendar },
    { id: "settings", label: "Settings", icon: Settings },
  ];

  const adminMenuItems = [
    { id: "dashboard", label: "Dashboard", icon: Home },
    { id: "classes", label: "Classes", icon: BookOpen },
    { id: "users", label: "Users", icon: Users },
    { id: "analytics", label: "Analytics", icon: BarChart },
    { id: "settings", label: "Settings", icon: Settings },
  ];

  const menuItems =
    userRole === "admin"
      ? adminMenuItems
      : userRole === "teacher"
      ? teacherMenuItems
      : studentMenuItems;

  return (
    <div className="h-screen w-64 bg-white border-r border-gray-200 flex flex-col">
      {/* Logo */}
      <div className="p-6 border-b border-gray-200">
        <div className="flex items-center gap-2">
          <div className="w-10 h-10 bg-blue-600 rounded-lg flex items-center justify-center">
            <BookOpen className="w-6 h-6 text-white" />
          </div>
          <div>
            <h1 className="text-blue-600">EduCenter</h1>
            <p className="text-gray-500 text-xs capitalize">{userRole} Portal</p>
          </div>
        </div>
      </div>

      {/* Menu Items */}
      <nav className="flex-1 p-4">
        <ul className="space-y-2">
          {menuItems.map((item) => {
            const Icon = item.icon;
            const isActive = activePage === item.id;
            return (
              <li key={item.id}>
                <button
                  onClick={() => onNavigate(item.id)}
                  className={cn(
                    "w-full flex items-center gap-3 px-4 py-3 rounded-lg transition-colors",
                    isActive
                      ? "bg-blue-50 text-blue-600"
                      : "text-gray-600 hover:bg-gray-50"
                  )}
                >
                  <Icon className="w-5 h-5" />
                  <span>{item.label}</span>
                </button>
              </li>
            );
          })}
        </ul>
      </nav>

      {/* Logout */}
      <div className="p-4 border-t border-gray-200">
        <button
          onClick={() => onNavigate("login")}
          className="w-full flex items-center gap-3 px-4 py-3 rounded-lg text-gray-600 hover:bg-red-50 hover:text-red-600 transition-colors"
        >
          <LogOut className="w-5 h-5" />
          <span>Logout</span>
        </button>
      </div>
    </div>
  );
}
