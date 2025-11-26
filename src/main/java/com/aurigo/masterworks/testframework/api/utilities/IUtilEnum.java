package com.aurigo.masterworks.testframework.api.utilities;

public interface IUtilEnum<E extends Enum<E>> {
    long getId(String enumDescription);

    long getId();

    E getEnum();
}
