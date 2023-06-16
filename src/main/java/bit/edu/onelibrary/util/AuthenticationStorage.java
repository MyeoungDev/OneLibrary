package bit.edu.onelibrary.util;

import bit.edu.onelibrary.user.dto.UserAuthenticationDto;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class AuthenticationStorage {

    //이 코드는 ThreadLocal을 사용하여, 각 스레드마다 독립적인 인스턴스를 가지도록 합니다. 
    //ThreadLocal은 스레드마다 독립적인 값을 저장하고, 접근할 수 있도록 하는 기능입니다. 
    //이를 통해, 각 스레드마다 독립적인 인증 정보를 저장할 수 있습니다.

    // ThreadLocal 객체를 생성합니다.
    private static final ThreadLocal<UserAuthenticationDto> storage = new ThreadLocal<>();

    // UserAuthenticationDto 객체를 저장하는 메소드입니다.
    public static void saveAuthentication(UserAuthenticationDto userAuthenticationDto) {
        // ThreadLocal 객체에 UserAuthenticationDto 객체를 저장합니다.
        storage.set(userAuthenticationDto);
    }

    // UserAuthenticationDto 객체를 반환하는 메소드입니다.
    public static UserAuthenticationDto getAuthentication() {
        // ThreadLocal 객체에서 UserAuthenticationDto 객체를 반환합니다.
        return storage.get();
    }

    // ThreadLocal 객체에서 UserAuthenticationDto 객체를 제거하는 메소드입니다.
    public static void removeAuthentication() {
        storage.remove();
    }
}