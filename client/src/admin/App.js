import React from 'react';
import { Admin, Resource } from 'react-admin';
import { UserList, UserEdit, UserCreate, UserShow } from './resources/User';
import { TeacherList, TeacherEdit, TeacherCreate, TeacherShow } from './resources/Teacher';
import { StudentList, StudentEdit, StudentCreate, StudentShow } from './resources/Student';
import { RoomList, RoomEdit, RoomCreate, RoomShow } from './resources/Room';
import { ClassList, ClassEdit, ClassCreate, ClassShow } from './resources/Class';
import dataProvider from './DataProvider';

const AdminApp = () => (
  <Admin basename="/admin" dataProvider={dataProvider}>
    <Resource name="users" list={UserList} edit={UserEdit} create={UserCreate} show={UserShow} />
    <Resource name="teachers" list={TeacherList} edit={TeacherEdit} create={TeacherCreate} show={TeacherShow} />
    <Resource name="students" list={StudentList} edit={StudentEdit} create={StudentCreate} show={StudentShow} />
    <Resource name="rooms" list={RoomList} edit={RoomEdit} create={RoomCreate} show={RoomShow} />
    <Resource name="classes" list={ClassList} edit={ClassEdit} create={ClassCreate} show={ClassShow} />
  </Admin>
);

export default AdminApp;
