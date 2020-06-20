package com.cucci.common.exceptions;

/**
 * 业务异常
 *
 * <br>
 * 代码中能够察觉到的异常都应该转换成该异常，然后通过统一异常处理器进行处理
 * </>
 *
 * @author shenyw
 **/
public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 982523153570073846L;

    public ApplicationException(String message) {
        super(message);
    }

}
