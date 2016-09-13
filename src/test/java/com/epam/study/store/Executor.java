package com.epam.study.store;

import com.epam.study.store.customer.RetailCustomerNotificationService;
import com.epam.study.store.customer.Topics;
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
    @Mock private Subscriber subscriber1;
    @Mock private Subscriber subscriber2;

    @Before
    public void setUpSenders() {
        Item item1 = new Item(Topics.SPORT, "M", 10, false, true);
        Item item2 = new Item(Topics.CLOTHES, "XL", 15, true, true);
        Item item3 = new Item(Topics.FOOD, "Large", 50, false, true);
        Item item4 = new Item(Topics.SPORT, "L", 3, false, false);
        store.addItem(item1);
        store.addItem(item2);
        store.addItem(item3);
        store.addItem(item4);
        new RetailCustomerNotificationService(store, retailCustomerSender);
        new WholesaleCustomerNotificationService(store, wholesaleCustomerSender);
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

    @Test
    public void mainSubscriverTest(){
        store.subscribe(subscriber1, Topics.BOOKS.toString() + "," + Topics.CLOTHES.toString());
        store.subscribe(subscriber2, Topics.SPORT.toString() + "," + Topics.CLOTHES.toString());
        Item item1 = new Item(Topics.SPORT, "M", 10, false, true);
        Item item2 = new Item(Topics.CLOTHES, "XL", 15, true, true);
        store.addItem(item1);
        store.addItem(item2);

        ArgumentCaptor<Item> captor1 = ArgumentCaptor.forClass(Item.class);
        Mockito.verify(subscriber1).notifySubscriber(captor1.capture());
        assertThat(captor1.getAllValues().size(), is(1));

        ArgumentCaptor<Item> captor2 = ArgumentCaptor.forClass(Item.class);
        Mockito.verify(subscriber2, Mockito.times(2)).notifySubscriber(captor2.capture());
        assertThat(captor2.getAllValues().size(), is(2));

    }



}
