import { PageContainer } from '@ant-design/pro-components';
import {List, message} from 'antd';
import React, {useEffect, useState} from 'react';
import {
  listInterfaceByPageUsingGET,
} from "@/services/api-backend/interfaceInfoController";

const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [list, setList] = useState<API.InterfaceInfo[]>([]);
  const [total, setTotal] = useState<number>(0);

  const loadDate = async (current = 1, pageSize = 10) => {
    try {
      const res = await listInterfaceByPageUsingGET({
        current, pageSize
      });
      setList(res?.data?.records ?? []);
      setTotal(res?.data?.total ?? 0);
    } catch (error: any) {
      message.error('加载数据失败:' + error.message);
    }
    setLoading(false);

  }
  useEffect(() => {
    loadDate();
  }, [])

  // @ts-ignore
  return (
    <PageContainer title={"在线接口平台"}>
      <List
        className="demo-loadmore-list"
        loading={loading}
        itemLayout="horizontal"
        dataSource={list}
        renderItem={(item) => (
          <List.Item
            actions={[<a key={item.id} href={`/interface_info/${item.id}`}>查看</a>]}
          >
              <List.Item.Meta
                title={<a href={`/interface_info/${item.id}`}>{item.name}</a>}
                description={item.description}
              />
          </List.Item>
        )}


        pagination = {
          {
            pageSize : 10,
            total: total,
            onChange(page, pageSize) {
              loadDate(page, pageSize);
            },
            showTotal(totalList: number) {
              return '总数：' + totalList;
            },
          }
        }
      />
    </PageContainer>
  );
};

export default Index;
