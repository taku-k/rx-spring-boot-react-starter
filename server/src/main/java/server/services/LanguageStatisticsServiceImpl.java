package server.services;

import io.reactivex.Flowable;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import server.domain.CommittedFile;
import server.domain.Language;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class LanguageStatisticsServiceImpl implements LanguageStatisticsService {
    private static final Logger LOG = LoggerFactory.getLogger(LanguageStatisticsServiceImpl.class);

    private class LanguageConstructor extends Constructor {
        @Override
        @SuppressWarnings("unchecked")
        protected Object constructObject(Node node) {
            if (node.getTag() == Tag.MAP) {
                LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) super
                        .constructObject(node);
                if (Language.isValid(map)) {
                    return Language.createFromMap(map);
                }
            }
            return super.constructObject(node);
        }
    }

    private Map<String, Language> languages;

    private Map<String, Language> extensionLanguage;

    private Map<String, Language> filenameLanguage;

    @SuppressWarnings("unchecked")
    public LanguageStatisticsServiceImpl() {
        // Load languages.yml and construct Language instances
        Yaml yaml = new Yaml(new LanguageConstructor());
        languages = (Map<String, Language>) yaml.load(
                getClass().getClassLoader().getResourceAsStream("languages.yml"));
        languages.entrySet().forEach(stringLanguageEntry ->
                stringLanguageEntry.getValue().setName(stringLanguageEntry.getKey())
        );

        // Generate hash map which key is extension or filename and value is Language instance
        extensionLanguage = new HashMap<>();
        filenameLanguage = new HashMap<>();
        languages.values().forEach(language -> {
            Optional.ofNullable(language.getExtensions())
                    .ifPresent(extensions ->
                            extensions.forEach(ext ->
                                    extensionLanguage.put(ext, language)
                            )
                    );
            Optional.ofNullable(language.getFilenames())
                    .ifPresent(filenames ->
                            filenames.forEach(filename ->
                                    filenameLanguage.put(filename, language)
                            )
                    );
        });
    }

    @Override
    public Language getLanguageByName(String name) {
        return languages.get(name);
    }

    @Override
    public Map<Language, Double> calcLangStats(Flowable<CommittedFile> files) {
        Map<Language, Double> stats = new HashMap<>();
        Map<Language, List<CommittedFile>> filesByLang = new HashMap<>();
        AtomicLong sum = new AtomicLong(0);
        files.blockingIterable().forEach(file -> {
            String ext = FilenameUtils.getExtension(file.getFilename());
            if (extensionLanguage.containsKey(ext)) {
                Language lang = extensionLanguage.get(ext);
                filesByLang.putIfAbsent(lang, new ArrayList<>());
                filesByLang.get(lang).add(file);
                sum.addAndGet(file.getChanges());
            } else {
                LOG.info("Extension is not found: " + ext);
            }
        });
        for (Map.Entry<Language, List<CommittedFile>> e : filesByLang.entrySet()) {
            long sumLang = e.getValue().stream().mapToLong(CommittedFile::getChanges).sum();
            double rate = (double)sumLang / (double)sum.get() * 100.0;
            stats.put(e.getKey(), rate);
        }
        return stats;
    }

}
