# -*- encoding=utf-8 -*-

#导入time模块
import time ;

#高级线程接口
import threading

#低级接口
import _thread

#定义函数
def hello(s):
    #获得当前线程的名字
    tname = threading.current_thread().getName()
    for i in [1,2,3,4,5,6,7,8,9]:
        print(tname + " : " + str(i))
        import sys
        import os
        cc = int(time.time() * 1000)
        print(cc)

_thread.start_new_thread(hello, ("xx",))
_thread.start_new_thread(hello, ("uu",))

#休眠5秒时间
time.sleep(3)

