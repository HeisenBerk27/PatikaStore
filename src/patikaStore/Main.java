package patikaStore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PatikaStore patikaStore = new PatikaStore();

        
        Brand samsung = new Brand(1, "Samsung");
        Brand lenovo = new Brand(2, "Lenovo");
        Brand apple = new Brand(3, "Apple");

        
        Phone phone1 = new Phone(1, 2000, 0.1, 100, "Galaxy S21", samsung, "128 GB", 6.1, 4000, 6, "Siyah");
        Phone phone2 = new Phone(2, 1500, 0.05, 50, "iPhone 12", apple, "64 GB", 6.1, 3500, 4, "Mavi");
        Notebook notebook1 = new Notebook(3, 3500, 0.2, 30, "IdeaPad", lenovo, 8, "512 SSD", 14);
        Notebook notebook2 = new Notebook(4, 4500, 0.15, 20, "MacBook Pro", apple, 16, "1 TB SSD", 13);

        
        patikaStore.addProduct(phone1);
        patikaStore.addProduct(phone2);
        patikaStore.addProduct(notebook1);
        patikaStore.addProduct(notebook2);

        
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- PatikaStore ---");
            System.out.println("1. Ürünleri Listele");
            System.out.println("2. Cep Telefonları Listele");
            System.out.println("3. Notebookları Listele");
            System.out.println("4. Ürün Ekle");
            System.out.println("5. Ürün Sil");
            System.out.println("6. Markaya Göre Ürünleri Listele");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminizi yapın: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 

            switch (choice) {
                case 1:
                    System.out.println("\n--- Tüm Ürünler ---");
                    patikaStore.listProducts("Tüm Ürünler");
                    patikaStore.listProducts(null);
                    break;
                case 2:
                    System.out.println("\n--- Cep Telefonları ---");
                    patikaStore.listProducts("Cep Telefonu");
                    break;
                case 3:
                    System.out.println("\n--- Notebooklar ---");
                    patikaStore.listProducts("Notebook");
                    break;
                case 4:
                    System.out.print("\nÜrün Adı: ");
                    String name = scanner.nextLine();
                    System.out.print("Birim Fiyatı: ");
                    double price = scanner.nextDouble();
                    System.out.print("İndirim Oranı: ");
                    double discount = scanner.nextDouble();
                    System.out.print("Stok Miktarı: ");
                    int stock = scanner.nextInt();
                    scanner.nextLine(); // 
                    System.out.print("Marka: ");
                    String brandName = scanner.nextLine();
                    System.out.print("Kategori (Cep Telefonu/Notebook): ");
                    String category = scanner.nextLine();

                    Brand brand = patikaStore.getBrandByName(brandName);
                    if (brand == null) {
                        System.out.println("Geçersiz marka. Ürün eklenemedi.");
                    } else {
                        if (category.equalsIgnoreCase("Cep Telefonu")) {
                            System.out.print("Hafıza Bilgisi: ");
                            String memory = scanner.nextLine();
                            System.out.print("Ekran Boyutu: ");
                            double screenSize = scanner.nextDouble();
                            System.out.print("Pil Gücü: ");
                            int batteryPower = scanner.nextInt();
                            System.out.print("RAM: ");
                            int ram = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.print("Renk: ");
                            String color = scanner.nextLine();

                            Phone newPhone = new Phone(patikaStore.getNextProductId(), price, discount, stock,
                                    name, brand, memory, screenSize, batteryPower, ram, color);
                            patikaStore.addProduct(newPhone);
                        } else if (category.equalsIgnoreCase("Notebook")) {
                            System.out.print("RAM: ");
                            int ram = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.print("Depolama: ");
                            String storage = scanner.nextLine();
                            System.out.print("Ekran Boyutu: ");
                            double screenSize = scanner.nextDouble();

                            Notebook newNotebook = new Notebook(patikaStore.getNextProductId(), price, discount, stock,
                                    name, brand, ram, storage, screenSize);
                            patikaStore.addProduct(newNotebook);
                        } else {
                            System.out.println("Geçersiz kategori. Ürün eklenemedi.");
                        }
                    }
                    break;
                case 5:
                    System.out.print("Silmek istediğiniz ürünün ID'sini girin: ");
                    int productId = scanner.nextInt();
                    patikaStore.removeProduct(productId);
                    break;
                case 6:
                    System.out.print("Listelemek istediğiniz markanın adını girin: ");
                    String brandToFilter = scanner.nextLine();
                    patikaStore.filterByBrand(brandToFilter);
                    break;
                case 0:
                    running = false;
                    System.out.println("Çıkış yapıldı. İyi günler!");
                    break;
                default:
                    System.out.println("Geçersiz seçim. Tekrar deneyin.");
            }
        }

        scanner.close();
    }
}
