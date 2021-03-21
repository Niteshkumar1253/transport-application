package com.capstone.favouriteService.service;

import com.capstone.favouriteService.model.Routes;

public interface RouteService {

    Routes addFavourite(Routes routes);
    Routes getRoutesById(String email);
    Routes updateFavourite(Routes routes);
    void deleteFavourite(String email,String id);
}
