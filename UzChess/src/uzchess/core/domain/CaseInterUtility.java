package uzchess.core.domain;

import java.util.ArrayList;
import uzchess.constantes.Direction;

public class CaseInterUtility {

    private static Case[][] cases;

    private CaseInterUtility() {
    }

    public static void setCases(Case[][] cases) {
        CaseInterUtility.cases = cases;
    }

    public static ArrayList<Case> getCasesInter(Case caseDep, Case caseArr) {

        ArrayList<Case> maListInter = new ArrayList<>();
        Direction maDir;
        maDir = caseDep.getDirection(caseArr);
        switch (maDir) {

            case O:
                maListInter = getO(caseDep, caseArr);
                break;
            case NO:
                maListInter = getNO(caseDep, caseArr);
                break;
            case N:
                maListInter = getN(caseDep, caseArr);
                break;
            case NE:
                maListInter = getNE(caseDep, caseArr);
                break;
            case E:
                maListInter = getE(caseDep, caseArr);
                break;
            case SE:
                maListInter = getSE(caseDep, caseArr);
                break;
            case S:
                maListInter = getS(caseDep, caseArr);
                break;
            case SO:
                maListInter = getSO(caseDep, caseArr);
                break;
            default:
                break;
        }
        return maListInter;
    }

    private static ArrayList<Case> getO(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = (byte) (caseDep.getColonne() - 1);
        while (iCol != caseArr.getColonne()) {
            maListInter.add(cases[caseDep.getLigne()][iCol]);
            iCol--;
        }
        return maListInter;
    }

    private static ArrayList<Case> getNO(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = (byte) (caseDep.getColonne() - 1);
        byte iLig = (byte) (caseDep.getLigne() - 1);
        while (iCol != caseArr.getColonne() && iLig != caseArr.getLigne()) {
            maListInter.add(cases[iLig][iCol]);
            iLig--;
            iCol--;
        }
        return maListInter;
    }

    private static ArrayList<Case> getN(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iLig = (byte) (caseDep.getLigne() - 1);
        while (iLig != caseArr.getLigne()) {
            maListInter.add(cases[iLig][caseDep.getColonne()]);
            iLig--;
        }
        return maListInter;
    }

    private static ArrayList<Case> getNE(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = (byte) (caseDep.getColonne() + 1);
        byte iLig = (byte) (caseDep.getLigne() - 1);
        while (iCol != caseArr.getColonne() && iLig != caseArr.getLigne()) {
            maListInter.add(cases[iLig][iCol]);
            iLig--;
            iCol++;
        }
        return maListInter;
    }

    private static ArrayList<Case> getE(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = (byte) (caseDep.getColonne() + 1);
        while (iCol != caseArr.getColonne()) {
            maListInter.add(cases[caseDep.getLigne()][iCol]);
            iCol++;
        }
        return maListInter;
    }

    private static ArrayList<Case> getSE(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = (byte) (caseDep.getColonne() + 1);
        byte iLig = (byte) (caseDep.getLigne() + 1);
        while (iCol != caseArr.getColonne() && iLig != caseArr.getLigne()) {
            maListInter.add(cases[iLig][iCol]);
            iLig++;
            iCol++;
        }
        return maListInter;
    }

    private static ArrayList<Case> getSO(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iCol = (byte) (caseDep.getColonne() - 1);
        byte iLig = (byte) (caseDep.getLigne() + 1);
        while (iCol != caseArr.getColonne() && iLig != caseArr.getLigne()) {
            maListInter.add(cases[iLig][iCol]);
            iLig++;
            iCol--;
        }
        return maListInter;
    }

    private static ArrayList<Case> getS(Case caseDep, Case caseArr) {
        ArrayList<Case> maListInter = new ArrayList<>();
        byte iLig = (byte) (caseDep.getLigne() + 1);
        while (iLig != caseArr.getLigne()) {
            maListInter.add(cases[iLig][caseDep.getColonne()]);
            iLig++;
        }
        return maListInter;
    }
}
