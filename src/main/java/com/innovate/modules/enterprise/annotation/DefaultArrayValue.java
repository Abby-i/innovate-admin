package com.innovate.modules.enterprise.annotation;

import com.innovate.common.enums.DefValueEnum;

import java.lang.annotation.*;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description Map集合中指定key为空时初始为默认值
 * @date 2019/10/9
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DefaultArrayValue {

    /**
     * 目标类型
     * @return
     */
    Class<?> targetType();

    /**
     * 类型所在索引
     * @return
     */
    int index();

    /**
     * 指定键
     * @return
     */
    String[] key();

    /**
     * 指定值
     * @return
     */
    String[] defValue();

    /**
     * 指定值的类型
     * @return
     */
    DefValueEnum[] defValueEnum() default {DefValueEnum.STRING};

}

