package com.code.chang.dagger2studydemo.entity;

/**
 * 我们创建的这些依赖类都不用依赖于其它依赖类,但是如果需要依赖于其它依赖类又要怎么弄呢?
 * Created by Administrator on 2017/3/22 09:28
 * mail：changliugang@sina.com
 */
public class Clothes {

    private Cloth cloth;

    public Cloth getCloth() {
        return cloth;
    }


    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    /**
     * 我们又来创建一个衣服类Clothes,制作衣服时需要布料,所以我们在创建Clothes的实例时需要用到Cloth实例
     */
    public Clothes(Cloth cloth) {
        this.cloth = cloth;
    }
//    /**
//     * 同理,在带有@Inject注解的构造函数要是依赖于其它对象,Dagger2也会帮你自动注入.
//     */
//    @Inject
//    public Clothes(Cloth cloth) {
//        this.cloth = cloth;
//    }

    @Override
    public String toString() {
        return cloth.getColor()+"的衣服";
    }
}
