package io.github.zsgdyf.User.Action;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.github.zsgdyf.User.User;

public class AuthenticationService {
	public static String getToken(User user) {
		String token = "";
		token = JWT.create().withAudience(user.getId().toString())
				.sign(Algorithm.HMAC256(user.getPassword()));
		return token;
	}
}
