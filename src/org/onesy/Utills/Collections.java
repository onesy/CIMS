package org.onesy.Utills;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Collections {

	/**
	 * toString() 将会被调用
	 * @param src
	 * @return
	 */
	public static <T> byte[] calculateMD5(T src){
		String SRCStr = src.toString();
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		md5.update(SRCStr.getBytes());
		byte[] result = md5.digest();
		return result;
	}
	/**
	 * 将byte数组转化为BigInteger
	 * @param bytes
	 * @return
	 */
	public static BigInteger byteToBigInteger(byte[] bytes){
		return new BigInteger(bytes);
	}
	/**
	 * 以BigInteger的形式取得一个对象的MD5码的绝对值
	 * 对象<T> magic必须实现了toString()方法
	 * @param magic
	 * @return
	 */
	public static <T> BigInteger getMD5AbsBigInteger(T magic) {
		return byteToBigInteger(calculateMD5(magic)).abs();
	}
	/**
	 * 这个函数严密的和业务相关请慎用
	 * keyOrVal = true 为取得键，依照target的值对照值
	 * keyOrVal = false 为取得值，依照target的值对照键
	 * @param map
	 * @param keyOrVal
	 * @param value
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static ArrayList MapToArrayOrcdBool(Map map,boolean keyOrVal,boolean target){
		ArrayList rtn = new ArrayList();
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			if(keyOrVal || entry.getValue().equals(target)){
				rtn.add(entry.getKey());
			}
			if(!keyOrVal || entry.getKey().equals(target)){
				rtn.add(entry.getValue());
			}
		}
		return rtn;
	}
}
