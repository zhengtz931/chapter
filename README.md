# chapter
郑天泽小组成员
郑天泽 2017012313
聂立    2017012695  https://github.com/NIELI310/yitihua3
肖瑞杰2017010303 https://github.com/7xjerry1/Final-Homework.git
李明   2017012579 https://github.com/lmdyhm/yitihua3final.git
项目分工：郑天泽负责 文章管理
          李明负责栏目管理，
          聂立负责用户管理curd，
          肖瑞杰负责用户登录。
          最终我将所用代码整合，完善。
在组员每个模块完成后，在多对一多对多多个模块融合中流程如下。

以post模块为例，

先角色管理，创建角色，然后使用角色登录，也可以退出，通过session管理用户登录

进入category栏目模块新建一个栏目，

然后进入post模块，新建一条记录，此时创建者为当前登录用户，多对一。

然后post-category多对多，这里选择文章所属栏目，数据库中post表与post-category两表更新。

2020-6-27-一体化实践三记
