package com.jihoho.myspring.springboot.config.auth.dto;

import com.jihoho.myspring.springboot.domain.user.Role;
import com.jihoho.myspring.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String,Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // OAuth2User에서 반환하는 사용자 정보는 Map 이기 때문에 값 하나하나를 변환해야만 함.
    public static OAuthAttributes of(String registartionId, String userNameAttributeName, Map<String ,Object> attributes){
        if("naver".equals(registartionId)){
            return ofNaver("id",attributes);
        }
        return ofGoogle(userNameAttributeName,attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String,Object> attributes){
        Map<String,Object> response=(Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("picture"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
   /*
        User 엔티티를 생성함
        OAuthAttributes에서 엔티티를 생성하는 시점은 처음
        가입할때의 기본권한을 GUEST로 주기 위해서 role빌더값에는 Role.GUSET 를 사용.
        OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성
    */
    public User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
