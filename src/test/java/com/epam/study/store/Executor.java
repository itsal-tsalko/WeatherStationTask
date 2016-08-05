package com.epam.study.store;

import com.epam.study.store.customer.RetailCustomerNotificationService;
import com.epam.study.store.customer.WholesaleCustomerNotificationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Iuliia Tsal-Tsalko
 */
@RunWith(MockitoJUnitRunner.class)
public class Executor {

    Store store = new Store();

    @Mock private AdvertisementSender retailCustomerSender;
    @Mock private AdvertisementSender wholesaleCustomerSender;

    @Before
    public void setUpSenders() {
        new RetailCustomerNotificationService(store, retailCustomerSender);
        new WholesaleCustomerNotificationService(store, wholesaleCustomerSender);
        Item item1 = new Item("dress", "M", 10, false, true);
        Item item2 = new Item("t-short", "XL", 15, true, true);
        Item item3 = new Item("cap", "S", 50, false, true);
        Item item4 = new Item("glass", "L", 3, false, false);
        store.addItem(item1);
        store.addItem(item2);
        store.addItem(item3);
    }

    @Test
    public void mainSRetailCustomerNotificationServiceTest(){
        store.sendAdvertisementsForAllCustomers();

        Advertisement actualRetailCustomersAdvertisement = captureCustomersAdvertisements(retailCustomerSender);
        assertThat(actualRetailCustomersAdvertisement.items.size(), is(2));

        Advertisement actualWholesaleCustomersAdvertisement = captureCustomersAdvertisements(wholesaleCustomerSender);
        assertThat(actualWholesaleCustomersAdvertisement.items.size(), is(1));
    }

    private Advertisement captureCustomersAdvertisements(AdvertisementSender sender) {
        ArgumentCaptor<Advertisement> captor = ArgumentCaptor.forClass(Advertisement.class);
        Mockito.verify(sender).send(captor.capture());
        return captor.getValue();
    }

}
