import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  EmailField,
  BooleanField,
  DateField,
  Edit,
  SimpleForm,
  TextInput,
  BooleanInput,
  Create,
  Show,
  SimpleShowLayout,
  EditButton,
  DeleteButton,
  ShowButton
} from 'react-admin';

// Danh sách người dùng
export const UserList = () => (
  <List>
    <Datagrid>
      <TextField source="id" />
      <TextField source="fullName" />
      <EmailField source="email" />
      <TextField source="phone" />
      <BooleanField source="enable" />
      <DateField source="createAt" />
      <EditButton />
      <ShowButton />
      <DeleteButton />
    </Datagrid>
  </List>
);

// Chỉnh sửa người dùng
export const UserEdit = () => (
  <Edit>
    <SimpleForm>
      <TextInput disabled source="id" />
      <TextInput source="fullName" />
      <TextInput source="email" />
      <TextInput source="phone" />
      <BooleanInput source="enable" />
    </SimpleForm>
  </Edit>
);

// Tạo mới người dùng
export const UserCreate = () => (
  <Create>
    <SimpleForm>
      <TextInput source="fullName" />
      <TextInput source="email" />
      <TextInput source="phone" />
      <BooleanInput source="enable" defaultValue={true} />
    </SimpleForm>
  </Create>
);

// Xem chi tiết người dùng
export const UserShow = () => (
  <Show>
    <SimpleShowLayout>
      <TextField source="id" />
      <TextField source="fullName" />
      <EmailField source="email" />
      <TextField source="phone" />
      <BooleanField source="enable" />
      <DateField source="createAt" />
    </SimpleShowLayout>
  </Show>
);
