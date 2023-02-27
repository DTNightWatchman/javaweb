import {
  ProColumns, ProFormInstance,
  ProTable,
} from '@ant-design/pro-components';
import '@umijs/max';
import React, {useEffect, useRef} from 'react';
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
  onSubmit: (values: API.InterfaceInfo) => Promise<void>;
  visible: boolean;
  values: API.InterfaceInfo;
};
const UpdateModel: React.FC<Props> = (props) => {
  // eslint-disable-next-line @typescript-eslint/no-shadow
  const {columns, visible, onCancel, onSubmit, values} = props
  const formRef = useRef<ProFormInstance>();
  useEffect(() => {
    if (formRef) {
      formRef.current?.setFieldsValue(values);
    }
  }, [values])
  return (
  <Modal
    visible={visible}
    title= "修改接口信息"
    onCancel={()=> onCancel?.()}
    footer={null}
  >
    <ProTable
      type="form"
      columns={columns}
      formRef={formRef}
      // form={{
      //   initialValues: values
      // }}
      onSubmit={
        async (value) => {
          await onSubmit(value)
        }
      } />
  </Modal>

  )
};
export default UpdateModel;
