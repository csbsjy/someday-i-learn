package dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserLoginDto {
    private String userId;
    private String password;
    private String type;

    public UserLoginDto(String userId, String password, String type) {
        this.userId = userId;
        this.password = password;
        this.type = type;
    }
}
