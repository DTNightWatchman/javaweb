export default [
  { path: '/', name: '欢迎', icon: 'smile', component: './Index' },
  { path: '/interface_info/:id', name: '查看接口', hideInMenu: true ,icon: 'smile', component: './InterfaceInfo'},

  {
    path: '/user',
    layout: false,
    routes: [{ name: '登录', path: '/user/login', component: './User/Login' },],
  },
  { path: '/center', name: '个人中心' ,icon: 'smile', component: './UserCenter'},
  {
    path: '/admin',
    name: '管理页',
    icon: 'crown',
    access: 'canAdmin',
    routes: [
      { name: '查询接口', icon: 'table', path: '/admin/interface_info', component: './Admin/InterfaceInfo' },
    ],
  },

  // { path: '/', redirect: '/' },
  { path: '*', layout: false, component: './404' },
];
