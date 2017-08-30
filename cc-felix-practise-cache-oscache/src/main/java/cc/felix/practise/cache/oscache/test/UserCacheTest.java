package cc.felix.practise.cache.oscache.test;

import cc.felix.practise.cache.oscache.model.User;
import cc.felix.practise.cache.oscache.service.CacheManager;

public class UserCacheTest {
	
	public static void main(String[] args) {
		CacheManager cm = CacheManager.getInstance();
		UserCacheTest test = new UserCacheTest();
		test.print(cm);
	}

	public void print(CacheManager cm) {
		User user = null;
		for (int i = 0; i < 20; i++) {
			user = cm.getUser(100);
			System.out.println("<<" + i + ">>: " + user);
			if (i == 5) {
				// 删除缓存id的对象
				cm.removeUser(100 + "");
			}
			if (i == 10) {
				// 删除所有缓存的对象
				cm.removeAllUser();
			}
			// 睡眠部分
			try {
				Thread.sleep(1000);
			} catch (Exception e) {

			}
		}
	}
}
