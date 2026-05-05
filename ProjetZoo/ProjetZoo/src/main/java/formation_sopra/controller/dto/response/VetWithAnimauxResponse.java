package formation_sopra.controller.dto.response;

import java.util.List;

public class VetWithAnimauxResponse extends VeterinaireResponse {

    private List<AnimalResponse> animaux;

    public VetWithAnimauxResponse() {
    }

    public VetWithAnimauxResponse(List<AnimalResponse> animaux) {
        this.animaux = animaux;
    }

    public List<AnimalResponse> getAnimaux() {
        return animaux;
    }

    public void setAnimaux(List<AnimalResponse> animaux) {
        this.animaux = animaux;
    }

    public static VetWithAnimauxResponse convert(formation_sopra.model.Veterinaire veterinaire){
        VetWithAnimauxResponse vetWithAnimauxResponse = new VetWithAnimauxResponse();

        vetWithAnimauxResponse.setId(veterinaire.getId());
        vetWithAnimauxResponse.setLogin(veterinaire.getLogin());
        vetWithAnimauxResponse.setPassword(veterinaire.getPassword());

        List<AnimalResponse> animaux = veterinaire.getAnimaux().stream()
                .map(AnimalResponse::convert)
                .toList();
        vetWithAnimauxResponse.setAnimaux(animaux);

        return vetWithAnimauxResponse;
    }

}
