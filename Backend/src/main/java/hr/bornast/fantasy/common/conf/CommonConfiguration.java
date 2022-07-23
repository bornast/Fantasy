package hr.bornast.fantasy.common.conf;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import hr.bornast.fantasy.application.dto.common.RecordNameDto;
import hr.bornast.fantasy.domain.model.User;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        var result = new ModelMapper();

        Converter<Date, String> toStringDate = new AbstractConverter<Date, String>() {
            @Override
            protected String convert(Date source) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.format(source);
            }
        };
        result.addConverter(toStringDate);

        Converter<OffsetDateTime, String> toStringOffsetDateTime = new AbstractConverter<OffsetDateTime, String>() {
            @Override
            protected String convert(OffsetDateTime source) {
                DateTimeFormatter datetimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                return datetimeFormat.format(source);
            }
        };
        result.addConverter(toStringOffsetDateTime);

        Converter<User, RecordNameDto> userRecordNameConverter = new AbstractConverter<User, RecordNameDto>() {
            @Override
            protected RecordNameDto convert(User source) {
                return new RecordNameDto(source.getId(), source.getUsername());
            }
        };
        result.addConverter(userRecordNameConverter);

        result.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return result;
    }



}
