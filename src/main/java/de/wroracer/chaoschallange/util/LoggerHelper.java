package de.wroracer.chaoschallange.util;

import de.wroracer.chaoschallange.ChaosChallange;

import java.util.logging.Logger;

public interface LoggerHelper {

    default Logger log(){
        return ChaosChallange.INSTANCE.getLogger();
    }
}
