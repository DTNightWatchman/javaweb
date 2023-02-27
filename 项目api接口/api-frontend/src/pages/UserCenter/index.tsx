import {
  PageContainer, ProColumns,
} from '@ant-design/pro-components';
import '@umijs/max';
import React, {useEffect, useState} from 'react';
import {
  Avatar,
  Button,
  Card,
  Col,
  Descriptions,
  Divider,
  message,
  Row,
  Space,
  Typography,
  Upload,
  UploadProps,
} from 'antd';
import { AntDesignOutlined, UploadOutlined } from '@ant-design/icons';
// @ts-ignore
import {
  getAkSkUsingGET,
  getCurrentUserUsingGET, updateAkSkUsingGET,
  updateUserUsingPOST
} from "@/services/api-backend/userController";
import UpdateModal from "@/pages/UserCenter/components/UpdateModal";

const { Title } = Typography;

const TableList: React.FC = () => {
  const props: UploadProps = {
    name: 'image',
    action: 'http://localhost:7529/api/user/uploadAvatar',
    withCredentials: true,
    showUploadList: false,
    maxCount: 1,
    onChange(info) {
      if (info.file.status !== 'uploading') {
        console.log(info.file, info.fileList);
      }
      if (info.file.status === 'done') {
        message.success(`${info.file.name} 上传头像成功`);
      } else if (info.file.status === 'error') {
        message.error(`${info.file.name} 上传头像失败`);
      }
    },
  };

  const [akSk, setAkSk] = useState<API.AkSkVO>({accessKey:"********",secretKey:"********"});
  const [updateModalVisible,handleUpdateModalVisible ] = useState<Boolean>(false);
  const [userInfo, setUserInfo] = useState<API.UserVO>({});

  const getUserInfo = async () => {
    try {
      const res = await getCurrentUserUsingGET();
      if (res.data == null) {
        message.error("获取用户信息失败");
        return;
      }
      setUserInfo(res?.data);
    } catch (error: any) {
      message.error("获取用户信息失败");
    }
    return;
  }

  /**
   * 钩子函数，获取用户信息
   */
  useEffect(() => {
    getUserInfo();
  }, [])

  const handleUpdate = async (value: API.UserVO) => {
    try {
      const res = await updateUserUsingPOST({
        ...value
      });
      if (res.data !== true) {
        message.error("更新失败");
      } else {
        handleUpdateModalVisible(false);
        message.success("更新成功");
      }
      return;
    } catch (errry: any) {
      message.error("更新失败");
    }
    return;
  }

  const upDateAkSk = async () => {
    try {
      const res = await updateAkSkUsingGET();
      if (res.data === false) {
        message.error("更新失败");
      } else {
        window.location.reload();
      }
    } catch (error: any) {
      message.error('加载数据失败:' + error.message);
    }
  }
  const loadAkSk = async () => {
    try {
      const res = await getAkSkUsingGET();
      // @ts-ignore
      setAkSk(res.data);
    } catch (error: any) {
      message.error('加载数据失败:' + error.message);
    }
  }
  const columns: ProColumns<API.UserVO>[] = [

    {
      title: '用户名称',
      dataIndex: 'userName',
      valueType: "text",
      formItemProps: {
        rules:[{
          required: true,
          message: "接口名不能为空"
        }
        ]
      }
    },
    {
      title: '用户手机号码',
      dataIndex: 'phone',
      valueType: 'text',
    },
    {
      title:"用户性别",
      dataIndex: "gender",
      valueType: "select",
      request: async () => [
        {
          label: '男',
          value: 0,
        },
        {
          label: '女',
          value: 1,
        },

      ],
    }

  ];

  // @ts-ignore
  return (
    <PageContainer>
      <Space direction="vertical" size="middle" style={{ display: 'flex' }}>
        <Card title={<Title level={5}>{"个人信息"}</Title> } size="small">
          <Row align={"middle"}>
            <Col flex={4.9}>
              <Space split={<Divider type="vertical" />}>
                <Typography.Link>
                  <Avatar
                    size={{ xs: 24, sm: 32, md: 80, lg: 64, xl: 80, xxl: 100 }}
                    src={userInfo.userAvatar}
                    onError={() => {
                      return message.error('头像加载失败');
                    }}

                  />
                </Typography.Link>
                <Typography.Link>
                  <Title level={3}>{userInfo.userName}</Title>
                  {`手机号码：${userInfo.phone}`}
                </Typography.Link>
              </Space>
            </Col>
            {/*<Col flex={3.9}>*/}

            {/*</Col>*/}
            <Col flex={0.1} >
              <Button
                onClick={() => {
                  handleUpdateModalVisible(true)
                }
              }
              >
                修改信息
              </Button>
              <Upload {...props}>
                <Button icon={<UploadOutlined />}>上传头像</Button>
              </Upload>
            </Col>
          </Row>
        </Card>
        <Card title={<Title level={5}>{"接口的accessKey和SecretKey"}</Title>} size="small">

          <div className="space-align-block">
            <Space align="center">

              <span className="mock-block">
                <Descriptions title="accessKey，secretKey" column={1}>
                  <Descriptions.Item label="accessKey">{akSk.accessKey}</Descriptions.Item>
                  <Descriptions.Item label="secretKey">{akSk.secretKey}</Descriptions.Item>
                </Descriptions>
              </span>
              <Space direction="vertical" size="middle" style={{ display: 'flex' }}>
                <Button type="primary" onClick={loadAkSk}>获取accessKey和secretKey</Button>
                <Button danger onClick={upDateAkSk}>修改accessKey和secretKey</Button>
              </Space>
            </Space>
          </div>


        </Card>
      </Space>

      <UpdateModal
        columns={columns}
        onSubmit={(value) => {handleUpdate(value)}}
        onCancel={() => {handleUpdateModalVisible(false)}}
        visible={updateModalVisible}
        values={userInfo}
      />

    </PageContainer>


  );
};
export default TableList;
