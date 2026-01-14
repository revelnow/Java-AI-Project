# 📅 2026.01.13：Web02 — JavaScript + Vue + Axios（从语法到异步员工列表）

## 🎯 今日目标
- [x] **JS 基础**：掌握脚本引入、基础语法、数据类型、函数、对象
- [x] **DOM & 事件**：掌握 DOM 操作与事件监听
- [x] **Axios 入门**：掌握 GET/POST 请求与别名写法
- [x] **Vue 入门（CDN 版）**：掌握 createApp → data → methods → mount 的最小链路
- [x] **综合案例**：Tlias 员工列表（表单 v-model + 列表 v-for + 条件渲染 v-if + 异步查询）

---

## 💡 核心工程洞察 (Technical Insight)

### 1️⃣ 为什么前端“异步请求”必须配合回调/Promise/async-await？
- **本质**：网络请求是异步的，数据不会立刻到达。
- **结果**：如果你按同步思维写代码，就会出现“数据还没回来就渲染/打印”的错觉。
- **你今天的升级点**：从 `then/catch`（Promise 链）升级到 `async/await`（更接近同步阅读体验）。:contentReference[oaicite:0]{index=0}

### 2️⃣ 为什么 Vue 能“数据变了页面就变”？
- **本质**：Vue 把 data 变成响应式数据，模板里写的是“数据映射”。
- **你今天用到的关键点**：
    - `v-model`：表单输入 ↔ 数据双向绑定
    - `v-for`：列表渲染
    - `v-if / v-else-if`：条件渲染（职位映射）
    - `mounted()`：页面加载完自动拉取初始数据:contentReference[oaicite:1]{index=1}

---

## 🛠️ 关键 Demo 与你完成的能力

### ✅ Axios 入门：GET/POST 基础写法
- 你实现了按钮点击触发请求：
    - GET：查询员工列表
    - POST：更新（请求体 `id=1`）:contentReference[oaicite:2]{index=2}

### ✅ Axios 请求别名：get/post 更简洁
- 你使用了 `axios.get(...)` 和 `axios.post(...)` 的别名写法，减少模板代码:contentReference[oaicite:3]{index=3}

### ✅ Vue 案例（异步交互版）：Tlias 员工列表
你完成了一个“真实 Web 开发最小闭环”：
- 表单区域：`v-model` 收集查询条件（name/gender/job）
- 查询按钮：`@click="search"` 触发异步请求
- 清空按钮：`clear()` 重置条件并重新查询
- 表格区域：`v-for` 渲染员工列表
- 职位显示：`v-if / v-else-if` 映射职位文本
- 初始加载：`mounted()` 自动加载数据:contentReference[oaicite:4]{index=4}

---

## 🛡️ Web02 审计 (Industrial Audit)

- **node_modules 永远不提交**：依赖由 `npm install` 生成，提交会污染仓库且体积爆炸。
- **请求失败必须可观测**：Axios 至少要有 `.catch(err => console.log(err))` 或 try/catch，保证问题可定位。:contentReference[oaicite:5]{index=5}
- **DOM 选择要“缩小范围”**：不要用过宽选择器（例如直接选所有 `tr`），优先限定到 `tbody tr`，避免表头也被影响。（这是典型边界条件意识）
- **类型一致性**：`job/gender` 建议统一数字或统一字符串；避免依赖 `==` 的隐式类型转换（后续维护容易踩坑）。:contentReference[oaicite:6]{index=6}
- **模板与样式分离（可选升级）**：案例里用了 `<style>` 内联样式，后续可以抽成外链 CSS，提高复用性。:contentReference[oaicite:7]{index=7}

---



---

## 🎙️ 面试高频关注点 (Interview Questions)

| 考点方向 | 核心回答点 (Key Points) |
|---|---|
| 为什么网络请求是异步的 | IO 不可控，JS 不能阻塞主线程；用 Promise/async-await 承接结果。:contentReference[oaicite:13]{index=13} |
| axios.get/post 与 axios({}) 的区别 | `axios({url, method, data})` 更通用；`get/post` 是常用别名更简洁。 |
| Vue 的最小启动链路 | `createApp({ data, methods, mounted }).mount('#id')`。:contentReference[oaicite:15]{index=15} |
| v-model / v-for / v-if 各解决什么 | v-model：表单数据绑定；v-for：列表渲染；v-if：条件渲染与分支展示。:contentReference[oaicite:16]{index=16} |
| mounted 的意义 | 页面渲染完成后执行初始化逻辑（如首屏拉数据）。:contentReference[oaicite:17]{index=17} |

---

## 🧠 防遗忘附录 (Post-mortem)

### 一开始我哪里想错了？
- **异步误区**：以为请求发出后数据立刻可用，结果打印顺序/渲染顺序不符合预期。  
  ✅ 纠正：用 `then/catch` 或 `async/await + try/catch` 等到结果返回再更新数据。

- **绑定误区**：想用 DOM 手动取值再拼 URL，代码又长又容易错。  
  ✅ 纠正：用 Vue 的 `v-model` 收集条件，methods 里统一发请求。:contentReference[oaicite:19]{index=19}

### 我最后记住的 3 句话
1. **异步不是“慢”，是“先走后等”**：要用 Promise/await 把结果接回来。
2. **Vue = 数据驱动 UI**：改 `empList`，表格就自动刷新。
3. **请求可观测**：成功打印数据，失败打印错误，调试才有抓手。:contentReference[oaicite:20]{index=20}

---

