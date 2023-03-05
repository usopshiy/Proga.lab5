package io;

import exceptions.InvalidDataException;

@FunctionalInterface
public interface Askable<T> {
    T ask() throws InvalidDataException;
}
