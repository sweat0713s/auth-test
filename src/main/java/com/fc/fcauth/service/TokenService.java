package com.fc.fcauth.service;

import com.fc.fcauth.dto.ValidateTokenDto;
import com.fc.fcauth.model.Api;
import com.fc.fcauth.model.App;
import com.fc.fcauth.dto.AppTokenRespDto;
import com.fc.fcauth.repository.ApiRepository;
import com.fc.fcauth.repository.AppRepository;
import com.fc.fcauth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

  private final AppRepository appRepository;
  private final ApiRepository apiRepository;

  public AppTokenRespDto createAppToken(Long appId) {
    App app = appRepository.getById(appId);
    String token = JwtUtil.createAppToken(app);
    return  AppTokenRespDto.builder()
        .token(token)
        .build();
  }

  public ResponseEntity<String> validateToken(ValidateTokenDto dto) {
    Api api = apiRepository.findByMethodAndPath(dto.getMethod(), dto.getPath());
    return JwtUtil.validateAppToken(dto, api);
  }
}
