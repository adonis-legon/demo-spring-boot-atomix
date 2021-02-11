package com.example.demospringbootatomix.config;

import lombok.Data;

@Data
public class ServerDetails {
    String name;
    String host;
    int port;
}