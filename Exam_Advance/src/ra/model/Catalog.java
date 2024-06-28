package ra.model;

import ra.service.impl.ICatalogService;

import java.util.Scanner;

public class Catalog implements IOData {
    private int catalogId;
    private String catalogName;
    private String descriptions;

    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName, String descriptions) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    @Override
    public void inputData(Scanner scanner) {
    this.catalogId=inputCatalogId();
    this.catalogName=inputCatalogName(scanner);
    this.descriptions=inputDescriptions(scanner);
    }
    public void updateData(Scanner scanner) {

        this.catalogName=inputCatalogName(scanner);
        this.descriptions=inputDescriptions(scanner);
    }
    private int inputCatalogId() {
        int maxId = 0;
        for (Catalog catalog : ICatalogService.catalogList) {
            if (maxId < catalog.getCatalogId()) {
                maxId = catalog.getCatalogId();
            }
        }
        return maxId + 1;
    }

    private String inputCatalogName(Scanner scanner) {
        System.out.println("Mời nhập tên danh mục:");
        do {
            String catalogName = scanner.nextLine();
            if (catalogName.trim().isEmpty()){
                System.err.println("Không được để trống, vui lòng nhập lại");
            }
            else {
                return catalogName;
            }
        }while (true);
    }

    private String inputDescriptions(Scanner scanner){
        System.out.println("Mời nhập mô tả về danh mục:");
        do {
            String descriptions=scanner.nextLine();
            if (descriptions.trim().isEmpty()){
                System.err.println("Không được để trống, vui lòng nhập lại");
            }else {
                return descriptions;
            }
        }while (true);
    }
    @Override
    public String toString() {
        return "Catalog{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                '}';
    }


}
