package br.com.danilochaves.catalogproject.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInsertDTO extends UserDTO {
    private String password;
}
