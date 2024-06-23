package br.dev.hygino.exceptions;

public class OutOfBalanceException extends RuntimeException {
    public OutOfBalanceException(String msg) {
        super(msg);
    }
}
