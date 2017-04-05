package com.code.chang.dagger2studydemo;

import com.code.chang.dagger2studydemo.component.BaseComponent;
import com.code.chang.dagger2studydemo.injector.PreActivity;
import com.code.chang.dagger2studydemo.module.MainModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/21 11:52
 * mail：changliugang@sina.com
 */
@PreActivity
@Component(modules = MainModule.class,dependencies = BaseComponent.class)
public interface MainComponent {
    /**
     * 编写Component类时要在该类上声明@Component以表明该类是Component类,这样Dagger2才能识别
     */
    /**
     * 这个@Component注解有modules和dependencies两个属性,这两个属性的类型都是Class数组,modules的作用就是
     * 声明该Component含有哪几个Module,当Component需要某个依赖对象时,就会通过这些Module类中对应的方法获取
     * 依赖对象,MainComponent中只包含MainModule,所以令modules=MainModule.class,相当于供应商和商店老板确
     * 定合作关系的合同.而dependencies属性则是声明Component类的依赖关系,这个下面再详讲.
     */

    /**
     * 我们现在只是声明了Component类,但我们要怎么将Component类和依赖需求方对象联合起来呢?答案就是通过这
     * 个inject方法,这个方法可以将依赖需求方对象送到Component类中,Component类就会根据依赖需求方对象中
     * 声明的依赖关系来注入依赖需求方对象中所需要的对象,本Demo中MainActivity中需要Cloth对象,所以我们通
     * 过inject方法将MainActivity实例传入到MainComponent中,MainComponent就会从MainModule中的getCloth方
     * 法获取Cloth实例,并将该实例赋值给MainActivity中的cloth字段.相当于你去商店的道路,没有这条路,你就无法去
     * 商店和老板说明你所需要的东西.
     * （但是这里需要注意的是,inject方法的参数不能用子类来接收,例如本Demo中,如果inject的参数是Activity,那
     * 么Dagger2就会报错.）
     */
    void inject(MainActivity mainActivity);
}
