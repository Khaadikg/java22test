package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wrapper<T> {
    private T data;
}
