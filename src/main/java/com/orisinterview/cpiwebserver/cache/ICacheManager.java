package com.orisinterview.cpiwebserver.cache;

public interface ICacheManager {
    // wrapper for being able to easily update redis or other type of caches
    String get(String key);
    boolean containsQuiet(String key);
    String getQuiet(String key);
    void put(String k, String v);
    void clear();
    void delete(String k);
}
