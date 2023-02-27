import { PageContainer } from '@ant-design/pro-components';
import {Button, Card, Descriptions, Divider, message} from 'antd';
import React, {useEffect, useState} from 'react';
import {
  getInterfaceInfoByIdUsingGET, invokeInterfaceInfoUsingPOST,
} from "@/services/api-backend/interfaceInfoController";
import { useParams } from 'umi';
import Form from 'antd/es/form';
import TextArea from "antd/es/input/TextArea";
const Index: React.FC = () => {
  //const [setLoading] = useState(false);
  const [data, setData] = useState<API.InterfaceInfo>();
  const [invokeLoading ,setInvokeLoading] = useState<boolean>(false)
  const [invokeRes, setInvokeRes] = useState<any>()
  const params = useParams();

  const loadDate = async () => {
    if (!params.id) {
      message.error("无请求参数");
    }
    try {
      setInvokeLoading(true);
      const res = await getInterfaceInfoByIdUsingGET({
        id: Number(params?.id),
      });
      setData(res?.data);
    } catch (error: any) {
      message.error('加载数据失败:' + error.message);
    }
    setInvokeLoading(false);
  }

  const onFinish = async (values: any) => {
    if (!params.id) {
      message.error("参数不存在");
      return;
    }

    try {
      const res = await invokeInterfaceInfoUsingPOST({
        id: params.id,
        ...values
      })
      setInvokeRes(res?.data);
      message.success("请求成功");
    } catch (error: any) {
      message.error('请求失败:' + error.message);
    }

  };


  useEffect(() => {
    loadDate();
  }, [])

  // @ts-ignore
  return (
    <PageContainer title={"在线接口文档"}>
      <Card>
        {
          data ? (
            <Descriptions title={data.name} column={1} >
              <Descriptions.Item label="接口状态">{data.status === 0 ? "未上线" : "正常"}</Descriptions.Item>
              <Descriptions.Item label="描述">{data.description}</Descriptions.Item>
              <Descriptions.Item label="请求地址">{data.url}</Descriptions.Item>
              <Descriptions.Item label="请求参数">{data.requestParams}</Descriptions.Item>
              <Descriptions.Item label="请求方法">{data.method}</Descriptions.Item>
              <Descriptions.Item label="请求头">{data.requestHeader}</Descriptions.Item>
              <Descriptions.Item label="响应头">{data.responseHeader}</Descriptions.Item>
              <Descriptions.Item label="接口发布时间">{data.status}</Descriptions.Item>
              <Descriptions.Item label="接口更新时间">{data.status}</Descriptions.Item>
            </Descriptions>
          ) : (
            <>接口不存在</>
          )
        }
      </Card>
      <div>
        <Divider />
      </div>
      <Card>
        <Form
          name="invoke"
          onFinish={onFinish}
          layout={"vertical"}

          // onFinishFailed={onFinishFailed}
        >
          <Form.Item
            label="请求参数"
            name="userRequestParams"
            rules={[{ required: false, message: data?.description }]}
          >
            <TextArea />
          </Form.Item>


          <Form.Item wrapperCol={{ span: 16 }}>
            <Button type="primary" htmlType="submit">
              调用
            </Button>
          </Form.Item>
          <Card loading={invokeLoading} title={"测试结果"}>
            {invokeRes}
          </Card>
        </Form>
      </Card>

    </PageContainer>
  );
};

export default Index;


