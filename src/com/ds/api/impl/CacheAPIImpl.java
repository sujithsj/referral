package com.ds.api.impl;

import com.ds.api.CacheAPI;
import org.infinispan.Cache;
import org.infinispan.manager.EmbeddedCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author adlakha.vaibhav
 */
@Service
public class CacheAPIImpl implements CacheAPI {

  @Autowired
  private EmbeddedCacheManager cacheManager;

  @Override
  public Object get(CacheConfig cacheConfig, String key) {
    return getCache(cacheConfig).get(key);
  }

  private Cache getCache(CacheConfig cacheConfig) {
    Cache cache = getCacheManager().getCache(cacheConfig.name());
    return cache;
  }

  @Override
  public void put(CacheConfig cacheConfig, String key, Object value) {
    getCache(cacheConfig).put(key, value);
  }

  @Override
  public void remove(CacheConfig cacheConfig, String key) {
    getCache(cacheConfig).remove(key);
  }

  @Override
  public void clear(CacheConfig cacheConfig) {
    getCache(cacheConfig).clear();
  }

  public void put(CacheConfig cacheConfig, String key, Object value, long maxLife) {
    getCache(cacheConfig).put(key, value, maxLife, TimeUnit.SECONDS);
  }

  /**
   * @return the cacheManager
   */
  public EmbeddedCacheManager getCacheManager() {
    return cacheManager;
  }

  /**
   * @param cacheManager the cacheManager to set
   */
  public void setCacheManager(EmbeddedCacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

}
