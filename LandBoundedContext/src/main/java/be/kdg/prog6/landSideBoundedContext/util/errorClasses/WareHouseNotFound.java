package be.kdg.prog6.landSideBoundedContext.util.errorClasses;

import org.antlr.v4.runtime.RuntimeMetaData;

public class WareHouseNotFound extends RuntimeException {
    public WareHouseNotFound(String message) {
        super(message);
    }
}
