package com.code.chang.dagger2studydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.code.chang.dagger2studydemo.entity.Cloth;
import com.code.chang.dagger2studydemo.entity.Clothes;
import com.code.chang.dagger2studydemo.entity.Shoes;
import com.code.chang.dagger2studydemo.module.MainModule;

import javax.inject.Inject;
import javax.inject.Named;


public class MainActivity extends AppCompatActivity {
    private MainComponent mMainComponent;
    private TextView tv;

    //*************************************************************************************************************************

    /**
     * 引用自 http://www.jianshu.com/p/1d84ba23f4d2#
     *
     * Dagger2是Android中比较热门的依赖注入框架.
     * 什么是依赖注入呢?
     * 通俗的来讲呢,就是一个类中需要依赖其他对象时,不需要你亲自为那些需要依赖的对象赋值,为那些对象赋值的操作交给了IOC框架.
     * <p>
     * 一般的IOC框架都是通过反射来实现的,但Dagger2作为Android端的IOC框架,为了不影响性能,它是通过apt动态生成代码来实现的.
     * (APT(Annotation Processing Tool 的简称)，可以在代码编译期解析注解，并且生成新的 Java 文件，减少手动的代码输入)
     * <p>
     * Dagger2主要分为三个模块:
     * <p>
     * 1、依赖提供方Module,负责提供依赖中所需要的对象,实际编码中类似于工厂类
     * 2、依赖需求方实例,它声明依赖对象,它在实际编码中对应业务类,例如Activity,当你在Activity中需要某个对象时,你只要在其中声明就行,声明的方法在下面会讲到.
     * 3、依赖注入组件Component,负责将对象注入到依赖需求方,它在实际编码中是一个接口,编译时Dagger2会自动为它生成一个实现类.
     * Dagger2的主要工作流程分为以下几步:
     * <p>
     * 1、将依赖需求方实例传入给Component实现类
     * 2、Component实现类根据依赖需求方实例中依赖声明,来确定该实例需要依赖哪些对象
     * 3、 确定依赖对象后,Component会在与自己关联的Module类中查找有没有提供这些依赖对象的方法,有的话就将Module类中提供的对象设置到依赖需求方实例中
     * <p>
     *
     * Dagger2添加到项目中：
     * <p>
     * 在项目下的build.gradle文件中添加apt插件:
     * classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
     * <p>
     * 在app目录的build.gradle文件中添加:
     * apply plugin: 'com.neenbedankt.android-apt'
     * //引入dagger2
     * compile 'com.google.dagger:dagger:2.4'
     * apt 'com.google.dagger:dagger-compiler:2.4'
     * //java注解
     * provided 'org.glassfish:javax.annotation:10.0-b28'
     */

    /**
     * 我们有两种方式可以提供依赖，一个是注解了@Inject的构造方法，一个是在Module里提供的依赖，那么Dagger2是怎么选择依赖提供的呢，规则是这样的：
     * 步骤1：查找Module中是否存在创建该类的方法。
     * 步骤2：若存在创建类方法，查看该方法是否存在参数
     * 步骤2.1：若存在参数，则按从步骤1开始依次初始化每个参数
     * 步骤2.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束
     * 步骤3：若不存在创建类方法，则查找Inject注解的构造函数，看构造函数是否存在参数
     * 步骤3.1：若存在参数，则从步骤1开始依次初始化每个参数
     * 步骤3.2：若不存在参数，则直接初始化该类实例，一次依赖注入到此结束
     * 也就说Dagger2会递归的提供依赖.
     */

    /**
     * Component的生命周期
     *一般情况下我们都是在Activity的onCreate方法中创建Component实例,再调用inject方法完成依赖.所以Component依赖可以分为三个过程:
     * 1、创建Component实例（就是那个build）
     * 2、component.inject(this);调用完这个方法整个依赖就完成了.
     * 3、Component实例被销毁
     * onCreate()方法调用完成后,Component实例就会因为没有被引用而被垃圾回收器回收.其中传入给Component实例
     * 的Module实例也会一同被回收,这也就能说明不同的Component实例之间是没有联系的(Component依赖除外).这里
     * 需要注意的是,使用Lazy和Provider时,与该依赖对象有关的Module实例会被Lazy和Provider引用,所以该Module
     * 实例不会被垃圾回收器回收
     */

    /**
     * 总结
     *1、component 的 inject 函数不要声明基类参数；
     2、Scope 注解必须用在 module 的 provide 方法上，否则并不能达到局部单例的效果；
     3、如果 module 的 provide 方法使用了 scope 注解，那么 component 就必须使用同一个注解，否则编译会失败；
     4、如果 module 的 provide 方法没有使用 scope 注解，那么 component 和 module 是否加注解都无关紧要，可以通过编译，但是没有局部单例效果；
     5、对于直接使用 @Inject 构造函数的依赖，如果把 scope 注解放到它的类上，而不是构造函数上，就能达到局部单例的效果了；
     6、被依赖的Component能提供某个对象时,一定要在接口中声明以该对象为返回值的方法(也就是暴露接口).这样依赖它的Component才能获取到这种对象.
     */

    //*************************************************************************************************************************


    /**
     * 声明依赖对象Cloth,就是在cloth字段上添加@Inject注解,Dagger2中声明依赖对象都是通过@Inject注解,但
     * 是@Inject注解的字段不能是private和protected的.
     */
    @Inject
    Cloth mCloth;
    @Inject
    Shoes mShoes;
    @Inject
    Clothes mClothes;
    /**
     * 我们在getRedCloth方法上使用@Named("red")表明此方法返回的是红布料,同理,在getBlueCloth方法上使
     * 用@Named("blue")表明此方法返回的是蓝布料,接下我们只要在MainActivity中的布料字段上同样使用@Named注
     * 解,就可以一一配对了.
     */
    @Inject
    @Named("blue")
    Cloth mBlueCloth;
    @Inject
    @Named("black")
    Cloth mBlackCloth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 现在的需求是MainActivity中需要使用到Cloth对象,所以我们要为MainActivity书写一个Module类用来提供Cloth对象,相当于创建了一个提供商
         */
        tv = (TextView) findViewById(R.id.text);
        /**
         *通过Dagger2自动生成的类来创建Component的实现类,创建时需要传入该Component实现类所需要的Module类实
         * 例,传入方法就是调用Module类类名首字母小写对应的方法.这里我们通过Dagger2自动生成的DaggerMainComponent类
         * 创建了MainComponent的实例,相当于我们创建了一个实实在在的商店,不再是理论上的商店,但是创建商店一定
         * 也要创建真实的供应商嘛,所以创建Component实现类时一定要传入Module的实例.
         * (注意编写完Component接口后Dagger2并不会自动创建对应的类,DaggerMainComponent在写完是不生成的，要Ctrl+F9编译一下).
         */
//        mMainComponent = DaggerMainComponent.builder().mainModule(new MainModule()).build();
        /**
         * 再将MainActivity通过inject方法发送到MainComponent中,调用完inject方法后,你就会发
         * 现,MainActivity中的cloth字段已经被赋值,而且该cloth对应的就是我们在MainModule类getCloth方
         * 法中创建的Cloth对象.
         */
//        mMainComponent.inject(this);
//        tv.setText("这里有一件" + mClothes + "和一双" + mShoes + "还有" + mCloth+"\nNamed:"+mBlueCloth+mBlackCloth);
        startActivity(new Intent(MainActivity.this, SecondActivity.class));

        baseInject(tv);

    }

    @Inject
    ClothHandler mClothHandler;

    private void baseInject(TextView textView) {
        MainComponent mainComponent = DaggerMainComponent.builder().baseComponent(((AppApplication)
                getApplication()).getBaseComponent()).mainModule(new MainModule()).build();
        mainComponent.inject(this);
        textView.setText(mClothHandler.handle(mCloth) + "\nmClothHandler地址--->" + mClothHandler);
    }
}
