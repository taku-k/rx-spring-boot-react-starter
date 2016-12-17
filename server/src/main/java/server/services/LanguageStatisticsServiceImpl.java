package server.services;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import server.domain.Language;

import java.util.LinkedHashMap;
import java.util.Map;

public class LanguageStatisticsServiceImpl implements LanguageStatisticsService {
    private class LanguageConstructor extends Constructor {
        @Override
        protected Object constructObject(Node node) {
            if (node.getTag() == Tag.MAP) {
                LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) super
                        .constructObject(node);
                if (Language.checkLanguage(map)) {
                    Language lang = Language.createFromMap(map);
                    return lang;
                }
            }
            return super.constructObject(node);
        }
    }

    private Map<String, Language> languages;

    public LanguageStatisticsServiceImpl() {
        Constructor constructor = new LanguageConstructor();
        Yaml yaml = new Yaml(constructor);
//        languages = (Map<String, Language>) yaml.load(getClass().getResourceAsStream("languages.yml"));
        languages = (Map<String, Language>) yaml.load("Ruby:\n  type: programming\n  color: \"#701516\"\n  extensions:\n    - \".rb\"\n    - \".builder\"\n  ace_model: ruby\n  language_id: 326");
        System.out.println(languages);
    }

    @Override
    public Language getLanguageByName(String name) {
        return languages.get(name);
    }
}
