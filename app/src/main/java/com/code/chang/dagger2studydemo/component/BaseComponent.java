package com.code.chang.dagger2studydemo.component;

import com.code.chang.dagger2studydemo.ClothHandler;
import com.code.chang.dagger2studydemo.module.BaseModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * .(注意:这里能实现单例跟BaseComponent中声明了@Singleton有很大关系,因为BaseComponent都没有单例的话,外部依赖它的Component就更不可能单例了).
 * Created by Administrator on 2017/3/22 16:22
 * mail：changliugang@sina.com
 */
@Singleton
@Component(modules = BaseModule.class)
public interface BaseComponent {

    ClothHandler getClothHandler();

    /**
     * 这个Component没有inject方法,因为我们通过inject方法把依赖需求方实例送到Component中,从而帮助依赖需求
     * 方实现依赖,但是我们这个BaseComponent是给其他Component提供依赖的,所以我们就可以不用inject方法,但
     * 是BaseComponent中多了一个getClothHandler方法,它的返回值是ClothHandler对象，它的作用就是告诉依赖
     * 于BaseComponent的Component,BaseComponent能为你们提供ClothHandler对象,如果没有这个方法,BaseComponent
     * 就不能提供ClothHandler对象(这个提供规则和上面的依赖规则相同,可以实现单例)
     */
}
