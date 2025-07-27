import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  TextInput,
  SimpleForm,
  Edit,
  Create,
  Show,
  SimpleShowLayout,
  ShowButton,
  DeleteButton,
  EditButton
} from 'react-admin';

// 📄 Danh sách giáo viên (GET)
export const TeacherList = () => (
  <List>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="teacherName" />
      <TextField source="specialization" />
      <TextField source="phone" />
      <TextField source="userId" />
      <ShowButton />
      <EditButton />
      <DeleteButton />
    </Datagrid>
  </List>
);

// ✏️ Sửa thông tin giáo viên (PUT)
export const TeacherEdit = () => (
  <Edit>
    <SimpleForm>
      <TextInput source="id" disabled />
      <TextInput source="specialization" />
      <TextInput source="phone" />
    </SimpleForm>
  </Edit>
);

// ➕ Tạo giáo viên mới (POST)
export const TeacherCreate = () => (
  <Create>
    <SimpleForm>
      <TextInput source="specialization" />
      <TextInput source="phone" />
      <TextInput source="userId" />
    </SimpleForm>
  </Create>
);

// 🔍 Xem chi tiết giáo viên (GET /teacher/:id)
export const TeacherShow = () => (
  <Show>
    <SimpleShowLayout>
      <TextField source="id" />
      <TextField source="teacherName" />
      <TextField source="specialization" />
      <TextField source="phone" />
      <TextField source="userId" />
    </SimpleShowLayout>
  </Show>
);
