package com.lcore.core.utils;

import com.lcore.core.entity.User;

public class Env {
	public  User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static Env getEnv() {
		return env;
	}

	public static void setEnv(Env env) {
		Env.env = env;
	}

	private static Env env = null;

	private Env() {

	}

	public static Env instance() {
		if (env == null) {
			env = new Env();
		}

		return env;
	}

}
