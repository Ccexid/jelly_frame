package org.jelly.frame.core.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 检查性异常数据
 *
 * @author zhangxb
 */
@AllArgsConstructor
@Accessors(chain = true)
@Data
public class JellyCheckedException extends Exception{
    /**
     * 错误编码
     */
    private int code;
    /**
     * 错误消息
     */
    private String message;


    public JellyCheckedException(String s) {
        super(s);
        this.message = s;

    }
}
