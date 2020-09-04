package com.zxkj;

public class TulingDemo {

    private String name;


    public TulingDemo(){
        System.out.println("通过无参构造函数创建");
    }


    public TulingDemo(String name){
        System.out.println("通过有构造函数创建:"+name);
        this.name = name;
    }

    public void showName(){
        System.out.println(name);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private void welcome(String content){
        System.out.println("Welcome!!"+content);
    }
}
