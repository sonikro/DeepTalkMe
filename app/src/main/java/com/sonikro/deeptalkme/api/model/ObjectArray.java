package com.sonikro.deeptalkme.api.model;

/**
 * Created by Jonathan Nagayoshi on 2/07/2016.
 */
public class ObjectArray<Type> {
    private Class typeClass;

    public ObjectArray(Class typeClass)
    {
        this.typeClass = typeClass;
    }

    private Type[] objectArray;

    public Type[] getObjectArray() {
        return objectArray;
    }

    public void setObjectArray(Type[] objectArray) {
        this.objectArray = objectArray;
    }
}
