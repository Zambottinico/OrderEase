package bar.back.controllers;

import bar.back.dtos.DetailDto;
import bar.back.dtos.LoungeDto;
import bar.back.dtos.OrderDetailDto;
import bar.back.dtos.PutLoungeDto;
import bar.back.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PutMapping("order/AddDetail/")
    public ResponseEntity<Boolean> AddDetail(@RequestBody DetailDto dto) throws JsonProcessingException {
        return  ResponseEntity.ok(orderService.AddDetail(dto));
    }

    @GetMapping("order/getDetails/{idLounge}/{idTable}")
    public ResponseEntity<List<OrderDetailDto>> getDetails(@PathVariable Long idLounge,@PathVariable Long idTable){
        return ResponseEntity.ok(orderService.GetDetails(idLounge,idTable));
    }
}
