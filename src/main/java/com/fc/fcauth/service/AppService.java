package com.fc.fcauth.service;

import com.fc.fcauth.model.App;
import com.fc.fcauth.model.Department;
import com.fc.fcauth.repository.AppRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppService {

  public final AppRepository appRepository;

  public List<App> listApps() {
    return appRepository.findAll();
  }

}
