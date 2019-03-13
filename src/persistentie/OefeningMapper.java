/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import java.security.SecureRandom;

/**
 *
 * @author IndyV
 */
public class OefeningMapper {
    
    private final String[] benamingen = {
       "Age uke","Soto uke","Uchi uke","Gedan barai","Shuto uke","Morote uke",
        "Kakiwake uke", "Otosohi uke","Juji uke","nagashi uke","hasami uke",
        "haishu uke", "sukui uke","ude uke","kake uke","oi tsuki","gyaku tsuki",
        "kizame tsuki","Nukite","choku tsuki","age tsukie", "kagi stuki","mawashi tsuki",
        "heiko tushi","ura tsuki","shuto uchi","uraken uchi","tettsui uchi"
    };
    
    private final String[] fotos={
    };
    
    private final String[] youtube={};
    
    
    private int randomBenaming(){
        SecureRandom random = new SecureRandom();
        return random.nextInt(benamingen.length);
    }
    
    
    
}
