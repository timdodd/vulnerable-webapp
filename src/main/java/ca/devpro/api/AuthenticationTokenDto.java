package ca.devpro.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AuthenticationTokenDto implements Serializable {
  private static final int serialVersionUID = 1;

  private String token;
}
