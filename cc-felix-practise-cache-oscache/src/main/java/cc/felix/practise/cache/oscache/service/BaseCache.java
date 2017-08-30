package cc.felix.practise.cache.oscache.service;

import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class BaseCache extends GeneralCacheAdministrator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6295406658818841467L;

	private int refreshPeriod; // 过期时间(单位为秒)
	private String keyPrefix; // 关键字前缀字符

	public BaseCache(String keyPrefix, int refreshPeriod) {
		super();
		this.keyPrefix = keyPrefix;
		this.refreshPeriod = refreshPeriod;
	}

	/**
	 * 添加被缓存的对象
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value) {
		this.putInCache(this.keyPrefix + "_" + key, value);
	}

	/**
	 * 删除被缓存的对象
	 * 
	 * @param key
	 */
	public void remove(String key) {
		this.removeEntry(this.keyPrefix + "_" + key);
	}

	/**
	 * 删除所有被缓存的对象
	 */
	public void removeAll() {
		this.flushAll();
	}

	/**
	 * 获取被缓存的对象
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public Object get(String key) throws NeedsRefreshException {
		return this.getFromCache(this.keyPrefix + "_" + key, this.refreshPeriod);
	}

	public void cancel(String key) {
		this.cancelUpdate(key);
	}

	public void put(String key, Object value, String[] groups) {
		this.putInCache(this.keyPrefix + "_" + key, value, groups);
	}

	/**
	 * 删除该组的缓存对象
	 * 
	 * @param group
	 */
	public void removeObjectByGroup(String group) {
		this.flushGroup(group);
	}
}
