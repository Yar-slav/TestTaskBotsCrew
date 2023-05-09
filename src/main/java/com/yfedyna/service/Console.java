package com.yfedyna.service;

public interface Console {

    void handleWhoCommand(String command);

    void handleShowCommand(String command);

    void handleGlobalSearch(String command);
}
