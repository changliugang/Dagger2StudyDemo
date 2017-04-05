package com.code.chang.dagger2studydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.code.chang.dagger2studydemo.component.DaggerSingletonComponent;
import com.code.chang.dagger2studydemo.component.SingletonComponent;
import com.code.chang.dagger2studydemo.entity.Cloth;
import com.code.chang.dagger2studydemo.entity.Clothes;
import com.code.chang.dagger2studydemo.module.SingletonModeModule;

import javax.inject.Inject;

public class SecondActivity extends AppCompatActivity {

    @Inject
    Cloth redCloth;
    @Inject
    Clothes redClothes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        /**
         * 有时我们的生成的singletonModeModule()方法被标记为@Deprecated,这是因为我的module里面什么都没有
         * 或者全是在已有Module中定义过的,所以被认为是没有必要的.
         */
//        SingletonComponent singletonComponent = DaggerSingletonComponent.builder().singletonModeModule(new SingletonModeModule()).build();
//        singletonComponent.inject(this);
        TextView tv = (TextView) findViewById(R.id.second_text);
//        tv.setText(redCloth+"做"+redClothes);
        /**
         * 这个@Singleton
         *
         * 你会发现,MainActivity中的Cloth对象和Clothes中的Cloth对象并不是同一个对象,注入过程中,对cloth注
         * 入时会调用一次getRedCloth方法,创建了一个Cloth对象;注入Clothes时又会调用一次getRedCloth方法,这
         * 时又会创建一个Cloth对象,所以才会出现上面的结果.但是如果需要MainActivity中的Cloth对象和Clothes中
         * 的Cloth对象是同一个对象又要怎么办呢?Dagger2为我们提供了@Singleton注解,和名字一样,这个注解的作用
         * 就是声明单例模式,我们先看看它怎么使用.
         * 1、首先,在getRedCloth方法上添加@Singleton注解
         * 2、再在对应的Component接口上添加@Singleton注解
         */

        /**
         * 顾名思义,@Scope就是用来声明作用范围的.@Scope和@Qulifier一样,需要我们自定义注解才能使用.
         */

//        tv.setText("redCloth=redClothes中的cloth吗?--->"+(redCloth==redClothes.getCloth()));


        baseInject(tv);


    }

    @Inject
    ClothHandler mClothHandler;

    private void baseInject(TextView textView){
        /**
         * MainComponent和SecondComponent实例时多了一个baseComponent方法
         *
         * 这个方法需要我们传入一个BaseComponent实例,原因很简单,MainComponent和SecondComponent既然依
         * 赖BaseComponent,肯定需要你传入一个BaseComponent实例给它,它才能从BaseComponent实例中获取到它需要的对象嘛.
         */
        SingletonComponent component = DaggerSingletonComponent.builder().baseComponent(
                ((AppApplication) getApplication()).getBaseComponent()).singletonModeModule(new SingletonModeModule()).build();
        component.inject(this);
        textView.setText(mClothHandler.handle(redCloth)+"\n地址："+mClothHandler);
    }
}
