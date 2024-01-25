package br.com.danilochaves.catalogproject.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class RoleDTO {
    private Long id;
    private String authority;
}
