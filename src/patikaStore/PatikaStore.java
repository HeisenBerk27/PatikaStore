package patikaStore;

import java.util.*;

public class PatikaStore {
    private List<Brand> brands;
    private List<Product> products;
    private int nextProductId;

    public PatikaStore() {
        brands = new ArrayList<>();
        products = new ArrayList<>();
        nextProductId = 1;
        initializeBrands();
    }

    private void initializeBrands() {
        
        String[] brandNames = {"Samsung", "Lenovo", "Apple", "Huawei", "Casper", "Asus", "HP", "Xiaomi", "Monster"};
        Arrays.sort(brandNames);
        for (int i = 0; i < brandNames.length; i++) {
            Brand brand = new Brand(i + 1, brandNames[i]);
            brands.add(brand);
        }
    }
    
    public int getNextProductId() {
        return nextProductId++;
    }
    
    public Brand getBrandByName(String brandName) {
        for (Brand brand : brands) {
            if (brand.getName().equalsIgnoreCase(brandName)) {
                return brand;
            }
        }
        return null; // Marka bulunamazsa null döndürülecek
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void listProducts(String category) {
        // Kullanıcının seçtiği kategoriye göre ürünleri listeleme
        for (Product product : products) {
            if (category == null || category.isEmpty()) { // Tüm Ürünler için kategori null olarak gönderilecek
                displayProduct(product);
            } else if (product instanceof Phone && category.equalsIgnoreCase("Cep Telefonu")) {
                displayProduct(product);
            } else if (product instanceof Notebook && category.equalsIgnoreCase("Notebook")) {
                displayProduct(product);
            }
        }
    }


    private void displayProduct(Product product) {
        
        String format = "| %-5s | %-15s | %-15s | %-15s | %-20s | %-10s |%n";
        System.out.format(format, "ID", "Ürün Adı", "Marka", "Birim Fiyatı", "Stok Miktarı", "İndirim Oranı");
        System.out.format(format, product.getId(), product.getName(), product.getBrand().getName(),
                product.getPrice(), product.getStock(), product.getDiscount());
    }

    public void removeProduct(int productId) {
        products.removeIf(product -> product.getId() == productId);
    }

    public void filterByBrand(String brandName) {
        
        for (Product product : products) {
            if (product.getBrand().getName().equalsIgnoreCase(brandName)) {
                displayProduct(product);
            }
        }
    }
}
