package com.dealerhondabali.astraecatalog.fragment.navigation;

import java.util.ArrayList;

/**
 * @author msahakyan
 */

public interface InnerNavigationManager {

    void showFragmentWarna(String title, ArrayList<String> listWarna);


    void showFragmentAksesoris(String title,String stringAksesoris);

    void showFragmentSpesifikasi(String title, ArrayList<String> listNamaSpek, ArrayList<String> listSpek);

    void showFragmentFitur(String title, ArrayList<String> listNamaFitur, ArrayList<String> listDeskripsiFitur, ArrayList<String> listGambarFitur);
}
