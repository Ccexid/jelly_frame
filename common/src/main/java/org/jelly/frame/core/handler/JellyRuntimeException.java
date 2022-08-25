package org.jelly.frame.core.handler;

import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 运行时异常
 *
 * @author zhangxb
 */
@AllArgsConstructor
@Accessors(chain = true)
public class JellyRuntimeException extends RuntimeException{
    public JellyRuntimeException(Exception e) {
        super(e);
    }
}
