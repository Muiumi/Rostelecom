package com.muyumi.RTKDataStructures.dataloaders;

import java.io.FileNotFoundException;
import java.util.List;

public interface DataLoader<T> {
    List<T> readDataFromFile() throws FileNotFoundException;

}
