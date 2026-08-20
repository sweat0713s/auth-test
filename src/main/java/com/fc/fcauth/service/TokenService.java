package com.fc.fcauth.service;

import com.fc.fcauth.model.App;
import com.fc.fcauth.dto.AppTokenRespDto;
import com.fc.fcauth.repository.AppRepository;
import com.fc.fcauth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

  public final AppRepository appRepository;

  public AppTokenRespDto createAppToken(Long appId) {
    App app = appRepository.getById(appId);
    String token = JwtUtil.createAppToken(app);
    return  AppTokenRespDto.builder()
        .token(token)
        .build();
  }
}
