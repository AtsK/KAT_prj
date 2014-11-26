package ee.ut.math.tvt.salessystem.domain.controller;

import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.Client;
import ee.ut.math.tvt.salessystem.domain.data.Sale;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Sales domain controller is responsible for the domain specific business
 * processes.
 */
public interface SalesDomainController {

    /**
     * Load the current state of the warehouse.
     *
     * @return List of ${link
     *         ee.ut.math.tvt.salessystem.domain.data.StockItem}s.
     */
    public List<StockItem> getAllStockItems();


    public List<Client> getAllClients();

    public List<Sale> getAllSales();

    public Client getClient(long id);

    public void createStockItem(StockItem stockItem);

    /**
     * Rollback business transaction - purchase of goods.
     *
     */
    public void cancelCurrentPurchase();

    /**
     * Commit business transaction - purchase of goods.
     *
     */
    public void registerSale(Sale sale);

    public void setModel(SalesSystemModel model);

    /**
     * Close all resources
     */
    public void endSession();
}
