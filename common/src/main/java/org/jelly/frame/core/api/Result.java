package org.jelly.frame.core.api;

/**
 * @author zhangxb
 */

public enum Result implements IResult {
    /**
     * 接口正常返回数据
     */
    SUCCESS(0, "请求执行成功"),
    /**
     * 接口失败返回数据 正常执行失败，例如事务回滚
     */
    FAIL(1, "请求执行失败"),
    /**
     * 参数异常,接口返回数据
     */
    PARAMETER_ERROR(103, "请求参数错误，请检查!"),
    /**
     * 服务接口发生错误，接口返回数据 例如404，或者自定义异常
     */
    REQUEST_ERROR(104, "接口请求发生错误，请联系管理人员"),
    /**
     * 服务异常
     */
    SYSTEM_ERROR(105, "系统发生异常，请联系管理人员");


    private final int code;

    private final String message;

    Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
