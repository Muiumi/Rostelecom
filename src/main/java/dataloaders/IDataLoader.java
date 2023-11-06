/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dataloaders;

import java.util.ArrayList;

/**
 * @param <Person>
 * @author Timkov Anton
 */
public interface IDataLoader<Person> {

    ArrayList<Person> loadData();

}
