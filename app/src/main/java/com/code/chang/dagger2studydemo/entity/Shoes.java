package com.code.chang.dagger2studydemo.entity;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/3/21 17:11
 * mail：changliugang@sina.com
 */
public class Shoes {

    /**
     * 但是这次我们创建的方式和Cloth不一样了,我们在构造函数上声明了@Inject注解,这个注解有什么用呢?作用可大
     * 了,当Component在所拥有的Module类中找不到依赖需求方需要类型的提供方法时,Dagger2就会检查该需要类型的
     * 有没有用@Inject声明的构造方法,有则用该构造方法创建一个.相当于你去商店购买东西,你需要的东西商店的供应
     * 商不生产,商店老板就只好帮你去网上看看有没有你需要的东西,有则帮你网购一个.
     * 为什么不都用这种方法来声明呢?为什么要用Module类?
     * 答案是这样的,项目中我们会用到别人的jar包,我们无法修改别人的源码,就更别说在人家的类上添加注解了,所以我们只能通过Module类来提供.
     */

    @Inject
    public Shoes() {
    }

    @Override
    public String toString() {
        return "鞋子";
    }
}
