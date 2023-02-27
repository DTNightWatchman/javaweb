import Footer from '@/components/Footer';
import {
  AlipayCircleOutlined,
  LockOutlined,
  MobileOutlined,
  TaobaoCircleOutlined,
  UserOutlined,
  WeiboCircleOutlined,
} from '@ant-design/icons';
import {
  LoginForm,
  ProFormCaptcha,
  ProFormCheckbox,
  ProFormText,
} from '@ant-design/pro-components';
import { history, useModel } from '@umijs/max';
import {Alert, Button, message, Tabs} from 'antd';
import React, { useState } from 'react';
import styles from './index.less';
import {userLoginUsingPOST, userRegisterUsingPOST} from "@/services/api-backend/userController";
const LoginMessage: React.FC<{
  content: string;
}> = ({ content }) => {
  return (
    <Alert
      style={{
        marginBottom: 24,
      }}
      message={content}
      type="error"
      showIcon
    />
  );
};
const Login: React.FC = () => {
  const [userLoginState] = useState<API.LoginResult>({});
  const [type, setType] = useState<string>('account');
  const { setInitialState } = useModel('@@initialState');

  const handleSubmit = async (values: API.UserLoginRequest, values2: API.UserRegisterRequest) => {
    if (type === 'account') {
      try {
        // 登录
        const res = await userLoginUsingPOST({
          ...values,
        });
        if (res.data) {
          const urlParams = new URL(window.location.href).searchParams;
          history.push(urlParams.get('redirect') || '/');
          // @ts-ignore
          setInitialState ({
            loginUser: res.data
          })
          return;
        } else {
          const defaultLoginFailureMessage = '账号或密码错误';
          message.error(defaultLoginFailureMessage);
          return;
        }
      } catch (error) {
        const defaultLoginFailureMessage = '登录失败，请重试！';
        console.log(error);
        message.error(defaultLoginFailureMessage);
      }
    } else {
      try {
        // 登录
        const res = await userRegisterUsingPOST({
          ...values2,
        });
        if (res.data) {
          const urlParams = new URL(window.location.href).searchParams;
          history.push(urlParams.get('redirect') || '/');
          return;
        } else {
          message.error(`注册失败:${res.message}`);
          return;
        }
      } catch (error) {
        const defaultLoginFailureMessage = '注册失败，请重试！';
        console.log(error);
        message.error(defaultLoginFailureMessage);
      }
    }
  };
  const { status, type: loginType } = userLoginState;
  return (

    <div className={styles.container}>
      <div className={styles.content}>
        <LoginForm
          submitter={{
            searchConfig: {
              submitText: type === 'register' ? '注册' : '登录'
            }
          }}

          logo={<img alt="logo" src="https://ytbaiduren-1309783343.cos.ap-nanjing.myqcloud.com/logo%20%282%29.svg" />}
          title="在线api接口平台"
          subTitle={'基于 Ant Design 为前端实现的api开放平台'}
          initialValues={{
            autoLogin: true,
          }}

          onFinish={async (values) => {
            if (type === 'register') {
              await handleSubmit(
                null as unknown as API.UserLoginRequest,
                values as API.UserRegisterRequest,
              );
            } else {
              await handleSubmit(
                values as API.UserLoginRequest,
                null as unknown as API.UserRegisterRequest,
              );

            }
          }}
        >
          <Tabs
            activeKey={type}
            onChange={setType}
            centered
            items={[
              {
                key: 'account',
                label: '账户密码登录',
              },
              {
                key: 'register',
                label: '注册新用户',
              },
            ]}
          />

          {status === 'error' && loginType === 'account' && (
            <LoginMessage content={'错误的用户名和密码(admin/ant.design)'} />
          )}
          {type === 'account' && (
            <>
              <ProFormText
                name="userAccount"
                fieldProps={{
                  size: 'large',
                  prefix: <UserOutlined className={styles.prefixIcon} />,
                }}
                placeholder={'用户名: admin or user'}
                rules={[
                  {
                    required: true,
                    message: '用户名是必填项！',
                  },
                ]}
              />
              <ProFormText.Password
                name="userPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon} />,
                }}
                placeholder={'密码: ant.design'}
                rules={[
                  {
                    required: true,
                    message: '密码是必填项！',
                  },
                ]}
              />
            </>
          )}

          {type === 'register' && (
            <>

              <ProFormText
                fieldProps={{
                  size: 'large',
                  prefix: <MobileOutlined className={styles.prefixIcon} />,
                }}
                name="userAccount"
                placeholder={'请输入你要创建的账号！'}
                rules={[
                  {
                    required: true,
                    message: '新账号是必填项！',
                  },
                ]}
              />
              <ProFormText.Password
                name="userPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon} />,
                }}
                placeholder={'密码: ant.design'}
                rules={[
                  {
                    required: true,
                    message: '密码是必填项！',
                  },
                ]}
              />
              <ProFormText.Password
                name="checkPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon} />,
                }}
                placeholder={'密码: ant.design'}
                rules={[
                  {
                    required: true,
                    message: '密码是必填项！',
                  },
                  {

                  }
                ]}
              />
            </>
          )}
          <div
            style={{
              marginBottom: 24,
            }}
          >
            <ProFormCheckbox noStyle name="autoLogin">
              自动登录
            </ProFormCheckbox>
            <a
              style={{
                float: 'right',
              }}
            >
              忘记密码 ?
            </a>

          </div>
        </LoginForm>
      </div>
      <Footer />
    </div>
  );
};
export default Login;
