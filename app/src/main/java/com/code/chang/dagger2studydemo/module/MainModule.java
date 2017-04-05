package com.code.chang.dagger2studydemo.module;

import com.code.chang.dagger2studydemo.entity.Cloth;
import com.code.chang.dagger2studydemo.entity.Clothes;
import com.code.chang.dagger2studydemo.injector.PreActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Module类用来提供Cloth对象
 * Created by Administrator on 2017/3/21 11:11
 * mail：changliugang@sina.com
 */
@Module
public class MainModule {
    /**
     * 注解是Dagger2中的关键,编写Module类时要在该类上声明@Module以表明该类是Module类,这样Dagger2才能识别
     */

    /**
     * 这个@Provides的作用是声明Module类中哪些方法是用来提供依赖对象的,当Component类需要依赖对象
     * 时,他就会根据返回值的类型来在有@Provides注解的方法中选择调用哪个方法.在一个方法上声明@Provides注解,就
     * 相当于创建了一条生产线,这条生产线的产物就是方法的返回值类型.
     */
    @PreActivity
    @Provides
    public Cloth getCloth() {
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
       return cloth;
    }

    /**
     * 注解@Named
     * 假设我们现在又有了新的需求,MainActivity中需要两种布料,分别是红布料和蓝布料,但我们的MainModule类中只能提供红布料,怎么办呢?
     * 读者可能会想:在MainModule类中再添加一个提供蓝布料的方法不就行了。
     * 可问题就来了,Dagger2是通过返回值类型来确定的,当你需要红布料时,它又怎么知道哪个是红布料呢?所以Dagger2为
     * 我们提供@Named注解,它怎么使用呢?它有一个value值,用来标识这个方法是给谁用的.修改我们的代码:
     */

    @Provides
    @Named("blue")
    public Cloth getBlueCloth(){
        Cloth cloth = new Cloth();
        cloth.setColor("蓝色");
        return cloth;
    }

    /**
     * 我们在getRedCloth方法上使用@Named("red")表明此方法返回的是红布料,同理,在getBlueCloth方法上使
     * 用@Named("blue")表明此方法返回的是蓝布料,接下我们只要在MainActivity中的布料字段上同样使用@Named注
     * 解,就可以一一配对了.
     */
    @Provides
    @Named("black")
    public Cloth getBlackCloth(){
        Cloth cloth = new Cloth();
        cloth.setColor("黑色");
        return cloth;
    }

    /**
     * Dagger2提供了这样的功能,我们只要在getClothes方法中添加Cloth参数,Dagger2就会像帮依赖需求方
     * 找依赖对象一样帮你找到该方法依赖的Cloth实例,所以我们代码可以这样写
     */
    @Provides
    public Clothes getClothes(Cloth cloth) {
        return new Clothes(cloth);
    }
    /**
     * 并且@Named注解还能使用在依赖参数上,比如这个:
     */
//    @Provides
//    public Clothes getClothes(@Named("black") Cloth cloth) {
//        return new Clothes(cloth);
//    }
}
