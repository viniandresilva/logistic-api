package logistic.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Getter
public class ArgumentNotValid {
    private Integer status;
    private LocalDateTime date;
    private List<InvalidField> fields;
}
