# Java-Projects
Java课程的一些小型项目，适合初学者入门。

# Contents

### 1.WordCounts

针对以下的test.txt文件，请统计每个的单词总数：

（每行有多个单词，单词可能有重复，单词之间严格以分号隔开，无需区分大小写，#号行不统计）

请按照次数多少降序输出单词和次数，将输出结果保存到test1.txt中。
```
test.txt 文件示例：
-------------------------------
Question;Position
#Hello;World
Position;Java
-------------------------------
test1.txt 文件示例：
-------------------------------
Position,2
Question,1
Java,1
-------------------------------
```

### 2.WriteWordTemplate
假设存在以下的一个docx模板，里面有$userNo$, $ userName$,  $image1$。

将userNo替换为工号，userName替换为姓名，图片任意，并转为PDF文件。

### 3.ProcessXLS
请读如下形式的xls文档，统计全班同学的数学的平均值，输出数学的人数和平均值。

### 4.OutputJSON
请写一个方法，传入以下的类定义的对象，输出其对应的JSONObject。

Class A{int a; ArrayList<String> b; Date c} ，其中List类型请转化为JSONArray， 时间类型请按照YYYY-MM-DD Hi24:mi:ss输出。

### 5.ReadXML
请读取以下的xml文件，并统计学生总分平均值，结果保留2位小数。（要求采用DOM和SAX两种方法）。

```
<java-student>
  <student><name>张三</name><course>语文</course> 
    <score>80</score></student>
  <student><name>李四</name><course>语文</course> 
    <score>82</score></student>
  <student><name>张三</name><course>数学</course> 
    <score>80</score></student>
</java-student>
```
分析：一级节点是<java-student>，二级节点是<student>，最后一级是各种属性\<name\>、\<course\>、\<score\>。

### 6.GrabData
请将https://faculty.ecnu.edu.cn/ 数据抓取下来，统计机构总数并输出。

### 7.DataBase
请访问mysql数据库，访问t_test表(stuNo, stuName, questionNos). 插入一条自己的考题记录，如(110，’张三’，’1,3,5,7,9’)。

数据库信息: 192.168.1.100，3306端口, test数据库, root/123456。要求使用绑定变量的Statement。

### 8.ForkJoin
给定一个未知的整数数组（请用随机数产生），并打印出来，采用Fork-join进行二分查找最大值，最后输出全局的最大值。

### 9.JUnit
采用JUnit对以下函数进行全分支覆盖测试。
```
public int judge(int a)
{
  if(a<0) 
{  return -1;}
else if (a>0)
{  return 1; }
else
{  return 0;}
}
```

### 10.DataBase2
请按照第七题的数据库信息，读取该表数据，并写入到一个xlsx或xls文件中。


### 11.DataBase3
读取以下文件sixteen.txt，将相同id的单词用逗号连接起来，最后采用按照第七题的数据库信息，以addBatch的方式插入到t_sixteen(id, str)中。
```
sixteen.txt:
1,hello
2,world
1,ecnu
```

### 12.WebCrawler
请对以下100个网址采用Executor框架进行并行爬取内容，并将结果保存到文件中。

每个文件一个网页内容。http://news.ecnu.edu.cn/cf/4c/c1833a118604/page.psp 网页地址从8604开始往下递减100个即可。

