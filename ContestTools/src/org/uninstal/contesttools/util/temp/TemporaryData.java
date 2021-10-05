package org.uninstal.contesttools.util.temp;

import java.util.HashMap;
import java.util.Map;

public class TemporaryData {

	private static Map<Object, Object> tempMap = new HashMap<>();
	
	public static void temp(Object key, Object value) {
		tempMap.put(key, value);
	}
	
	public static Object get(Object key) {
		return tempMap.get(key);
	}
	
	public static void remove(Object key) {
		tempMap.remove(key);
	}
	
	public static void clear() {
		tempMap.clear();
	}
}