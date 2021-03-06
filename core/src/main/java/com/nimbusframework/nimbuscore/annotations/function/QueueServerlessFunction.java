package com.nimbusframework.nimbuscore.annotations.function;

import com.nimbusframework.nimbuscore.annotations.function.repeatable.QueueServerlessFunctions;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(QueueServerlessFunctions.class)
public @interface QueueServerlessFunction {
    Class<?> queue();
    int batchSize();
    int timeout() default 10;
    int memory() default 1024;
    String[] stages() default {};
}