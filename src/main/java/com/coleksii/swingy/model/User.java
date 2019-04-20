package com.coleksii.swingy.model;

import com.coleksii.swingy.enums.State;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private State state;
    private Hero hero;
    private MapCell [][] map;
}
