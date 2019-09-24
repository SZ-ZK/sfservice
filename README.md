# sfservice

版权声明：本文项目遵循 CC 4.0 BY-SA 版权协议
参考文献（SCDN）：https://blog.csdn.net/Mona__/article/details/79937658


本项目是通过API接入方式，实现订单管理（下订单接口、订单取消接口、路由查询接口、路由推送接口、订单状态推送接口和时效查询接口）。

1. 登录丰桥服务：http://qiao.sf-express.com/index.html
2. 申请成为开发者。获取顾客编号（clientCode）、校验码（checkword）、月结卡号（测试环境）和调用地址（url:https://qiao.sf-express.com/index.html）
3. 当用户完成API接口测试(每个API均需调用成功3次或以上才能申请接入生产)以后,就可以申请接入生产环境了

调用顺丰API接口的步骤：
1. 获取接口报文xml
2. 配置参数
3. 结合xml+checkword
4. MD5加密 + Base64编码
5. post 请求
6. XML转JavaBean
7. 执行业务逻辑操作