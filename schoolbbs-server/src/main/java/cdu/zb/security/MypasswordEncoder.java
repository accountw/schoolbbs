package cdu.zb.security;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author accountw
 * @date 2020-01-14 22:00
 * @description:
 */
public class MypasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(rawPassword.toString());
    }
}
