package com.example.docker.hello;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String getResult() {
        return "pong";
    }

}
