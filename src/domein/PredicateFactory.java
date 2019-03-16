/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;
import java.util.function.Predicate;
import static util.Validatie.*;

/**
 *
 * @author Mout
 */
public class PredicateFactory {

    public static <T> Predicate makePredicate(SorteerType type, List<String> parameters) {
        Predicate<T> full = a -> true;

        switch (type) {
            case AANWEZIGHEID:
                Predicate aanwezig = genPred(Lid.class, parameters);
                full = full.and(aanwezig);
                break;
            case ACTIVITIET:
                Predicate activiteit = genPred(Activiteit.class, parameters);
                full = full.and(activiteit);
                break;
            case CLUBKAMPIOENSCHAP:
                Predicate kampioenschap = genPred(Kampioenschap.class, parameters);
                full = full.and(kampioenschap);
                break;
            case INSCHRIJVING:
                Predicate inschrijving = genPred(Lid.class, parameters);
                full = full.and(inschrijving);
                break;
            case LESMATERIAAL:
                Predicate lesmateriaal = genPred(Oefening.class, parameters);
                full = full.and(lesmateriaal);
                break;
            case LID:
                Predicate lid = genPred(Lid.class, parameters);
                full = full.and(lid);
                break;
        }
        return full;
    }

    private static <T> Predicate<T> genPred(T obj, List<String> parameters) {
        Predicate<T> full = a -> true;
        for (String parameter : parameters) {
            if (!isLeeg(parameter)) {
                if (!parameter.equalsIgnoreCase("alles")) {
                    Predicate<T> param = a -> a.equals("");
                    full = full.and(param);
                }
            }
        }
        return full;
    }
}
