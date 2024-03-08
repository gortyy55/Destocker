/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tn.CodeCommanders.Service;

import java.util.List;

/**
 *
 * @author karim
 */
public interface IServiceUser<T> {
    public void ajouter(T t);
    public void supprimer (T t);
    public void modifier (T t);
    public List<T> getAll();
    public  T getOneByID(int id);


}
