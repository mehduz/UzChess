package uzchess.core.rules;

import uzchess.constantes.*;
import uzchess.core.model.Case;
import uzchess.core.model.Echiquier;

public class VerificateurPion implements Deplacement {

//penser  dep début, prise diago
    @Override
    public boolean verifierDeplacement(Case dep, Case arr) {

        boolean verif = false;

        if (dep.getPiece().getCouleur() == Couleur.BLANC) {
            verif = verifierDeplacementPionBlanc(dep, arr);
        } else {
            verif = verifierDeplacementPionNoir(dep, arr);
        }

        return verif;
    }

    private boolean verifierDeplacementPionBlanc(Case dep, Case arr) {
        byte colDep = dep.getColonne();
        byte colArr = arr.getColonne();
        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();
        boolean verif = false;
        if (arr.getPiece() != null) {
            System.out.println("case occupée");

            if (ligArr == ligDep - 1) {
                if ((colArr == colDep + 1) || (colDep == colArr - 1)) {
                    verif = true;
                }
            }

        } else {
            Echiquier ech = Echiquier.getInstance();
            //System.out.println("case vide"); 
            if (colArr == colDep) {
                if (ligDep == 1) {
                    if (ligArr == ligDep - 1) {
                        verif = true;
                    } else {
                        verif = ech.verifCasesInter(ech.getCasesInter(dep, arr));
                    }

                } else {
                    verif = ligArr == ligDep - 1;
                }
            }

        }

        return verif;
    }

    private boolean verifierDeplacementPionNoir(Case dep, Case arr) {
        byte colDep = dep.getColonne();
        byte colArr = arr.getColonne();
        byte ligDep = dep.getLigne();
        byte ligArr = arr.getLigne();
        boolean verif = false;
        if (arr.getPiece() != null) {
            System.out.println("case occupée");

            if (ligArr == ligDep + 1) {
                if ((colArr == colDep + 1) || (colDep == colArr - 1)) {
                    verif = true;
                }
            }

        } else {
            Echiquier ech = Echiquier.getInstance();
            //System.out.println("case vide");
            if (colArr == colDep) {
                if (ligDep == 1) {
                    if (ligArr == ligDep + 1) {
                        verif = true;
                    } else {
                        verif = ech.verifCasesInter(ech.getCasesInter(dep, arr));
                    }
                } else {
                    verif = ech.verifCasesInter(ech.getCasesInter(dep, arr));
                }

            } else {
                verif = ligArr == ligDep + 1;
            }
        }
        return verif;
    }
}
