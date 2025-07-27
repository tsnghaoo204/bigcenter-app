import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  DateField,
  TextInput,
  DateInput,
  SimpleForm,
  Edit,
  Create,
  Show,
  SimpleShowLayout,
  ShowButton,
  EditButton,
  DeleteButton,
} from 'react-admin';

// 📋 Danh sách học sinh (GET)
export const StudentList = () => (
  <List>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="studentName" />
      <DateField source="dob" />
      <TextField source="phone" />
      <TextField source="guardianInf" />
      <TextField source="userId" />
      <ShowButton />
      <EditButton />
      <DeleteButton />
    </Datagrid>
  </List>
);

// ✏️ Sửa học sinh (PUT)
export const StudentEdit = () => (
  <Edit>
    <SimpleForm>
      <TextInput source="id" disabled />
      <DateInput source="dob" />
      <TextInput source="phone" />
      <TextInput source="guardianInf" />
    </SimpleForm>
  </Edit>
);

// ➕ Tạo học sinh mới (POST)
export const StudentCreate = () => (
  <Create>
    <SimpleForm>
      <DateInput source="dob" />
      <TextInput source="phone" />
      <TextInput source="guardianInf" />
      <TextInput source="userId" />
    </SimpleForm>
  </Create>
);

// 🔍 Xem chi tiết học sinh
export const StudentShow = () => (
  <Show>
    <SimpleShowLayout>
      <TextField source="id" />
      <TextField source="studentName" />
      <DateField source="dob" />
      <TextField source="phone" />
      <TextField source="guardianInf" />
      <TextField source="userId" />
    </SimpleShowLayout>
  </Show>
);
