package com.code.chang.dagger2studydemo;

import com.code.chang.dagger2studydemo.entity.Cloth;
import com.code.chang.dagger2studydemo.entity.Clothes;

/**
 * Created by Administrator on 2017/3/22 16:06
 * mail：changliugang@sina.com
 */
public class ClothHandler {

    public Clothes handle(Cloth cloth) {
        return new Clothes(cloth);
    }

}
