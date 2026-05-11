package formation_sopra.controller.dto.response;

import java.util.List;

import formation_sopra.model.Soin;

public class VetWithSoinsResponse extends VeterinaireResponse {

    private List<SoinResponse> soins;

    public VetWithSoinsResponse() {
    }

    public VetWithSoinsResponse(List<SoinResponse> soins) {
        this.soins = soins;
    }

    public List<SoinResponse> getSoins() {
        return soins;
    }

    public void setSoins(List<SoinResponse> soins) {
        this.soins = soins;
    }

    public static VetWithSoinsResponse convert(formation_sopra.model.Veterinaire veterinaire){
        VetWithSoinsResponse vetWithSoinsResponse = new VetWithSoinsResponse();

        vetWithSoinsResponse.setId(veterinaire.getId());
        vetWithSoinsResponse.setLogin(veterinaire.getLogin());
        vetWithSoinsResponse.setPassword(veterinaire.getPassword());

        List<Soin> soins = veterinaire.getSoins();
        List<SoinResponse> soinsResponses = soins.stream().map(SoinResponse::convert).collect(java.util.stream.Collectors.toList());
        vetWithSoinsResponse.setSoins(soinsResponses);

        return vetWithSoinsResponse;
    }

}
