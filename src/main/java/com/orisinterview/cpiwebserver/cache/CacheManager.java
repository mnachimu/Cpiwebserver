package com.orisinterview.cpiwebserver.cache;

import com.orisinterview.cpiwebserver.Utility;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CacheManager implements ICacheManager {

    private static CacheManager instance;
    private static Object monitor = new Object();
    private Map<String, String> cache = Collections.synchronizedMap(new HashMap<String, String>());
    private Map<String, Long> cacheAccessTtl = Collections.synchronizedMap(new HashMap<String, Long>());

    private CacheManager() {

    }
    @Override
    public String get(String key) {
        if (cacheAccessTtl.containsKey(key) && cacheAccessTtl.get(key) > Utility.getCurrentTimeInMillis()) {
            cacheAccessTtl.put(key, Utility.generateOneDayExpiry());
            return this.getQuiet(key);
        }
        return null;
    }

    @Override
    public boolean containsQuiet(String key) {
        if (cacheAccessTtl.containsKey(key) && cacheAccessTtl.get(key) > Utility.getCurrentTimeInMillis()) {
            return cache.containsKey(key);
        }
        return false;
    }

    @Override
    public String getQuiet(String key) {
        if (cacheAccessTtl.containsKey(key) && cacheAccessTtl.get(key) > Utility.getCurrentTimeInMillis()) {
            System.out.println("Getting from Cache value...." + key);
            return cache.get(key);
        }
        return null;
    }

    @Override
    public void put(String k, String v) {
        System.out.println("Putting key " + k + " with value " + v);
        cacheAccessTtl.put(k, Utility.generateOneDayExpiry());
        cache.put(k, v);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public void delete(String k) {
        cacheAccessTtl.put(k, Utility.getCurrentTimeInMillis());
        cache.put(k, null);
    }

    public static CacheManager getInstance() {
        if (instance == null) {
            synchronized (monitor) {
                if (instance == null) {
                    instance = new CacheManager();
                }
            }
        }
        return instance;
    }

}
