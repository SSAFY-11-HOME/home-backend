package com.ssafy.homebackend.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ssafy.homebackend.exception.UnAuthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JWTUtil {

	@Value("${jwt.salt}")
	private String salt;

	@Value("${jwt.access-token.expiretime}")
	private long accessTokenExpireTime;

	@Value("${jwt.refresh-token.expiretime}")
	private long refreshTokenExpireTime;

	public String createAccessToken(String userId) {
		return create(userId, "access-token", accessTokenExpireTime);
	}

//	should be longer than AccessToken Expire Time
	public String createRefreshToken(String userId) {
		return create(userId, "refresh-token", refreshTokenExpireTime);
	}

//	Token 발급
//	JWT 구조 = HEADER + PAYLOAD + VERIFY SIGNATURE
// 	Claim : 토큰에서 사용할 정보(내용은 개발자 오마카세)
//	PAYLOAD = Registered Claim(이미 정해진 종류의 데이터) + 사용자가 정의한 데이터(Public Claim, Private Claim)

// 	Registered Claim
//	iss: 토큰 발급자(issuer)
//	sub: 토큰 제목(subject)
//	aud: 토큰 대상자(audience)
//	exp: 토큰 만료 시간(expiration), NumericDate 형식으로 되어 있어야 함 ex) 1480849147370
//	nbf: 토큰 활성 날짜(not before), 이 날이 지나기 전의 토큰은 활성화되지 않음
//	iat: 토큰 발급 시간(issued at), 토큰 발급 이후의 경과 시간을 알 수 있음
//	jti: JWT 토큰 식별자(JWT ID), 중복 방지를 위해 사용하며, 일회용 토큰(Access Token) 등에 사용
	
//	key : Claim에 셋팅될 key 값
//	value : Claim에 셋팅 될 data 값
//	subject : payload에 sub의 value로 들어갈 subject값
//	expire : 토큰 유효기간 설정을 위한 값

	private String create(String userId, String subject, long expireTime) {
//	Payload에 들어갈 Claim 설정
// 	Registered Claim
		Claims claims = Jwts.claims()
						.setSubject(subject) // sub : 토큰 제목 access-token 또는 refresh-token
						.setIssuedAt(new Date()) // ist : 생성일
						.setExpiration(new Date(System.currentTimeMillis() + expireTime)); // exp : 만료일(유효기간)

//	Public Claim 
		claims.put("userId", userId); // 저장할 data의 key, value

// jwt 생성
		String jwt = Jwts.builder()
				.setHeaderParam("typ", "JWT") // header 설정 : 토큰 타입
				.setClaims(claims) // payload 설정
				// header 설정 : 해쉬 알고리즘 
				// Signature 설정 : secret key를 활용한 암호화.
				.signWith(SignatureAlgorithm.HS256, this.generateKey()) 
				.compact(); // 직렬화해서 jwt 생성
		return jwt;
	}
	
	
//	Signature 설정에 들어갈 key 생성.
	private byte[] generateKey() {
		byte[] key = null;
		
		try {
			key = salt.getBytes("UTF-8"); // charset 설정 : 미설정 시 사용자 플랫폼의 기본 인코딩 설정으로 인코딩 됨.
		} catch (UnsupportedEncodingException e) {
			if (log.isInfoEnabled()) {
				e.printStackTrace();
			} else {
				log.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}
		return key;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//	요청 시 전달 받은 토큰이 제대로 생성된 것인지 확인 하고 문제가 있다면 UnauthorizedException 발생.
	public boolean checkToken(String token) {
		try {
//			Json Web Signature? 서버에서 인증을 근거로 인증 정보를 서버의 private key 서명 한것을 토큰화 한것
//			setSigningKey : JWS 서명 검증을 위한  secret key 세팅
//			parseClaimsJws : 파싱하여 원본 jws 만들기
			Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
//			Claims 는 Map 구현체 형태
			log.debug("claims: {}", claims);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}
	
	public String getUserId(String authorization) {
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(authorization);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new UnAuthorizedException();
		}
		Map<String, Object> value = claims.getBody();
		log.info("value : {}", value);
		return (String) value.get("userId");
	}
}
