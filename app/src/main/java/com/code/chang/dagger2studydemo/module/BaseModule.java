package com.code.chang.dagger2studydemo.module;

import com.code.chang.dagger2studydemo.ClothHandler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/22 16:13
 * mail：changliugang@sina.com
 */
@Module
public class BaseModule {

    /**
     * 多个Activity需要调用的公共实体实现单例怎么办呢?Dagger2提供了Component依赖dependencies来解决这个问题.
     * 在面向对象的思想中,我们碰到这种情况一般都要抽取父类,Dagger2也是用的这种思想。
     * 1、我们创建一个BaseModule。
     * 2、在创建一个BaseComponent接口
     * 3、接下来在MainComponent和SecondComponent中声明依赖,就要用到@Component中的dependencies属性
     */

    @Singleton
    @Provides
    public ClothHandler getClothHandle(){
        return new ClothHandler();
    }

}
