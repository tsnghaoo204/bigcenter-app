import { BookOpen, Clock, Award, TrendingUp, Calendar, Users } from "lucide-react";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "../components/ui/card";
import { Progress } from "../components/ui/progress";
import { Badge } from "../components/ui/badge";
import { Avatar, AvatarFallback, AvatarImage } from "../components/ui/avatar";
import { Button } from "../components/ui/button";

interface StudentDashboardProps {
  onNavigate: (page: string) => void;
}

export function StudentDashboard({ onNavigate }: StudentDashboardProps) {
  const enrolledClasses = [
    {
      id: 1,
      name: "Advanced Mathematics",
      teacher: "Dr. Sarah Johnson",
      progress: 75,
      nextClass: "Mon, 10:00 AM",
      students: 28,
    },
    {
      id: 2,
      name: "Physics Fundamentals",
      teacher: "Prof. Michael Chen",
      progress: 60,
      nextClass: "Tue, 2:00 PM",
      students: 32,
    },
    {
      id: 3,
      name: "Computer Science 101",
      teacher: "Dr. Emily Davis",
      progress: 85,
      nextClass: "Wed, 11:00 AM",
      students: 25,
    },
    {
      id: 4,
      name: "English Literature",
      teacher: "Prof. James Wilson",
      progress: 45,
      nextClass: "Thu, 3:00 PM",
      students: 30,
    },
  ];

  const upcomingSchedule = [
    { day: "Monday", time: "10:00 AM", class: "Advanced Mathematics", room: "Room 301" },
    { day: "Monday", time: "2:00 PM", class: "Computer Science 101", room: "Lab 204" },
    { day: "Tuesday", time: "2:00 PM", class: "Physics Fundamentals", room: "Room 102" },
    { day: "Wednesday", time: "11:00 AM", class: "Computer Science 101", room: "Lab 204" },
    { day: "Thursday", time: "3:00 PM", class: "English Literature", room: "Room 205" },
  ];

  return (
    <div className="space-y-6">
      {/* Header */}
      <div>
        <h1 className="text-gray-900 mb-2">Welcome back, Alex!</h1>
        <p className="text-gray-600">Here's what's happening with your courses today.</p>
      </div>

      {/* Stats Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        <Card className="rounded-xl shadow-sm">
          <CardContent className="p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm">Enrolled Classes</p>
                <h3 className="text-gray-900 mt-1">4</h3>
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
                <p className="text-gray-500 text-sm">Hours Studied</p>
                <h3 className="text-gray-900 mt-1">24.5</h3>
              </div>
              <div className="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center">
                <Clock className="w-6 h-6 text-green-600" />
              </div>
            </div>
          </CardContent>
        </Card>

        <Card className="rounded-xl shadow-sm">
          <CardContent className="p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm">Average Grade</p>
                <h3 className="text-gray-900 mt-1">85.5%</h3>
              </div>
              <div className="w-12 h-12 bg-yellow-100 rounded-lg flex items-center justify-center">
                <Award className="w-6 h-6 text-yellow-600" />
              </div>
            </div>
          </CardContent>
        </Card>

        <Card className="rounded-xl shadow-sm">
          <CardContent className="p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm">Completed</p>
                <h3 className="text-gray-900 mt-1">12/16</h3>
              </div>
              <div className="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
                <TrendingUp className="w-6 h-6 text-purple-600" />
              </div>
            </div>
          </CardContent>
        </Card>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Enrolled Classes */}
        <div className="lg:col-span-2 space-y-4">
          <Card className="rounded-xl shadow-sm">
            <CardHeader>
              <CardTitle>My Classes</CardTitle>
              <CardDescription>Track your progress in each course</CardDescription>
            </CardHeader>
            <CardContent className="space-y-4">
              {enrolledClasses.map((course) => (
                <div
                  key={course.id}
                  className="p-4 border border-gray-200 rounded-lg hover:shadow-md transition-shadow cursor-pointer"
                  onClick={() => onNavigate("class-details")}
                >
                  <div className="flex items-start justify-between mb-3">
                    <div className="flex-1">
                      <h4 className="text-gray-900">{course.name}</h4>
                      <p className="text-gray-500 text-sm mt-1">{course.teacher}</p>
                    </div>
                    <Badge variant="outline" className="bg-blue-50 text-blue-600 border-blue-200">
                      Active
                    </Badge>
                  </div>
                  <div className="space-y-2">
                    <div className="flex items-center justify-between text-sm">
                      <span className="text-gray-600">Progress</span>
                      <span className="text-gray-900">{course.progress}%</span>
                    </div>
                    <Progress value={course.progress} className="h-2" />
                  </div>
                  <div className="flex items-center justify-between mt-3 pt-3 border-t border-gray-100">
                    <div className="flex items-center gap-2 text-sm text-gray-600">
                      <Clock className="w-4 h-4" />
                      <span>{course.nextClass}</span>
                    </div>
                    <div className="flex items-center gap-2 text-sm text-gray-600">
                      <Users className="w-4 h-4" />
                      <span>{course.students} students</span>
                    </div>
                  </div>
                </div>
              ))}
            </CardContent>
          </Card>
        </div>

        {/* Sidebar */}
        <div className="space-y-6">
          {/* Upcoming Schedule */}
          <Card className="rounded-xl shadow-sm">
            <CardHeader>
              <CardTitle>Upcoming Schedule</CardTitle>
              <CardDescription>This week's classes</CardDescription>
            </CardHeader>
            <CardContent className="space-y-3">
              {upcomingSchedule.slice(0, 5).map((schedule, index) => (
                <div key={index} className="flex items-start gap-3 pb-3 border-b border-gray-100 last:border-0 last:pb-0">
                  <div className="w-10 h-10 bg-blue-50 rounded-lg flex items-center justify-center flex-shrink-0">
                    <Calendar className="w-5 h-5 text-blue-600" />
                  </div>
                  <div className="flex-1 min-w-0">
                    <p className="text-gray-900 text-sm truncate">{schedule.class}</p>
                    <p className="text-gray-500 text-xs mt-1">{schedule.day}, {schedule.time}</p>
                    <p className="text-gray-400 text-xs">{schedule.room}</p>
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
                <BookOpen className="w-4 h-4 mr-2" />
                View All Classes
              </Button>
              <Button variant="outline" className="w-full justify-start rounded-lg">
                <Calendar className="w-4 h-4 mr-2" />
                Check Schedule
              </Button>
              <Button variant="outline" className="w-full justify-start rounded-lg">
                <Award className="w-4 h-4 mr-2" />
                View Grades
              </Button>
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  );
}
