# -*- encoding=utf-8 -*-

#定义类
class Dog:
    #类似于java中的静态成员，直接通过类访问。
    name = "dahuang" ;

    #私有成员
    __color = "yellow" ;

    #定义构造函数
    def  __init__(self,age):
        print("new Dog()...")
        #相当于添加实例变量
        self.age = age ;

        #删除实例变量
        #del self.age ;

    def __del__(self):
        print("销毁对象了")

    def watch(self):
        print("watch house!!")

    def add(self,a,b):
        return a + b ;

#创建对象，等价于new Dog();
d1 = Dog(12)

#访问静态成员
print(Dog.name)

#调用实例方法
d1.watch()

#访问实例对象的方法
print(d1.add(1,2))

#访问实例变量
print(d1.age)

#判断对象是否有指定的属性
print(hasattr(d1,"age"))

#python内置函数，删除对象属性
delattr(d1,"age")

print(hasattr(d1, "age"))

# dict1 = Dog.__dict__
# for key in dict1.keys():
#     print(key + ": " + str(dict1[key]))

#java : final | finally | finalize
#删除对象，调用析构函数,类似于finalize();
#d1 = None ;
#del d1

class Cat:
    #静态方法.
    def HHH(s):
        print("hhhh") ;

    def __init__(self,name,age):
        self.name2 = name ;
        self.age2 = age ;
    def catchMouse(self):
        self.__eatFish()
        print("抓了只老鼠!!!")

    #定义私有方法,只能在当前对象中访问
    def __eatFish(self):
        print("好吃！！")

class DogCat(Dog,Cat):
    def __init__(self):
        Dog.__init__(self,12)
        Cat.__init__(self,"tom",23)
    def run(self):
        print(",,,,,,")

    def catchMouse(self):
        print("抓了只青蛙！！")

dc = DogCat();
dc.watch()
dc.catchMouse()
Cat.HHH("ss")


def add(a,b):
    return a + b
