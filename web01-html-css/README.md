# 📅 2026.01.13：Web 基础（HTML + CSS）Demo 递进实战（央视新闻 + Tlias）

## 🎯 今日目标
- [x] **HTML 基础**：掌握页面骨架、常用标签、相对路径引用
- [x] **CSS 基础**：掌握选择器、文本样式、盒子模型、外链样式
- [x] **布局入门**：掌握容器居中 + flex 导航栏/表单布局


---

## 💡 核心工程洞察 (Technical Insight)

### 1️⃣ 为什么学习阶段也要“外链 CSS”？
- **本质**：重复样式会在多个 HTML 里“拷贝粘贴”，后续修改一次要改四次，学习效率会被消耗。
- **策略**：把通用样式抽到 `css/*.css`，HTML 只负责结构，CSS 负责样式，形成“可维护的最小工程习惯”。

### 2️⃣ 为什么 Live Server 打开会看到目录页？
- **原因**：Live Server 默认把“工作区根目录”当站点根目录，你打开的是根目录就会出现文件夹列表。
- **解决**：
  - 对 **具体 html 文件** 右键 `Open with Live Server`；或
  - VSCode **只打开前端目录**（例如 `Javaweb01-html-css`）再启动 Live Server。

---

## 🛠️ 关键实现内容（本次 Demo 覆盖）

> **递进链条**：从“会写标签” → “会写样式” → “能做出页面结构 + 基本布局”。

- **央视新闻页面链路（03~08）**：标题/时间/正文/资源加载/排版/整体居中布局
- **盒子模型（09）**：margin/padding/border 与 `box-sizing`
- **Tlias 页面链路（10~14）**：navbar（flex）/搜索表单（flex）/表格展示（斑马纹）/页脚（居中）

---

## 🛡️ Web 学习版审计 (Industrial Audit)

- **资源路径优先相对化**：图片/音视频统一走相对路径（`img/` `video/` `audio/`），避免写死盘符导致别人无法复现。
- **避免重复样式爆炸**：同类组件（navbar / table / form）不要在多个 html 里反复写 `<style>`，至少抽 1 个外链 CSS。
- **HTML 语义优先**：尽量用 `header/main/footer` 代替大量 `<br>` 做间距，间距交给 CSS（margin/padding）。
- **Git 干净提交**：`node_modules/` 永远不提交（体积巨大、无意义），只提交源文件（html/css/js/资源）。

---

## 🎙️ 面试高频关注点 (Interview Questions)

| 考点方向 | 核心回答点 (Key Points) |
|---|---|
| 外链 CSS 的意义 | 复用 / 可维护 / 避免重复；HTML 结构与样式分离。 |
| flex 解决了什么 | 快速实现水平分布、垂直居中、间距控制；特别适合导航栏与表单行。 |
| 盒子模型为什么容易踩坑 | `width` 默认只算 content；padding/border 会撑开盒子；`box-sizing: border-box` 更可控。 |
| 相对路径为何重要 | 可移植、可复现；换机器/换目录不崩；适合团队协作与仓库交付。 |
| 为什么不要提交 node_modules | 体积巨大、可由 `npm install` 生成；污染仓库、影响 clone/CI。 |

---

## 🧠 防遗忘附录 (Post-mortem) — HTML/CSS 版

### 一开始我哪里想错了？
- **结构误区**：以为“先写样式更快”，结果 HTML 结构不清晰，后面 CSS 越写越乱。
  ✅ 纠正：先搭结构（标题/段落/列表/图片区/表格区），再用 class 定位样式。

- **路径误区**：图片/音视频引用时写成“依赖当前打开方式”的路径，换目录或 Live Server 根目录不同就加载失败。
  ✅ 纠正：统一用相对路径，并把资源集中放在 `img/ video/ audio/` 下。

- **布局误区**：想用 `<br>` 硬撑间距，页面一复杂就控制不住。
  ✅ 纠正：间距交给 CSS（`margin/padding/line-height`），结构用容器包起来。

- **样式复用误区**：同一个 navbar / table / form 在多个 html 里复制 `<style>`，改一次要改多处。
  ✅ 纠正：抽外链 CSS（如 `css/news.css` / `css/tlias.css`），HTML 只保留结构。

### 我最后记住的 3 句话
1. **先结构后样式**：HTML 负责骨架，CSS 负责皮肤，别混着写。
2. **相对路径 + 资源归类**：img/video/audio 统一目录，换环境也能跑。
3. **少写 `<br>`，多用 margin**：排版用 CSS 控制，页面才可维护。


