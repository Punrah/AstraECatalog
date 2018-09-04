package com.dealerhondabali.astraecatalog.fragment.navigation;

import com.dealerhondabali.astraecatalog.persistence.Kredit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author msahakyan
 */

public interface NavigationManager {

    void showFragmentHome(String title);


    void showFragmentMotor(String title);

    void showFragmentKredit(String firstActionMovie, ArrayList<Kredit> listKredit);

    void showFragmentPrice(String firstActionMovie, ArrayList<String> listPrice);

    void showFragmentShow(String title);

    void showFragmentSimulasi(String firstActionMovie, ArrayList<String> listPrice);
}
