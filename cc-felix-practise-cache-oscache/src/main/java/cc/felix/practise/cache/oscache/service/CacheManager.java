package cc.felix.practise.cache.oscache.service;

import cc.felix.practise.cache.oscache.model.User;

public class CacheManager {
	private boolean update = false;
	private BaseCache userCache;
	private static CacheManager instance;
	private static Object lock = new Object();

	private CacheManager() {
		// 这个根据配置文件来，初始BaseCache而已;
		userCache = new BaseCache("user", 120);
	}

	public static CacheManager getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new CacheManager();
				}
			}
		}
		return instance;
	}

	public void putUser(User user) {
		userCache.put(user.getId() + "", user);
	}

	public void removeUser(String id) {
		userCache.remove(id);
	}

	public void removeAllUser() {
		userCache.removeAll();
	}

	public User getUser(int id) {
		try {
			// 从Cache中获得
			return (User) userCache.get(id + "");
		} catch (Exception e) {
			// Cache中没有则从DB库获取
			System.out.println(String.format("[%s]从db中获取", id + ""));
			User user = new User(id);
			// 把获取的对象再次存入Cache中
			this.putUser(user);
			update = true;
			return user;
		} finally {
			if (!update) {
				// 如果Cache中的内容更新出现异常，则终止该方法
				userCache.cancel(id + ""); // 取消对id的更新
			}
		}
	}
}
