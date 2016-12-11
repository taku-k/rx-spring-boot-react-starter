package server.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleCommit {
    private List<CommittedFile> files;
}
