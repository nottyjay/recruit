package com.d3code.recruit.gather.exception;

import java.io.IOException;

/**
 * Created by Nottyjay on 2016/11/30 0030.
 */
public class GatherException extends RuntimeException {

    public GatherException(String message) {
        super(message);
    }

    public GatherException(String message, Throwable cause) {
        super(message, cause);
    }

    public GatherException(Throwable cause) {
        super(cause);
    }
}
