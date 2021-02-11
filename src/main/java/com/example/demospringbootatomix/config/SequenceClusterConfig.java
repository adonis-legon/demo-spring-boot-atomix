package com.example.demospringbootatomix.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Configurable
@ConfigurationProperties(prefix = "app.cluster")
@Data
public class SequenceClusterConfig {
    ServerDetails node;
    List<ServerDetails> members;
    String storagePath;
}