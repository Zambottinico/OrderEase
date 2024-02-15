package bar.back.controllers;

import bar.back.dtos.LoungeDto;
import bar.back.dtos.OccupyTableDto;
import bar.back.dtos.PutLoungeDto;
import bar.back.services.DiningTableService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DiningTableController {
    @Autowired
    private DiningTableService diningTableService;

    @PutMapping("diningTable/Edit/")
    public ResponseEntity<Boolean> occupyTable(@RequestBody OccupyTableDto dto) throws JsonProcessingException {
        return ResponseEntity.ok(diningTableService.occupyTable(dto));
    }
}
