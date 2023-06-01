package bit.edu.onelibrary.util;

import bit.edu.onelibrary.user.dto.UserAuthenticationDto;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class AuthenticationStorage {

    private static final ThreadLocal<UserAuthenticationDto> storage = new ThreadLocal<>();

    public static void saveAuthentication(UserAuthenticationDto userAuthenticationDto) {
        storage.set(userAuthenticationDto);
    }

    public static UserAuthenticationDto getAuthentication() {
        return storage.get();
    }

    public static void removeAuthentication() {
        storage.remove();
    }
}
