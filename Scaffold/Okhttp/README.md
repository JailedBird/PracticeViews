# Okhttp & Retrofit & Network
## Handler 

- 一文搞定面试 | Handler源码解析 https://juejin.cn/post/7220778786126577720
围绕Handler Looper Message MessageQueue 展开
- Handler同步屏障 https://juejin.cn/post/6971981915934949390
讲述 垂直同步时候 插入同步屏障 优先执行异步消息 
普通消息 存在target对象=自己的handler 异步字段false 同步屏障不存在handler 因为不需要回调 需要调用API插入 另外需要根据token删除 View绘制的
会插入异步消息 runnable执行前立即通过token取消同步屏障 避免同步消息也无法执行
## Okhttp

### 拦截器和整体流程

复习文章 https://juejin.cn/post/7311632859085357056

| 拦截器                          | 介绍                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| **client.interceptors**         | 客户端自定义拦截器                                           |
| **RetryAndFollowUpInterceptor** | 处理请求的重试和重定向的拦截器，在请求失败时负责决定是否进行重试，并在重定向时更新请求的 URL |
| **BridgeInterceptor**           | 主要用于桥接连接，将用户的请求转换为网络请求，以及将网络响应转换为用户响应，处理一些请求头的添加，以及响应头的处理 |
| **CacheInterceptor**            | 缓存拦截器，用于处理缓存相关的逻辑，在发起请求前，检查是否有缓存可用，如果有则返回缓存的响应；在获得网络响应后，将响应缓存起来 |
| **ConnectInterceptor**          | 处理与服务器建立连接的拦截器，负责发起 TCP 连接、进行 TLS 握手等工作 |
| **CallServerInterceptor**       | 处理与服务器进行通信的拦截器，发送请求到服务器，并获取服务器的响应，然后将响应传递给上层的拦截器 |



```
@Throws(IOException::class)
internal fun getResponseWithInterceptorChain(): Response {
  // Build a full stack of interceptors.
  val interceptors = mutableListOf<Interceptor>()
  interceptors += client.interceptors
  interceptors += RetryAndFollowUpInterceptor(client)
  interceptors += BridgeInterceptor(client.cookieJar)
  interceptors += CacheInterceptor(client.cache)
  interceptors += ConnectInterceptor
  if (!forWebSocket) {
    interceptors += client.networkInterceptors
  }
  interceptors += CallServerInterceptor(forWebSocket)

  val chain = RealInterceptorChain(
      call = this,
      interceptors = interceptors,
      index = 0,
      exchange = null,
      request = originalRequest,
      connectTimeoutMillis = client.connectTimeoutMillis,
      readTimeoutMillis = client.readTimeoutMillis,
      writeTimeoutMillis = client.writeTimeoutMillis
  )
```

