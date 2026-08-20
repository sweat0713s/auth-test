package com.fc.fcauth.controller;

import com.fc.fcauth.model.KakaoUserInfoRespDto;
import com.fc.fcauth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class KakaoLoginController {
  
  private final KakaoService kakaoService;
  
  @GetMapping("/kakao/callback")
  public ResponseEntity callback(@RequestParam("code") String code) {
    String token = kakaoService.getAccessTokenFromKakao(code);
    KakaoUserInfoRespDto dto = kakaoService.getUserFromKakao(token);
    log.info("nickname : {}", dto.getKakaoAccount().getProfile().getNickname());
    return new ResponseEntity(HttpStatus.OK);
  }

}
