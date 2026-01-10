# 📅 2026.01.10：网络编程基础（TCP/UDP）与并发文件上传服务

## 🎯 今日目标
- [x] 理解 `InetAddress`：主机名/地址解析
- [x] 跑通 UDP：`Sender → Receiver` 单向消息传递
- [x] 跑通 TCP：`Client → Server` 文件上传 + ACK 回写
- [x] 升级并发：`MultiThreadServer`（每连接一线程）
- [x] 工程化：`PoolServer`（ThreadPoolExecutor + CallerRunsPolicy 背压）
- [ ] **计划对齐加分项**：手写 HTTP POST 报文（为 LLM API 调用打底）

---

## 💡 核心工程洞察 (Technical Insight)

### 1️⃣ 为什么“文件上传服务端”必须上线程池？
- **风险**：每个连接 `new Thread()`，并发一高会导致线程数爆炸，CPU 切换成本上升、内存压力飙升，最终拖垮服务。
- **策略**：用 `ThreadPoolExecutor` 控制 **最大并发** 与 **队列容量**，并通过拒绝策略实现“背压”，让系统在压力下仍可控。

✅ 你现在的 `PoolServer` 就属于“可控并发”的工程版本（比纯 demo 强一档）。

---

### 2️⃣ 为什么客户端必须 `shutdownOutput()`？
- 你当前协议是：**客户端发完文件字节流 → 服务端读到 `-1` 代表结束**。
- 如果客户端不 `shutdownOutput()`，服务端会一直等“更多数据”，读阻塞，导致：
    - 服务端不落盘或不回 ACK
    - 客户端也读不到 ACK

👉 **这就是 TCP 半关闭的意义**：我不关连接，但我声明“我发完了”。

---

### 3️⃣ 为什么路径不能写死成 `D:\` / `E:\`？
- 这会让代码只在你机器能跑，别人 clone 直接挂。
- 工程化做法：**相对路径** + **自动创建目录**：
    - 输入：`data/1.jpg`
    - 输出：`data/uploads/`

---

## 🛠️ 关键代码实现：线程池版 TCP 上传服务（PoolServer）

> **深度审计结论**：上传服务端优先使用线程池版本（而不是每连接 new Thread）。

```java
ThreadPoolExecutor pool = new ThreadPoolExecutor(
    2, 4,
    60, TimeUnit.SECONDS,
    new ArrayBlockingQueue<>(8),
    Executors.defaultThreadFactory(),
    new ThreadPoolExecutor.CallerRunsPolicy()
);
```
## 🛡️ 并发服务端审计 (Industrial Audit)

- **队列必须有界**：避免请求堆积导致内存不可控
- **拒绝策略建议 CallerRunsPolicy**：队列满时让“提交者线程”执行任务，形成背压
- **资源必须 try-with-resources**：socket/流任何一处异常都不会泄漏
- **输出目录自动创建**：避免第一次运行因目录不存在而失败
- **ACK 编码统一 UTF-8**：跨平台不乱码

---

## ✅ How to Run（可复现运行指南）

> 建议 IntelliJ 设置：**Working directory = `$MODULE_WORKING_DIR$`**（模块目录）

### 1) 准备文件
在模块根目录创建（没有就新建）：
- `data/1.jpg`（任意文件都行）

### 2) 启动服务端（线程池）
运行：
- `tcp.PoolServer`

默认：
- 端口：`8899`
- 保存目录：`data/uploads/`

### 3) 启动客户端上传
运行：
- `tcp.Client`

默认：
- 上传 `data/1.jpg` 到 `localhost:8899`

### ✅ Expected（验收标准）
- 客户端打印：`OK: saved to data/uploads/upload_*.bin`
- 模块目录出现：`data/uploads/upload_*.bin`

## ✅ How to Run（HTTP：手写报文）

运行：
- `http.HttpPostRawBuilderDemo`

✅ Expected（验收标准）
- 输出包含 4 部分：请求行 / 请求头 / 空行 / JSON Body
- `Content-Length` 等于 JSON Body 的 **UTF-8 字节长度**
- 换行符为 `\r\n`（HTTP 标准 CRLF）


---

## 🎙️ 面试高频关注点 (Interview Questions)

| 考点方向 | 核心回答点 (Key Points) |
|---|---|
| 为什么服务端要线程池 | 可控并发（最大线程/队列），避免线程爆炸；拒绝策略实现背压。 |
| CallerRunsPolicy 的意义 | 队列满时让提交线程执行任务，降低吞吐但保护系统不崩。 |
| TCP 传输结束信号 | 当前 demo 用 `shutdownOutput()` + 服务端读到 `-1` 判定结束。 |
| UDP 的优缺点 | 无连接、低开销，但不保证到达/顺序/重传，不适合可靠文件传输。 |
| 如何升级协议 | 进阶：先发文件名/长度头，再按长度读 body；支持校验与断点。 |

---

## 🧠 防遗忘附录 (Post-mortem)

### 一开始我哪里想错了？
- **可复现误区**：写死 `D:\` / `E:\` 路径，导致别人无法运行。  
  ✅ 纠正：用相对路径 `data/`，并自动创建 `uploads/` 目录。
- **阻塞误区**：忘记 `shutdownOutput()`，服务端读阻塞导致不回 ACK。  
  ✅ 纠正：客户端发送完必须半关闭输出，服务端才能读到结束信号。

### 我最后记住的 3 句话
1. **并发要可控**：服务端优先用线程池，不要随手 `new Thread()`。
2. **半关闭很关键**：TCP 发完要 `shutdownOutput()`，否则对端可能永远等不到结束。
3. **可复现优先**：路径相对化 + 自动建目录，仓库才是真正“可交付”。

---

## Self-Reflection（自我反思）
- **我今天的提升**：从“能连上 socket”升级到“线程池可控并发 + ACK 可验证 + 可复现运行”。
- **我还欠的一步**：把网络编程对齐到后续 LLM：补一个“手写 HTTP POST 报文”的 demo。
- **明天最小动作**：新增 `HttpPostRawBuilderDemo`：打印完整请求行、Header、JSON Body、Content-Length。  
