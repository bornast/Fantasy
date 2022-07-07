package hr.bornast.fantasy.common.conf;

import java.text.SimpleDateFormat;
import java.util.Date;

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

        result.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return result;
    }



}
