package tn.CodeCommanders.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
public interface iservice<T> {
    void add (T t);
    ArrayList<T> getAll();
    void update(T t) throws SQLException;
    boolean delete(T t);
//getOne getById

}
