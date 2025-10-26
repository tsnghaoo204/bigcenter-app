import { BookOpen, Users, FileText, Clock, Upload, CheckSquare } from "lucide-react";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "../components/ui/card";
import { Badge } from "../components/ui/badge";
import { Button } from "../components/ui/button";
import { Avatar, AvatarFallback, AvatarImage } from "../components/ui/avatar";

interface TeacherDashboardProps {
  onNavigate: (page: string) => void;
}

export function TeacherDashboard({ onNavigate }: TeacherDashboardProps) {
  const assignedClasses = [
    {
      id: 1,
      name: "Advanced Mathematics",
      level: "Grade 11-12",
      students: 28,
      schedule: "Mon, Wed, Fri - 10:00 AM",
      attendanceRate: 92,
    },
    {
      id: 2,
      name: "Calculus I",
      level: "Grade 12",
      students: 24,
      schedule: "Tue, Thu - 2:00 PM",
      attendanceRate: 88,
    },
    {
      id: 3,
      name: "Statistics Basics",
      level: "Grade 10",
      students: 32,
      schedule: "Mon, Wed - 1:00 PM",
      attendanceRate: 95,
    },
  ];

  const recentStudents = [
    { id: 1, name: "Emma Wilson", class: "Advanced Mathematics", avatar: "https://i.pravatar.cc/150?img=1" },
    { id: 2, name: "Liam Johnson", class: "Calculus I", avatar: "https://i.pravatar.cc/150?img=2" },
    { id: 3, name: "Olivia Brown", class: "Statistics Basics", avatar: "https://i.pravatar.cc/150?img=3" },
    { id: 4, name: "Noah Davis", class: "Advanced Mathematics", avatar: "https://i.pravatar.cc/150?img=4" },
    { id: 5, name: "Ava Martinez", class: "Calculus I", avatar: "https://i.pravatar.cc/150?img=5" },
  ];

  const upcomingTasks = [
    { task: "Grade Assignment 5 - Calculus I", dueDate: "Today, 5:00 PM", priority: "high" },
    { task: "Prepare Quiz - Advanced Math", dueDate: "Tomorrow, 9:00 AM", priority: "medium" },
    { task: "Update Course Materials", dueDate: "Oct 28, 2025", priority: "low" },
    { task: "Parent-Teacher Meeting Prep", dueDate: "Oct 30, 2025", priority: "medium" },
  ];

  return (
    <div className="space-y-6">
      {/* Header */}
      <div>
        <h1 className="text-gray-900 mb-2">Welcome, Dr. Johnson!</h1>
        <p className="text-gray-600">Manage your classes and track student progress.</p>
      </div>

      {/* Stats Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        <Card className="rounded-xl shadow-sm">
          <CardContent className="p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm">Total Classes</p>
                <h3 className="text-gray-900 mt-1">3</h3>
              </div>
              <div className="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
                <BookOpen className="w-6 h-6 text-blue-600" />
              </div>
            </div>
          </CardContent>
        </Card>

        <Card className="rounded-xl shadow-sm">
          <CardContent className="p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm">Total Students</p>
                <h3 className="text-gray-900 mt-1">84</h3>
              </div>
              <div className="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center">
                <Users className="w-6 h-6 text-green-600" />
              </div>
            </div>
          </CardContent>
        </Card>

        <Card className="rounded-xl shadow-sm">
          <CardContent className="p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm">Assignments</p>
                <h3 className="text-gray-900 mt-1">12</h3>
              </div>
              <div className="w-12 h-12 bg-yellow-100 rounded-lg flex items-center justify-center">
                <FileText className="w-6 h-6 text-yellow-600" />
              </div>
            </div>
          </CardContent>
        </Card>

        <Card className="rounded-xl shadow-sm">
          <CardContent className="p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm">Avg. Attendance</p>
                <h3 className="text-gray-900 mt-1">91.7%</h3>
              </div>
              <div className="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
                <CheckSquare className="w-6 h-6 text-purple-600" />
              </div>
            </div>
          </CardContent>
        </Card>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Assigned Classes */}
        <div className="lg:col-span-2 space-y-6">
          <Card className="rounded-xl shadow-sm">
            <CardHeader className="flex flex-row items-center justify-between">
              <div>
                <CardTitle>My Classes</CardTitle>
                <CardDescription>Classes you're currently teaching</CardDescription>
              </div>
              <Button size="sm" className="bg-blue-600 hover:bg-blue-700 rounded-lg">
                <Upload className="w-4 h-4 mr-2" />
                Upload Materials
              </Button>
            </CardHeader>
            <CardContent className="space-y-4">
              {assignedClasses.map((classItem) => (
                <div
                  key={classItem.id}
                  className="p-4 border border-gray-200 rounded-lg hover:shadow-md transition-shadow cursor-pointer"
                  onClick={() => onNavigate("class-details")}
                >
                  <div className="flex items-start justify-between mb-3">
                    <div className="flex-1">
                      <h4 className="text-gray-900">{classItem.name}</h4>
                      <p className="text-gray-500 text-sm mt-1">{classItem.level}</p>
                    </div>
                    <Badge
                      variant="outline"
                      className={
                        classItem.attendanceRate >= 90
                          ? "bg-green-50 text-green-600 border-green-200"
                          : "bg-yellow-50 text-yellow-600 border-yellow-200"
                      }
                    >
                      {classItem.attendanceRate}% Attendance
                    </Badge>
                  </div>
                  <div className="flex items-center justify-between">
                    <div className="flex items-center gap-2 text-sm text-gray-600">
                      <Users className="w-4 h-4" />
                      <span>{classItem.students} Students</span>
                    </div>
                    <div className="flex items-center gap-2 text-sm text-gray-600">
                      <Clock className="w-4 h-4" />
                      <span>{classItem.schedule}</span>
                    </div>
                  </div>
                  <div className="mt-3 pt-3 border-t border-gray-100 flex gap-2">
                    <Button size="sm" variant="outline" className="flex-1 rounded-lg">
                      View Attendance
                    </Button>
                    <Button size="sm" variant="outline" className="flex-1 rounded-lg">
                      Manage Class
                    </Button>
                  </div>
                </div>
              ))}
            </CardContent>
          </Card>

          {/* Recent Students */}
          <Card className="rounded-xl shadow-sm">
            <CardHeader>
              <CardTitle>Recent Student Activity</CardTitle>
              <CardDescription>Students who recently submitted work</CardDescription>
            </CardHeader>
            <CardContent>
              <div className="space-y-3">
                {recentStudents.map((student) => (
                  <div key={student.id} className="flex items-center gap-3 p-3 rounded-lg hover:bg-gray-50 transition-colors">
                    <Avatar>
                      <AvatarImage src={student.avatar} alt={student.name} />
                      <AvatarFallback>{student.name.split(" ").map(n => n[0]).join("")}</AvatarFallback>
                    </Avatar>
                    <div className="flex-1">
                      <p className="text-gray-900">{student.name}</p>
                      <p className="text-gray-500 text-sm">{student.class}</p>
                    </div>
                    <Button size="sm" variant="ghost" className="rounded-lg">
                      View
                    </Button>
                  </div>
                ))}
              </div>
            </CardContent>
          </Card>
        </div>

        {/* Sidebar */}
        <div className="space-y-6">
          {/* Upcoming Tasks */}
          <Card className="rounded-xl shadow-sm">
            <CardHeader>
              <CardTitle>Upcoming Tasks</CardTitle>
              <CardDescription>Things to complete</CardDescription>
            </CardHeader>
            <CardContent className="space-y-3">
              {upcomingTasks.map((item, index) => (
                <div key={index} className="p-3 border border-gray-200 rounded-lg">
                  <div className="flex items-start justify-between gap-2 mb-2">
                    <p className="text-gray-900 text-sm flex-1">{item.task}</p>
                    <Badge
                      variant="outline"
                      className={
                        item.priority === "high"
                          ? "bg-red-50 text-red-600 border-red-200 text-xs"
                          : item.priority === "medium"
                          ? "bg-yellow-50 text-yellow-600 border-yellow-200 text-xs"
                          : "bg-gray-50 text-gray-600 border-gray-200 text-xs"
                      }
                    >
                      {item.priority}
                    </Badge>
                  </div>
                  <div className="flex items-center gap-2 text-xs text-gray-500">
                    <Clock className="w-3 h-3" />
                    <span>{item.dueDate}</span>
                  </div>
                </div>
              ))}
            </CardContent>
          </Card>

          {/* Quick Actions */}
          <Card className="rounded-xl shadow-sm">
            <CardHeader>
              <CardTitle>Quick Actions</CardTitle>
            </CardHeader>
            <CardContent className="space-y-2">
              <Button variant="outline" className="w-full justify-start rounded-lg">
                <Upload className="w-4 h-4 mr-2" />
                Upload Materials
              </Button>
              <Button variant="outline" className="w-full justify-start rounded-lg">
                <CheckSquare className="w-4 h-4 mr-2" />
                Take Attendance
              </Button>
              <Button variant="outline" className="w-full justify-start rounded-lg">
                <FileText className="w-4 h-4 mr-2" />
                Create Assignment
              </Button>
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  );
}
