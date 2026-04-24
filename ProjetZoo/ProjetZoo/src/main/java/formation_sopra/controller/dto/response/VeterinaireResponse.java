package formation_sopra.controller.dto.response;

public class VeterinaireResponse {
    private Integer id;
    private String login;
    private String password;

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

    public static VeterinaireResponse convert(formation_sopra.model.Veterinaire veterinaire){
        VeterinaireResponse veterinaireResponse = new VeterinaireResponse();

        veterinaireResponse.setId(veterinaire.getId());
        veterinaireResponse.setLogin(veterinaire.getLogin());
        veterinaireResponse.setPassword(veterinaire.getPassword());

        return veterinaireResponse;
    }

}
