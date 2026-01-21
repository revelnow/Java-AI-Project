# 📅 2026.01.20：web06-mybatis — 后端 Web 基础（Java 操作数据库 / MyBatis）

> 本模块目标：用 MyBatis 把“Java ↔ SQL ↔ MySQL”跑通，形成可复现链路：
> **Test → Mapper(接口代理) → Mapper.xml(SQL) → DB → User 映射返回**

---

## 💡 核心工程洞察 (Technical Insight)

### 1️⃣ MyBatis 把“执行 SQL”抽象成“调用 Java 方法”

- 你写的是 `UserMapper` 接口（没有实现类）
- 运行时 MyBatis 生成 **Mapper 代理对象**（实现接口）
- 调用 `userMapper.xxx()` → 代理去 XML 找 SQL → 执行 → 映射成 `User`

一句话口播：

> **接口是门面，XML 是 SQL，代理负责把方法调用翻译成数据库操作。**

---

### 2️⃣ XML 和接口如何对上（最核心规则）

- `UserMapper.xml` 的 `namespace` 必须等于接口全限定名（如 `com.theo.mapper.UserMapper`）
- `<select id="findAll">` 的 `id` 必须等于接口方法名 `findAll`

口诀：

> **namespace = 接口全名，id = 方法名**

---

### 3️⃣ `#{}` 的工程语义：预编译参数，占位符（更安全）

- `#{username}` / `#{password}` 不是字符串拼接
- MyBatis 会做参数绑定（预编译），降低注入风险


---

## ▶️ How to Run（运行方式）

### 1) 数据库准备

确保你 MySQL 可登录，并且目标库/表存在（以你配置为准）：

```sql
SHOW DATABASES;
USE web01;
SHOW TABLES;
```



### 2) 配置数据源（application.properties）

最小要求：`url / username / password` 正确。

推荐稳定写法（避免时区/编码问题）：

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false
spring.datasource.username=java_ai
spring.datasource.password=123456

mybatis.mapper-locations=classpath*:mapper/**/*.xml
mybatis.type-aliases-package=com.theo.pojo
```

> 工程建议：不要用 `root` 连接项目；练习可以，作品集用 `java_ai` 这种业务账号（最小权限）。

### 3) 运行测试（推荐）

在项目根目录：

```bash
mvn -pl web06-mybatis -am test
```

或在 IDEA 里直接跑 `Web06MybatisApplicationTests`。

### ✅ Expected（预期结果）

- `findAll()` 能查出用户列表（`size > 0`）
- `getById(x)` 能查到某条用户或返回 `null`（你要能解释原因）
- `insert(user)` 能插入一条数据（可选：回填 id）

---

## 🛡️ 工业级审计 (Industrial Audit)

### 1) 多参数绑定风险（最常见“运行时报错点”）

如果接口是：

```java
List<User> getByUsernameAndPassword(String username, String password);
```

而 XML 写：

```sql
WHERE username = #{username} AND password = #{password}
```

⚠️ 可能报：`Parameter 'username' not found`

原因：编译后参数名可能变成 `arg0/arg1`（拿不到 `username/password`）。

✅ 最稳修法（推荐你立刻改）：

```java
List<User> getByUsernameAndPassword(
  @Param("username") String username,
  @Param("password") String password
);
```

### 2) XML 扫描路径必须和文件位置一致

你配置：

```properties
mybatis.mapper-locations=classpath*:mapper/**/*.xml
```

✅ 结论：XML 必须放在 `resources/mapper/` 下（你现在这样放是对的）。

### 3) 数据源安全语义：不要用 root 连接项目

✅ 建议：

- 练习：`root` 可以
- 项目/作品集：用 `java_ai` 账号，只给业务库权限

### 4) 表名 user 的潜在冲突

`user` 容易和系统语义混淆。

✅ 建议：

- 表改名 `users`
- 或 SQL 使用反引号：``from `user```。

### 5) Insert 后 id 不回填（容易让你“感觉不对”）

若表 id 自增，插入后 Java 对象 id 可能仍为 `null`。

✅ 可选增强：

```xml
<insert id="insert" parameterType="com.theo.pojo.User"
        useGeneratedKeys="true" keyProperty="id">
```

### 6) 测试工程语义：println 不算验证

至少加 2 个断言：

- `findAll()`：断言 `size > 0`
- `getById(存在的id)`：断言非空或字段符合预期

写操作测试建议加：

- `@Transactional + @Rollback`（避免污染数据库）

---

## 🎙️ 面试高频关注点 (Interview Questions)

| 考点方向 | 核心回答点 (Key Points) |
| --- | --- |
| MyBatis 怎么让接口“自动有实现”？ | 运行时生成 Mapper 代理对象，方法调用 → 执行 XML SQL |
| namespace / id 是什么？ | namespace = 接口全限定名；id = 接口方法名 |
| `#{}` 和 `${}` 区别？ | `#{}` 预编译参数更安全；`${}` 字符串拼接有注入风险 |
| 为什么多参数要 `@Param`？ | 避免参数名丢失，保证 XML 能按名字取到值 |
| mapper-locations 的作用？ | 决定 MyBatis 去哪里找 XML |
| 为什么不用 root 连接？ | 权限最小化，减少误操作与泄露风险 |

---

## 🧠 防遗忘附录 (Post-mortem)

### 一开始我哪里想错了？

- 误区 1：以为写了接口就能直接执行
  - 实际上：需要 **代理 + XML 映射** 才能把方法变成 SQL。
- 误区 2：以为 `#{username}` 一定能拿到参数
  - 实际上：多参数/未保留参数名时，必须 `@Param`。
- 误区 3：测试绿了就等于对
  - 实际上：必须用断言验证行为；`println` 不算测试。

### 我最后记住的 3 句话

1. `namespace = 接口全名，id = 方法名`。
2. 多参数必加 `@Param`，否则 XML 可能取不到。
3. `#{}` 是预编译参数，别用字符串拼 SQL。
