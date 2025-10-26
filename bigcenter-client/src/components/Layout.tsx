import { ReactNode } from "react";
import { Sidebar } from "./Sidebar";
import { Topbar } from "./Topbar";

interface LayoutProps {
  children: ReactNode;
  userRole: "student" | "teacher" | "admin";
  userName: string;
  userAvatar?: string;
  activePage: string;
  onNavigate: (page: string) => void;
}

export function Layout({
  children,
  userRole,
  userName,
  userAvatar,
  activePage,
  onNavigate,
}: LayoutProps) {
  return (
    <div className="flex h-screen bg-gray-50">
      <Sidebar userRole={userRole} activePage={activePage} onNavigate={onNavigate} />
      <div className="flex-1 flex flex-col overflow-hidden">
        <Topbar userName={userName} userAvatar={userAvatar} userRole={userRole} />
        <main className="flex-1 overflow-y-auto p-6">
          {children}
        </main>
      </div>
    </div>
  );
}
