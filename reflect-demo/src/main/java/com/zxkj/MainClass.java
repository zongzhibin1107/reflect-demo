package com.zxkj;

import java.lang.reflect.*;

public class MainClass {

    public static void main(String[] args) {
        test5();
    }



    //根据一个字符串得到一个类
    public static void test(){
        String name = "TulingDemo";
        Class c1 = name.getClass();
        System.out.println(c1.getName());
    }

    //Class.forName   我们还可以c1.getSuperclass()获取到他的父类
    public static void test1(){
        String name = "com.zxkj.TulingDemo";

        Class c1 = null;
        try {
            c1 = Class.forName(name);
            System.out.println(c1.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //基本类型有Type属性
    public static void test2(){
        Class c1 = Boolean.TYPE;
        System.out.println(c1.getName());
    }


    /**
     * 当类中方法定义为私有的时候我们能调用？不能！
     * 当变量是私有的时候我们能获取吗？不能！
     * 但是反射可以，比如源码中有你需要用到的方法，但是那个方法是私有的，这个时候你就可以通过反射去执行这个私有方法，并且获取私有变量。
     */

    //获取类的所有构造方法
    //getModifiers可以得到构造方法的类型
    //getParameterTypes可以得到构造方法的所有参数
    public static void test3(){
        TulingDemo tulingDemo = new TulingDemo();
        Class c4 = tulingDemo.getClass();

        Constructor[] constructors = c4.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; i++) {
            System.out.print(Modifier.toString(constructors[i].getModifiers()) + "参数：");
            Class[] parametertypes = constructors[i].getParameterTypes();
            for (int j = 0; j < parametertypes.length; j++) {
                System.out.print(parametertypes[j].getName() + " ");
            }
            System.out.println("");
        }

    }

    //获取类中特定的构造方法
    //getDeclaredConstructor()方法传参获取特定参数类型的构造方法
    //通过构造函数的newInstance方法 创建实例
    //如果是私有的构造函数  constructors.setAccessible(true);
    //getDeclaredMethod方法获取到这个私有方法，第一个参数是方法名，第二个参数是参数类型
    public static void test4(){
        Class c4 = TulingDemo.class;

        Constructor declaredConstructor = null;
        try {
            //declaredConstructor = c4.getDeclaredConstructor();   获取无参构造函数
            Class[] p = {String.class};
            declaredConstructor = c4.getDeclaredConstructor(p);

            Class[] parametertypes = declaredConstructor.getParameterTypes();
            for (int j = 0; j < parametertypes.length; j++) {
                System.out.print(parametertypes[j].getName() + " ");
            }


            TulingDemo o = (TulingDemo)declaredConstructor.newInstance("378783");


            Class[] p1 = {String.class};
            //第一个参数为方法名字  第二个参数为方法的参数
            Method method = c4.getDeclaredMethod("welcome",p1);
            method.setAccessible(true);

            Object arg1s[] = {"欢迎关注代码男人技术公众号"};
            method.invoke(o,arg1s);



        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取类的私有字段并修改值
     * c4.getDeclaredField("name");
     */
    public static void test5(){
        Class c4 = TulingDemo.class;

        try {
            Field name = c4.getDeclaredField("name");
            Constructor declaredConstructor = c4.getDeclaredConstructor();
            TulingDemo o = (TulingDemo)declaredConstructor.newInstance();
            name.setAccessible(true);
            name.set(o,"代码男人");
            System.out.println(o.getName());
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
