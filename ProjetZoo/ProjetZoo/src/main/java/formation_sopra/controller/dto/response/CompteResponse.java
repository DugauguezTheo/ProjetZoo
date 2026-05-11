package formation_sopra.controller.dto.response;

import formation_sopra.model.Compte;

public class CompteResponse {

    protected Integer id;
    protected String login;
    protected String password;
    protected String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static CompteResponse convert(Compte compte){
        CompteResponse compteResponse = new CompteResponse();

        compteResponse.setId(compte.getId());
        compteResponse.setLogin(compte.getLogin());
        compteResponse.setPassword(compte.getPassword());
        compteResponse.setRole(compte.getRole());
        return compteResponse;
    }
}

