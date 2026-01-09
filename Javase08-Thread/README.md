# 📅 2026.01.08：多线程并发与 AI 算力调度基础

## 🎯 今日目标
- [x] 掌握开启线程的 3 种方式（重点理解 `Callable` 获取 AI 结果的特性）
- [x] 深入理解 `synchronized` 与 `Lock` 的同步机制
- [x] **深度审计**：手动构建 `ThreadPoolExecutor` 规避 OOM 风险
- [x] 理解守护线程（Daemon）在 AI 任务监控中的应用

---

## 💡 核心工程洞察 (Technical Insight)

### 1️⃣ 为何 AI 场景严禁显式 `new Thread()`？
AI 推理任务（如大模型生成、向量化埋点）通常极度消耗 CPU 和内存资源。
* **风险**：如果每个请求都 `new Thread()`，在流量洪峰下会导致系统资源瞬间耗尽而崩溃。
* **策略**：必须使用**线程池**作为“流量堤坝”。通过限制核心线程数和任务队列，实现请求的平滑处理，保护后端推理服务器。

### 2️⃣ `Callable` 与非阻塞 AI 交互
* **局限**：`Runnable` 无返回值，无法直接获取 AI 接口生成的文本。
* **实践**：使用 `Callable` 配合 `FutureTask`。主线程在调用 `.get()` 阻塞等待结果前，可以继续执行其他 UI 渲染或逻辑，从而实现“伪异步”体验。

---

## 🛠️ 关键代码实现：工业级线程池配置

> **深度审计结论**：严禁使用 `Executors.newFixedThreadPool()`。其默认无界队列（容量为 21 亿）在 AI 高并发下会导致 **OOM (内存溢出)**。



```java
// 结合 ThreadPoolDemo2.java 的重构实现
ThreadPoolExecutor agentPool = new ThreadPoolExecutor(
    2,                          // 1. 核心线程：常驻 AI 处理工位
    5,                          // 2. 最大线程：应对突发流量的临时工
    10, TimeUnit.SECONDS,        // 3. 存活时间：临时工下班倒计时
    new ArrayBlockingQueue<>(100), // 4. 任务队列：强制指定容量，防止内存堆积
    Executors.defaultThreadFactory(), // 5. 线程工厂
    new ThreadPoolExecutor.CallerRunsPolicy() // 6. 拒绝策略：背压机制，让调用者自己运行
);
```
## 性能公式

**最大承载能力：**  
$最大承载能力 = 最大线程数 (maximumPoolSize) + 队列容量 (queueCapacity)$

### 🛡️ 工业级线程池审计 (Industrial Audit)

- **核心公式**：$最大承载量 = MaximumPoolSize + QueueCapacity$。
- **背压策略**：使用 `CallerRunsPolicy`。当 AI 算力达到极限时，通过阻塞调用方来保护系统稳定，防止内存溢出（OOM）。
- **线程命名**：通过 `ThreadFactory` 为线程命名，这是在分布式日志中定位“哪次 AI 推理卡住了”的唯一凭证。
- **优雅停机**：使用 `shutdown()` + `awaitTermination()`。严禁直接关闭 JVM，否则会导致正在生成的 AI 文本丢失或数据库连接断开。

---

## 🎙️ 面试高频关注点 (Interview Questions)

| 考点方向 | 核心回答点 (Key Points) |
|---|---|
| 线程池 7 大参数 | 核心/最大线程、存活时间/单位、任务队列、工厂、拒绝策略。 |
| 为何禁止 Executors | 默认队列长度为 $2^{31}-1$，高并发堆积请求会导致 OOM 崩溃。 |
| sync vs Lock | `synchronized` 是关键字（隐式锁）；`Lock` 是接口（显示锁），支持尝试获取锁等高级操作。 |
| 守护线程 (Daemon) | 当所有用户线程结束，守护线程会自动停止。常用于 AI 的心跳检测任务。 |

---

## 🧠 防遗忘附录 (Post-mortem)

### 一开始我哪里想错了？
- **优先级误区：**以为 `setPriority(10)` 就能强制让 AI 线程先跑完。  
  实际上它只是一个“建议”，CPU 调度仍有随机性。
- **资源清理漏洞：**在 `ThreadDemo1.java` 中使用了已废弃的 `finalize()`。  
  **纠正：**`finalize()` 执行时机不可控。在 AI 工程中应改用 `AutoCloseable` 或 `Cleaner`。

### 我最后记住的 3 句话
1. **接口胜过继承：**开启线程优先选 `Runnable/Callable`，保留单继承的机会。
2. **锁对象要唯一：**涉及共享资源写操作，锁对象必须全局唯一（通常用 `.class`）。
3. **参数定生死：**线程池必须手动配，队列容量绝对不能设为无限大。
****