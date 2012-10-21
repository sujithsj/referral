package com.ds.api;

/**
 * @author adlakha.vaibhav
 */
public interface CacheAPI {

   public enum CacheConfig {
        COMPANY_CACHE, PRODUCT_CACHE, ISSUTRACKER_CACHE, TAG_CACHE, USER_CACHE,
        USER_SETTINGS_CACHE,PLAN_CACHE, COMPANY_CACHE_ID
    }

    /**
     * Puts Element into the Cache Identified by Cache with the given key
     *
     * @param cache in which value has to be added
     * @param key key to be used
     * @param value value to be associated
     */
    public void put(CacheConfig cache, String key, Object value);

    /**
     * Puts Element into the Cache Identified by Cache with the given key and specifies a maxLifeInseconds after which
     * cache entry will be evicted
     *
     * @param cache in which value has to be added
     * @param key key to be used
     * @param maxLifeInSeconds max life of cache element
     */
    public void put(CacheConfig cache, String key, Object valuel, long maxLifeInSeconds);

    /**
     * Reads from they cache entry specified by key
     *
     * @param cache
     * @param key
     * @return Value stored in cache for key, null if not found
     */
    public Object get(CacheConfig cache, String key);

    /**
     * Removes the Value from Cache , entry specified by key
     *
     * @param cache
     * @param key
     */
    public void remove(CacheConfig cache, String key);

    /**
     * Remove all the Values stored in Cache
     *
     * @param cache
     */
    public void clear(CacheConfig cache);
}
