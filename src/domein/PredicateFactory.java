/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.time.LocalDate;
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
                Predicate aanwezig = genPredAanwezig(parameters);
                full = full.and(aanwezig);
                break;
            case ACTIVITEIT:
                Predicate activiteit = genPredActiviteit(parameters);
                full = full.and(activiteit);
                break;
            case CLUBKAMPIOENSCHAP:
                Predicate kampioenschap = genPredKampioenschap(parameters);
                full = full.and(kampioenschap);
                break;
            case INSCHRIJVING:
                Predicate inschrijving = genPredInschrijving(parameters);
                full = full.and(inschrijving);
                break;
            case LESMATERIAAL:
                Predicate lesmateriaal = genPredLesmateriaal(parameters);
                full = full.and(lesmateriaal);
                break;
            case LID:
                Predicate lid = genPredLid(parameters);
                full = full.and(lid);
                break;
        }
        return full;
    }

//    private static <T> Predicate<T> genPred(T obj, List<String> parameters) {
//        Predicate<T> full = a -> true;
//        for (String parameter : parameters) {
//            if (!isLeeg(parameter)) {
//                if (!parameter.equalsIgnoreCase("alles")) {
//                    Predicate<T> param = a -> a.equals("");
//                    full = full.and(param);
//                }
//            }
//        }
//        return full;
//    }
    private static Predicate genPredLid(List<String> parameters) {
        Predicate<Lid> full = l -> true;
        if (!isNull(parameters)) {
            String voornaamFilter = parameters.get(0);
            String familienaamFilter = parameters.get(1);
            String graadFilter = parameters.get(2);
            String typeFilter = parameters.get(3);

            Predicate<Lid> voornaam = lid -> lid.getVoornaam().toLowerCase().startsWith(voornaamFilter.toLowerCase());
            Predicate<Lid> familienaam = lid -> lid.getFamilienaam().toLowerCase().startsWith(familienaamFilter.toLowerCase());
            Predicate<Lid> graad = lid -> lid.getGraad().toString().toLowerCase().startsWith(graadFilter.toLowerCase());
            Predicate<Lid> lidType = lid -> lid.getType().toString().toLowerCase().startsWith(typeFilter.toLowerCase());

            if (!isNullOrEmpty(voornaamFilter)) {
                full = full.and(voornaam);
            }

            if (!isNullOrEmpty(familienaamFilter)) {
                full = full.and(familienaam);
            }

            if (!isNullOrEmpty(graadFilter)) {
                if (!graadFilter.equalsIgnoreCase("alles")) {
                    full = full.and(graad);
                }
            }

            if (!isNullOrEmpty(typeFilter)) {
                if (!typeFilter.equalsIgnoreCase("alles")) {
                    full = full.and(lidType);
                }
            }
        }
        return full;
    }

    private static Predicate genPredLesmateriaal(List<String> parameters) {
        Predicate<Oefening> full = o -> true;

        if (!isNull(parameters)) {
            String graad = parameters.get(0);
            String naam = parameters.get(1);

            Predicate<Oefening> onGraad = o -> o.getGraad().toString().equalsIgnoreCase(graad);
            Predicate<Oefening> onONaam = oefening -> oefening.getNaam().toLowerCase().startsWith(naam.toLowerCase());

            if (!isNullOrEmpty(graad)) {
                if (!graad.equalsIgnoreCase("alles")) {
                    full = full.and(onGraad);
                }
            }

            if (!isNullOrEmpty(naam)) {
                full = full.and(onONaam);
            }

        }
        return full;
    }

    private static Predicate genPredInschrijving(List<String> parameters) {
        Predicate<Lid> full = l -> true;

        if (!isNull(parameters)) {
            String inschrijvingdatum = parameters.get(0);
            String voornaam = parameters.get(1);
            String formule = parameters.get(2);

            Predicate<Lid> onInschrijving = lid -> lid.getInschrijvingsdatum().equals(LocalDate.parse(inschrijvingdatum));
            Predicate<Lid> onName = lid -> lid.getVoornaam().toLowerCase().startsWith(voornaam.toLowerCase());
            Predicate<Lid> onFormule = lid -> lid.getLessen().toString().equals(formule);

            if (!isNullOrEmpty(inschrijvingdatum)) {
                full = full.and(onInschrijving);
            }

            if (!isNullOrEmpty(voornaam)) {
                full = full.and(onName);
            }

            if (!isNullOrEmpty(formule)) {
                if (!formule.equalsIgnoreCase("alles")) {
                    full = full.and(onFormule);
                }
            }
        }

        return full;
    }

    private static Predicate genPredKampioenschap(List<String> parameters) {
        Predicate<Kampioenschap> full = l -> true;

        if (!isNull(parameters)) {
            String naam = parameters.get(0);
            String leeftijdcategorie = parameters.get(1);
            String datum = parameters.get(2);
            Predicate<Kampioenschap> onName = kamp -> kamp.getName().toLowerCase().startsWith(naam.toLowerCase());
            Predicate<Kampioenschap> onLeeftijd = kamp -> kamp.getLeeftijdCategorie().toString().equalsIgnoreCase(leeftijdcategorie);
            Predicate<Kampioenschap> onDate = kamp -> kamp.getDate().equals(LocalDate.parse(datum));

            if (!isNullOrEmpty(naam)) {
                full = full.and(onName);
            }

            if (!isNullOrEmpty(leeftijdcategorie)) {
                if (!leeftijdcategorie.equalsIgnoreCase("alles")) {
                    full = full.and(onLeeftijd);
                }
            }

            if (!isNullOrEmpty(datum)) {
                full = full.and(onDate);
            }
        }
        return full;
    }

    private static Predicate genPredActiviteit(List<String> parameters) {
        Predicate<Activiteit> full = l -> true;

        if (!isNull(parameters)) {
            String aNaam = parameters.get(1);
            String aType = parameters.get(0);
            String aStart = parameters.get(2);
            String aEind = parameters.get(3);
            Predicate<Activiteit> onType = a -> a.getActiviteitType().toString().equalsIgnoreCase(aType);
            Predicate<Activiteit> onAName = a -> a.getNaam().toLowerCase().startsWith(aNaam.toLowerCase());
            Predicate<Activiteit> onStart = a -> a.getBeginDatum().equals(LocalDate.parse(aStart));
            Predicate<Activiteit> onEind = a -> a.getEindDatum().equals(LocalDate.parse(aEind));

            if (!isNullOrEmpty(aType)) {
                if (!aType.equalsIgnoreCase("alles")) {
                    full = full.and(onType);
                }
            }
            if (!isNullOrEmpty(aNaam)) {
                full = full.and(onAName);
            }

            if (!isNullOrEmpty(aStart)) {
                full = full.and(onStart);
            }

            if (!isNullOrEmpty(aEind)) {
                full = full.and(onEind);
            }
        }
        return full;
    }

    private static Predicate genPredAanwezig(List<String> parameters) {
        Predicate<Lid> full = l -> true;

        if (!isNull(parameters)) {
            String aanwezigheidDatum = parameters.get(0);
            String lidVoornaam = parameters.get(1);
            String lidFormule = parameters.get(2);

            Predicate<Lid> onAanwezigheid = lid -> lid.getAanwezigheden().contains(LocalDate.parse(aanwezigheidDatum));
            Predicate<Lid> onNameA = lid -> lid.getVoornaam().toLowerCase().startsWith(lidVoornaam.toLowerCase());
            Predicate<Lid> onFormuleA = lid -> lid.getLessen().toString().equalsIgnoreCase(lidFormule);

            if (!isNullOrEmpty(aanwezigheidDatum)) {
                full = full.and(onAanwezigheid);
            }

            if (!isNullOrEmpty(lidVoornaam)) {
                full = full.and(onNameA);
            }

            if (!isNullOrEmpty(lidFormule)) {
                if (!lidFormule.equalsIgnoreCase("alles")) {
                    full = full.and(onFormuleA);
                }
            }
        }
        return full;
    }
}
