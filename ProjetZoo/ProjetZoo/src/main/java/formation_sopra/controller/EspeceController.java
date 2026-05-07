package formation_sopra.controller;

import formation_sopra.model.Espece;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/espece")
public class EspeceController {

    @GetMapping
    public List<String> getEspeces() {
        return Arrays.stream(Espece.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
