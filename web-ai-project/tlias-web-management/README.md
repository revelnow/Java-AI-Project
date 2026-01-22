# web-ai-project / tlias-web-management（部门管理）

> 目标：用 **Spring Boot + MyBatis + MySQL** 完成一个“部门管理”的最小闭环：  
> **Controller（接口）→ Service（业务）→ Mapper（SQL）→ MySQL（dept 表）**  
> 并能通过接口 `/depts` 完成增删改查。

---

## 💡 核心工程洞察 (Technical Insight)

### 1️⃣ 分层不是形式，是“责任隔离”

- Controller：只做“接参 + 调 service + 返回 Result”
- Service：做业务规则（比如补 createTime/updateTime）
- Mapper：只关心 SQL（增删改查）
- 数据库：保证约束（主键、自增、unique）

### 2️⃣ 驼峰映射解决字段名差异

数据库字段：`create_time / update_time`  
Java 字段：`createTime / updateTime`

已在 `application.yml` 开启：

```yaml
mybatis:
  configuration:
    map-underscore-to-camel-case: true
```

### 3️⃣ 接口返回统一包装 Result，前端/调用方才好处理

返回不直接丢实体列表，而是 `Result.success(data)` / `Result.error(msg)`；这样前端只需要判断 `code / msg / data` 即可。

---

## 🛠️ 关键代码与结论（我今天最重要的产出）

### A) 后端接口：GET /depts 返回部门列表

- 请求：`GET /depts`
- 返回：`Result.success(List<Dept>)`

### B) 业务：新增/修改时补 createTime/updateTime

- 新增：`createTime` 和 `updateTime` 一般都写“当前时间”
- 修改：更新 `updateTime`

工程语义：时间戳是业务字段，尽量在 Service 层统一补齐，避免 Controller/Mapper 到处写。

### C) MyBatis：用 Mapper 承载 SQL

- 查询：`select id, name, create_time, update_time from dept`
- 新增：`insert into dept (...) values (...)`
- 修改：`update dept set ... where id = ?`
- 删除：`delete from dept where id = ?`

---

## ▶️ How to Run（运行方式）

### 1) 准备数据库（推荐脚本回放）

把下面脚本放到模块中执行（建议路径）：

- `src/main/resources/sql/01-ddl.sql`
- `src/main/resources/sql/02-dml.sql`

执行顺序：`01 → 02`

**01-ddl.sql（建库/建表）**

```sql
CREATE DATABASE IF NOT EXISTS tlias
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_0900_ai_ci;

USE tlias;

DROP TABLE IF EXISTS dept;

CREATE TABLE dept (
  id int unsigned PRIMARY KEY AUTO_INCREMENT COMMENT 'ID, 主键',
  name varchar(10) NOT NULL UNIQUE COMMENT '部门名称',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '修改时间'
) COMMENT '部门表';
```

**02-dml.sql（初始化数据）**

```sql
USE tlias;

INSERT INTO dept (id, name, create_time, update_time) VALUES
(1,'学工部','2024-09-25 09:47:40','2024-09-25 09:47:40'),
(2,'教研部','2024-09-25 09:47:40','2024-09-09 15:17:04'),
(3,'咨询部','2024-09-25 09:47:40','2024-09-30 21:26:24'),
(4,'就业部','2024-09-25 09:47:40','2024-09-25 09:47:40'),
(5,'人事部','2024-09-25 09:47:40','2024-09-25 09:47:40'),
(6,'行政部','2024-11-30 20:56:37','2024-09-30 20:56:37');
```

### 2) 配置数据库连接（application.yml）

当前配置（示例）：

```yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tlias
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: root
    password: 123456

mybatis:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    map-underscore-to-camel-case: true
```

可选增强：URL 增加时区/编码参数，避免乱码与时区问题。

### 3) 启动项目

在仓库根目录执行（按你的多模块结构）：

```powershell
mvn -pl web-ai-project/tlias-web-management -am spring-boot:run
```

或者在 IDEA 中直接运行 `TliasWebManagementApplication`。

### 4) 验证接口

查询列表：浏览器访问

- http://localhost:8080/depts

预期：返回 `Result`，其中 `data` 是部门列表。

若控制台看到 MyBatis 输出 SQL，说明链路已跑通。

---

## ✅ Expected（预期结果）

- `/depts` 返回结构稳定（`code/msg/data`，以你的 Result 定义为准）
- `data` 内每条包含：`id / name / createTime / updateTime`
- 执行了 `02-dml.sql` 后，至少能看到 6 条部门数据
- 字段映射正确：DB `create_time` 能映射到 Java `createTime`（驼峰映射开启）

---

## 🛡️ 工业级审计 (Industrial Audit)

### 1) 数据库账号安全（root 风险）

你现在用 root 连接能跑，但工程上不建议。

建议：

- 创建业务账号（最小权限）
- 只授权 tlias 库（而不是全库）

### 2) 高危 SQL：DELETE / UPDATE 必须带 WHERE

这是生产事故高发点。

建议（自检习惯）：

- 先写 `SELECT ... WHERE ...` 确认影响范围
- 再执行 `UPDATE/DELETE`

### 3) 入参校验缺失（容易写入脏数据）

例如：新增时 name 为空、删除时 id 为空等。

建议（你还没学 Validation 也能做）：

- Service 层做 null/blank 判断，返回 `Result.error("...")`
- 后面再升级到 `@Validated + @NotBlank/@NotNull`

### 4) Mapper 返回值建议用 int（更可控）

增删改如果返回 void，无法判断是否“真的影响了数据”。

建议：

- insert/update/delete 返回 `int rows`
- `rows == 0` → 业务上可返回 `Result.error("未找到记录/更新失败")`

### 5) 控制台 SQL 输出属于学习期方案（后面换日志体系）

你现在用 MyBatis 的 StdOutImpl 打印 SQL，学习阶段 OK。

建议：

- 后面学习日志（logback/slf4j）后再替换
- 避免线上输出敏感信息

---

## 🎙️ 面试高频关注点 (Interview Questions)

| 考点方向 | 核心回答点 (Key Points) |
|---|---|
| 为什么要分层（Controller/Service/Mapper）？ | 责任隔离：接口、业务、数据访问分开，便于维护/测试/扩展 |
| MyBatis 的 Mapper 做什么？ | Mapper 负责 SQL 映射，把 Java 方法和 SQL 绑定 |
| 驼峰映射是什么？ | DB 下划线字段自动映射到 Java 驼峰字段（create_time → createTime） |
| Result 包装的意义？ | 返回格式统一，前端/调用方处理更稳定 |
| 为什么不建议用 root 连接？ | 权限过大，误操作风险高；工程应最小权限 |
| DELETE/UPDATE 为什么必须 WHERE？ | 不带 WHERE 可能全表改/删，属于事故级风险 |

---

## 🧠 防遗忘附录 (Post-mortem)

### 一开始我哪里想错了？

- 误区1：以为能跑就算会了。实际上还要能解释：分层职责、字段映射、Result 语义。
- 误区2：忽略“驼峰映射”。DB 字段和 Java 字段不一致时，必须靠配置或别名，否则会出现字段为 null。
- 误区3：用 root 不觉得危险。工程上 root 误操作后果极大，应养成最小权限账号习惯。

### 我最后记住的 3 句话

- Controller 只做转发，业务放 Service，SQL 放 Mapper。
- 下划线字段靠驼峰映射（或 SQL 起别名）保证字段对齐。
- UPDATE/DELETE 不带 WHERE = 事故。
