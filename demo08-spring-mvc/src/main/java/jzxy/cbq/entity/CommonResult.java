package jzxy.cbq.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.ObjectUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author: cbq1024
 * @description: CommonResult
 * @since 2024/7/11 下午4:22
 */
@SuppressWarnings("unused")
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonResult extends HashMap<String, Object> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 CommonResult 对象，使其表示一个空消息
     */
    public CommonResult() {
    }

    /**
     * 初始化一个新创建的 CommonResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     */
    public CommonResult(int code, String msg) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 CommonResult 对象
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param data 数据对象
     */
    public CommonResult(int code, String msg, Object data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (!ObjectUtils.isEmpty(data)) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static CommonResult success() {
        return CommonResult.success("操作成功");
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static CommonResult success(Object data) {
        return CommonResult.success("操作成功", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static CommonResult success(String msg) {
        return CommonResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static CommonResult success(String msg, Object data) {
        return new CommonResult(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static CommonResult warn(String msg) {
        return CommonResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static CommonResult warn(String msg, Object data) {
        return new CommonResult(HttpStatus.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return 错误消息
     */
    public static CommonResult error() {
        return CommonResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 错误消息
     */
    public static CommonResult error(String msg) {
        return CommonResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static CommonResult error(String msg, Object data) {
        return new CommonResult(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 错误消息
     */
    public static CommonResult error(int code, String msg) {
        return new CommonResult(code, msg, null);
    }

    /**
     * 是否为成功消息
     *
     * @return 结果
     */
    public boolean isSuccess() {
        return Objects.equals(HttpStatus.SUCCESS, this.get(CODE_TAG));
    }

    /**
     * 是否为警告消息
     *
     * @return 结果
     */
    public boolean isWarn() {
        return Objects.equals(HttpStatus.WARN, this.get(CODE_TAG));
    }

    /**
     * 是否为错误消息
     *
     * @return 结果
     */
    public boolean isError() {
        return Objects.equals(HttpStatus.ERROR, this.get(CODE_TAG));
    }

    public static CommonResult getResult(Boolean row) {
        if (row) {
            return CommonResult.success();
        } else {
            return CommonResult.error();
        }
    }

    public static CommonResult getResult(Integer row) {
        if (row > 0) {
            return CommonResult.success();
        } else {
            return CommonResult.error();
        }
    }

    /**
     * 方便链式调用
     *
     * @param key   键
     * @param value 值
     * @return 数据对象
     */
    @Override
    public CommonResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
