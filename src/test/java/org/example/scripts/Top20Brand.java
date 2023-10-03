package org.example.scripts;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Top20Brand {
    private List<Brand> brandList;

    public Top20Brand(HashSet<String> brandList) {
        Pattern patternBrand = Pattern.compile("([\\w\\- А-я]+)\\s*(?:\\((\\d+)\\))?");
        List<Brand> brand = new ArrayList<>();

        for (String brandname : brandList) {
            Matcher matcherBrand = patternBrand.matcher(brandname);
            if (matcherBrand.find()) {
                String name = matcherBrand.group(1);
                String count = matcherBrand.group(2);
                count = count == null ? "1" : count;
                brand.add(new Brand(name, Integer.parseInt(count)));
            }
        }

        this.brandList = brand;
        this.brandList.sort((Brand brand1, Brand brand2) -> brand2.count - brand1.count);
    }

    public List<Brand> getTop20Brands() {
        return brandList.subList(0, 20);
    }

    public void createTable() {
        System.out.printf("| %-20s | %-21s |\n", "Фирма", "Количество объявлений");
        System.out.println("------------------------------------------------");
        for (Brand brand : getTop20Brands()) {
            System.out.printf("| %-20s | %-21s |\n", brand.name, brand.count);
        }
    }
}
