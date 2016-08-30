package com.jsonDispose;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.users.User_JSON;

public class JSONdispose {
	public JSONdispose() {
		// TODO Auto-generated constructor stub
	}

	public User_JSON JsonRing_up(String content) {
		User_JSON ring_json = new User_JSON();
		JSONObject jsonObject = JSON.parseObject(content);
		ring_json = JSON.toJavaObject(jsonObject, User_JSON.class);
		return ring_json;
	}
}
