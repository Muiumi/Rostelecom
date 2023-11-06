package com.muyumi.rtkdatagroupsproject;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Timkov Anton
 */
public interface IService<T> {
    
    void loadDataInSelectedStructure(T object) throws SQLException;
    List<Person> loadDataFromLoader();
}
