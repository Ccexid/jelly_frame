package org.jelly.frame.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * 获取SpringBean工具类
 *
 * @author zhangxb
 */
public class JellySpringBeanUtil {
    /**
     * 代表 Web 应用程序执行环境的 ServletContext 的标准实现。
     */
    private static ApplicationContext context = null;

    /**
     * 赋值 ApplicationContext
     *
     * @param applicationContext {@link org.springframework.context.ApplicationContext}
     */
    public static void setContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    /**
     * 根据名称获取对象
     *
     * @param beanName Bean名称
     * @param <T>      Object
     * @return Object or null
     * @throws BeansException 无法创建
     */
    public static <T> T getBeanByBeanName(String beanName) throws BeansException {
        if (context == null) {
            return null;
        }
        return (T) context.getBean(beanName);
    }

    /**
     * 根据名称获取对象
     *
     * @param beanName     Bean名称
     * @param requiredType Object类型
     * @param <T>          Object
     * @return Object or null
     * @throws BeansException 无法创建
     */
    public static <T> T getBeanByBeanName(String beanName, Class<T> requiredType) throws BeansException {
        if (context == null) {
            return null;
        }
        return context.getBean(beanName, requiredType);
    }

    /**
     * 根据名称获取对象
     *
     * @param beanName Bean名称
     * @param args     构造函数的参数
     * @param <T>      Object
     * @return Object or null
     * @throws BeansException 无法创建
     */
    public static <T> T getBeanByBeanName(String beanName, Object... args) throws BeansException {
        if (context == null) {
            return null;
        }
        return (T) context.getBean(beanName, args);
    }

    /**
     * 根据名称获取对象
     *
     * @param requiredType Object类型
     * @param <T>          Object
     * @return Object or null
     * @throws BeansException 无法创建
     */
    public static <T> T getBeanByClass(Class<T> requiredType) throws BeansException {
        if (context == null) {
            return null;
        }
        return (T) context.getBean(requiredType);
    }


    /**
     * 根据名称获取对象
     *
     * @param requiredType Object类型
     * @param <T>          Object
     * @return Object or null
     * @throws BeansException 无法创建
     */
    public static <T> T getBeanByClass(Class<T> requiredType, Object... args) throws BeansException {
        if (context == null) {
            return null;
        }
        return (T) context.getBean(requiredType, args);
    }
}
