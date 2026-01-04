# 📅 2026.01.04：Java 常用 API 与时间日期 API 总结

## 🎯 今日目标
- [x] 熟悉 Java 常用工具类 API，减少基础代码卡顿
- [x] 理解基本类型、包装类与精度问题
- [x] 掌握数组、系统工具类的工程用法
- [x] 掌握 Java 时间与日期的正确使用方式
- [x] 为后续 IO / 工程代码打好 API 基础

---

## 📦 涉及的核心 API

### 常用工具类
- `Math`
- `System`
- `Integer`
- `BigDecimal`
- `Arrays`

### 时间 / 日期 API
- `Date`
- `SimpleDateFormat`
- `LocalDate`
- `LocalTime`
- `LocalDateTime`
- `DateTimeFormatter`
- `ChronoUnit`

---

## 💡 核心工程理解

## 一、常用工具类 API

### 1️⃣ Math：基础计算工具
- 提供常见数学计算方法：
    - `abs`
    - `max / min`
    - `pow`
    - `round`
- 工程中常用于：
    - 边界处理
    - 数值兜底
    - 简单计算逻辑

---

### 2️⃣ Integer：基本类型与对象的桥梁
- `int`：基本类型
- `Integer`：包装类
- 自动装箱 / 拆箱本质是方法调用

⚠️ 工程注意点：
- 包装类可能为 `null`
- 比较时优先使用 `equals`
- 避免混用导致空指针异常

---

### 3️⃣ BigDecimal：精度问题的唯一正解
- `double` 不适合精确计算
- `BigDecimal` 适合：
    - 金额
    - 比例
    - 精度敏感业务

工程原则：
- ❌ `new BigDecimal(double)`
- ✅ `new BigDecimal(String)`

---

### 4️⃣ Arrays：数组工具类
- 常用方法：
    - `toString`
    - `sort`
    - `binarySearch`
- 工程中常与 Stream 结合使用
- 数组不是不能用，而是要“用对”

---

### 5️⃣ System：程序级工具箱
- 常用方法：
    - `currentTimeMillis`
    - `arraycopy`
    - `exit`
- 常见场景：
    - 性能测试
    - 程序控制
    - 底层工具方法

---

## 二、时间与日期 API

### 6️⃣ Date：旧时间 API（了解即可）
- Java 早期时间类
- 问题：
    - API 设计混乱
    - 可读性差
- 新代码中 **不推荐作为主力使用**

---

### 7️⃣ SimpleDateFormat：旧格式化方案
- 用于时间与字符串转换
- ⚠️ **线程不安全**
- 在并发场景中需要格外小心

---

### 8️⃣ LocalDate / LocalTime / LocalDateTime（主力方案）
- Java 8 引入的新时间 API
- 特点：
    - 语义清晰
    - 不可变对象（线程安全）

分工明确：
- `LocalDate`：日期
- `LocalTime`：时间
- `LocalDateTime`：日期 + 时间

👉 工程中首选方案

---

### 9️⃣ DateTimeFormatter：安全的格式化工具
- 用于新时间 API 的格式化
- 线程安全
- 推荐配合 `LocalDateTime` 使用

---

### 🔟 ChronoUnit：时间计算工具
- 用于计算时间差
- 常见单位：
    - `DAYS`
    - `HOURS`
    - `MINUTES`

工程场景：
- 超时判断
- 过期计算
- 时间区间统计

---

## 🛠️ 关键代码锚点

### 常用 API
- Math 示例：`MathDemo.java`
- System 示例：`SystemDemo.java`
- Integer 示例：`IntegerDemo1.java`、`IntegerDemo2.java`
- 包装类测试：`IntegerTest.java`
- 精度计算：`BigdecimalDemo1.java`
- 数组工具：`ArraysDemo1.java`

### 时间 API
- Date 示例：`DateDemo.java`
- 旧格式化：`SimpleDateFormatDemo.java`
- LocalDate：`LocalDateDemo.java`
- LocalTime：`LocalTimeDemo.java`
- LocalDateTime：`LocalDateTimeDemo.java`
- 新格式化：`DateTimeFormatterDemo.java`
- 时间差计算：`ChronoUnitDemo.java`

---

## 🎙️ 面试高频关注点
- 为什么不推荐 `Date`？
- `SimpleDateFormat` 为什么线程不安全？
- 包装类和基本类型的区别？
- `BigDecimal` 为什么不能用 double 构造？
- 如何计算两个时间点的差值？

---

## 🧠 防遗忘附录（Common API）

### 我当时在干什么？
- 学 Java 常用工具类和时间 API
- 写 Demo 验证 API 行为
- 为 IO 和工程代码提前扫清基础障碍

---

### 一开始我哪里容易想错？
- 以为 double 足够精确
- 忽略包装类可能为 null
- 把 Date 当成主力时间方案

---

### 我最后记住的 5 句话
- 金额计算只用 BigDecimal
- 包装类比较优先用 equals
- API 是为了减少重复劳动
- Java 8 之后时间优先用 LocalXXX
- 时间差计算交给 ChronoUnit

---

### 如果以后全忘了
👉 记住一句话：  
**“基础 API 用对，工程代码就不会乱。”**
