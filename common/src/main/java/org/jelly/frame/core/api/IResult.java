package org.jelly.frame.core.api;

/**
 * Result 接口
 *
 * @author zhangxb
 */
public interface IResult {
    /**
     * 获取结果状态
     *
     * @return 结果状态
     */
    Integer getCode();

    /**
     * 获取结果消息
     *
     * @return 结果消息
     */
    String getMessage();
}
