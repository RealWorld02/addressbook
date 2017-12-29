package com.shay.addressbook.serviceImpl;

import java.security.AlgorithmParameters;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shay.addressbook.DAO.UserDAO;
import com.shay.addressbook.entity.User;
import com.shay.addressbook.service.UserService;
import com.shay.addressbook.util.LoginUtil;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Override
	public String saveUser(String code, String encryptedData, String iv) {
		String session_key = null;
		JSONObject userInfo = null;
		String token = LoginUtil.getWebAccess(code);
		String rec = LoginUtil.httpGet(token);
		JSONObject json = JSON.parseObject(rec);
		if (json != null) {
			session_key = json.getString("session_key");
		}
		byte[] dataByte = Base64.decode(encryptedData);// 被加密的数据
		byte[] keyByte = Base64.decode(session_key);// 加密秘钥
		byte[] ivByte = Base64.decode(iv);// 偏移量
		try {
			int base = 16;
			if (keyByte.length % base != 0) {// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				userInfo = JSON.parseObject(result);
			}
			log.info("User information:"+userInfo);
			User user = new User();
			user.setOpenId(userInfo.getString("openId"));
			user.setNickName(userInfo.getString("nickName"));
			user.setGender(userInfo.getString("gender"));
			user.setLanguage(userInfo.getString("language"));
			user.setCity(userInfo.getString("city"));
			user.setProvince(userInfo.getString("province"));
			user.setCountry(userInfo.getString("country"));
			user.setAvatarUrl(userInfo.getString("avatarUrl"));
			if (userDAO.getUserInfo(user) == null){ //user不存在则保存，存在则更新。当用户修改头像时avatarUrl会改变。
				userDAO.saveUser(user);
			} else{
				userDAO.updateUser(user);
			}
			return userInfo.getString("openId");
		}
		catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public User getUserInfo(User user) {
		User userInfo = userDAO.getUserInfo(user);
		return userInfo;
	}

	@Override
	public String saveAddress(User user) {
		userDAO.saveAddress(user);
		return "success";
	}
}
