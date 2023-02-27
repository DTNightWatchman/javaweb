import {
  ProColumns, ProFormInstance,
  ProTable,
} from '@ant-design/pro-components';

import '@umijs/max';
import {Modal} from "antd";
import '@umijs/max';
import React, {useEffect, useRef} from 'react';
export type FormValueType = {
  target?: string;
  template?: string;
  type?: string;
  time?: string;
  frequency?: string;
} & Partial<API.RuleListItem>;
export type Props = {
  columns: ProColumns<API.UserVO>[];
  onCancel: () => void;
  onSubmit: (values: API.User) => Promise<void>;
  visible: boolean;
  values: API.UserVO;
};
const UpdateModel: React.FC<Props> = (props) => {
  // eslint-disable-next-line @typescript-eslint/no-shadow
  const {columns, visible, onCancel, onSubmit, values} = props
  const formRef = useRef<ProFormInstance>();
  console.log(values);
  useEffect(() => {
    if (formRef) {
      formRef?.current?.setFieldsValue(values);
    }
  })
  return (
  <Modal
    visible={visible}
    title= "修改用户信息"
    onCancel={()=> onCancel?.()}
    footer={null}
  >
    <ProTable
      type="form"
      columns={columns}
      formRef={formRef}
      onSubmit={
        async (value) => {
          console.log(value)
          await onSubmit(value)
        }
      } />
  </Modal>

  )
};
export default UpdateModel;
