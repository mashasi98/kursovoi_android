package Interfaces;

import java.util.List;

import BaseClases.CaffeClass;

public interface ICaffeLoadLissener {
    void onCaffeLoadSuccess(List<CaffeClass> caffeClassList);
    void onCaffeLoadfaild(String message);

}
