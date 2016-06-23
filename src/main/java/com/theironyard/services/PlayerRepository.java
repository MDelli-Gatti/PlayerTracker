package com.theironyard.services;

import com.theironyard.entities.Player;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by michaeldelli-gatti on 6/23/16.
 */
public interface PlayerRepository extends CrudRepository<Player, Integer> {
}
