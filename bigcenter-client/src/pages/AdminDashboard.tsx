import { Users, BookOpen, GraduationCap, TrendingUp, Search, Plus, Edit, Trash2 } from "lucide-react";
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "../components/ui/card";
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "../components/ui/table";
import { Badge } from "../components/ui/badge";
import { Button } from "../components/ui/button";
import { Input } from "../components/ui/input";
import { Avatar, AvatarFallback, AvatarImage } from "../components/ui/avatar";

interface AdminDashboardProps {
  onNavigate: (page: string) => void;
}

export function AdminDashboard({ onNavigate }: AdminDashboardProps) {
  const users = [
    { id: 1, name: "Emma Wilson", email: "emma.w@educenter.com", role: "Student", status: "Active", joinDate: "Jan 15, 2025" },
    { id: 2, name: "Dr. Sarah Johnson", email: "sarah.j@educenter.com", role: "Teacher", status: "Active", joinDate: "Sep 1, 2024" },
    { id: 3, name: "Liam Brown", email: "liam.b@educenter.com", role: "Student", status: "Active", joinDate: "Feb 20, 2025" },
    { id: 4, name: "Prof. Michael Chen", email: "michael.c@educenter.com", role: "Teacher", status: "Active", joinDate: "Aug 10, 2024" },
    { id: 5, name: "Olivia Davis", email: "olivia.d@educenter.com", role: "Student", status: "Inactive", joinDate: "Mar 5, 2025" },
  ];

  const classes = [
    { id: 1, name: "Advanced Mathematics", teacher: "Dr. Sarah Johnson", students: 28, schedule: "Mon, Wed, Fri", status: "Active" },
    { id: 2, name: "Physics Fundamentals", teacher: "Prof. Michael Chen", students: 32, schedule: "Tue, Thu", status: "Active" },
    { id: 3, name: "Computer Science 101", teacher: "Dr. Emily Davis", students: 25, schedule: "Mon, Wed", status: "Active" },
    { id: 4, name: "English Literature", teacher: "Prof. James Wilson", students: 30, schedule: "Tue, Thu", status: "Active" },
  ];

  const teachers = [
    { id: 1, name: "Dr. Sarah Johnson", department: "Mathematics", classes: 3, students: 84, rating: 4.8 },
    { id: 2, name: "Prof. Michael Chen", department: "Physics", classes: 2, students: 56, rating: 4.9 },
    { id: 3, name: "Dr. Emily Davis", department: "Computer Science", classes: 2, students: 48, rating: 4.7 },
    { id: 4, name: "Prof. James Wilson", department: "English", classes: 2, students: 52, rating: 4.6 },
  ];

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-gray-900 mb-2">Admin Dashboard</h1>
          <p className="text-gray-600">Manage all users, classes, and system settings.</p>
        </div>
        <Button className="bg-blue-600 hover:bg-blue-700 rounded-lg">
          <Plus className="w-4 h-4 mr-2" />
          Add New User
        </Button>
      </div>

      {/* Stats Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        <Card className="rounded-xl shadow-sm">
          <CardContent className="p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm">Total Students</p>
                <h3 className="text-gray-900 mt-1">1,248</h3>
                <p className="text-green-600 text-xs mt-1">+12% this month</p>
              </div>
              <div className="w-12 h-12 bg-blue-100 rounded-lg flex items-center justify-center">
                <Users className="w-6 h-6 text-blue-600" />
              </div>
            </div>
          </CardContent>
        </Card>

        <Card className="rounded-xl shadow-sm">
          <CardContent className="p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm">Total Teachers</p>
                <h3 className="text-gray-900 mt-1">64</h3>
                <p className="text-green-600 text-xs mt-1">+3 new this month</p>
              </div>
              <div className="w-12 h-12 bg-green-100 rounded-lg flex items-center justify-center">
                <GraduationCap className="w-6 h-6 text-green-600" />
              </div>
            </div>
          </CardContent>
        </Card>

        <Card className="rounded-xl shadow-sm">
          <CardContent className="p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm">Active Classes</p>
                <h3 className="text-gray-900 mt-1">156</h3>
                <p className="text-gray-500 text-xs mt-1">Across all departments</p>
              </div>
              <div className="w-12 h-12 bg-yellow-100 rounded-lg flex items-center justify-center">
                <BookOpen className="w-6 h-6 text-yellow-600" />
              </div>
            </div>
          </CardContent>
        </Card>

        <Card className="rounded-xl shadow-sm">
          <CardContent className="p-6">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-gray-500 text-sm">Enrollment Rate</p>
                <h3 className="text-gray-900 mt-1">94.2%</h3>
                <p className="text-green-600 text-xs mt-1">+2.4% from last year</p>
              </div>
              <div className="w-12 h-12 bg-purple-100 rounded-lg flex items-center justify-center">
                <TrendingUp className="w-6 h-6 text-purple-600" />
              </div>
            </div>
          </CardContent>
        </Card>
      </div>

      {/* Users Management */}
      <Card className="rounded-xl shadow-sm">
        <CardHeader>
          <div className="flex items-center justify-between">
            <div>
              <CardTitle>User Management</CardTitle>
              <CardDescription>Manage all registered users in the system</CardDescription>
            </div>
            <div className="flex gap-2">
              <div className="relative">
                <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-400" />
                <Input
                  type="text"
                  placeholder="Search users..."
                  className="pl-10 w-64 rounded-lg"
                />
              </div>
            </div>
          </div>
        </CardHeader>
        <CardContent>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead>Name</TableHead>
                <TableHead>Email</TableHead>
                <TableHead>Role</TableHead>
                <TableHead>Status</TableHead>
                <TableHead>Join Date</TableHead>
                <TableHead className="text-right">Actions</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {users.map((user) => (
                <TableRow key={user.id}>
                  <TableCell>
                    <div className="flex items-center gap-3">
                      <Avatar className="w-8 h-8">
                        <AvatarImage src={`https://i.pravatar.cc/150?img=${user.id}`} />
                        <AvatarFallback>{user.name.split(" ").map(n => n[0]).join("")}</AvatarFallback>
                      </Avatar>
                      <span>{user.name}</span>
                    </div>
                  </TableCell>
                  <TableCell className="text-gray-600">{user.email}</TableCell>
                  <TableCell>
                    <Badge
                      variant="outline"
                      className={
                        user.role === "Teacher"
                          ? "bg-blue-50 text-blue-600 border-blue-200"
                          : "bg-green-50 text-green-600 border-green-200"
                      }
                    >
                      {user.role}
                    </Badge>
                  </TableCell>
                  <TableCell>
                    <Badge
                      variant="outline"
                      className={
                        user.status === "Active"
                          ? "bg-green-50 text-green-600 border-green-200"
                          : "bg-gray-50 text-gray-600 border-gray-200"
                      }
                    >
                      {user.status}
                    </Badge>
                  </TableCell>
                  <TableCell className="text-gray-600">{user.joinDate}</TableCell>
                  <TableCell className="text-right">
                    <div className="flex justify-end gap-2">
                      <Button size="sm" variant="ghost" className="rounded-lg">
                        <Edit className="w-4 h-4" />
                      </Button>
                      <Button size="sm" variant="ghost" className="rounded-lg text-red-600 hover:text-red-700 hover:bg-red-50">
                        <Trash2 className="w-4 h-4" />
                      </Button>
                    </div>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </CardContent>
      </Card>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        {/* Classes Management */}
        <Card className="rounded-xl shadow-sm">
          <CardHeader>
            <div className="flex items-center justify-between">
              <div>
                <CardTitle>Classes Overview</CardTitle>
                <CardDescription>Active classes in the system</CardDescription>
              </div>
              <Button size="sm" variant="outline" className="rounded-lg">
                <Plus className="w-4 h-4 mr-2" />
                Add Class
              </Button>
            </div>
          </CardHeader>
          <CardContent>
            <div className="space-y-3">
              {classes.map((classItem) => (
                <div
                  key={classItem.id}
                  className="p-4 border border-gray-200 rounded-lg hover:shadow-md transition-shadow cursor-pointer"
                  onClick={() => onNavigate("class-details")}
                >
                  <div className="flex items-start justify-between mb-2">
                    <div className="flex-1">
                      <h4 className="text-gray-900">{classItem.name}</h4>
                      <p className="text-gray-500 text-sm mt-1">{classItem.teacher}</p>
                    </div>
                    <Badge variant="outline" className="bg-green-50 text-green-600 border-green-200">
                      {classItem.status}
                    </Badge>
                  </div>
                  <div className="flex items-center gap-4 text-sm text-gray-600">
                    <span>{classItem.students} students</span>
                    <span>•</span>
                    <span>{classItem.schedule}</span>
                  </div>
                </div>
              ))}
            </div>
          </CardContent>
        </Card>

        {/* Teachers Overview */}
        <Card className="rounded-xl shadow-sm">
          <CardHeader>
            <div className="flex items-center justify-between">
              <div>
                <CardTitle>Teachers Overview</CardTitle>
                <CardDescription>Teaching staff performance</CardDescription>
              </div>
              <Button size="sm" variant="outline" className="rounded-lg">
                View All
              </Button>
            </div>
          </CardHeader>
          <CardContent>
            <div className="space-y-3">
              {teachers.map((teacher) => (
                <div key={teacher.id} className="p-4 border border-gray-200 rounded-lg">
                  <div className="flex items-center justify-between mb-3">
                    <div className="flex items-center gap-3">
                      <Avatar>
                        <AvatarImage src={`https://i.pravatar.cc/150?img=${teacher.id + 10}`} />
                        <AvatarFallback>{teacher.name.split(" ").map(n => n[0]).join("")}</AvatarFallback>
                      </Avatar>
                      <div>
                        <h4 className="text-gray-900">{teacher.name}</h4>
                        <p className="text-gray-500 text-sm">{teacher.department}</p>
                      </div>
                    </div>
                    <div className="text-right">
                      <div className="flex items-center gap-1">
                        <span className="text-yellow-500">★</span>
                        <span className="text-gray-900">{teacher.rating}</span>
                      </div>
                    </div>
                  </div>
                  <div className="flex items-center gap-4 text-sm text-gray-600">
                    <span>{teacher.classes} classes</span>
                    <span>•</span>
                    <span>{teacher.students} students</span>
                  </div>
                </div>
              ))}
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
