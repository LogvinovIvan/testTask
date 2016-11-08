package com.epam.lab.news.manager.exception;

/**
 * Created by Ivan_Lohvinau on 10/13/2016.
 */
public class ServiceException extends Exception {
    public ServiceException() {
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
