package br.com.guimsmendes.dnasimians.entrypoint.controller;

import br.com.guimsmendes.dnasimians.entrypoint.mapper.StatsMapper;
import br.com.guimsmendes.dnasimians.entrypoint.model.request.SimianRequest;
import br.com.guimsmendes.dnasimians.entrypoint.model.response.StatsResponse;
import br.com.guimsmendes.dnasimians.usecase.DnaUseCase;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimianController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimianController.class);

    private final DnaUseCase dnaUseCase;
    private final StatsMapper statsMapper;

    @Autowired
    public SimianController(DnaUseCase dnaUseCase, StatsMapper statsMapper) {
        this.dnaUseCase = dnaUseCase;
        this.statsMapper = statsMapper;
    }

    @PostMapping("/simian")
    public ResponseEntity<Object> isSimian(@RequestBody @Valid SimianRequest request) {
        if (!dnaUseCase.isSimian(request.asDnaDomain())) {
            LOGGER.info("Able to retrieve a Human DNA Type");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        LOGGER.info("Able to retrieve a Simian DNA Type");
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats() {
            return ResponseEntity.status(HttpStatus.OK).body(statsMapper.toStatsResponse(dnaUseCase.getStats()));
    }
}
