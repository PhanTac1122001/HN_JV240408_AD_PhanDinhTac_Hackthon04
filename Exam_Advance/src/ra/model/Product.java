package ra.model;

import ra.service.impl.ICatalogService;
import ra.service.impl.IProductService;

import java.util.Scanner;

public class Product implements IOData{
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private Catalog catalog;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, double productPrice, String description, int stock, Catalog catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.stock = stock;
        this.catalog = catalog;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @Override
    public void inputData(Scanner scanner) {
        this.productId=inputProductId();

        this.productName=inputProductName(scanner );

        this.productPrice=inputProductPrice(scanner);

        this.description=inputDescription(scanner);

        this.stock=inputStock(scanner);

        this.catalog=inputCatalog(scanner);

        this.status=inputStatus();
    }

    private int inputStock(Scanner scanner) {
        System.out.println("Mời nhập số lượng sản phẩm:");
        do {
            String productStock=scanner.nextLine();
            if(productStock.trim().isEmpty()){
                System.err.println("Số lượng không được để trống, vui lòng nhập lại");
            }else {
                if(Double.parseDouble(productStock)<10){
                    System.err.println("Số lượng phải ít nhất là 10, vui lòng nhập lại");
                }
                else {
                    return Integer.parseInt(productStock);
                }

            }
        }while (true);

    }

    private boolean inputStatus() {
        boolean status=true;
        return status;
    }

    private Catalog inputCatalog(Scanner scanner) {
        for (Catalog c : ICatalogService.catalogList) {

            System.out.printf("[ ID: %d | Name: %10s ]\n", c.getCatalogId(), c.getCatalogName());
        }

        System.out.println("Nhập vào ID danh mục muốn thêm: ");
        do {
            int catalogIdChoice = Integer.parseInt(scanner.nextLine());
            int indexCatalog = findCatalogIndexById(catalogIdChoice);
            if (indexCatalog >= 0) {
                return ICatalogService.catalogList.get(indexCatalog);
            } else {
                System.err.println("không tìm thấy danh mục, vui lòng nhập lại id");
            }
        } while (true);
    }
    public int findCatalogIndexById(int catalogId) {
        for (int i = 0; i < ICatalogService.catalogList.size(); i++) {
            if (ICatalogService.catalogList.get(i).getCatalogId() == catalogId) {
                return i;
            }
        }
        return -1;
    }



    private String inputDescription(Scanner scanner) {
        System.out.println("Mời nhập mô tả sản phẩm");
        do {
            String descriptions=scanner.nextLine();
            if (descriptions.trim().isEmpty()){
                System.err.println("Không được để trống, vui lòng nhập lại");
            }else {
                return descriptions;
            }
        }while (true);
    }

    private double inputProductPrice(Scanner scanner) {
        System.out.println("Mời nhập giá sản phẩm:");
        do {
            String productPrice=scanner.nextLine();
            if(productPrice.trim().isEmpty()){
                System.err.println("Giá không được để trống, vui lòng nhập lại");
            }else {
                if(Double.parseDouble(productPrice)<0){
                    System.err.println("Giá phải lớn hơn 0, vui lòng nhập lại");
                }
                else {
                    return Double.parseDouble(productPrice);
                }

            }
        }while (true);
    }

    private String inputProductName(Scanner scanner) {
        System.out.println("Mời nhập tên sản phẩm:");
        do {
            String productName=scanner.nextLine();
            if(productName.trim().isEmpty()){
                System.err.println("Tên sản phẩm không được để trống, vui lòng nhập lại");
            }else{
                    return productName;
                }

        }while (true);
    }

    private String inputProductId() {
        String result="P";
        int maxId=0;
        for (Product product: IProductService.productList){
            String prooducts = product.getProductId();
            int numberId=Integer.parseInt(prooducts.replaceAll("P","0"));
            if (maxId < numberId){
                maxId=numberId;
            }
        }
        result+=String.format("%04d",maxId+1);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", catalog=" + catalog.getCatalogName() +
                ", status=" + status +
                '}';
    }


}
