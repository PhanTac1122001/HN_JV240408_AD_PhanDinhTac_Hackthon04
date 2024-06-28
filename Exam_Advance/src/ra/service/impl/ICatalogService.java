package ra.service.impl;

import ra.model.Catalog;
import ra.service.CatalogService;

import java.util.ArrayList;
import java.util.List;

public class ICatalogService implements CatalogService {
    public static List<Catalog> catalogList=new ArrayList<>();
    @Override
    public List<Catalog> getAll() {
        return catalogList;
    }

    @Override
    public void save(Catalog catalog) {
        if (findById(catalog.getCatalogId()) == null) {
            catalogList.add(catalog);
        } else {
            catalogList.set(catalogList.indexOf(findById(catalog.getCatalogId())), catalog);
        }
    }

    @Override
    public Catalog findById(Integer integer) {
        for (Catalog c : catalogList) {
            if (c.getCatalogId()==integer) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer integer) {
        if (findById(integer) != null) {
            catalogList.remove(findById(integer));
        } else {
            System.err.println("Không có sản phẩm này");
        }
    }
}
