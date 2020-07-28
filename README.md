# five_in_row
The first project of mine using Java
PVP五子棋

### 说明
1. 打开顺序 (在newconn包下)Server-->Client(先手)-->ClientW(后手)
2. 在界面上点击按钮之后, 需要在控制台输入y/Y确认每一步(主要是为了线程阻塞)
3. 代码质量和设计模式堪忧是必然的,毕竟90%的代码都出自一个从helloworld至今只有1.5个月的Java小白
4. 后期有时间再进行改进, 最后赶的时候把所有成员变量都public, 以及无数的throws语句都体现了我的暴躁
### 分工
1. 选题: 不是我
2. 思路: 两位学长
3. 实现层面: 除了一开始同学帮我定位了按钮在gui界面上的位置, 以及判定胜负的API是网上找的外.

### 用到的知识
1. Swing
2. AcctionListener
3. Socket通信
4. IO 
5. Serializable
6. 多线程

### 小技巧
1. 在IO流传递对象
2. 继承JButton 添加属性 从而获取坐标等信息
3. 在Server中添加Map通过clinetID映射指定的输出流, 实现对某一个client发消息
 
