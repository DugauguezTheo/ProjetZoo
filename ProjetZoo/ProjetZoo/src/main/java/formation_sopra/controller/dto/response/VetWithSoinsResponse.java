package formation_sopra.controller.dto.response;

import java.util.List;

import formation_sopra.model.Soin;

public class VetWithSoinsResponse extends VeterinaireResponse {

    private List<Soin> soins;

    public VetWithSoinsResponse() {
    }

    public VetWithSoinsResponse(List<Soin> soins) {
        this.soins = soins;
    }

    public List<Soin> getSoins() {
        return soins;
    }

    public void setSoins(List<Soin> soins) {
        this.soins = soins;
    }

    public static VetWithSoinsResponse convert(formation_sopra.model.Veterinaire veterinaire){
        VetWithSoinsResponse vetWithSoinsResponse = new VetWithSoinsResponse();

        vetWithSoinsResponse.setId(veterinaire.getId());
        vetWithSoinsResponse.setLogin(veterinaire.getLogin());
        vetWithSoinsResponse.setPassword(veterinaire.getPassword());

        List<Soin> soins = veterinaire.getSoins();
        vetWithSoinsResponse.setSoins(soins);

        return vetWithSoinsResponse;
    }

}
