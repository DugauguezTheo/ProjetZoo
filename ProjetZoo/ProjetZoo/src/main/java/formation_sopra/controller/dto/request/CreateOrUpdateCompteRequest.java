package formation_sopra.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public class CreateOrUpdateCompteRequest {

    @NotBlank
    protected String login;

    @NotBlank
    protected String password;
    
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
    
