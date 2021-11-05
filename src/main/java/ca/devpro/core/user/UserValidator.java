package ca.devpro.core.user;

import ca.devpro.api.UserDto;
import ca.devpro.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UserValidator {

  static final String FIRST_NAME_REQUIRED = "FIRST_NAME_REQUIRED";
  static final String LAST_NAME_REQUIRED = "LAST_NAME_REQUIRED";
  static final String USERNAME_REQUIRED = "USERNAME_REQUIRED";
  static final String PASSWORD_REQUIRED = "PASSWORD_REQUIRED";
  static final String USERNAME_TAKEN = "USERNAME_TAKEN";

  private final UserRepository userRepository;

  @Autowired
  public UserValidator(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void validateAndThrow(UserDto dto) {
    Map<String, String> errors = validate(dto);
    if (!errors.isEmpty()) {
      throw new ValidationException(errors);
    }
  }

  public Map<String, String> validate(UserDto dto) {
    Map<String, String> errors = new LinkedHashMap<>();
    validateFirstName(errors, dto);
    validateLastName(errors, dto);
    validateUsername(errors, dto);
    validatePassword(errors, dto);
    return errors;
  }

  private void validateFirstName(Map<String, String> errors, UserDto dto) {
    if (StringUtils.isBlank(dto.getFirstName())) {
      errors.put("firstName", FIRST_NAME_REQUIRED);
    }
  }

  private void validateLastName(Map<String, String> errors, UserDto dto) {
    if (StringUtils.isBlank(dto.getLastName())) {
      errors.put("lastName", LAST_NAME_REQUIRED);
    }
  }

  private void validatePassword(Map<String, String> errors, UserDto dto) {
    if (StringUtils.isBlank(dto.getPassword())) {
      errors.put("password", PASSWORD_REQUIRED);
    }
  }

  private void validateUsername(Map<String, String> errors, UserDto dto) {
    if (StringUtils.isBlank(dto.getUsername())) {
      errors.put("username", USERNAME_REQUIRED);
    } else if (isCreate(dto) && userRepository.existsByUsernameIgnoreCase(dto.getUsername())) {
      errors.put("username", USERNAME_TAKEN);
    }
  }

  private boolean isCreate(UserDto dto) {
    return dto.getUserId() == null;
  }

}
