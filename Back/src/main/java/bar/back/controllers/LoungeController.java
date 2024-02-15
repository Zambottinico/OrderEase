package bar.back.controllers;


import bar.back.dtos.LoungeDto;
import bar.back.dtos.PutLoungeDto;
import bar.back.entities.DiningTable;
import bar.back.entities.Lounge;
import bar.back.services.LoungeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoungeController {
    @Autowired
    private LoungeService loungeService;
    @Autowired
    private ObjectMapper objectMapper;
    @PostMapping("lounge/Create")
    public ResponseEntity<Lounge> CreateLounge() throws JsonProcessingException {
        return  ResponseEntity.ok(loungeService.CreateLounge());
    }

    @GetMapping("/GetById/{id}")
    public LoungeDto GetLoungeById(@PathVariable Long id) throws JsonProcessingException {

        return loungeService.GetLoungeById(id);

    }
    @GetMapping("/GetLounges/")
    public ResponseEntity<List<LoungeDto>> GetLounges(){
        return ResponseEntity.ok(loungeService.getLounges());
    }
    @PutMapping("lounge/Edit/{id}")
    public ResponseEntity<LoungeDto> EditLounge(@RequestBody PutLoungeDto putLoungeDto, @PathVariable Long id) throws JsonProcessingException {
        return  ResponseEntity.ok(loungeService.EditLounge(id, putLoungeDto));
    }
}
