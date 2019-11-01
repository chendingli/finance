package com.chinasoft.annotation;


import java.lang.annotation.*;


/**
 * 自定义注解，用于描述Controller的方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodDesc {

    enum Type {

        OTHER(0),ADD(1),UPDATE(2),DELETE(3),VIEW(4);

        private int type;

        // 构造方法
        Type(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    /**
     * 操作名称
     * @return
     */
    String value();

    /**
     * 操作类型
     * @return
     */
    Type type();

}
