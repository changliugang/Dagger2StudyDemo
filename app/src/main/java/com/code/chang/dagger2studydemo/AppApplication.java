package com.code.chang.dagger2studydemo;

import android.app.Application;

import com.code.chang.dagger2studydemo.component.BaseComponent;
import com.code.chang.dagger2studydemo.component.DaggerBaseComponent;
import com.code.chang.dagger2studydemo.module.BaseModule;

/**
 * Created by Administrator on 2017/3/22 16:48
 * mail：changliugang@sina.com
 */
public class AppApplication extends Application {

    private BaseComponent mBaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 这种方式还有一种好处,就是当我们在BaseModule中需要用到Application实例时,我们就可以在创建BaseModule时传入this
         */
        mBaseComponent = DaggerBaseComponent.builder().baseModule(new BaseModule()).build();
    }

    /**
     * 但是需要注意的是,如果要MainComponent和SecondComponent依赖到的对象是同一个的话(也就是单例),创建它们
     * 是传入的BaseComponent实例也必须是同一个,不同的Component实例是无法提供相同的依赖实例的,因为它们之间是
     * 没有联系的.这样的话,我们就需要在不同Activity中能获取到同一个BaseComponent实例,我们一般都会自定义一
     * 个Application类,用它来提供BaseComponent实例,因为在整个App生命周期内都只有一个Application实例,所以
     * 其中的BaseComponent实例也不会变
     */
    public BaseComponent getBaseComponent() {
        return mBaseComponent;
    }

}
