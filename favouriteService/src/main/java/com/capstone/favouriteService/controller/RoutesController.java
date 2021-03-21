package com.capstone.favouriteService.controller;

import com.capstone.favouriteService.model.Details;
import com.capstone.favouriteService.model.Routes;
import com.capstone.favouriteService.repository.RoutesRespository;
import com.capstone.favouriteService.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/favourite")
@Slf4j
public class RoutesController {

    private RouteService service;
    private RoutesRespository repository;

    @Autowired
    public RoutesController(RouteService service,RoutesRespository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> addFavourite(@RequestBody Routes routes){
        log.error(routes.getEmail());
        Optional<Routes> optional = repository.findById(routes.getEmail());
        if(optional.isEmpty()){
            service.addFavourite(routes);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{
            service.updateFavourite(routes);
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @GetMapping("/{email}")
    public Details[] getFavourite(@PathVariable String email){

        Routes temp = service.getRoutesById(email);
        return temp.getDetails();
    }

    @DeleteMapping("/del")
    public ResponseEntity<?> deleteFavourite(@RequestParam String email,@RequestParam String index){
//        http://localhost:9006/api/v1/favourite?email=sohan@gmail.com&index=2
        service.deleteFavourite(email,index);
        return new ResponseEntity<>("successfully deleted",HttpStatus.OK);
    }

    @Bean
    public WebMvcConfigurer configurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("OPTIOINS","GET", "POST","PUT", "DELETE");
            }
        };
    }

}
