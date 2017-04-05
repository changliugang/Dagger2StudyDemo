package com.code.chang.dagger2studydemo.component;

import com.code.chang.dagger2studydemo.SecondActivity;
import com.code.chang.dagger2studydemo.injector.PreActivity;
import com.code.chang.dagger2studydemo.module.SingletonModeModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/22 14:07
 * mail：changliugang@sina.com
 */
@PreActivity
@Component(modules = SingletonModeModule.class,dependencies = BaseComponent.class)
public interface SingletonComponent {

    void inject(SecondActivity secondActivity);

}
