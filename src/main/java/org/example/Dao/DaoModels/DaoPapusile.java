package org.example.Dao.DaoModels;

import org.example.Dao.DaoBase;
import org.example.Models.Papusile;

public interface DaoPapusile extends DaoBase<Integer, Papusile> {
    void selectPapusiAsc();
}
