package com.edraky.fileservice.service;

public interface Crud<T> {
    T getById(Long Id);
    T save(T object);

}
