package com.fc.fcauth.service;

import com.fc.fcauth.model.KakaoTokenRespDto;
import com.fc.fcauth.model.KakaoUserInfoRespDto;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Service
public class KakaoService {

  @Value("${kakao.client_id}")
  private String clientId;

  @Value("${kakao.redirect_uri}")
  private String redirectUri;

  @Value("${kakao.client_secret}")
  private String clientSecret;

  private final String KAKAO_AUTH_URL = "https://kauth.kakao.com";
  private final String KAKAO_USER_URL = "https://kapi.kakao.com";

  public KakaoUserInfoRespDto getUserFromKakao(String accessToken){
    return WebClient.create(KAKAO_USER_URL)
        .get()
        .uri(uriBuilder -> uriBuilder
            .scheme("https")
            .path("/v2/user/me")
            .build())
        .header(HttpHeaders.AUTHORIZATION,"Bearer " + accessToken)
        .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
        .retrieve()
        .bodyToMono(KakaoUserInfoRespDto.class)
        .block();
  }

  public String getAccessTokenFromKakao(String code) {
    log.info("clientId:{}",clientId);
    log.info("redirect_uri:{}",redirectUri);

    KakaoTokenRespDto kakaoTokenRespDto =
        WebClient.create(KAKAO_AUTH_URL)
            .post()
            .uri(uriBuilder -> uriBuilder
                .scheme("https")
                .path("/oauth/token")
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", clientId)
                .queryParam("redirect_uri", redirectUri)
                .queryParam("code", code)
                .queryParam("client_secret", clientSecret)
                .build())
            .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=UTF-8")
            .retrieve()
            .bodyToMono(KakaoTokenRespDto.class)
            .block();

    return kakaoTokenRespDto.getAccessToken();
  }
}
