package com.code.chang.dagger2studydemo.module;

import com.code.chang.dagger2studydemo.entity.Cloth;
import com.code.chang.dagger2studydemo.entity.Clothes;
import com.code.chang.dagger2studydemo.injector.PreActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/22 13:55
 * mail：changliugang@sina.com
 */
@Module
public class SingletonModeModule {

    @PreActivity
    @Provides
    public Cloth getRedCloth(){
        Cloth cloth = new Cloth();
        cloth.setColor("红色");
        return cloth;
    }

    @Provides
    public Clothes getRedClothes(Cloth cloth){
        return new Clothes(cloth);
    }

}
