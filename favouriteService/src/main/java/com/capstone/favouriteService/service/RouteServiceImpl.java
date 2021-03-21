package com.capstone.favouriteService.service;

import com.capstone.favouriteService.model.Details;
import com.capstone.favouriteService.model.Routes;
import com.capstone.favouriteService.repository.RoutesRespository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RouteServiceImpl implements RouteService{
    private RoutesRespository routesRespository;

    @Autowired
    public RouteServiceImpl(RoutesRespository routesRespository) {
        this.routesRespository = routesRespository;
    }

    @Override
    public Routes addFavourite(Routes routes) {
        return routesRespository.save(routes);
    }

    @Override
    public Routes getRoutesById(String email) {
        Optional<Routes> routesOptional = routesRespository.findById(email);
        return routesOptional.get();
    }

    @Override
    public Routes updateFavourite(Routes routes) {

        Routes temp = routesRespository.findById(routes.getEmail()).get();
        List<Details> s = new ArrayList<>(Arrays.asList(temp.getDetails()));

        String source = routes.getDetails()[0].getSource();
        String destination = routes.getDetails()[0].getDestination();
        String srcLon = routes.getDetails()[0].getSrcLon();
        String srcLat = routes.getDetails()[0].getSrcLat();
        String destLon = routes.getDetails()[0].getDestLon();
        String destLat = routes.getDetails()[0].getDestLat();

        s.add(new Details(source,destination,srcLon,srcLat,destLon,destLat));
        temp.setDetails(s.toArray(new Details[s.size()]));

        return routesRespository.save(temp);
    }

    @Override
    public void deleteFavourite(String email, String index) {
        int i = Integer.parseInt(index);
        Routes routes = routesRespository.findById(email).get();

//        Details[] temp = routes.getDetails();
        List<Details> list = new ArrayList<>(Arrays.asList(routes.getDetails()));
        list.remove(i);
        routes.setDetails(list.toArray(new Details[list.size()]));
        routesRespository.save(routes);
    }
}
