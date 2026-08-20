package com.fc.fcauth.controller;

import com.fc.fcauth.dto.AppTokenRespDto;
import com.fc.fcauth.dto.ValidateTokenDto;
import com.fc.fcauth.service.TokenService;
import com.fc.fcauth.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/token")
@Tag(name = "App2App Token", description = "app2app token API")
public class AppTokenController {

  private final TokenService tokenService;

  @Operation(description = "토큰 발급")
  @PostMapping(value = "/new/{appId}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AppTokenRespDto> createNewAppToken(@PathVariable Long appId){
    AppTokenRespDto dto = tokenService.createAppToken(appId);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @Operation(description = "토큰 밸리데이션")
  @PostMapping(value = "/validate",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> validateAppToken(@RequestBody ValidateTokenDto dto) {
    return tokenService.validateToken(dto);
  }
}
