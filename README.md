

## 常用方法

### Map

```java
Map<String, Integer> counts = new HashMap();
// 如果不存在，则返回0
counts.getOrDefault(cur, 0);
```

### String

```java
String domain = "900 google.mail.com";
// 分隔符 切分
String[] cpinfo = domain.split("\\s+");
// 特殊符号远点 切分
String[] frags = cpinfo[1].split("\\.");

String s = "aaabb";
int k = 3;
StringBuilder sb = new StringBuilder(s);
// 遍历字符串的每一个字符，并在遍历的时候操作
for(int i=0; i<s.length(); i++) {
    char c = s.charAt();
    sb.delete(i - k + 1, i + 1);
    i = i - k;
}
```

### Array

```java
// 初始化 数组
int[] keyValue = {1, 2};

int[] people;
// 排序 默认数组元素 进行升序排序
Arrays.sort(people);

```

### List

```java
List<int[]> list = new ArrayList<>();
// 根据集合元素 进行 升序排序 （1,1,2,3）
Collections.sort(list, Comparator.comparingInt(item -> item[1]));
```

### Set

```java
Set<String> set = new HashSet();
// 取集合第一个元素
set.iterator().next().length()
```

### int

```java
int ret, maxlen;
// 两者取最大的
ret = Math.max(ret, maxlen);
```

### Stack

```java
// 栈 压栈出栈操作
Stack<Integer> counts = new Stack<>();
counts.push(1);
counts.pop();
```

## 其他

```java
// 整形取值，最好用Long，因为Integer会有范围，部分测试用例可能不能通过
Long.MIN_VALUE;
Long.MAX_VALUE;
```

## 常用英文

```java
// 遍历
traverse();
// 行数
int rows;
// 列数
int columns;
// 大小
long max,min;
long upper,lower;

```



## 常见问题

- 题目是给开发工程师看的，对题目文字要敏感，比如包含于 表示 主语一般是主集合
- 遍历的时候，容易弄错left,right
- 递归的时候，容易copy带来低级错误



## 预先练习题型集合

