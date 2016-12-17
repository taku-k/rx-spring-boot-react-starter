package server.domain;

import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Language {
    private String type;
    private String color;
    private List<String> extensions;
    private String ace_mode;
    private int language_id;

    public static Language createFromMap(Map<String, Object> map) {
        return Language.builder()
                .type((String) map.get("type"))
                .color((String) map.get("color"))
                .extensions((List<String>) map.get("extensions"))
                .ace_mode((String) map.get("ace_mode"))
                .language_id((Integer) map.get("language_id"))
                .build();
    }

    public static boolean checkLanguage(Map<String, Object> map) {
        return map.containsKey("type");
    }
}
