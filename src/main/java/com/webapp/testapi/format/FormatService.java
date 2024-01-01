package com.webapp.testapi.format;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FormatService {

    private final FormatRepository formatRepository;

    public Format findById(Long id) {
        return formatRepository.findById(id).orElseThrow(() ->
                new RuntimeException(String.format("Format with id %d not found.", id)));
    }

}
