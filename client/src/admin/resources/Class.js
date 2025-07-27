import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  DateField,
  Edit,
  SimpleForm,
  TextInput,
  DateInput,
  Create,
  Show,
  SimpleShowLayout,
  ShowButton,
  EditButton,
  DeleteButton,
} from 'react-admin';

// 📋 Danh sách lớp học
export const ClassList = () => (
  <List>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="name" />
      <TextField source="subject" />
      <DateField source="startDate" />
      <DateField source="endDate" />
      <ShowButton />
      <EditButton />
      <DeleteButton />
    </Datagrid>
  </List>
);

// ✏️ Sửa lớp học
export const ClassEdit = () => (
  <Edit>
    <SimpleForm>
      <TextInput source="id" disabled />
      <TextInput source="name" />
      <TextInput source="subject" />
      <DateInput source="startDate" />
      <DateInput source="endDate" />
    </SimpleForm>
  </Edit>
);

// ➕ Tạo lớp học mới
export const ClassCreate = () => (
  <Create>
    <SimpleForm>
      <TextInput source="name" />
      <TextInput source="subject" />
      <DateInput source="startDate" />
      <DateInput source="endDate" />
    </SimpleForm>
  </Create>
);

// 🔍 Xem chi tiết lớp học
export const ClassShow = () => (
  <Show>
    <SimpleShowLayout>
      <TextField source="id" />
      <TextField source="name" />
      <TextField source="subject" />
      <DateField source="startDate" />
      <DateField source="endDate" />
    </SimpleShowLayout>
  </Show>
);
