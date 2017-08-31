package me.maxct.academic.exception

import org.springframework.transaction.TransactionException

/**
 * Created by imaxct on 17-8-31.
 */
class ServiceException : TransactionException {
    constructor(msg: String?) : super(msg)
    constructor(msg: String?, throwable: Throwable?) : super(msg, throwable)
}