package formation_sopra.controller.dto.response;

import formation_sopra.model.Article;

public class CompteResponse {

    protected Integer id;
    protected String login;
    protected String password;

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

    public static CompteResponse convert(Compte compte){
        CompteResponse compteResponse = new CompteResponse();

        compteResponse.setId(compte.getId());
        compteResponse.setLogin(compte.getLogin());
        compteResponse.setPassword(compte.getPassword());

        return compteResponse;
    }
}

