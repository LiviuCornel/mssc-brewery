package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.exception.MyException;
import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("api/v1/beer")
@RestController
public class BeerController {
    
    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }


    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId)throws MyException{
        try{
            return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
        }catch (Exception e){
        e.printStackTrace();
        System.out.println(e.getMessage());
        throw new MyException("Error with beer get method!");
        }

    }


    @PostMapping("/save/{beerId}")
    public ResponseEntity<BeerDto> saveBeer(@RequestBody BeerDto beerDto)throws MyException {
        try{
            BeerDto savedDto = beerService.saveNewBeer(beerDto);
            System.out.println(savedDto.toString());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location","api/v1/beer/"+ savedDto.getId().toString());
            return new ResponseEntity<BeerDto>(savedDto, headers, HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new MyException("Error with beer save method!");
        }

    }

    @PutMapping("/update/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto )throws MyException{
        try{
            beerService.updateBeer(beerId, beerDto);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new MyException("Error with beer update method!");
        }
    }

    @DeleteMapping("/delete/{beerId}")
    public ResponseEntity deleteBeer(@PathVariable("beerId") UUID beerId)throws MyException{
        try{
            beerService.deleteBeer(beerId);
            return  new ResponseEntity(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new MyException("Error with beer delete method!");
        }
    }
}
