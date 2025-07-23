package dev.ayub.student_management_system.model.dto.Response.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLoginResponseDTO {
    private Long memberId;
    private String name;
    private String email;
    private Long familyId;
    private String familyName;
    private Integer familyMemberCount;
    private String token;
    private String refreshToken;
    private List<String> roles;
    private long expiresIn;
}
