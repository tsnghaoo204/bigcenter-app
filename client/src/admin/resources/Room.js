import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  NumberField,
  TextInput,
  NumberInput,
  SimpleForm,
  Edit,
  Create,
  Show,
  SimpleShowLayout,
  ShowButton,
  EditButton,
  DeleteButton,
} from 'react-admin';

// 📋 Danh sách phòng
export const RoomList = () => (
  <List>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="name" />
      <NumberField source="capacity" />
      <ShowButton />
      <EditButton />
      <DeleteButton />
    </Datagrid>
  </List>
);

// ✏️ Sửa phòng
export const RoomEdit = () => (
  <Edit>
    <SimpleForm>
      <TextInput source="id" disabled />
      <TextInput source="name" />
      <NumberInput source="capacity" />
    </SimpleForm>
  </Edit>
);

// ➕ Tạo phòng mới
export const RoomCreate = () => (
  <Create>
    <SimpleForm>
      <TextInput source="name" />
      <NumberInput source="capacity" />
    </SimpleForm>
  </Create>
);

// 🔍 Xem chi tiết phòng
export const RoomShow = () => (
  <Show>
    <SimpleShowLayout>
      <TextField source="id" />
      <TextField source="name" />
      <NumberField source="capacity" />
    </SimpleShowLayout>
  </Show>
);
