package ra.run;

import ra.model.Catalog;
import ra.service.CatalogService;
import ra.service.impl.ICatalogService;

import java.util.Scanner;

public class CatalogManagement {
    private static final CatalogService catalogService=new ICatalogService();

    public  static void menuCatalog(Scanner scanner) {

        boolean isExist;
        do {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━ Catalog-Management ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.printf("┃			%30s			   ┃\n", "1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
            System.out.printf("┃			%30s									   ┃\n", "2. Hiển thị thông tin tất cả các danh mục");
            System.out.printf("┃			%30s									       ┃\n", "3.\tSửa tên danh mục theo mã danh mục");
            System.out.printf("┃			%30s 		       ┃\n", "4.\tXóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm)");
            System.out.printf("┃			%30s	                                               ┃\n", "5.Quay lại");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Mời bạn chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());
            isExist = true;
            switch (choice) {
                case 1:
                    addCatalog(scanner);
                    break;
                case 2:
                    showAll();
                    break;
                case 3:
                   updateCatalog(scanner);
                    break;
                case 4:
                    deleteCatalog(scanner);
                    break;
                case 5:
                    isExist = false;
                    break;

                default:
                    System.err.println("Vui lòng chọn lại từ 1 ->5");

            }
        } while (isExist);
    }


    private static void deleteCatalog(Scanner scanner) {
        System.out.println("Danh mục bạn muốn xóa:");
        int deleteId=Integer.parseInt(scanner.nextLine());
        int indexDelete= catalogService.findById(deleteId).getCatalogId();
        if(indexDelete<0){
            System.err.println("Không tồn tại ID muốn xóa");
            return;
        }
        catalogService.delete(deleteId);
    }

    private static void updateCatalog(Scanner scanner) {
        System.out.println("Chọn danh mục bạn muốn update theo ID:");
        int updateId=Integer.parseInt(scanner.nextLine());
        int indexUpdate= catalogService.findById(updateId).getCatalogId();
        if(indexUpdate<0){
            System.err.println("Không tồn tại ID muốn cập nhật");
            return;
        }
        Catalog catalogUpdate=ICatalogService.catalogList.get(indexUpdate);
        boolean isLoop=true;
        do {
                    System.out.println("Tên danh mục muốn sửa:");
            catalogUpdate.setCatalogName(scanner.nextLine());


            catalogService.save(catalogUpdate);
        }while (isLoop);
    }

    private static void addCatalog(Scanner scanner) {
        System.out.println("Nhập vào số lượng muốn thêm danh mục: ");
        int numbers=Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numbers; i++) {
            Catalog catalog=new Catalog();
            catalog.inputData(scanner);
            catalogService.save(catalog);
        }
    }

    private static void showAll() {
        if(ICatalogService.catalogList.isEmpty()){
            System.err.println("Chưa có danh mục nào, Vui lòng thêm danh mục");
            return;
        }
        for (Catalog catalog: ICatalogService.catalogList){
            System.out.println(catalog.toString());
        }
    }
}
