/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.DomeinController;
import domein.Graad;
import domein.Lid;
import java.util.List;
import java.util.stream.Collectors;
import persistentie.ExportFiles;

/**
 *
 * @author Nante
 */
public class testExport {
    public static void main(String[] args) {
        DomeinController dc = new DomeinController();
        
        Lid lid1 = new Lid("Nante", "Vermeulen", Graad.GROEN, "0472112233", "nante.vermeulen@student.hogent.be", "Straat", 9300, "Landeghem");
        Lid lid0 = new Lid("Indy", "Van Canegem", Graad.BRUIN, "0032472112233", "indy.vancanegem@student.hogent.be", "Straat", 9240, "Zele");
        Lid lid2 = new Lid("Jef", "Malfliet", Graad.ORANJE, "0234567890", "jef.malfliet@student.hogent.be", "Straat", 9220, "Hamme");
        Lid lid3 = new Lid("Mout", "Pessemier", Graad.WIT, "0234567890", "mout.pessemier@student.hogent.be", "Straat", 9320, "Erembodegen");

        dc.voegLidToe(lid0);
        dc.voegLidToe(lid1);
        dc.voegLidToe(lid2);
        dc.voegLidToe(lid3);
        
         
        List<String> lijstleden = dc.getLeden().stream().map(Lid::toString).collect(Collectors.toList());
        ExportFiles.toExcel(lijstleden, 40, 20, "bestandtest");
    }
   
}
