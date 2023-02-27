import {
  ProColumns,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import React from 'react';
import {Modal} from "antd";
export type FormValueType = {
  target?: string;
  template?: string;
  type?: string;
  time?: string;
  frequency?: string;
} & Partial<API.RuleListItem>;
export type Props = {
  columns: ProColumns<API.InterfaceInfo>[];
  onCancel: () => void;
  onSubmit: (values: API.InterfaceInfo) => void;
  // onSubmit: (values: API.InterfaceInfo) => Promise<void>;
  visible: boolean;
 // values: Partial<API.RuleListItem>;
};
const CreateModal: React.FC<Props> = (props) => {
  const {columns, visible, onCancel, onSubmit} = props

  return (
  <Modal
    visible={visible}
    title= "添加接口"
    onCancel={()=> onCancel?.()}
    footer={null}
  >
    <ProTable
      type="form"
      columns={columns}
      onSubmit={
        async (value) => {
          console.log(value)
          onSubmit?.(value)
        }
      } />
  </Modal>

  )
};
export default CreateModal;
