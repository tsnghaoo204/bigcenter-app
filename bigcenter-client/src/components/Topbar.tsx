import { Search, Bell } from "lucide-react";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";
import { Badge } from "./ui/badge";
import { Input } from "./ui/input";

interface TopbarProps {
  userName: string;
  userAvatar?: string;
  userRole: string;
}

export function Topbar({ userName, userAvatar, userRole }: TopbarProps) {
  return (
    <div className="h-16 bg-white border-b border-gray-200 flex items-center justify-between px-6">
      {/* Search Bar */}
      <div className="flex-1 max-w-xl">
        <div className="relative">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-400" />
          <Input
            type="text"
            placeholder="Search classes, students, or teachers..."
            className="pl-10 bg-gray-50 border-gray-200 rounded-lg"
          />
        </div>
      </div>

      {/* Right Section */}
      <div className="flex items-center gap-4">
        {/* Notifications */}
        <button className="relative p-2 hover:bg-gray-100 rounded-lg transition-colors">
          <Bell className="w-5 h-5 text-gray-600" />
          <span className="absolute top-1 right-1 w-2 h-2 bg-red-500 rounded-full"></span>
        </button>

        {/* User Profile */}
        <div className="flex items-center gap-3 pl-4 border-l border-gray-200">
          <div className="text-right">
            <p className="text-gray-900">{userName}</p>
            <p className="text-gray-500 text-xs capitalize">{userRole}</p>
          </div>
          <Avatar>
            <AvatarImage src={userAvatar} alt={userName} />
            <AvatarFallback className="bg-blue-600 text-white">
              {userName.split(" ").map(n => n[0]).join("")}
            </AvatarFallback>
          </Avatar>
        </div>
      </div>
    </div>
  );
}
