package com.muyumi.RTKDataStructures.dataloaders;

import java.util.ArrayList;

public interface IDataLoader<T> {
    ArrayList<T> readDataFromFile();

}
