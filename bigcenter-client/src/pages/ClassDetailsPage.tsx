import { ArrowLeft, Users, Clock, Calendar, MapPin, FileText, Video, Download } from "lucide-react";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "../components/ui/card";
import { Badge } from "../components/ui/badge";
import { Button } from "../components/ui/button";
import { Avatar, AvatarFallback, AvatarImage } from "../components/ui/avatar";
import { Separator } from "../components/ui/separator";
import { Progress } from "../components/ui/progress";

interface ClassDetailsPageProps {
  onNavigate: (page: string) => void;
}

export function ClassDetailsPage({ onNavigate }: ClassDetailsPageProps) {
  const classInfo = {
    name: "Advanced Mathematics",
    code: "MATH401",
    teacher: "Dr. Sarah Johnson",
    teacherAvatar: "https://i.pravatar.cc/150?img=10",
    level: "Grade 11-12",
    students: 28,
    schedule: "Mon, Wed, Fri - 10:00 AM - 11:30 AM",
    room: "Room 301, Building A",
    description:
      "This advanced mathematics course covers calculus, linear algebra, and advanced problem-solving techniques. Students will develop critical thinking skills and mathematical reasoning.",
    progress: 75,
    startDate: "Sep 1, 2024",
    endDate: "Jun 15, 2025",
  };

  const studentsList = [
    { id: 1, name: "Emma Wilson", attendance: 95, grade: "A", avatar: "https://i.pravatar.cc/150?img=1" },
    { id: 2, name: "Liam Johnson", attendance: 88, grade: "B+", avatar: "https://i.pravatar.cc/150?img=2" },
    { id: 3, name: "Olivia Brown", attendance: 92, grade: "A-", avatar: "https://i.pravatar.cc/150?img=3" },
    { id: 4, name: "Noah Davis", attendance: 90, grade: "A", avatar: "https://i.pravatar.cc/150?img=4" },
    { id: 5, name: "Ava Martinez", attendance: 85, grade: "B", avatar: "https://i.pravatar.cc/150?img=5" },
    { id: 6, name: "Ethan Garcia", attendance: 93, grade: "A", avatar: "https://i.pravatar.cc/150?img=6" },
    { id: 7, name: "Sophia Lee", attendance: 87, grade: "B+", avatar: "https://i.pravatar.cc/150?img=7" },
    { id: 8, name: "Mason Anderson", attendance: 91, grade: "A-", avatar: "https://i.pravatar.cc/150?img=8" },
  ];

  const schedule = [
    { day: "Monday", time: "10:00 AM - 11:30 AM", topic: "Calculus - Derivatives", room: "Room 301" },
    { day: "Wednesday", time: "10:00 AM - 11:30 AM", topic: "Linear Algebra - Matrices", room: "Room 301" },
    { day: "Friday", time: "10:00 AM - 11:30 AM", topic: "Problem Solving Session", room: "Room 301" },
  ];

  const materials = [
    { name: "Lecture Notes - Week 8.pdf", type: "PDF", size: "2.4 MB", date: "Oct 20, 2025" },
    { name: "Assignment 5 - Derivatives.docx", type: "DOCX", size: "156 KB", date: "Oct 22, 2025" },
    { name: "Practice Problems Set 3.pdf", type: "PDF", size: "1.8 MB", date: "Oct 18, 2025" },
    { name: "Video Lecture - Matrices.mp4", type: "Video", size: "245 MB", date: "Oct 21, 2025" },
  ];

  return (
    <div className="space-y-6">
      {/* Back Button */}
      <Button
        variant="ghost"
        onClick={() => onNavigate("student-dashboard")}
        className="rounded-lg"
      >
        <ArrowLeft className="w-4 h-4 mr-2" />
        Back to Dashboard
      </Button>

      {/* Class Header */}
      <Card className="rounded-xl shadow-sm">
        <CardContent className="p-6">
          <div className="flex items-start justify-between mb-4">
            <div className="flex-1">
              <div className="flex items-center gap-3 mb-2">
                <h1 className="text-gray-900">{classInfo.name}</h1>
                <Badge variant="outline" className="bg-blue-50 text-blue-600 border-blue-200">
                  {classInfo.code}
                </Badge>
                <Badge variant="outline" className="bg-green-50 text-green-600 border-green-200">
                  Active
                </Badge>
              </div>
              <p className="text-gray-600">{classInfo.description}</p>
            </div>
          </div>

          <Separator className="my-4" />

          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
            <div className="flex items-center gap-3">
              <div className="w-12 h-12 bg-blue-50 rounded-lg flex items-center justify-center">
                <Avatar className="w-10 h-10">
                  <AvatarImage src={classInfo.teacherAvatar} alt={classInfo.teacher} />
                  <AvatarFallback>SJ</AvatarFallback>
                </Avatar>
              </div>
              <div>
                <p className="text-gray-500 text-sm">Instructor</p>
                <p className="text-gray-900">{classInfo.teacher}</p>
              </div>
            </div>

            <div className="flex items-center gap-3">
              <div className="w-12 h-12 bg-green-50 rounded-lg flex items-center justify-center">
                <Users className="w-6 h-6 text-green-600" />
              </div>
              <div>
                <p className="text-gray-500 text-sm">Students</p>
                <p className="text-gray-900">{classInfo.students} Enrolled</p>
              </div>
            </div>

            <div className="flex items-center gap-3">
              <div className="w-12 h-12 bg-yellow-50 rounded-lg flex items-center justify-center">
                <Clock className="w-6 h-6 text-yellow-600" />
              </div>
              <div>
                <p className="text-gray-500 text-sm">Schedule</p>
                <p className="text-gray-900 text-sm">{classInfo.schedule.split(" - ")[0]}</p>
              </div>
            </div>

            <div className="flex items-center gap-3">
              <div className="w-12 h-12 bg-purple-50 rounded-lg flex items-center justify-center">
                <MapPin className="w-6 h-6 text-purple-600" />
              </div>
              <div>
                <p className="text-gray-500 text-sm">Location</p>
                <p className="text-gray-900">{classInfo.room.split(",")[0]}</p>
              </div>
            </div>
          </div>

          <div className="mt-6">
            <div className="flex items-center justify-between mb-2">
              <span className="text-gray-600 text-sm">Course Progress</span>
              <span className="text-gray-900">{classInfo.progress}%</span>
            </div>
            <Progress value={classInfo.progress} className="h-2" />
            <div className="flex items-center justify-between mt-2 text-xs text-gray-500">
              <span>Started: {classInfo.startDate}</span>
              <span>Ends: {classInfo.endDate}</span>
            </div>
          </div>
        </CardContent>
      </Card>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Left Column */}
        <div className="lg:col-span-2 space-y-6">
          {/* Students List */}
          <Card className="rounded-xl shadow-sm">
            <CardHeader>
              <CardTitle>Enrolled Students</CardTitle>
              <CardDescription>Students currently taking this class</CardDescription>
            </CardHeader>
            <CardContent>
              <div className="space-y-3">
                {studentsList.map((student) => (
                  <div
                    key={student.id}
                    className="flex items-center justify-between p-3 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors"
                  >
                    <div className="flex items-center gap-3">
                      <Avatar>
                        <AvatarImage src={student.avatar} alt={student.name} />
                        <AvatarFallback>{student.name.split(" ").map(n => n[0]).join("")}</AvatarFallback>
                      </Avatar>
                      <div>
                        <p className="text-gray-900">{student.name}</p>
                        <p className="text-gray-500 text-sm">Attendance: {student.attendance}%</p>
                      </div>
                    </div>
                    <Badge
                      variant="outline"
                      className={
                        student.grade.startsWith("A")
                          ? "bg-green-50 text-green-600 border-green-200"
                          : "bg-blue-50 text-blue-600 border-blue-200"
                      }
                    >
                      Grade: {student.grade}
                    </Badge>
                  </div>
                ))}
              </div>
            </CardContent>
          </Card>

          {/* Course Materials */}
          <Card className="rounded-xl shadow-sm">
            <CardHeader>
              <CardTitle>Course Materials</CardTitle>
              <CardDescription>Download lecture notes, assignments, and resources</CardDescription>
            </CardHeader>
            <CardContent>
              <div className="space-y-3">
                {materials.map((material, index) => (
                  <div
                    key={index}
                    className="flex items-center justify-between p-3 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors"
                  >
                    <div className="flex items-center gap-3 flex-1">
                      <div className="w-10 h-10 bg-blue-50 rounded-lg flex items-center justify-center">
                        {material.type === "Video" ? (
                          <Video className="w-5 h-5 text-blue-600" />
                        ) : (
                          <FileText className="w-5 h-5 text-blue-600" />
                        )}
                      </div>
                      <div className="flex-1 min-w-0">
                        <p className="text-gray-900 truncate">{material.name}</p>
                        <p className="text-gray-500 text-sm">
                          {material.size} • {material.date}
                        </p>
                      </div>
                    </div>
                    <Button size="sm" variant="ghost" className="rounded-lg">
                      <Download className="w-4 h-4" />
                    </Button>
                  </div>
                ))}
              </div>
            </CardContent>
          </Card>
        </div>

        {/* Right Column */}
        <div className="space-y-6">
          {/* Class Schedule */}
          <Card className="rounded-xl shadow-sm">
            <CardHeader>
              <CardTitle>Class Schedule</CardTitle>
              <CardDescription>Weekly meeting times</CardDescription>
            </CardHeader>
            <CardContent className="space-y-3">
              {schedule.map((item, index) => (
                <div key={index} className="p-3 border border-gray-200 rounded-lg">
                  <div className="flex items-center gap-2 mb-2">
                    <Calendar className="w-4 h-4 text-blue-600" />
                    <p className="text-gray-900">{item.day}</p>
                  </div>
                  <p className="text-gray-600 text-sm mb-1">{item.time}</p>
                  <p className="text-gray-900 text-sm">{item.topic}</p>
                  <div className="flex items-center gap-2 mt-2 text-xs text-gray-500">
                    <MapPin className="w-3 h-3" />
                    <span>{item.room}</span>
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
                <FileText className="w-4 h-4 mr-2" />
                Submit Assignment
              </Button>
              <Button variant="outline" className="w-full justify-start rounded-lg">
                <Users className="w-4 h-4 mr-2" />
                View Classmates
              </Button>
              <Button variant="outline" className="w-full justify-start rounded-lg">
                <Calendar className="w-4 h-4 mr-2" />
                Add to Calendar
              </Button>
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  );
}
