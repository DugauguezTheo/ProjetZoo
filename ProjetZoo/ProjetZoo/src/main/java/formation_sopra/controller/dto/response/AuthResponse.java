package formation_sopra.controller.dto.response;

public record AuthResponse(boolean success, String token, String role, String login) {

}
