package br.com.guimsmendes.dnasimians.entrypoint.controller;

import br.com.guimsmendes.dnasimians.entrypoint.mapper.StatsMapper;
import br.com.guimsmendes.dnasimians.entrypoint.model.request.SimianRequest;
import br.com.guimsmendes.dnasimians.entrypoint.model.response.StatsResponse;
import br.com.guimsmendes.dnasimians.usecase.DnaUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimianController {

    private final DnaUseCase dnaUseCase;
    private final StatsMapper statsMapper;

    @Autowired
    public SimianController(DnaUseCase dnaUseCase,StatsMapper statsMapper) {
        this.dnaUseCase = dnaUseCase;
        this.statsMapper = statsMapper;
    }

    @PostMapping("/simian")
    public ResponseEntity<Object> isSimian(@RequestBody SimianRequest request){
        if(!dnaUseCase.isSimian(request.asDnaDomain())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getStats() {
        return ResponseEntity.status(HttpStatus.OK).body(statsMapper.toStatsResponse(dnaUseCase.getStats()));
    }
}
