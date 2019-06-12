package org.california.controller;

import org.california.service.model.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("admin/mainProductsList")
    public boolean addMainProducts() {

//        String list = "Owoce, warzywa\n" +
//                "        Owoce\n" +
//                "        Warzywa\n" +
//                "        Sałatki\n" +
//                "        Zioła\n" +
//                "        Grzyby\n" +
//                "        Orzechy i ziarniste\n" +
//                "        Owoce suszone i bakalie na wagę\n" +
//                "Nabiał i jaja\n" +
//                "        Mleko\n" +
//                "        Jogurty\n" +
//                "        Śmietana\n" +
//                "        Desery mleczne\n" +
//                "        Ser\n" +
//                "        Masło i margaryna\n" +
//                "        Jaja i drożdże\n" +
//                "        Zdrowa żywność\n" +
//                "Pieczywo, cukiernia\n" +
//                "        Chleb\n" +
//                "        Chleb pakowany\n" +
//                "        Bułki\n" +
//                "        Słone przekąski\n" +
//                "        Słodkie wypieki\n" +
//                "        Ciasta i desery\n" +
//                "        Tosty i pieczywo do odpieku\n" +
//                "        Pieczywo bezglutenowe    \n" +
//                "Mięso, ryby, garmaż\n" +
//                "        Mięso i drób na wagę\n" +
//                "        Mięso i drób paczkowane\n" +
//                "        Wędliny\n" +
//                "        Ryby\n" +
//                "        Garmaż\n" +
//                "        Dania gotowe\n" +
//                "Art. spożywcze\n" +
//                "        Sól i cukier\n" +
//                "        Mąka\n" +
//                "        Makaron, ryż i kasza\n" +
//                "        Płatki śniadaniowe i musli\n" +
//                "        Olej, oliwa i ocet\n" +
//                "        Przyprawy\n" +
//                "        Sosy\n" +
//                "        Konserwy\n" +
//                "        Dania gotowe i zupy\n" +
//                "        Przetwory owocowe, miód\n" +
//                "        Produkty do pieczenia \n" +
//                "        Słodycze\n" +
//                "        Słone przekąski\n" +
//                "        Kuchnie świata\n" +
//                "        Zdrowa żywność\n" +
//                "Mrożonki\n" +
//                "        Mrożone warzywa\n" +
//                "        Mrożone owoce\n" +
//                "        Mrożone mięso i drób\n" +
//                "        Ryby i owoce morza\n" +
//                "        Mrożone pizza i frytki\n" +
//                "        Dania mrożone\n" +
//                "        Lody\n" +
//                "Napoje\n" +
//                "        Herbata\n" +
//                "        Kawa\n" +
//                "        Kakao, gorąca czekolada i cappucino\n" +
//                "        Woda\n" +
//                "        Soki świeże\n" +
//                "        Soki, nektary, napoje owocowe\n" +
//                "        Napoje gazowane\n" +
//                "        Ice Tea\n" +
//                "        Syropy\n" +
//                "        Napoje energetyczne i izotoniczne\n" +
//                "        Napoje bezalkoholowe\n" +
//                "Chemia\n" +
//                "        Proszki do prania, żele, kapsułki\n" +
//                "        Do płukania tkanin\n" +
//                "        Zmywanie\n" +
//                "        Sprzątanie\n" +
//                "        Akcesoria do sprzątania\n" +
//                "        Papiery toaletowe \n" +
//                "        Ręczniki papierowe\n" +
//                "        Folie\n" +
//                "        Worki na śmieci\n" +
//                "        Odświeżacze powietrza\n" +
//                "        Środki owadobójcze\n" +
//                "        Pielęgnacja obuwia\n" +
//                "Kosmetyki\n" +
//                "        Do twarzy\n" +
//                "        Do ciała\n" +
//                "        Pielęgnacja dłoni i stóp\n" +
//                "        Mydło, do kąpieli\n" +
//                "        Chusteczki, wata, akcesoria\n" +
//                "        Do włosów\n" +
//                "        Do zębów\n" +
//                "        Golenie\n" +
//                "        Higiena intymna\n" +
//                "        Dezodoranty\n" +
//                "        Wody\n" +
//                "        Półka zdrowia\n" +
//                "Dla dzieci\n" +
//                "        Żywność dla dzieci\n" +
//                "        Pieluchy i chusteczki\n" +
//                "        Pielęgnacja\n" +
//                "        Akcesoria dla dzieci\n" +
//                "        Akcesoria dla mam\n" +
//                "Dla zwierząt\n" +
//                "        Dla kota\n" +
//                "        Dla psa\n" +
//                "        Dla gryzoni\n" +
//                "        Dla rybek\n" +
//                "        Dla ptaków";
//
//        String[] listArray = list.split("\n");
//
//        Category parent = new Category();
//        Category child;
//
//        for (String row: listArray) {
//            System.out.println("Przerabiany row to: " + row);
//            if(row.startsWith("        ")) {
//                row = row.replace("        ", "");
//                child = new Category();
//                child.setName(row);
//                child.setParent(parent);
//                categoryService.(child);
//            } else {
//                parent = new Category();
//                parent.setName(row);
//                categoryService.create(parent);
//            }
//        }
//
//    return true;
        return true;

    }
}
