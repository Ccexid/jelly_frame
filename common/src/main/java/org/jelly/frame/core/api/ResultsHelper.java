package org.jelly.frame.core.api;

import lombok.Data;

import java.io.Serializable;

/**
 * 构建结果返回工具类
 *
 * @author zhangxb
 * @version 1.0.0
 * @since {@link 1.8}
 */
@Data
public class ResultsHelper<T> implements Serializable {

    private static final long serialVersionUID = -2910555418138876833L;

    /**
     * 返回执行编号
     */
    private Integer code;
    /**
     * 执行消息返回体
     */
    private String message;
    /**
     * 返回结果承载
     */
    private T data;

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public ResultsHelper(Integer code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultsHelper() {
        super();
    }

    public ResultsHelper(Builder<T> builder) {
        this(builder.code, builder.message, builder.data);
    }

    public static class Builder<T> {
        /**
         * builder返回执行编号
         */
        private Integer code;
        /**
         * builder执行消息返回体
         */
        private String message;
        /**
         * builder返回结果承载
         */
        private T data;

        public Builder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }


        /**
         * 构建一个成功返回结构
         *
         * @return {@link ResultsHelper}
         */
        public Builder<T> success() {
            return this.customize(Result.SUCCESS, null);
        }

        /**
         * 构建一个成功返回结构
         *
         * @param data 数据承载对象
         * @return {@link ResultsHelper}
         */
        public Builder<T> success(T data) {
            return this.customize(Result.SUCCESS, data);
        }

        /**
         * 构建一个成功返回结构
         *
         * @param message 成功消息
         * @return {@link ResultsHelper}
         */
        public Builder<T> success(String message) {
            return this.customize(Result.SUCCESS.getCode(), message, null);
        }

        /**
         * 构建一个成功返回结构
         *
         * @param message 成功消息
         * @param data    数据承载
         * @return {@link ResultsHelper}
         */
        public Builder<T> success(String message, T data) {
            return this.customize(Result.SUCCESS.getCode(), message, data);
        }


        /**
         * 构建一个失败返回结构
         *
         * @return {@link ResultsHelper}
         */
        public Builder<T> fail() {
            return this.customize(Result.FAIL, null);
        }

        /**
         * 构建一个失败返回结果
         *
         * @param data 数据承载对象
         * @return {@link ResultsHelper}
         */
        public Builder<T> fail(T data) {
            return this.customize(Result.FAIL, data);
        }

        /**
         * 构建一个失败返回结构
         *
         * @param message 失败消息
         * @return {@link ResultsHelper}
         */
        public Builder<T> fail(String message) {
            return this.customize(Result.FAIL.getCode(), message, null);
        }

        /**
         * 构建一个失败返回结构
         *
         * @param message 失败消息
         * @param data    数据承载
         * @return {@link ResultsHelper}
         */
        public Builder<T> fail(String message, T data) {
            return this.customize(Result.FAIL.getCode(), message, data);
        }

        public Builder<T> customize(Result constant, T data) {
            return this.customize(constant.getCode(), constant.getMessage(), data);
        }

        public Builder<T> customize(Integer code, String message, T data) {
            return this.code(code).message(message).data(data);
        }

        public ResultsHelper<T> build() {
            return new ResultsHelper<>(this);
        }


    }
}
